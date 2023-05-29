package crearte

class User {

    int dni
    String name
    String lastName
    String description

    static constraints = {
        dni matches: /\d{8}/, blank: false, nullable: false
    }
}
