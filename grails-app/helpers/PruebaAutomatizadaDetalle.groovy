import redwine.PruebaAutomatizada

class PruebaAutomatizadaDetalle {
    String descripcion
    int nroOrden

    PruebaAutomatizadaDetalle(PruebaAutomatizada prueba) {
        this.descripcion = prueba.descripcion
        this.nroOrden = prueba.nroOrden
    }
}