package crearte

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class ProjectControllerSpec extends Specification implements ControllerUnitTest<ProjectController> {

    def setup() {
        ProjectController controller = new ProjectController()
        User user = new User(
            name: "Jorge",
            lastName: "Morales",
            dni: 40314876,
            description: "Actor proactivo en busca de proyectos motivadores"
        ).save(failOnError: true)

    }

    def cleanup() {
    }

    void "The convener is notified when there are 3 days left until their published project expires."() {

        given: "convener has published a project named 'Proyecto 1'"
                Project project1 = new Project(
                name: "Proyecto 1",
                description: "Proyecto destinado a un cortometraje de ficcion para presentar en el festival Cancun Cine 2024. Seriedad y compromiso de parte de todo el equipo",
                state: Project.ProjectState.DRAFT,
                ownerDni: 40314876,
                creationDate: LocalDate.of(2023,1,30)
            ).save(failOnError: true)
            
        user.projects.add(project1)
        user.save(flush:true)

        when: "when there are 3 days left until its publication expires."
        controller.notifyIfNecessary(LocalDate.of(2023,1,27))

        then:"The convener is notified with a message that says informing that the publication of the project will expire soon."
        !user.notifications.isEmpty()
        user.notifications.last() == "Su projecto publicado de nomb esta por expirar."
    }
}
