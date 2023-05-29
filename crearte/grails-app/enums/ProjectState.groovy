enum ProjectState {
    PUBLISHED('Publicado'),
    EXPIRED('Vencido'),
    COMPLETED('Completado'),
    INACTIVE('Inactivo')
    
    final String label

    ProjectState(String label) {
        this.label = label
    }

    String toString() {
        label
    }
}