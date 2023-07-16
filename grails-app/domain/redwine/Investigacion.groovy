package redwine

class Investigacion {
    Proyecto proyecto
    int puntajeOtorgado
    String rutaPreguntas

    static belongsTo = [proyecto: Proyecto]

    static constraints = {
        proyecto nullable: false
    }
}