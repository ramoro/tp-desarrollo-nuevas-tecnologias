package crearte

class Role {

    String name
    String description

    static constraints = {
        name blank: false, nullable: false
        description blank: false, nullable: false
    }

}
