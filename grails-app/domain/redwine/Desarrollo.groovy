package redwine

class Desarrollo {
    Proyecto proyecto
    int puntajeOtorgado
    int nroOrden

    static belongsTo = [proyecto: Proyecto]

    static constraints = {
        proyecto insertable: false, updateable: false
    }
}
