package crearte

import java.time.LocalDate

class PostulationController {

    PostulationService postulationService

    def create() {
        LocalDate date = LocalDate.now()
        Postulation postulation
        try {
            postulation = postulationService.createPostulation(params.roleName, params.userId, params.projectName, date)
        }
        catch (RuntimeException e) {
            render 'ya existe'
            return
        }
        User postulationProjectUser = User.findByDni(postulation.project.userId)

        //postulationProjectUser = postulation.user

        render(view: '/postulation/create', model: [postulation: postulation, postulationProjectUser: postulationProjectUser])
    }

}
