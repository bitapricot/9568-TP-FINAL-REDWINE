package redwine
import groovy.json.JsonBuilder

class ResultadoDesarrollo {
    List<ResultadoPrueba> resultadosPruebas = []
    int desarrolladorId
    int desarrolloId

    ResultadoDesarrollo(int desarrolladorId, int desarrolloId) {
        this.desarrolladorId = desarrolladorId
        this.desarrolloId = desarrolloId
    }

    void agregarResultadoPrueba(ResultadoPrueba resultado) {
        resultadosPruebas.add(resultado)
    }

    List<ResultadoPrueba> obtenerPruebasPasaron() {
        resultadosPruebas.findAll { it.isOk() }
    }

    List<ResultadoPrueba> obtenerPruebasFallaron() {
        resultadosPruebas.findAll { it.isError() }
    }

    boolean desarrolloOk() {
        resultadosPruebas.every { it.isOk() }
    }

    String obtenerOutputGeneral() {
        resultadosPruebas.collect { it.getOutput() }.join("\n")
    }

    Map<Long, String> obtenerOutputsPruebas() {
        resultadosPruebas.collectEntries { [it.pruebaId, it.getOutput()] }
    }

    String serialize() {
        def jsonBuilder = new JsonBuilder()
        jsonBuilder {
            resultadosPruebas resultadosPruebas.collect { resultadoPrueba ->
                [
                    prueba: resultadoPrueba.prueba.descripcion,
                    estado: resultadoPrueba.estado.toString(),
                    output: resultadoPrueba.output
                ]
            }
        }
    }
}