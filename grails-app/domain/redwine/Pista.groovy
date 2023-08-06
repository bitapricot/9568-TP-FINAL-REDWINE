package redwine
import groovy.json.JsonBuilder
import groovy.json.JsonOutput

// TO-DO: modelar pistas para HU 
class Pista {
    Desarrollo desarrollo
    String descripcion
    int costo

    static belongsTo = [desarrollo: Desarrollo]

    static constraints = {
        desarrollo insertable: false, updateable: false
    }
}
