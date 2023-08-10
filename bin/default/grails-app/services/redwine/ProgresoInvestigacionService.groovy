package redwine

import grails.gorm.services.Service

@Service(ProgresoInvestigacion)
class ProgresoInvestigacionService {
    ProgresoInvestigacion get(Serializable id) {
        // Implementación del método get
    }

    List<ProgresoInvestigacion> list(Map args) {
        // Implementación del método list
    }

    Long count() {
        // Implementación del método count
    }

    void delete(Serializable id) {
        // Implementación del método delete
    }

    ProgresoInvestigacion save(ProgresoInvestigacion progresoInvestigacion) {
        // Implementación del método save
    }

    def obtenerProgresoInvestigacion(Investigacion investigacion, Desarrollador desarrollador) {
        try {
            def progreso = investigacion.iniciar(desarrollador)
            progreso.save()
            return progreso
        } catch(Exception e) {
            throw e
        }

    }
}