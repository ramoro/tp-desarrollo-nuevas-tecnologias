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
    ProjectState state
    int ownerDni

    static hasMany = [roles: Role]

    static constraints = {
        description blank: false, nullable: false, minSize: 100
        state blank: false, nullable: false
        ownerDni matches: /\d{8}/, blank: false, nullable: false
        creationDate nullable: false
        publicationDate nullable: true
        expirationDate nullable: true
    }


    static class ProjectNotPublishedException extends Exception {
        ProjectNotPublishedException(String errorMessage) {
            super(errorMessage);
        }
    }

    boolean canBePublished(LocalDate publicationDate, LocalDate expirationDate) {
        if (this.state != ProjectState.DRAFT) throw new RuntimeException("Project is not a DRAFT")
        if (this.creationDate > publicationDate) throw new RuntimeException("""Project has creationDate > publicationDate. $this.creationDate $publicationDate""")
        if (publicationDate > expirationDate) throw new RuntimeException("""Project has publicationDate > expirationDate. $publicationDate $expirationDate""")
        if (this.roles.size() < 1) throw new RuntimeException("Project has no Roles")
        return this.state == ProjectState.DRAFT &&
               this.creationDate <= publicationDate &&
               publicationDate <= expirationDate
               this.roles.size() >= 1;
    }

    boolean isAboutToExpire(LocalDate actualDate) {
        if (this.state != ProjectState.PUBLISHED) {
            throw new Project.ProjectNotPublishedException("El projecto no está publicado.")
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
            throw new Project.ProjectNotPublishedException("El projecto no está publicado.")
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

}
