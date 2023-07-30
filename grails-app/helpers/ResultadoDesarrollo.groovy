package redwine

class ResultadoDesarrollo {
    List<ResultadoPrueba> resultadosPruebas = []

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
}