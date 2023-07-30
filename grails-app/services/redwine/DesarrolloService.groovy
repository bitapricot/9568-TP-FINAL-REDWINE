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

        // TO-DO: arreglar esto xd
        def pruebasAutomatizadas = PruebaAutomatizada.findAllByDesarrollo(Desarrollo.get(desarrolloId))

        pruebasAutomatizadas.each { pruebaAutomatizada ->
            // TO-DO: Ejecutar la prueba
            ResultadoPrueba resultado = pruebaAutomatizada.ejecutar()
            // TO-DO: Agregar el resultado de la prueba al resultado general del desarrollo
            resultadoDesarrollo.agregarResultadoPrueba(resultado)
        }

        return resultadoDesarrollo
    }
}