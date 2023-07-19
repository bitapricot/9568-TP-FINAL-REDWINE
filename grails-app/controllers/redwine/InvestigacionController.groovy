package redwine

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
        respond investigacionService.get(id)
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
