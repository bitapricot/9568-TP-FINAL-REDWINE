package redwine

import grails.gorm.services.Service

@Service(Desarrollo)
class DesarrolloService {

    Desarrollo get(Serializable id) {
        // Implementación del método get
    }

    List<Desarrollo> list(Map args) {
        // Implementación del método list
    }

    Long count() {
        // Implementación del método count
    }

    void delete(Serializable id) {
        // Implementación del método delete
    }

    Desarrollo save(Desarrollo desarrollo) {
        // Implementación del método save
    }

    ResultadoDesarrollo ejecutarPruebasAutomatizadasPorDesarrolloId(CodigoDesarrollador codigoDesarrollador, int desarrolloId) {
        def resultadoDesarrollo = new ResultadoDesarrollo(codigoDesarrollador.desarrolladorId, desarrolloId)

        def pruebasAutomatizadas = PruebaAutomatizada.findAllByDesarrollo(Desarrollo.get(desarrolloId))

        pruebasAutomatizadas.each { pruebaAutomatizada ->
            ResultadoPrueba resultado = pruebaAutomatizada.ejecutar(codigoDesarrollador)
            resultadoDesarrollo.agregarResultadoPrueba(resultado)
        }

        return resultadoDesarrollo
    }

     ResultadoPista obtenerPista(int desarrolladorId, int desarrolloId) {
        Pista pista = Pista.findByDesarrollo(Desarrollo.get(desarrolloId))
        ProgresoDesarrollador progresoDesarrollador = ProgresoDesarrollador.findByDesarrolloAndDesarrollador(Desarrollo.get(desarrolloId), Desarrollador.get(desarrolladorId))
        
        ResultadoPista resultadoPista = new ResultadoPista(pista, progresoDesarrollador)

        return resultadoPista
    }
}