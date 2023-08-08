package redwine

class Investigacion {
    Proyecto proyecto
    String nombre
    String descripcion
    int puntajeOtorgado
    int nroOrden

    static belongsTo = [proyecto: Proyecto]

    static constraints = {
        proyecto insertable: false, updateable: false
    }
}