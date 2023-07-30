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
        // TODO: que esto sea un metodo del service de ProgresoDesarrollador
        ProgresoDesarrollador progresoDesarrollador = ProgresoDesarrollador.findByDesarrolloAndDesarrollador(resultado.desarrolloId, resultado.desarrolladorId)

        if (resultado.desarrolloOk) progresoDesarrollador.completado = true
    }
}