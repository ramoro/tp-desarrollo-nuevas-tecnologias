package crearte

import java.time.LocalDate
import grails.gorm.transactions.Transactional

@Transactional
class PostulationService {

    def createPostulation(String roleName, String userId, String projectName, LocalDate date) {

        Role role = Role.findByName(roleName)
        assert role

        if (!role.hasAvailableSpots()) 
            throw new RuntimeException("Postulation has no available spots")

        role.occupiedSpots += 1
        role.save(flush: true, failOnError: true)

        User user = User.findByDni(userId)
        assert user

        Project project = Project.findByName(projectName)
        assert project    

        Postulation postulation = new Postulation(
                state: Postulation.PostulationState.PENDING,
                date: date,
                role: role,
                user: user,
                project: project
            ).save(failOnError: true)

        return postulation
    }
}
