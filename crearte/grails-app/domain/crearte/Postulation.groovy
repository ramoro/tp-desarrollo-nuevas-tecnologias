package crearte
import java.time.*

class Postulation {

    enum PostulationState {
        PENDING,
        REJECTED,
        ACCEPTED
    }

    LocalDate date
    PostulationState state
    Role role
    User user
    Project project

    static constraints = {
        role blank: false, nullable: false
        user blank: false, nullable: false
        project blank: false, nullable: false
        state blank: false, nullable: false
    }

    boolean isEqual(Postulation p){
        return p.user == this.user && p.project == this.project && p.role == this.role
    }

}
