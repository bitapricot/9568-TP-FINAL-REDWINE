package redwine

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import groovy.json.JsonSlurper

class DesarrolloController {

    DesarrolloService desarrolloService
    ProgresoDesarrolladorService progresoDesarrolladorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond desarrolloService.list(params), model:[desarrolloCount: desarrolloService.count()]
    }

    def show(Long id) {
        def desarrollo = Desarrollo.get(id)

        // TO-DO: ver como pasar los datos para el navbar sin tener que incluirlo en cada controller
        // TO-DO: En algún momento habría que obtener esto de la sesión o de algún otro lado
        def currentDesarrolladorId = 1
        def desarrollador = Desarrollador.get(currentDesarrolladorId)
        
        try {
            def progreso = progresoDesarrolladorService.obtenerProgresoDesarrollador(desarrollo, desarrollador)
            
            // TO-DO: hacer un service para esto
            def pruebas = PruebaAutomatizada.findAllByDesarrollo(desarrollo)
            def pruebasDetalles = pruebas.collect { prueba ->
                new PruebaAutomatizadaDetalle(prueba)
            }

            render view: "show", model: [pruebasDetalles: pruebasDetalles, desarrolladorRango: desarrollador.rango, desarrollo: desarrollo, animacionHtml: desarrollo.animacionHtml, animacionScript: desarrollo.animacionScript, desarrolladorId: currentDesarrolladorId, codigoInicial: desarrollo.codigoInicial, puntosInvestigacion: desarrollador.puntosInvestigacion]
        } catch(Exception e) {
            render "Error: ${e.message}"
            return

        }
    }


    def create() {
        respond new Desarrollo(params)
    }

    def save(Desarrollo desarrollo) {
        if (desarrollo == null) {
            notFound()
            return
        }

        try {
            desarrolloService.save(desarrollo)
        } catch (ValidationException e) {
            respond desarrollo.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'desarrollo.label', default: 'Desarrollo'), desarrollo.id])
                redirect desarrollo
            }
            '*' { respond desarrollo, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond desarrolloService.get(id)
    }

    def update(Desarrollo desarrollo) {
        if (desarrollo == null) {
            notFound()
            return
        }

        try {
            desarrolloService.save(desarrollo)
        } catch (ValidationException e) {
            respond desarrollo.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'desarrollo.label', default: 'Desarrollo'), desarrollo.id])
                redirect desarrollo
            }
            '*'{ respond desarrollo, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        desarrolloService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'desarrollo.label', default: 'Desarrollo'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'desarrollo.label', default: 'Desarrollo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
