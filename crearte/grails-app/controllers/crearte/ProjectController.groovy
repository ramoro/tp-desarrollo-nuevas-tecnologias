package crearte

class ProjectController {

    ProjectService projectService

    def create() { 
        String dni = params.dni
        [dni: dni] // Pasamos el dni a la vista
        render(view: '/project/create')
    }

    def save() {

        Project existingProject = Project.findByName(params.name)

        if (existingProject) {
            flash.error = "Ya existe un proyecto con el nombre ${params.name}"
            render(view: '/project/create', model: [existingName: params.name, description: params.description, dni: params.dni])
            return
        } else {
            projectService.createProject(params.name, params.dni, params.description)

            flash.success = "Proyecto creado exitosamente con el nombre ${params.name}"
            render(view: 'save', model: [projectName: params.name, dni: params.dni])
        }
        
    }

    def show() {
        Project project = Project.findByName(params.name)

        render(view: '/project/show', model: [project: project])
    }
}
