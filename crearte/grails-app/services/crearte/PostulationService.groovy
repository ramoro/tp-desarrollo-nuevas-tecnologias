package crearte

import java.time.LocalDate
import grails.gorm.transactions.Transactional

@Transactional
class PostulationService {

    def createPostulation(String roleName, String ownerDni, String projectName, LocalDate date) {

        Role role = Role.findByName(roleName)
        assert role

        if (!role.hasAvailableSpots())
            throw new RuntimeException("Postulation has no available spots")

        role.occupiedSpots += 1
        role.save(flush: true, failOnError: true)

        User user = User.findByDni(ownerDni)
        assert user

        Project project = Project.findByName(projectName)
        assert project

        Postulation postulation = new Postulation(
                state: Postulation.PostulationState.PENDING,
                date: date,
                role: role,
                user: user,
                project: project)

        def ps = Postulation.getAll()
        for (Postulation p: ps) {
            if (postulation.isEqual(p))
                throw new RuntimeException("PostulationService postulation exists already")
        }

        postulation.save(failOnError: true)

        return postulation
    }
}
