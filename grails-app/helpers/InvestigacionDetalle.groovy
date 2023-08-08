import redwine.Investigacion
import redwine.ProgresoDesarrollador

class InvestigacionDetalle {
    Long id
    String nombre
    String descripcion
    int nroOrden
    boolean completado

    InvestigacionDetalle(Investigacion investigacion) {
        this.id = investigacion.id
        this.nombre = investigacion.nombre
        this.descripcion = investigacion.descripcion
        this.nroOrden = investigacion.nroOrden
        this.completado = false // hardcodeo false hasta que se implemente ProgresoInvestigacion
            // completado: ProgresoDesarrollador.findByDesarrollo(desarrollo).completado
    }
}