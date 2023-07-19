package redwine

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PistaController {

    PistaService pistaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond pistaService.list(params), model:[pistaCount: pistaService.count()]
    }

    def show(Long id) {
        respond pistaService.get(id)
    }

    def create() {
        respond new Pista(params)
    }

    def save(Pista pista) {
        if (pista == null) {
            notFound()
            return
        }

        try {
            pistaService.save(pista)
        } catch (ValidationException e) {
            respond pista.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pista.label', default: 'Pista'), pista.id])
                redirect pista
            }
            '*' { respond pista, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond pistaService.get(id)
    }

    def update(Pista pista) {
        if (pista == null) {
            notFound()
            return
        }

        try {
            pistaService.save(pista)
        } catch (ValidationException e) {
            respond pista.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pista.label', default: 'Pista'), pista.id])
                redirect pista
            }
            '*'{ respond pista, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        pistaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pista.label', default: 'Pista'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pista.label', default: 'Pista'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
