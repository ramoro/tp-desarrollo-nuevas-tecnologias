package crearte

class UserController {

    def logUser() {
        User user = new User(
            name: params.name,
            lastName: params.lastName,
            dni: params.dni,
            description: params.description
        ).save(failOnError: true)

        redirect action: 'showProfile', params: [dni: user.dni]
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
        User user = User.findByDni(params.dni)

        Set<Project> sortedProjects = user.projects.sort { project1, project2 ->
            if (project1.state == "published" && project2.state != "published") {
                // Si project1 está publicado pero project2 no, project1 tiene prioridad
                return -1
            } else if (project1.state != "published" && project2.state == "published") {
                // Si project2 está publicado pero project1 no, project2 tiene prioridad
                return 1
            } else if (project1.state == "published" && project2.state == "published") {
                // Ambos proyectos están publicados, comparar por fecha de publicación
                return project2.publicationDate <=> project1.publicationDate
            } else {
                // Ninguno de los proyectos está publicado, la prioridad es segun la fecha de creacion mas reciente
                return project2.creationDate <=> project1.creationDate
            }
        }
        render(view: '/project/listProjects', model: [sortedProjects: sortedProjects, dni:user.dni])
    }

}