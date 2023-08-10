package redwine

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProyectoController {

    ProyectoService proyectoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    // def index(Integer max) {
    //     params.max = Math.min(max ?: 10, 100)
    //     respond proyectoService.list(params), model:[proyectoCount: proyectoService.count()]
    // }
    def index() {
        def proyectos = Proyecto.list() // Obtén la lista de proyectos desde la base de datos
        // render view: 'index', model: [proyectos: proyectos]
        render(view: "/index", model: [proyectos: proyectos, desarrollador: Desarrollador.get(1)])
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
        def investigaciones = Investigacion.findAllByProyecto(proyecto, [sort: 'nroOrden'])

        // Transformar desarrollos a DesarrolloDetalle
        def detallesDesarrollos = desarrollos.collect { desarrollo ->
            new DesarrolloDetalle(desarrollo, desarrollador)
        }

        // Transformar investigaciones a InvestigacionDetalle
        def detallesInvestigaciones = investigaciones.collect { investigacion ->
            new InvestigacionDetalle(investigacion, desarrollador)
        }

        // def progresosDesarrollador = ProgresoDesarrollador.findAllByDesarrolladorAndDesarrollo()

        def progresosDesarrollador = ProgresoDesarrollador.findAll {
            desarrollador.id == currentDesarrolladorId &&
            desarrollo in desarrollos
        }

        def puntosProgreso = 0
        progresosDesarrollador.collect { progreso -> 
            puntosProgreso += progreso.completado ? progreso.desarrollo.puntajeOtorgado : 0}

        def items = []
        items.addAll(detallesDesarrollos)
        items.addAll(detallesInvestigaciones)
        items.sort { it.nroOrden }

        render view: "show", model: [puntosProgreso: puntosProgreso, proyecto: proyecto, items: items, desarrolladorRango: desarrollador.rango, desarrolladorId: currentDesarrolladorId, puntosInvestigacion: desarrollador.puntosInvestigacion]
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
