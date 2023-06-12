package crearte

class User {

    int dni
    String name
    String lastName
    String description
    Set<Project> projects = []

    static constraints = {
        dni matches: /\d{8}/, blank: false, nullable: false
    }

    static hasMany = [projects: Project]
}
