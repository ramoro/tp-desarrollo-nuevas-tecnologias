package crearte

class User {

    int dni
    String name
    String lastName
    String description
    Set<Project> projects = []
    Set<ArtisticProfile> artisticProfiles = []

    static hasMany = [projects: Project]

    static constraints = {
        dni matches: /\d{8}/, blank: false, nullable: false
    }

    def getSortedProjects(){
        this.projects.sort { project1, project2 ->
            if (project1.state == "published" && project2.state != "published") {
                // Si project1 está publicado pero project2 no, project1 tiene prioridad
                return -1
            } else if (project1.state != "published" && project2.state == "published") {
                // Si project2 está publicado pero project1 no, project2 tiene prioridad
                return 1
            } else if (project1.state == "published" && project2.state == "published") {
                // Ambos proyectos están publicados, comparar por fecha de publicación
                return project2.publicationDate <=> project1.publicationDate
            } else {
                // Ninguno de los proyectos está publicado, la prioridad es segun la fecha de creacion mas reciente
                return project2.creationDate <=> project1.creationDate
            }
        }
    }

}
