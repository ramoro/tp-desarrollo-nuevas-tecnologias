package crearte

import java.time.LocalDate

class PostulationController {

    PostulationService postulationService

    def create() {
        LocalDate date = LocalDate.now()
        Postulation postulation = postulationService.createPostulation(params.roleName, params.userId, params.projectName, date)
        User postulationProjectUser = User.findByDni(postulation.project.userId)
        
        //postulationProjectUser = postulation.user

        render(view: '/postulation/create', model: [postulation: postulation, postulationProjectUser: postulationProjectUser])
    }

}
