package redwine

class Rango {
    String descripcion
    int puntajeMaximo

    static constraints = {
        descripcion nullable: false
        puntajeMaximo nullable: false
    }
}
