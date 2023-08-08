package redwine

class ProgresoInvestigacion {
    Desarrollador desarrollador
    Investigacion investigacion
    boolean completado = false

    static belongsTo = [desarrollador: Desarrollador, investigacion: Investigacion]
    static constraints = {
        completado nullable: false
    }
}