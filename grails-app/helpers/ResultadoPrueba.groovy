// ResultadoPrueba.groovy
package redwine

class ResultadoPrueba {
    PruebaAutomatizada prueba
    EstadoPrueba estado
    String output

    ResultadoPrueba(PruebaAutomatizada prueba, EstadoPrueba estado, String output) {
        this.prueba = prueba
        this.estado = estado
        this.output = output
    }

    boolean isOk() {
        estado == EstadoPrueba.OK
    }

    boolean isError() {
        estado == EstadoPrueba.ERROR
    }

    String getOutput() {
        output
    }
    
    int getPruebaId() {
        pruebaId
    }
}
