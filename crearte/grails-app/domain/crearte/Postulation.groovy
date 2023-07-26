package crearte
import java.time.*

class Postulation {

    static class PostulationAlreadyExistsException extends RuntimeException {
        PostulationAlreadyExistsException() {
            super("Postulation already exists")
        }
    }

    enum PostulationState {
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

    static class InvalidPostulationException extends RuntimeException {
        InvalidPostulationException(String errorMessage) {
            super(errorMessage);
        }
    }

    Postulation(LocalDate postulationDate, Role role, User owner, String projectName, Postulation.PostulationState state) {
        if (!owner.hasActiveProfile())
            throw new InvalidPostulationException("La postulacion no se ha podido crear debido a que el usuario no tiene un perfil artístico activo.")

        this.date = postulationDate
        this.role = role
        this.ownerDni = owner.dni
        this.projectName = projectName
        this.state = state    
    }

}
