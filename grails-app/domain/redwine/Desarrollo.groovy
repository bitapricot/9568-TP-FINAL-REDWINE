package redwine

class Desarrollo {
    Proyecto proyecto
    int puntajeOtorgado
    int nroOrden
    String nombre
    String descripcion

    static belongsTo = [proyecto: Proyecto]

    static constraints = {
        proyecto insertable: false, updateable: false
    }
}
