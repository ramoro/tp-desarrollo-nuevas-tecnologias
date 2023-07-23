package crearte

import java.time.LocalDate
import grails.gorm.transactions.Transactional

@Transactional
class PostulationService {

    def createPostulation(String roleName, int ownerDni, String projectName) {
        
        LocalDate date = LocalDate.now()
        Project project = Project.findByName(projectName)
        Role role = Role.findByName(roleName)
        User user = User.findByDni(ownerDni)
        
        Postulation postulation = project.createUserPostulationToRole(role, user, date)
        
        role.occupiedSpots += 1
        role.save(flush: true, failOnError: true)
        postulation.save(failOnError: true)
        project.save(failOnError: true)

        return postulation
    }


    def deletePostulationsWaitingForRole(Project project, Role role) {

        //Buscar postulaciones en estado de espera y borrarlas
        List<Postulation> postulationsWaiting = Postulation.findAllByProjectNameAndRoleNameAndState(project.name, role.name, Postulation.PostulationState.WAITING_LIST)
        List<Postulation> removedPostulations = project.deletePostulationsWaiting(postulationsWaiting)
        for (postulation in removedPostulations) {
            postulation.save(flush: true)
        }
        project.save(flush: true)
    }
}
