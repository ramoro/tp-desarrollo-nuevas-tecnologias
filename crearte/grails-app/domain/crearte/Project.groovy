package crearte

import java.time.LocalDate
import java.time.Period

class Project {

    enum ProjectState {
        DRAFT,
        PUBLISHED,
        EXPIRED
    }

    static final DaysLimitToNotifyExpiration = 3

    String name
    String description
    LocalDate creationDate
    LocalDate publicationDate
    LocalDate expirationDate
    Set<Role> roles = []
    Set<String> plantillas
    List<Postulation> postulations
    ProjectState state
    int ownerDni

    static hasMany = [roles: Role]

    static constraints = {
        description blank: false, nullable: false, minSize: 100
        state blank: false, nullable: false
        ownerDni matches: /\d{8}/, blank: false, nullable: false
        creationDate nullable: true
        publicationDate nullable: true
        expirationDate nullable: true
    }

    static class ProjectIsInInvalidStateException extends RuntimeException {
        ProjectIsInInvalidStateException() {
            super("Project is not a DRAFT");
        }
    }

    static class ProjectCreationDateExceedsPublicationDateException extends RuntimeException {
        ProjectCreationDateExceedsPublicationDateException() {
            super("Project has creationDate exceeds publicationDate");
        }
    }

    static class ProjectPublicationDateExceedsExpirationDateException extends RuntimeException {
        ProjectPublicationDateExceedsExpirationDateException() {
            super("Project has publicationDate exceeds expirationDate");
        }
    }

    static class ProjectHasNoRolesException extends RuntimeException {
        ProjectHasNoRolesException() {
            super("Project has no Roles");
        }
    }

    static class ProjectNotPublishedException extends RuntimeException {
        ProjectNotPublishedException(String errorMessage) {
            super(errorMessage);
        }
    }

    boolean canBePublished(LocalDate publicationDate, LocalDate expirationDate) {
        if (this.state != ProjectState.DRAFT) throw new ProjectIsInInvalidStateException()
        if (this.creationDate > publicationDate) throw new ProjectCreationDateExceedsPublicationDateException()
        if (publicationDate > expirationDate) throw new ProjectPublicationDateExceedsExpirationDateException()
        if (this.roles.size() < 1) throw new ProjectHasNoRolesException()
        return true
    }

    void notifyOwner(LocalDate actualDate, User owner) {

        if(this.isExpired(actualDate)) {
            owner.notify("Ha expirado la fecha de publicación de su projecto '${this.name}'.")
            return
        }

        if (this.isAboutToExpire(actualDate)){
            owner.notify("Su projecto publicado de nombre '${this.name}' esta por expirar.")
        }

        Set<Role> rolesAlmostCompleted = this.getRolesAboutToBeCompleted()

        if (this.hasOnlyRolesWithLimitedSpots() && this.hasAllRolesCompleted()) {
            owner.notify("Su projecto publicado de nombre '${this.name}' tiene todos sus roles con cupos completados.")
        } else if (!rolesAlmostCompleted.isEmpty()) {
            String message = "Su projecto publicado de nombre '${this.name}' tiene los siguientes roles por ser ocupados completamente:"
            for(roleName in rolesAlmostCompleted) {
                message += " '${roleName}'"
            }
            owner.notify(message)
        }
    }

    boolean isAboutToExpire(LocalDate actualDate) {
        if (this.state != ProjectState.PUBLISHED) {
            throw new ProjectNotPublishedException("El projecto no está publicado.")
        }

        Period period = Period.between(actualDate, this.expirationDate)
        if (period.getDays() <= DaysLimitToNotifyExpiration) {
            return true
        }

        return false
    }

    Set<String> getRolesAboutToBeCompleted() {
        Set<String> rolesAlmostCompleted = []

        for (role in roles) {
            if (role.isAboutToBeCompleted()) {
                rolesAlmostCompleted.add(role.name)
            }
        }

        return rolesAlmostCompleted
    }

    boolean isExpired(LocalDate actualDate) {
        if (this.state != ProjectState.PUBLISHED) {
            throw new ProjectNotPublishedException("El projecto no está publicado.")
        }

        Period period = Period.between(actualDate, this.expirationDate)

        return (period.getDays() <= 0)
    }

    boolean hasOnlyRolesWithLimitedSpots() {
        for (role in this.roles) {
            if (!role.hasLimitedSpots) return false
        }

        return true
    }

    boolean hasAllRolesCompleted() {
        for (role in this.roles) {
            if (role.occupiedSpots < role.totalSpots) return false
        }

        return true
    }

    Postulation createUserPostulationToRole(Role role, User user, LocalDate date) {
        Postulation.PostulationState initialState = Postulation.PostulationState.PENDING

        if (!role.hasAvailableSpots()){
            initialState = Postulation.PostulationState.WAITING_LIST
            if(!role.hasWaitingList()) {
                if (!user.isPremium)
                    throw new Role.RoleHasNoAvailableSpotsException()

                initialState = Postulation.PostulationState.WAITING_PREMIUM
            }
        }

        for(postulation in this.postulations) {
            if (postulation.ownerDni == user.dni && postulation.roleName == role.name) {
                throw new Postulation.PostulationAlreadyExistsException()
            }
        }
        Postulation postulation = new Postulation(date, role.name, user.dni, this.name, initialState)
        this.postulations.add(postulation)

        return postulation
    }

    Role createRole(String name, String description, boolean hasLimitedSpots, int totalSpots) {
        Role role = new Role(name, description, hasLimitedSpots, totalSpots)
        this.roles.add(role)
        return role
    }

    List<Postulation> deletePostulationsWaiting(List<Postulation> postulationsWaiting) {
        List<Postulation> removedPostulations = []
        for(postulation in postulationsWaiting) {
            postulation.state = Postulation.PostulationState.REJECTED
            removedPostulations.add(postulation)
            this.postulations.removeElement(postulation)
        }

        return removedPostulations
    }

}
