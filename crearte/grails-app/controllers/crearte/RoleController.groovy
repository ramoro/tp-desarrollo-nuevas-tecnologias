package crearte

class RoleController {

    RoleService roleService

    def create() {
        String projectName = params.projectName

        [projectName: projectName] 
        render(view: '/role/create')
    }

    def save() {
        if (params.hasLimitedSpots == null) {
            params.hasLimitedSpots = false
            params.totalSpots = 0
        }
        else params.hasLimitedSpots = true

        roleService.createRole(params.name, params.description, params.hasLimitedSpots, params.totalSpots.toInteger(), params.projectName)
        render(view: 'save', model: [role: params.name, hasLimitedSpots: params.hasLimitedSpots, totalSpots:params.totalSpots, projectName: params.projectName])
    }

}
