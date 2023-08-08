package redwine

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import groovy.json.JsonSlurper

class DesarrolloController {

    DesarrolloService desarrolloService

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

        if (!desarrollo) {
            render "Error: Desarrollo encontrados."
            return
        }

        def proyecto = desarrollo.proyecto
        def ordenDesarrollo = desarrollo.nroOrden

        // Si el desarrollo tiene orden 1, o si el ProgresoDesarrollador del desarrollo de orden 1 está completado
        if (ordenDesarrollo == 1 || Desarrollo.findByProyectoAndNroOrden(proyecto, ordenDesarrollo - 1) &&
                ProgresoDesarrollador.findByDesarrolloAndDesarrollador(Desarrollo.findByProyectoAndNroOrden(proyecto, ordenDesarrollo - 1), desarrollador)?.completado) {
            
            def progreso = ProgresoDesarrollador.findByDesarrolloAndDesarrollador(desarrollo, desarrollador)

            if (!progreso) {
                // Si no existe el ProgresoDesarrollador, lo creamos
                progreso = new ProgresoDesarrollador(
                    desarrollador: desarrollador,
                    desarrollo: desarrollo,
                    completado: false
                )
                progreso.save()
            }
        } else {
            // Manejar caso de error: Condiciones no cumplidas
            render "Error: No se cumplen las condiciones para iniciar el desarrollo."
            return
        }

        def pruebas = PruebaAutomatizada.findAllByDesarrollo(desarrollo)
        def pruebasDetalles = pruebas.collect { prueba ->
            new PruebaAutomatizadaDetalle(prueba)
        }

        render view: "show", model: [pruebasDetalles: pruebasDetalles, desarrolladorRango: desarrollador.rango, desarrollo: desarrollo, animacionHtml: desarrollo.animacionHtml, animacionScript: desarrollo.animacionScript, desarrolladorId: currentDesarrolladorId, codigoInicial: desarrollo.codigoInicial, puntosInvestigacion: desarrollador.puntosInvestigacion]
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
