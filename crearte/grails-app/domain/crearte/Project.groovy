package crearte
import java.time.*

class Project {

    enum ProjectState {
        DRAFT,
        PUBLISHED
    }

    String name
    String description
    LocalDate creationDate
    LocalDate publicationDate
    LocalDate expirationDate
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

    boolean can_be_published(LocalDate publicationDate) {
        if (this.state != ProjectState.DRAFT) throw new RuntimeException("Project is not a DRAFT")
        if (this.creationDate > publicationDate) throw new RuntimeException("""Project has creationDate > publicationDate. $this.creationDate $publicationDate""")
        if (this.roles.size() < 1) throw new RuntimeException("Project has no Roles")
        return this.state == ProjectState.DRAFT &&
               this.creationDate <= publicationDate &&
               this.roles.size() >= 1;
    }
}
