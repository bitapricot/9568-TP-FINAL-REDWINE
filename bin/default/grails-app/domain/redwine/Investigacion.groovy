package redwine

class Investigacion {
    Proyecto proyecto
    String nombre
    String descripcion
    int puntajeOtorgado
    int nroOrden

    static belongsTo = [proyecto: Proyecto]

    static constraints = {
        proyecto insertable: false, updateable: false
    }

    ProgresoInvestigacion iniciar(Desarrollador desarrollador) {
        if (Desarrollo.findByProyectoAndNroOrden(proyecto, this.nroOrden - 1) 
            && ProgresoDesarrollador.findByDesarrolloAndDesarrollador(Desarrollo.findByProyectoAndNroOrden(proyecto, this.nroOrden - 1), desarrollador)?.completado) {
            
            def progreso = ProgresoInvestigacion.findByInvestigacionAndDesarrollador(this, desarrollador)

            if (!progreso) {
                // Si no existe el ProgresoInvestigacion, lo creamos
                progreso = new ProgresoInvestigacion(
                    desarrollador: desarrollador,
                    investigacion: this,
                    completado: false
                )
            }
            return progreso
        } else {
            throw new Exception("No se cumplen las condiciones para iniciar esta investigación")
        }
    }

}