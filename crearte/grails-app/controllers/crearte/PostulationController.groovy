package crearte

import java.time.LocalDate

class PostulationController {

    PostulationService postulationService
    RoleService roleService

    def create() {
        Postulation postulation
        String currentUserName = User.findByDni(params.ownerDni).name

        try {
            postulation = postulationService.createPostulation(params.roleName, params.ownerDni as Integer, params.projectName)
        }
        catch (Role.RoleHasNoAvailableSpotsException e) {
            flash.error = "No hay mas cupos"
            render(view: '/postulation/rejected', model: [currentUserName: currentUserName, dni: params.ownerDni])
            return
        }
        catch (Postulation.PostulationAlreadyExistsException e) {
            flash.error = "Ya estás postulado"
            render(view: '/postulation/alreadyExists', model: [currentUserName: currentUserName, dni: params.ownerDni])
            return
        }
        catch (Exception e) {
            flash.error = e.message
            render e.message
            return
        }

        Project project = Project.findByName(params.projectName)
        String postulationProjectUserName = User.findByDni(project.ownerDni).name

        render(view: '/postulation/create', model: [postulation: postulation, currentUserName: currentUserName, postulationProjectUserName: postulationProjectUserName, dni: params.ownerDni])
    }

    def deleteWaitingListFromRole() {
        // deshabilitar waitList del Role
        Role role = Role.findByName(params.roleName)
        roleService.deleteWaitingListFromRole(role)
        Project project = Project.findByName(params.projectName)

        postulationService.deletePostulationOnWaitForRole(project, role)

        redirect(controller: 'project', action:'show', params: [name: params.projectName, dni: params.dni, project: project])
    }

}
