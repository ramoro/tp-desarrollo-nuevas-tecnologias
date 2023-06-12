package crearte
import java.time.*

class Project {

    String name
    String description
    LocalDateTime creationDate
    LocalDateTime publicationDate
    LocalDateTime expirationDate
    List<Role> roles
    List<String> plantillas
    String state

    static constraints = {
        description blank: false, nullable: false, minSize: 100
        state blank: false, nullable: false
        creationDate nullable: false
        publicationDate nullable: true
        expirationDate nullable: true
    }
}
