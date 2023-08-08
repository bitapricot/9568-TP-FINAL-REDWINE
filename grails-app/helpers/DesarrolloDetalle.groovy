import redwine.Desarrollo
import redwine.ProgresoDesarrollador

class DesarrolloDetalle {
    Long id
    String nombre
    String descripcion
    int nroOrden
    boolean iniciado
    boolean completado

    DesarrolloDetalle(Desarrollo desarrollo) {
        this.id = desarrollo.id
        this.nombre = desarrollo.nombre
        this.descripcion = desarrollo.descripcion
        this.nroOrden = desarrollo.nroOrden
        this.iniciado = ProgresoDesarrollador.findByDesarrollo(desarrollo) != null
        this.completado = ProgresoDesarrollador.findByDesarrollo(desarrollo)?.completado ?: false
    }
}