package redwine

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DesarrolladorController {

    DesarrolladorService desarrolladorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond desarrolladorService.list(params), model:[desarrolladorCount: desarrolladorService.count()]
    }

    def show(Long id) {
        respond desarrolladorService.get(id)
    }

    def create() {
        respond new Desarrollador(params)
    }

    def save(Desarrollador desarrollador) {
        if (desarrollador == null) {
            notFound()
            return
        }

        try {
            desarrolladorService.save(desarrollador)
        } catch (ValidationException e) {
            respond desarrollador.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'desarrollador.label', default: 'Desarrollador'), desarrollador.id])
                redirect desarrollador
            }
            '*' { respond desarrollador, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond desarrolladorService.get(id)
    }

    def update(Desarrollador desarrollador) {
        if (desarrollador == null) {
            notFound()
            return
        }

        try {
            desarrolladorService.save(desarrollador)
        } catch (ValidationException e) {
            respond desarrollador.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'desarrollador.label', default: 'Desarrollador'), desarrollador.id])
                redirect desarrollador
            }
            '*'{ respond desarrollador, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        desarrolladorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'desarrollador.label', default: 'Desarrollador'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'desarrollador.label', default: 'Desarrollador'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
