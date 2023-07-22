package crearte

import java.time.LocalDate

class PostulationController {

    PostulationService postulationService

    def create() {
        Postulation postulation
        try {
            postulation = postulationService.createPostulation(params.roleName, params.ownerDni as Integer, params.projectName)
        }
        catch (Role.RoleHasNoAvailableSpotsException e) {
            flash.error = e.message
            render e.message
            return
        }
        catch (Postulation.PostulationAlreadyExistsException e) {
            flash.error = e.message
            render e.message
            return
        }
        catch (Exception e) {
            flash.error = e.message
            render e.message
            return
        }
        
        Project project = Project.findByName(params.projectName)
        String postulationProjectUserName = User.findByDni(project.ownerDni).name
        String currentUserName = User.findByDni(params.ownerDni).name

        render(view: '/postulation/create', model: [postulation: postulation, currentUserName: currentUserName, postulationProjectUserName: postulationProjectUserName])
    }

}
