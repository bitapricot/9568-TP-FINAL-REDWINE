package redwine

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProyectoController {

    ProyectoService proyectoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond proyectoService.list(params), model:[proyectoCount: proyectoService.count()]
    }

    def show(Long id) {
        def proyecto = proyectoService.get(id)
        if (!proyecto) {
            render "Proyecto no encontrado"
            return
        }

        // TO-DO: ver como pasar los datos para el navbar sin tener que incluirlo en cada controller
        def currentDesarrolladorId = 1
        def desarrollador = Desarrollador.get(currentDesarrolladorId)
        
        def desarrollos = Desarrollo.findAllByProyecto(proyecto, [sort: 'nroOrden'])
        def investigaciones = Investigacion.findAllByProyecto(proyecto)

        // Transformar desarrollos a DesarrolloDetalle
        def detallesDesarrollos = desarrollos.collect { desarrollo ->
            new DesarrolloDetalle(desarrollo)
        }

        // Transformar investigaciones a InvestigacionDetalle
        def detallesInvestigaciones = investigaciones.collect { investigacion ->
            new InvestigacionDetalle(investigacion)
        }
        
        def items = []
        items.addAll(detallesDesarrollos)
        items.addAll(detallesInvestigaciones)
        items.sort { it.nroOrden }

        render view: "show", model: [proyecto: proyecto, items: items, desarrolladorRango: desarrollador.rango, desarrolladorId: currentDesarrolladorId, puntosInvestigacion: desarrollador.puntosInvestigacion]
    }

    def create() {
        respond new Proyecto(params)
    }

    def save(Proyecto proyecto) {
        if (proyecto == null) {
            notFound()
            return
        }

        try {
            proyectoService.save(proyecto)
        } catch (ValidationException e) {
            respond proyecto.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'proyecto.label', default: 'Proyecto'), proyecto.id])
                redirect proyecto
            }
            '*' { respond proyecto, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond proyectoService.get(id)
    }

    def update(Proyecto proyecto) {
        if (proyecto == null) {
            notFound()
            return
        }

        try {
            proyectoService.save(proyecto)
        } catch (ValidationException e) {
            respond proyecto.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'proyecto.label', default: 'Proyecto'), proyecto.id])
                redirect proyecto
            }
            '*'{ respond proyecto, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        proyectoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'proyecto.label', default: 'Proyecto'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'proyecto.label', default: 'Proyecto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
