package redwine

class Respuesta {
    Pregunta pregunta
    String descripcion
    boolean esCorrecta

    static belongsTo = [pregunta: Pregunta]

    static constraints = {
        descripcion nullable: false
    }

    static mapping = {
        pregunta column: 'pregunta_id', insertable: false, updateable: false
    }
}