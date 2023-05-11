package crearte

class RoleController {

    def save() {
        def rol = new Role(params)

        if (rol.validate()) {
            rol.save(flush: true)
            flash.message = "Rol guardado exitosamente."
            redirect(action: "index")
        } else {
            render(view: "create", model: [rol: rol])
        }
    }

}
