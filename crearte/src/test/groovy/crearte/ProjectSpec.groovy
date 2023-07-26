package crearte

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import java.time.LocalDate

class ProjectSpec extends Specification implements DomainUnitTest<Project> {

    User userConvener, userFrodo, userSpiderman
    Project project
    Role role1
    ArtisticProfile artisticProfile

    def setup() {
        userConvener = new User(
            name: "Jorge",
            lastName: "Morales",
            dni: 40314876,
            description: "Productor y director de peliculas de terror y acción. MANEJO BUEN PRESUPUESTO. Requiero compromiso y buena disposicion cuando trabajo con alguien.",
            notifications: [],
            projects: [],
            postulations: [],
            isPremium: false
        )

        userSpiderman = new User(
            name: "Peter",
            lastName: "Parker",
            dni: 40316876,
            description: "El actor mas aracnido del condado. HAbilidoso en todos los sentidos posibles. SExto sentido y apto para escenas de accion.",
            notifications: [],
            projects: [],
            postulations: [],
            isPremium: false
        )

        userFrodo = new User(
            name: "Frodo",
            lastName: "Baggins",
            dni: 12345678,
            description: "Actor de La Comarca",
            notifications: [],
            projects: [],
            postulations: [],
            isPremium: false
        )

        artisticProfile = new ArtisticProfile("Iorsh morales", "CABA", 30, 178, 80, "www.youtube.com/3324asdf34?", "iorsh.jpg") 

        project = new Project(
                name: "Largo de Terror - Esquizofrenia",
                description: "Largometraje de terror a grabar en Baradero. Se cubre caché de actor respentado los estándares actuales.",
                state: Project.ProjectState.DRAFT,
                ownerDni: userConvener.dni,
                creationDate: LocalDate.of(2023,03,28),
                roles: [],
                postulations: []
        )  
    
        role1 = new Role(
            name: "Actor principal",
            description: "Actor de 20 a 25 años morocho",
            hasLimitedSpots: true,
            totalSpots: 100,
            hasWaitingList: true)

        artisticProfile.state = 1
        userConvener.artisticProfiles.add(artisticProfile)
        userFrodo.artisticProfiles.add(artisticProfile)
        userSpiderman.artisticProfiles.add(artisticProfile)
        userConvener.projects.add(project)
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
        project.notifyOwner(LocalDate.of(2023,04,27), userConvener)

        then:"the project owner is notified with a message that says that his project is about to expire"
        userConvener.notifications.size == 1
        userConvener.notifications[0] == "Su projecto publicado de nombre '${project.name}' esta por expirar."
    }

    void "The convener is notified when there are 90% or more spots occupied within a role offered on an own published project"() {

        given: "the convener has a published project with a rol with limited spots"
        project.roles.add(role1)

        project.publicationDate = LocalDate.of(2023,04,05)
        project.expirationDate = LocalDate.of(2023,04,30)
        project.state = Project.ProjectState.PUBLISHED

        when: "when there are 90% or more spots occupied within the role"
        project.roles[0].occupiedSpots = 90
        project.notifyOwner(LocalDate.of(2023,04,25), userConvener)

        then:"the project owner is notified with a message that says that the role is about to be completed"
        userConvener.notifications.size == 1
        userConvener.notifications[0] == "Su projecto publicado de nombre '${project.name}' tiene los siguientes roles por ser ocupados completamente: '${role1.name}'"
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
        project.notifyOwner(LocalDate.of(2023,04,23), userConvener)

        then:"the project owner is notified with a message that says that all the roles from his project have been completed"
        userConvener.notifications.size == 1
        userConvener.notifications[0] == "Su projecto publicado de nombre '${project.name}' tiene todos sus roles con cupos completados."
    }

    void "A User creates a Postulation for a Role with no waiting list and no available spots in a Project results in an error"() {
        Role roleWithNoAvailableSpots

        given: "a published project with a role with all of its spots completed and the waiting list deleted, and the artist has an active profile"
        roleWithNoAvailableSpots = new Role(
            name: "Actor principal",
            description: "Actor de 20 a 25 años",
            hasLimitedSpots: true,
            hasWaitingList: false,
            occupiedSpots: 1,
            totalSpots: 1)
        when: "the artist apply his profile to that role"
        Postulation p = project.createUserPostulationToRole(roleWithNoAvailableSpots, userFrodo, LocalDate.of(2023,07,23))
        then: "an exception is thrown"
        Exception e = thrown()
        e.message == 'Los cupos del rol estan completos y no posee lista de espera.'
    }

