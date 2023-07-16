package redwine

class Proyecto {
    String descripcion
    List<Desarrollo> desarrollos = []
    List<Investigacion> investigaciones = []

    static constraints = {
        descripcion nullable: false
    }
}
