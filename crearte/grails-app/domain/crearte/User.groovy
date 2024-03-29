package crearte

class User {

    int dni
    String name
    String lastName
    String description
    boolean isPremium
    Set<Project> projects = []
    Set<ArtisticProfile> artisticProfiles = []
    Set<Postulation> postulations = []
    List<String> notifications = []

    static hasMany = [projects: Project]

    static constraints = {
        dni matches: /\d{8}/, blank: false, nullable: false
        name blank: false, nullable: false
        lastName blank: false, nullabe: false
    }

    static class InvalidProfileChangeException extends Exception {
        InvalidProfileChangeException(String errorMessage) {
            super(errorMessage);
        }
    }
    

    def getSortedProjects(){
        this.projects.sort { project1, project2 ->
            if (project1.state == Project.ProjectState.PUBLISHED && project2.state != Project.ProjectState.PUBLISHED) {
                // Si project1 está publicado pero project2 no, project1 tiene prioridad
                return -1
            } else if (project1.state != Project.ProjectState.PUBLISHED && project2.state == Project.ProjectState.PUBLISHED) {
                // Si project2 está publicado pero project1 no, project2 tiene prioridad
                return 1
            } else if (project1.state == Project.ProjectState.PUBLISHED && project2.state == Project.ProjectState.PUBLISHED) {
                // Ambos proyectos están publicados, comparar por fecha de publicación
                return project2.publicationDate <=> project1.publicationDate
            } else {
                // Ninguno de los proyectos está publicado, la prioridad es segun la fecha de creacion mas reciente
                return project2.creationDate <=> project1.creationDate
            }
        }
    }

    def hasActiveProfile() {

        return this.artisticProfiles.any { profile -> profile.state == 1 }
    }

    def setActiveProfile(ArtisticProfile profile) {
        
        if(profile.state == 0 && this.hasActiveProfile()) {
            throw new User.InvalidProfileChangeException("El usuario ya tiene un perfil activo")
        }

        profile.state = profile.state == 0 ? 1 : 0
    }

    def notify(String message) {
        this.notifications.add(message)
    }

}
