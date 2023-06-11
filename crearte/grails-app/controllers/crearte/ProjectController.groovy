package crearte

class ProjectController {

    def create() { 
        String dni = params.dni
        // Puedes realizar cualquier lógica adicional aquí si es necesario

        [dni: dni] // Pasamos el dni a la vista
        render(view: '/project/create')
    }

    def save() {
        Project existingProject = Project.findByName(params.name)
        print("paso por aca")
        print(params.description)
        print(params.name)
        if (existingProject == null) {
            flash.error = "Ya existe un proyecto con el nombre ${params.name}"
            // Pasamos el dni a la vista
            render(view: '/project/create', model: [existingName: params.name, description: params.description, dni: params.dni])
            return
            //render(view: '/project/create', model: [existingName: "prueba", description: "prueba2", dni: params.dni])
        } else {
            
            //Crear proyecto y asignarle proyecto al usuario
            flash.success = "Proyecto creado exitosamente"
            render(view: 'save', model: [projectName: "aca va nombre del projecto recien saveado"])
        }
    }
}
