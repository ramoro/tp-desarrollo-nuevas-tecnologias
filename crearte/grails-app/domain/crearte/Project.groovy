package crearte
import java.time.LocalDateTime
import java.time.LocalDate
import java.time.Period

class Project {

    enum ProjectState {
        DRAFT,
        PUBLISHED
    }

    static final DaysLimitToNotifyExpiration = 3

    String name
    String description
    LocalDateTime creationDate
    LocalDateTime publicationDate
    LocalDateTime expirationDate
    Set<Role> roles = []
    Set<String> plantillas
    ProjectState state
    String userId

    static hasMany = [roles: Role]

    static constraints = {
        description blank: false, nullable: false, minSize: 100
        state blank: false, nullable: false
        userId blank:false, nullable: false
        creationDate nullable: false
        publicationDate nullable: true
        expirationDate nullable: true
    }

    static class ProjectNotPublishedException extends Exception {
        ProjectNotPublishedException(String errorMessage) {
            super(errorMessage);
        }
    }

    boolean canBePublished(LocalDateTime publicationDate, LocalDateTime expirationDate) {
        return this.state == ProjectState.DRAFT &&
               this.creationDate < publicationDate &&
               publicationDate < expirationDate &&
               this.roles.size() >= 1;
    }

    boolean isAboutToExpire(LocalDateTime actualDate) {
        if (this.state != ProjectState.PUBLISHED) {
            throw new Project.ProjectNotPublishedException("El projecto no está publicado.")
        }

        Period period = Period.between(this.expirationDate, actualDate)

        if (period.getDays() <= DaysLimitToNotifyExpiration) {
            return true
        }
        return false
    }
}
