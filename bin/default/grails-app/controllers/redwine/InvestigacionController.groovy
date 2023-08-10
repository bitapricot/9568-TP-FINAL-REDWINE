package redwine

import redwine.Pregunta
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import groovy.json.JsonOutput

class InvestigacionController {

    InvestigacionService investigacionService
    ProgresoInvestigacionService progresoInvestigacionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond investigacionService.list(params), model:[investigacionCount: investigacionService.count()]
    }

    def show(Long id) {
        def investigacion = Investigacion.get(id)

        // TO-DO: ver como pasar los datos para el navbar sin tener que incluirlo en cada controller
        // TO-DO: En algún momento habría que obtener esto de la sesión o de algún otro lado
        def currentDesarrolladorId = 1
        def desarrollador = Desarrollador.get(currentDesarrolladorId)

        if (!investigacion) {
            render "Error: Investigación no encontrada."
            return
        }

        try {
            def progreso = progresoInvestigacionService.obtenerProgresoInvestigacion(investigacion, desarrollador)
            
            def preguntas = Pregunta.findAllByInvestigacion(investigacion)

            render view: "show", model: [preguntas: preguntas, investigacion: investigacion, desarrolladorRango: desarrollador.rango, desarrolladorId: currentDesarrolladorId, puntosInvestigacion: desarrollador.puntosInvestigacion]
        } catch(Exception e) {
            render "Error: ${e.message}"
            return
        }
    }

    def create() {
        respond new Investigacion(params)
    }

    def save(Investigacion investigacion) {
        if (investigacion == null) {
            notFound()
            return
        }

        try {
            investigacionService.save(investigacion)
        } catch (ValidationException e) {
            respond investigacion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'investigacion.label', default: 'Investigacion'), investigacion.id])
                redirect investigacion
            }
            '*' { respond investigacion, [status: CREATED] }
        }
    }

def procesarRespuestas() {
    def data = request.JSON
    def investigacionId = data.investigacionId
    def resultados = []

    def esCorrecto = true;
    data.respuestas.each { respuesta ->
        def pregunta = Pregunta.get(respuesta.preguntaId)
        if (pregunta) {
            def respuestaSeleccionada = Respuesta.get(respuesta.respuestaId)
            if (respuestaSeleccionada && !respuestaSeleccionada.esCorrecta) {
                esCorrecto = false;
            }
        }
    }
    def currentDesarrolladorId = 1
    def currentDesarrollador = Desarrollador.get(currentDesarrolladorId)
    def investigacion = Investigacion.get(investigacionId)
    def currentProgresoInvestigacion = ProgresoInvestigacion.findByInvestigacionAndDesarrollador(investigacion, currentDesarrollador)

    if (esCorrecto) {
        currentProgresoInvestigacion.completado = true
        currentDesarrollador.puntosInvestigacion += investigacion.puntajeOtorgado
        ProgresoInvestigacion.withTransaction { status ->
                try {
                    currentProgresoInvestigacion.save(flush: true)
                } catch (Exception e) {
                    status.setRollbackOnly()
                    println "Error al guardar el Progreso de la Investigación: ${e.message}"
                }
        }

        Desarrollador.withTransaction { status ->
                try {
                    currentDesarrollador.save(flush: true)
                } catch (Exception e) {
                    status.setRollbackOnly()
                    println "Error al guardar los cambios de Desarrollador: ${e.message}"
                }
        }
    }

    def map = [investigacionOk: esCorrecto, puntosInvestigacion: currentDesarrollador.puntosInvestigacion, puntajeOtorgado: investigacion.puntajeOtorgado]
    render JsonOutput.toJson(map)
}


    def edit(Long id) {
        respond investigacionService.get(id)
    }

    def update(Investigacion investigacion) {
        if (investigacion == null) {
            notFound()
            return
        }

        try {
            investigacionService.save(investigacion)
        } catch (ValidationException e) {
            respond investigacion.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'investigacion.label', default: 'Investigacion'), investigacion.id])
                redirect investigacion
            }
            '*'{ respond investigacion, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        investigacionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'investigacion.label', default: 'Investigacion'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'investigacion.label', default: 'Investigacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
