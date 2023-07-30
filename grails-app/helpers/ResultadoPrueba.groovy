// ResultadoPrueba.groovy
package redwine

class ResultadoPrueba {
    int pruebaId
    EstadoPrueba estado
    String output

    ResultadoPrueba(int pruebaId, EstadoPrueba estado, String output) {
        this.pruebaId = pruebaId
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
