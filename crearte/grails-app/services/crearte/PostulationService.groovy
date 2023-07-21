package crearte

import java.time.LocalDate
import grails.gorm.transactions.Transactional

@Transactional
class PostulationService {

    def createPostulation(String roleName, int ownerDni, String projectName, LocalDate date) {

        Role role = Role.findByName(roleName)

        if (!role.hasAvailableSpots())
            throw new Role.RoleHasNoAvailableSpotsException()

        User user = User.findByDni(ownerDni)

        Project project = Project.findByName(projectName)

        Postulation postulation = new Postulation(
                state: Postulation.PostulationState.PENDING,
                date: date,
                roleName: roleName,
                ownerDni: ownerDni,
                projectName: projectName)

        def ps = Postulation.getAll()
        for (Postulation p: ps) {
            if (postulation.isEqual(p))
                throw new Postulation.PostulationAlreadyExistsException()
        }

        role.occupiedSpots += 1
        role.save(flush: true, failOnError: true)
        postulation.save(failOnError: true)

        return postulation
    }
}
