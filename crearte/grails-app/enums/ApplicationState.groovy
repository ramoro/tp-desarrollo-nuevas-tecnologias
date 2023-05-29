enum PostulationState {
    ACCEPTED('Aceptada'),
    REJECTED('Rechazada'),
    IN_PROGRESS('En progreso')
    
    final String label

    PostulationState(String label) {
        this.label = label
    }

    String toString() {
        label
    }
}