package redwine

class Pregunta {
    Investigacion investigacion
    String descripcion
    static hasMany = [respuestas: Respuesta]

    static constraints = {
        descripcion nullable: false
    }
}
