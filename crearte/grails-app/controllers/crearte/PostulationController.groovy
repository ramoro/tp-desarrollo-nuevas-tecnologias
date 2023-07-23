package crearte

import java.time.LocalDate

class PostulationController {

    PostulationService postulationService
    RoleService roleService

    def create() {
        Postulation postulation
        try {
            postulation = postulationService.createPostulation(params.roleName, params.ownerDni as Integer, params.projectName)
        }
        catch (Role.RoleHasNoAvailableSpotsException e) {
            flash.error = "La postulacion fue rechazada." + e.message
            render flash.error
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
        print(postulation.state)
        render(view: '/postulation/create', model: [postulation: postulation, currentUserName: currentUserName, postulationProjectUserName: postulationProjectUserName])
    }

    def deleteWaitingListFromRole() {
        // deshabilitar waitList del Role
        Role role = Role.findByName(params.roleName)
        roleService.deleteWaitingListFromRole(role)
        Project project = Project.findByName(params.projectName)
        /*
        // buscar la postulacion
        def postulation = Postulation.findWhere(projectName: params.projectName, ownerDni: params.dni as Integer)

        // borrarla de project.postulaciones
        Project project = Project.findByName(params.projectName)
        projectService.deletePostulationFromProject(project, postulation)
        */


        postulationService.deletePostulationsWaitingForRole(project, role)

        redirect(controller: 'project', action:'show', params: [name: params.projectName, dni: params.dni, project: project])
    }

}
