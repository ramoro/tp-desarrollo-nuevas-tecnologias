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

        if (existingProject) {
            flash.error = "Ya existe un proyecto con el nombre ${params.name}"
            // Pasamos el dni a la vista
            render(view: '/project/create', model: [existingName: params.name, description: params.description, dni: params.dni])
            return
        } else {
            Project project = new Project(
                name: params.name,
                description: params.description,
                state: "draft"
            ).save(failOnError: true)
            
            User user = User.findByDni(params.dni)
            user.projects.add(project)

            flash.success = "Proyecto creado exitosamente"
            render(view: 'save', model: [projectName: params.name])
        }
    }
}
