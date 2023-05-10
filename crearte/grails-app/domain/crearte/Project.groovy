package crearte
import java.time.*

class Project {

    String description
    LocalDate originalPublicationDate
    LocalDate finalPublicationDate
    List<Role> roles

    static constraints = {
        description blank: false, nullable: false, minSize: 100
        originalPublicationDate blank: false, nullable: false
        finalPublicationDate blank: false, nullable: false
        roles blank: false, nullable:false
    }
}
