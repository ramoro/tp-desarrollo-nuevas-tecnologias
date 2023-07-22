package crearte

class UserController {

    UserService userService

    def logUser() {
        if (params.isPremium == null) {
            params.isPremium = false
        }
        else params.isPremium = true

        User user = new User(
            name: params.name,
            lastName: params.lastName,
            dni: params.dni,
            description: params.description,
            isPremium: params.isPremium
        ).save(failOnError: true)

        redirect action: 'showProfile', params: [dni: user.dni, name:user.name,lastName:user.lastName,description: user.description]
    }

    def showProfile(String dni) {
        // Obtener el usuario correspondiente al DNI y pasarlo a la vista
        User user = User.findByDni(dni)
        
        if (user) {
            [
                user: user,
            ]
        } else {
            render(status: 404, view: '/notFound')
        }
    }

    def listProjects(String dni) {
        User user = User.findByDni(dni)

        Set<Project> sortedProjects = user.getSortedProjects()

        render(view: '/project/listProjects', model: [sortedProjects: sortedProjects, dni:user.dni])
    }


    def listNotifications(String dni) {
        User user = User.findByDni(dni)

        render(view: '/user/listNotifications', model: [notifications: user.notifications, dni:user.dni])
    }

    def changeUserProfile() {
        try {
            userService.changeUserProfile(params.artisticName, params.dni)
            redirect action: 'showProfile', params: [dni: params.dni]
        } catch (Exception e) {
            handleError(e)
        }    
    }

    def handleError(Exception e){
        def response = '{"error": "' + e.getMessage() + '"}'
        render response, status: 500
    }

}