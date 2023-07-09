package crearte

class UserController {

    def logUser() {
        User user = new User(
            name: params.name,
            lastName: params.lastName,
            dni: params.dni,
            description: params.description
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

    def listArtisticProfiles(String dni) {
        User user = User.findByDni(dni)

        Set<ArtisticProfile> artisticProfiles = user.getArtisticProfiles()

        render(view: '/artisticProfile/listArtisticProfiles', model: [artisticProfiles: artisticProfiles, dni:user.dni])
    }

}