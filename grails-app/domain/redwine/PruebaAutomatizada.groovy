package redwine

class PruebaAutomatizada {
    Desarrollo desarrollo
    String descripcion
    String codigoInicial
    int nroOrden

    static belongsTo = [desarrollo: Desarrollo]

    static constraints = {
        desarrollo insertable: false, updateable: false
        codigoInicial maxSize: 1000, blank: false 
        descripcion maxSize: 200, blank: false 
    }

    ResultadoPrueba ejecutar(CodigoDesarrollador codigo) {
            try {
                String codigoFinal = this.obtenerCodigoFinal(codigo)
                def resultado = Eval.me(codigoFinal)
                ResultadoPrueba resultadoPrueba = new ResultadoPrueba(this, resultado ? EstadoPrueba.OK : EstadoPrueba.ERROR, resultado ? "Ok" : "Prueba fallida")
                return result
            } catch (Exception e) {
                return new ResultadoPrueba(this, EstadoPrueba.ERROR, e.message)
            }
        }

    private String obtenerCodigoFinal(CodigoDesarrollador codigo) {
        // TO-DO: poner {{reemplazar}} como una constante
        return codigoInicial.replace("{{reemplazar}}", codigo.valor)
    }
}
