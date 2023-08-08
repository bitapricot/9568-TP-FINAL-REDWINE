import redwine.Investigacion
import redwine.Desarrollador
import redwine.ProgresoInvestigacion

class InvestigacionDetalle {
    Long id
    String nombre
    String descripcion
    int nroOrden
    boolean iniciado
    boolean completado

    InvestigacionDetalle(Investigacion investigacion, Desarrollador desarrollador) {
        this.id = investigacion.id
        this.nombre = investigacion.nombre
        this.descripcion = investigacion.descripcion
        this.nroOrden = investigacion.nroOrden
        this.iniciado = ProgresoInvestigacion.findByInvestigacionAndDesarrollador(investigacion, desarrollador) != null
        this.completado = ProgresoInvestigacion.findByInvestigacionAndDesarrollador(investigacion, desarrollador)?.completado ?: false
    }
}