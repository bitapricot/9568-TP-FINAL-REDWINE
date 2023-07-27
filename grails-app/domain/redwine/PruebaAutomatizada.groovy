package redwine

class PruebaAutomatizada {
    Desarrollo desarrollo
    String codigoInicial
    int nroOrden

    static belongsTo = [desarrollo: Desarrollo]

    static constraints = {
        desarrollo insertable: false, updateable: false
        codigoInicial maxSize: 1000, blank: false 
    }
}
