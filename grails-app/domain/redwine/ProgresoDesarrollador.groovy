package redwine

class ProgresoDesarrollador { // Progreso
    Desarrollador desarrollador
    Desarrollo desarrollo
    boolean completado = false
    boolean pistaUsada = false

    static belongsTo = [desarrollador: Desarrollador, desarrollo: Desarrollo]
    static constraints = {
        desarrollador nullable: false
        desarrollo nullable: false
        completado nullable: false
        pistaUsada nullable: false
    }
}