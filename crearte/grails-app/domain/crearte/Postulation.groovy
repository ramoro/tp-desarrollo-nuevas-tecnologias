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
        ACCEPTED
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

    boolean isEqual(Postulation p){
        return p.ownerDni == this.ownerDni && p.projectName == this.projectName && p.roleName == this.roleName
    }

}
