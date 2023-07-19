package redwine

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class RangoController {

    RangoService rangoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond rangoService.list(params), model:[rangoCount: rangoService.count()]
    }

    def show(Long id) {
        respond rangoService.get(id)
    }

    def create() {
        respond new Rango(params)
    }

    def save(Rango rango) {
        if (rango == null) {
            notFound()
            return
        }

        try {
            rangoService.save(rango)
        } catch (ValidationException e) {
            respond rango.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rango.label', default: 'Rango'), rango.id])
                redirect rango
            }
            '*' { respond rango, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond rangoService.get(id)
    }

    def update(Rango rango) {
        if (rango == null) {
            notFound()
            return
        }

        try {
            rangoService.save(rango)
        } catch (ValidationException e) {
            respond rango.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'rango.label', default: 'Rango'), rango.id])
                redirect rango
            }
            '*'{ respond rango, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        rangoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'rango.label', default: 'Rango'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rango.label', default: 'Rango'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
