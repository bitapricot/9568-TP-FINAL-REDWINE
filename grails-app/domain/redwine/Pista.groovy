package redwine

class Pista {
    Desarrollo desarrollo
    String rutaPistas

    static belongsTo = [desarrollo: Desarrollo]

    static constraints = {
        desarrollo insertable: false, updateable: false
    }
}
