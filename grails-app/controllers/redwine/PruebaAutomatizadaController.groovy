package redwine

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PruebaAutomatizadaController {

    PruebaAutomatizadaService pruebaAutomatizadaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond pruebaAutomatizadaService.list(params), model:[pruebaAutomatizadaCount: pruebaAutomatizadaService.count()]
    }

    def show(Long id) {
        respond pruebaAutomatizadaService.get(id)
    }

    def create() {
        respond new PruebaAutomatizada(params)
    }

    def save(PruebaAutomatizada pruebaAutomatizada) {
        if (pruebaAutomatizada == null) {
            notFound()
            return
        }

        try {
            pruebaAutomatizadaService.save(pruebaAutomatizada)
        } catch (ValidationException e) {
            respond pruebaAutomatizada.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pruebaAutomatizada.label', default: 'PruebaAutomatizada'), pruebaAutomatizada.id])
                redirect pruebaAutomatizada
            }
            '*' { respond pruebaAutomatizada, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond pruebaAutomatizadaService.get(id)
    }

    def update(PruebaAutomatizada pruebaAutomatizada) {
        if (pruebaAutomatizada == null) {
            notFound()
            return
        }

        try {
            pruebaAutomatizadaService.save(pruebaAutomatizada)
        } catch (ValidationException e) {
            respond pruebaAutomatizada.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pruebaAutomatizada.label', default: 'PruebaAutomatizada'), pruebaAutomatizada.id])
                redirect pruebaAutomatizada
            }
            '*'{ respond pruebaAutomatizada, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        pruebaAutomatizadaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pruebaAutomatizada.label', default: 'PruebaAutomatizada'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pruebaAutomatizada.label', default: 'PruebaAutomatizada'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
