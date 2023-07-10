package crearte
import java.time.*;

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

    def publish() {
        LocalDateTime publicationDate = LocalDateTime.of(
            params.publicationDate_year.toInteger(),
            params.publicationDate_month.toInteger(),
            params.publicationDate_day.toInteger(),0,0);

        // try catch
        Project project = projectService.publish(params.name, publicationDate)
        if (project)
            flash.success = "Proyecto publicado exitosamente"
        else 
            flash.error = "El proyecto no cumple con las condiciones para ser publicado"

        render(view: '/project/show', model: [project: project])
    }

    def listProjects() {
        String dni = params.dni;
        Set<Project> projects = Project.getAll();

        projects = projects.findAll {
            it.userId != dni && it.state == Project.ProjectState.PUBLISHED;
        }

        render(view: '/project/listAllProjects', model: [sortedProjects: projects, dni:params.dni])
    }
}
