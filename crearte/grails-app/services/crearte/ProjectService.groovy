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
                userId: dni,
                creationDate: LocalDateTime.now()
            ).save(failOnError: true)
            
        User user = User.findByDni(dni)
        user.projects.add(project)
        user.save(flush:true)
    }

    Project publish(String projectName, LocalDateTime publicationDate, LocalDateTime expirationDate){
        Project project = Project.findByName(projectName);

        if (project && project.canBePublished(publicationDate, expirationDate)) {
            project.publicationDate = publicationDate;
            project.expirationDate = expirationDate;
            project.state = Project.ProjectState.PUBLISHED;
            project.save(flush: true, failOnError: true);
            return project;
        }

        throw RuntimeException;
    }
}
