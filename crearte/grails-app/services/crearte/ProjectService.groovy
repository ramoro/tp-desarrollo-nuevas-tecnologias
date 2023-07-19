package crearte

import java.time.*
import grails.gorm.transactions.Transactional

@Transactional
class ProjectService {

    def createProject(String name, String dni, String description) {
        Project project = new Project(
                name: name,
                description: description,
                state: Project.ProjectState.DRAFT,
                ownerDni: dni,
                creationDate: LocalDate.now()
            ).save(failOnError: true)
            
        User user = User.findByDni(dni)
        user.projects.add(project)
        user.save(flush:true)
    }

    Project publish(String projectName, LocalDate publicationDate, LocalDate expirationDate){

        Project project = Project.findByName(projectName)

        if (project && project.canBePublished(publicationDate, expirationDate)) {
            project.publicationDate = publicationDate
            project.expirationDate = expirationDate
            project.state = Project.ProjectState.PUBLISHED
            project.save(flush: true, failOnError: true)
            return project
        }

        throw new RuntimeException();
    }

    def updateProjects(List<Project> publishedProjects, LocalDate actualDate) {

        for (project in publishedProjects) {
            
            User owner = User.findByDni(project.ownerDni)
            project.updateProject(actualDate, owner)

            project.save(flush: true, failOnError: true)
            owner.save(flush: true, failOnError: true)
        }
    }
}
