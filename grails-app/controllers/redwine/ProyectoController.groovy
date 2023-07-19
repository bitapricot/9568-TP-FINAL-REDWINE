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
        respond proyectoService.get(id)
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
