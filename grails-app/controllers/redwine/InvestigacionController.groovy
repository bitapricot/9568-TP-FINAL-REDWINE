package redwine

import redwine.Pregunta
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class InvestigacionController {

    InvestigacionService investigacionService

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
            render "Error: Desarrollo encontrados."
            return
        }

        def proyecto = investigacion.proyecto
        def ordenInvestigacion = investigacion.nroOrden

        if (Desarrollo.findByProyectoAndNroOrden(proyecto, ordenInvestigacion - 1) &&
                ProgresoDesarrollador.findByDesarrolloAndDesarrollador(Desarrollo.findByProyectoAndNroOrden(proyecto, ordenInvestigacion - 1), desarrollador)?.completado) {
            
            def progreso = ProgresoInvestigacion.findByInvestigacionAndDesarrollador(investigacion, desarrollador)

            if (!progreso) {
                // Si no existe el ProgresoInvestigacion, lo creamos
                progreso = new ProgresoInvestigacion(
                    desarrollador: desarrollador,
                    investigacion: investigacion,
                    completado: false
                )
                progreso.save()
            }
        } else {
            render "Error: No se cumplen las condiciones para iniciar la investigación."
            return
        }

        def preguntas = Pregunta.findAllByInvestigacion(investigacion)

        render view: "show", model: [preguntas: preguntas, investigacion: investigacion, desarrolladorRango: desarrollador.rango, desarrolladorId: currentDesarrolladorId, puntosInvestigacion: desarrollador.puntosInvestigacion]
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
