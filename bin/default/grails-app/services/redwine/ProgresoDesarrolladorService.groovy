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

    def obtenerProgresoDesarrollador(Desarrollo desarrollo, Desarrollador desarrollador) {
        def proyecto = desarrollo.proyecto
        def ordenDesarrollo = desarrollo.nroOrden

        try {
            def progreso = desarrollo.iniciar(desarrollador)
            progreso.save()
            return progreso
        } catch(Exception e) {
            throw e
        }

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

    void actualizarPuntosInvestigacion(ResultadoObtencionPista resultado, Long desarrolladorId, Long desarrolloId) {
        if (resultado.pistaObtenida) {
            def desarrollador = Desarrollador.get(desarrolladorId)
            desarrollador.puntosInvestigacion = resultado.puntosInvestigacionRestantes
            
            ProgresoDesarrollador progresoDesarrollador = ProgresoDesarrollador.findByDesarrolloAndDesarrollador(Desarrollo.get(desarrolloId), Desarrollador.get(desarrolladorId))
            progresoDesarrollador.pistaUsada = true;

            Desarrollador.withTransaction { status ->
                try {
                    desarrollador.save(flush: true)
                } catch (Exception e) {
                    status.setRollbackOnly()
                    println "Error al guardar desarrollador: ${e.message}"
                }
            }

            ProgresoDesarrollador.withTransaction { status ->
                try {
                    progresoDesarrollador.save(flush: true)
                } catch (Exception e) {
                    status.setRollbackOnly()
                    println "Error al guardar progreso desarollador: ${e.message}"
                }
            }
        }
    }
}