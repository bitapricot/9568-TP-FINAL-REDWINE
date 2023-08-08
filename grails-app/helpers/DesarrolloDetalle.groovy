import redwine.Desarrollo
import redwine.Desarrollador
import redwine.ProgresoDesarrollador

class DesarrolloDetalle {
    Long id
    String nombre
    String descripcion
    int nroOrden
    boolean iniciado
    boolean completado

    DesarrolloDetalle(Desarrollo desarrollo, Desarrollador desarrollador) {
        this.id = desarrollo.id
        this.nombre = desarrollo.nombre
        this.descripcion = desarrollo.descripcion
        this.nroOrden = desarrollo.nroOrden
        this.iniciado = ProgresoDesarrollador.findByDesarrolloAndDesarrollador(desarrollo, desarrollador) != null
        this.completado = ProgresoDesarrollador.findByDesarrolloAndDesarrollador(desarrollo, desarrollador)?.completado ?: false
    }
}