package crearte

class Role {

    String name
    String description
    boolean hasLimitedSpots
    int totalSpots
    int occupiedSpots

    static constraints = {
        name blank: false, nullable: false
        description blank: false, nullable: false
    }

}
