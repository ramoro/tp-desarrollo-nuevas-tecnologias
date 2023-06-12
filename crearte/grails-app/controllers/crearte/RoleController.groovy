package crearte

class RoleController {

    def create() {
        String projectName = params.projectName

        [projectName: projectName] 
        render(view: '/role/create')
    }

}
