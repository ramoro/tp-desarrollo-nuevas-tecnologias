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
    Role role
    int ownerDni
    String projectName

    static constraints = {
        ownerDni blank: false, nullable: false
        projectName blank: false, nullable: false
        state blank: false, nullable: false
    }

    Postulation(LocalDate postulationDate, Role role, int ownerDni, String projectName, Postulation.PostulationState state) {
        this.date = postulationDate
        this.role = role
        this.ownerDni = ownerDni
        this.projectName = projectName
        this.state = state    
    }

}
