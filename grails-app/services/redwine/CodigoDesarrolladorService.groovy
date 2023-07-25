import groovy.util.Eval

class CodigoDesarrolladorService {

    def ejecutarCodigo(CodigoDesarrollador codigo) {
        try {
            def result = Eval.me(codigo.valor)
            return result
        } catch (Exception e) {
            return [error: "Error al ejecutar las pruebas: ${e.message}"]
        }
    }
}