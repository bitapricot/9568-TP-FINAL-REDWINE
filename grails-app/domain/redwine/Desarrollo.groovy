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
        descripcion maxSize: 500
    }

    ProgresoDesarrollador iniciar(Desarrollador desarrollador) {
        // Si el desarrollo tiene orden 1, o si el ProgresoDesarrollador del desarrollo de orden 1 está completado
        if (this.nroOrden == 1 || Desarrollo.findByProyectoAndNroOrden(this.proyecto, this.nroOrden - 1) &&
                ProgresoDesarrollador.findByDesarrolloAndDesarrollador(Desarrollo.findByProyectoAndNroOrden(this.proyecto, this.nroOrden - 1), desarrollador)?.completado) {
            
            def progreso = ProgresoDesarrollador.findByDesarrolloAndDesarrollador(this, desarrollador)

            if (!progreso) {
                // Si no existe el ProgresoDesarrollador, lo creamos
                progreso = new ProgresoDesarrollador(
                    desarrollador: desarrollador,
                    desarrollo: this,
                    completado: false
                )
            }
            return progreso
        } else {
            throw new Exception("No se cumplen las condiciones para iniciar este desarrollo")
        }
    }
}
