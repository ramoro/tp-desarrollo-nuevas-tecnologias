package crearte

class Role {

    String name
    String description
    boolean hasLimitedSpots = false
    int totalSpots
    int occupiedSpots

    static constraints = {
        name blank: false, nullable: false
        description blank: false, nullable: false
        totalSpots matches: /^[0-9]+$/, message: 'Ingrese solo números'
    }

    Role(String name, String description, boolean hasLimitedSpots, int totalSpots) {
        print("Entro a constructor!!!")
        this.name = name
        this.description = description
        if (hasLimitedSpots && hasLimitedSpots == true) {
            this.totalSpots = totalSpots
            this.occupiedSpots = 0
        }
        
    }
}