    void "A User creates a Postulation for a Role in a Project with available spots is successful"() {
        given: "a published project with a role with available spots and an artist that has an active artistic profile"
        project.roles.add(role1)

        project.publicationDate = LocalDate.of(2023,04,05)
        project.expirationDate = LocalDate.of(2023,04,30)
        project.state = Project.ProjectState.PUBLISHED

        when: "the artist apply his profile to that role"
        project.createUserPostulationToRole(role1, userFrodo, LocalDate.of(2023,07,23))
        
        then: "the postulation for that role is created succesfully"
        project.postulations.size() == 1
    }

    void "A User creates a Postulation for a Role with waiting list and no spots available in a Project is successful"() {
        Role roleWithNoAvailableSpots
        Postulation postulation
        given: "a published project with a role with no available spots and a waiting list, and an artist that has an active artistic profile"
        roleWithNoAvailableSpots = new Role(
            name: "Actor principal",
            description: "Actor de 20 a 25 años",
            hasLimitedSpots: true,
            hasWaitingList: true,
            occupiedSpots: 1,
            totalSpots: 1)
        when: "the artist apply his profile to that role"
        postulation = project.createUserPostulationToRole(roleWithNoAvailableSpots, userFrodo, LocalDate.of(2023,07,23))
        then: "the postulation for that role is created succesfully on a waiting list state"
        postulation.state == Postulation.PostulationState.WAITING_LIST
        project.postulations.size() == 1
    }

    void "A Premium User creates a Postulation for a Role with no waiting list and no available spots in a Project is successful"() {
        Role roleWithNoAvailableSpots
        Postulation postulation
        given: "a published project with a role with all of its spots completed and the waiting list deleted, and an artist with a premium user and an active profile"
        roleWithNoAvailableSpots = new Role(
            name: "Actor principal",
            description: "Actor de 20 a 25 años",
            hasLimitedSpots: true,
            hasWaitingList: false,
            occupiedSpots: 1,
            totalSpots: 1)
        when: "the artist apply his profile to that role"
        userFrodo.isPremium = true
        postulation = project.createUserPostulationToRole(roleWithNoAvailableSpots, userFrodo, LocalDate.of(2023,07,23))
        then: "the postulation for that role is created succesfully on a waiting premium state"
        postulation.state == Postulation.PostulationState.WAITING_PREMIUM
        project.postulations.size() == 1
    }    
    
    void "A waiting list is deleted for a certain role within a project"() {
        
        given: "the convener has created a role with limited spots for an own published project,the role is completed and there are two artist that have entered at the waiting list for that role"
        project.roles.add(role1)
        project.publicationDate = LocalDate.of(2023,04,05)
        project.expirationDate = LocalDate.of(2023,04,29)
        project.state = Project.ProjectState.PUBLISHED

        project.roles[0].occupiedSpots = 100

        Postulation postulationUser = project.createUserPostulationToRole(project.roles[0],  userSpiderman, LocalDate.of(2023,04,22))
        Postulation postulationUser2 = project.createUserPostulationToRole(project.roles[0],  userFrodo, LocalDate.of(2023,04,22))

        when: "when the waiting list is deleted"
        project.deletePostulationsOnWaitForUser(postulationUser, userSpiderman)
        project.deletePostulationsOnWaitForUser(postulationUser2, userFrodo)

        then:"all the postulations that were at the waiting list are rejected"
        postulationUser.state == Postulation.PostulationState.REJECTED
        postulationUser2.state == Postulation.PostulationState.REJECTED
    }

    void "A waiting list is deleted for a certain role within a project having a premium user in the waiting list"() {
        userFrodo.isPremium = true

        given: "the convener has created a role with limited spots for an own published project,the role is completed, there are two artist that have entered at the waiting list for that role and one of that artists is a premium user"
        project.roles.add(role1)
        project.publicationDate = LocalDate.of(2023,04,05)
        project.expirationDate = LocalDate.of(2023,04,29)
        project.state = Project.ProjectState.PUBLISHED

        project.roles[0].occupiedSpots = 100

        Postulation postulationUser = project.createUserPostulationToRole(project.roles[0],  userSpiderman, LocalDate.of(2023,04,22))
        Postulation postulationUser2 = project.createUserPostulationToRole(project.roles[0],  userFrodo, LocalDate.of(2023,04,22))

        when: "when the waiting list is deleted"
        project.deletePostulationsOnWaitForUser(postulationUser, userSpiderman)
        project.deletePostulationsOnWaitForUser(postulationUser2, userFrodo)

        then:"all the postulations that were at the waiting list are rejected except the premium user one, which postulation stays ina witing premium state"
        postulationUser.state == Postulation.PostulationState.REJECTED
        postulationUser2.state == Postulation.PostulationState.WAITING_PREMIUM
    }
}