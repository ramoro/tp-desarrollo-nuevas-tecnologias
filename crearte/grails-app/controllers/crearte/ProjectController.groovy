package crearte
import java.time.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ProjectController {

    ProjectService projectService
    UserService userService
    RoleService roleService
    PostulationService postulationService

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
        render(view: '/project/show', model: [project: project, dni: params.dni])
    }

    def showOtherProject() {
        Project project = Project.findByName(params.name)

        render(view: '/project/showOtherProject', model: [project: project])
    }

    def publish() {
        LocalDate publicationDate = LocalDate.of(
            params.publicationDate_year.toInteger(),
            params.publicationDate_month.toInteger(),
            params.publicationDate_day.toInteger())

        LocalDate expirationDate = LocalDate.of(
            params.expirationDate_year.toInteger(),
            params.expirationDate_month.toInteger(),
            params.expirationDate_day.toInteger())

        // try catch
        Project project = projectService.publish(params.name, publicationDate, expirationDate)
        if (project)
            flash.success = "Proyecto publicado exitosamente"
        else
            flash.error = "El proyecto no cumple con las condiciones para ser publicado"

        redirect(action: 'show', params: [name: project.name, dni: params.dni, project: project])
    }

    def listProjects() {
        Integer dni = params.dni as Integer;
        Set<Project> projects = Project.getAll();

        projects = projects.findAll {
            it.ownerDni != dni && it.state == Project.ProjectState.PUBLISHED;
        }

        render(view: '/project/listAllProjects', model: [projects: projects, dni:params.dni])
    }

    def notifyIfNecessary() {
        String dateString = params.date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        LocalDate actualDate = LocalDate.parse(dateString, formatter)
        try {
            projectService.updatePublishedProjects(actualDate)
        } catch (Exception e) {
            handleError(e)
        }

        render "Notificaciones correspondientes enviadas.", status: 200
    }

    def handleError(Exception e){
        def response = '{"error": "' + e.getMessage() + '"}'
        render response, status: 500
    }
}
