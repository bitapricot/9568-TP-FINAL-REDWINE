package redwine

class ProgresoDesarrollador { // Progreso
    Desarrollador desarrollador
    Desarrollo desarrollo
    boolean completado = false
    boolean pistaUsada = false

    static belongsTo = [desarrollador: Desarrollador, desarrollo: Desarrollo]
    static constraints = {
        // desarrollador insertable: false, updateable: false
        // desarrollo insertable: false, updateable: false
        completado nullable: false
        pistaUsada nullable: false
    }
}