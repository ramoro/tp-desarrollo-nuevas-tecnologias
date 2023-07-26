package crearte

class Role {

    static class RoleHasNoAvailableSpotsException extends RuntimeException {
        RoleHasNoAvailableSpotsException() {
            super("Los cupos del rol estan completos y no posee lista de espera.")
        }
    }

    String name
    String description
    boolean hasLimitedSpots = false
    int totalSpots
    int occupiedSpots
    boolean hasWaitingList

    static final PercentageRoleAlmostCompleted = 90

    static constraints = {
        name blank: false, nullable: false
        description blank: false, nullable: false
        totalSpots matches: /^[0-9]+$/, message: 'Ingrese solo números'
    }

    Role(String name, String description, boolean hasLimitedSpots, int totalSpots) {
        assert name != null
        assert description != null

        this.name = name
        this.description = description
        this.hasLimitedSpots = hasLimitedSpots
        if (hasLimitedSpots) {
            this.totalSpots = totalSpots
            this.occupiedSpots = 0
            this.hasWaitingList = true
        } else {
            this.hasWaitingList = false
        }
    }

    boolean hasAvailableSpots() {
        if (!this.hasLimitedSpots || this.occupiedSpots < this.totalSpots)
            return true
        return false
    }

    boolean hasWaitingList() {
        return this.hasWaitingList
    }

    boolean isAboutToBeCompleted() {
        if (this.hasLimitedSpots) {
            if (this.occupiedSpots * 100 / this.totalSpots >=  PercentageRoleAlmostCompleted) {
                return true
            }
        }

        return false
    }
}
