package crearte
import java.time.*

class Project {

    String name
    String description
    LocalDate publicationDate
    LocalDate expirationDate
    List<Role> roles
    List<String> plantillas
    ProjectState state

    static constraints = {
        description blank: false, nullable: false, minSize: 100
        originalPublicationDate blank: false, nullable: false
        finalPublicationDate blank: false, nullable: false
        roles blank: false, nullable:false
    }
}
