package crearte
import java.time.*

class Project {

    enum ProjectState {
        DRAFT,
        PUBLISHED
    }

    String name
    String description
    LocalDateTime creationDate
    LocalDateTime publicationDate
    LocalDateTime expirationDate
    Set<Role> roles = []
    Set<String> plantillas
    ProjectState state

    static hasMany = [roles: Role]

    static constraints = {
        description blank: false, nullable: false, minSize: 100
        state blank: false, nullable: false
        creationDate nullable: false
        publicationDate nullable: true
        expirationDate nullable: true
    }

    boolean can_be_published(LocalDateTime publicationDate) {
        return this.state == ProjectState.DRAFT &&
               this.creationDate < publicationDate &&
               this.roles.size() >= 1;
    }
}
