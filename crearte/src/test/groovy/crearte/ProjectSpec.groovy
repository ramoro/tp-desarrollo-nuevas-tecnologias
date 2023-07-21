package crearte

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import java.time.LocalDate

class ProjectSpec extends Specification implements DomainUnitTest<Project> {

    User user
    Project project
    Role role1
    
    def setup() {
        user = new User(
            name: "Jorge",
            lastName: "Morales",
            dni: 40314876,
            description: "Productor y director de peliculas de terror y acción. MANEJO BUEN PRESUPUESTO. Requiero compromiso y buena disposicion cuando trabajo con alguien.",
            notifications: [],
            projects: []
        )

        project = new Project(
                name: "Largo de Terror - Esquizofrenia",
                description: "Largometraje de terror a grabar en Baradero. Se cubre caché de actor respentado los estándares actuales.",
                state: Project.ProjectState.DRAFT,
                ownerDni: user.dni,
                creationDate: LocalDate.of(2023,03,28),
                roles: []
        )  
    
        role1 = new Role(
            name: "Actor principal",
            description: "Actor de 20 a 25 años morocho", 
            hasLimitedSpots: true,
            totalSpots: 100)

        user.projects.add(project)
    }

    def cleanup() {
    }

    void "The convener is notified when there are 3 days left until his published project expires."() {

        given: "the convener has published a project"
        project.roles.add(role1)
        project.publicationDate = LocalDate.of(2023,04,05)
        project.expirationDate = LocalDate.of(2023,04,29)
        project.state = Project.ProjectState.PUBLISHED

        when: "when there are 3 days or less until the project expires"
        project.notifyOwner(LocalDate.of(2023,04,27), user)

        then:"the project owner is notified with a message that says that his project is about to expire"
        user.notifications.size == 1
        user.notifications[0] == "Su projecto publicado de nombre '${project.name}' esta por expirar."
    }

    void "The convener is notified when there are 90% or more spots occupied within a role offered on an own published project"() {

        given: "the convener has a project with a rol with limited spots and the project is published"
        project.roles.add(role1)

        project.publicationDate = LocalDate.of(2023,04,05)
        project.expirationDate = LocalDate.of(2023,04,30)
        project.state = Project.ProjectState.PUBLISHED

        when: "when there are 90% or more spots occupied within the role"
        project.roles[0].occupiedSpots = 90
        project.notifyOwner(LocalDate.of(2023,04,25), user)

        then:"the project owner is notified with a message that says that the role is about to be completed"
        user.notifications.size == 1
        user.notifications[0] == "Su projecto publicado de nombre '${project.name}' tiene los siguientes roles por ser ocupados completamente: '${role1.name}'"
    }

    void "The convener is notified when all roles from his published project has been completed"() {

        given: "the convener has published a project and the project has two roles with limited spots"
        project.roles.add(role1)
        Role role2 = new Role(
            name: "Actor secundario",
            description: "Actor de 30 a 45 años rubio", 
            hasLimitedSpots: true,
            totalSpots: 100)
        project.roles.add(role2)
        project.publicationDate = LocalDate.of(2023,04,05)
        project.expirationDate = LocalDate.of(2023,04,29)
        project.state = Project.ProjectState.PUBLISHED

        when: "when all the spots from all the roles have been completed"
        for (role in project.roles) {
            role.occupiedSpots = 100
        }
        project.notifyOwner(LocalDate.of(2023,04,23), user)

        then:"the project owner is notified with a message that says that all the roles from his project have been completed"
        user.notifications.size == 1
        user.notifications[0] == "Su projecto publicado de nombre '${project.name}' tiene todos sus roles con cupos completados."
    }
}