package redwine

class Desarrollo {
    Proyecto proyecto
    int puntajeOtorgado
    int nroOrden
    String nombre
    String descripcion
    String codigoInicial
    String animacionHtml
    String animacionScript

    static belongsTo = [proyecto: Proyecto]

    static constraints = {
        proyecto insertable: false, updateable: false
        animacionHtml maxSize: 5000
        animacionScript maxSize: 5000
        codigoInicial maxSize: 5000
    }
}
