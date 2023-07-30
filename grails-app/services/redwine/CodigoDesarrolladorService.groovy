import groovy.util.Eval
// TO-DO: boletear este service
class CodigoDesarrolladorService {

    // def ejecutarPruebasAutomatizadasPorDesarrolloId(CodigoDesarrollador codigoDesarrollador, int desarrolloId) {
    //     // Obtener las pruebas automatizadas para el desarrollo actual, y por cada una hacer el replace
    //     // en su código inicial por el codigoDesarrollador recibido. Si todas dan Ok entonces podemos decir que
    //     // el desarrollo está completo

    //     // También podríamos pensar en una clase ResultadoDesarrollo que represente, entre otras cosas, el resultado de las pruebas
    //     // y del cual podamos consultar qué pruebas fallaron, el output del error, o si pasaron todas las pruebas, etc.

    // }

    def ejecutarCodigo(CodigoDesarrollador codigo) {
        try {
            def result = Eval.me(codigo.valor)
            return result
        } catch (Exception e) {
            return [error: "Error al ejecutar las pruebas: ${e.message}"]
        }
    }
}