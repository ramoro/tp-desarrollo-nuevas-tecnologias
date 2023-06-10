package crearte

class UserController {

    def logUser() {
        User user = new User(
            name: params.nombre,
            lastName: params.apellido,
            dni: params.dni,
            description: params.descripcion,
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


}