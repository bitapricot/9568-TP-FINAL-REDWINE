package redwine
import groovy.json.JsonBuilder
import groovy.json.JsonOutput

class Pista {
    Desarrollo desarrollo
    String descripcion
    int costo

    static belongsTo = [desarrollo: Desarrollo]

    static constraints = {
        desarrollo insertable: false, updateable: false
    }
}
