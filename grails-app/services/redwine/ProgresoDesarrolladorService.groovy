package redwine

import grails.gorm.services.Service

@Service(ProgresoDesarrollador)
class ProgresoDesarrolladorService {
    ProgresoDesarrollador get(Serializable id) {
        // Implementación del método get
    }

    List<ProgresoDesarrollador> list(Map args) {
        // Implementación del método list
    }

    Long count() {
        // Implementación del método count
    }

    void delete(Serializable id) {
        // Implementación del método delete
    }

    ProgresoDesarrollador save(ProgresoDesarrollador progresoDesarrollador) {
        // Implementación del método save
    }

    void actualizarProgresoPorResultadoDesarrollo(ResultadoDesarrollo resultado) {
        // TO-DO: que esto sea un metodo del service de ProgresoDesarrollador
        ProgresoDesarrollador progresoDesarrollador = ProgresoDesarrollador.findByDesarrolloAndDesarrollador(Desarrollo.get(resultado.desarrolloId), Desarrollador.get(resultado.desarrolladorId))
        
        if (resultado.desarrolloOk()) {
            progresoDesarrollador.completado = true
            ProgresoDesarrollador.withTransaction { status ->
                try {
                    progresoDesarrollador.save(flush: true)
                } catch (Exception e) {
                    status.setRollbackOnly()
                    println "Error al guardar progresoDesarrollador: ${e.message}"
                }
            }
        }
    }
}