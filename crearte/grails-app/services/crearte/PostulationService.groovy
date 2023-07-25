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


    def deletePostulationsOnWaitForRole(Project project, Role role) {

        //Buscar postulaciones en estado de espera y borrarlas
        List<Postulation> postulationsOnWait = Postulation.findAllByProjectNameAndRoleAndState(project.name, role, Postulation.PostulationState.WAITING_LIST)
        for(postulation in postulationsOnWait) {
            User userPostulated = User.findByDni(postulation.ownerDni)
            Postulation postulationRejected = project.deletePostulationsOnWaitForUser(postulation, userPostulated)
            postulationRejected.save(flush: true)
        }
        project.save(flush: true)
    }
}
