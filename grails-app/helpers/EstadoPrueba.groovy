package redwine

enum EstadoPrueba {
    OK("Ok"),
    ERROR("Error")

    final String estado

    EstadoPrueba(String estado) {
        this.estado = estado
    }

    @Override
    String toString() {
        estado
    }
}
