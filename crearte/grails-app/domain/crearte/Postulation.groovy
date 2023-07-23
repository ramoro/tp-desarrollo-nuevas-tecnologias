package crearte
import java.time.*

class Postulation {

    static class PostulationAlreadyExistsException extends RuntimeException {
        PostulationAlreadyExistsException() {
            super("Postulation already exists")
        }
    }

    enum PostulationState {
        PENDING,
        REJECTED,
        ACCEPTED,
        WAITING_LIST,
        WAITING_PREMIUM
    }

    LocalDate date
    PostulationState state
    String roleName
    int ownerDni
    String projectName

    static constraints = {
        roleName blank: false, nullable: false
        ownerDni blank: false, nullable: false
        projectName blank: false, nullable: false
        state blank: false, nullable: false
    }

    Postulation(LocalDate postulationDate, String roleName, int ownerDni, String projectName, Postulation.PostulationState state) {
        this.date = postulationDate
        this.roleName = roleName
        this.ownerDni = ownerDni
        this.projectName = projectName
        this.state = state    
    }

}
