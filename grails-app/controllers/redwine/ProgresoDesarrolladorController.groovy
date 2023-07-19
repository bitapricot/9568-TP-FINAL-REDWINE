package redwine

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProgresoDesarrolladorController {

    ProgresoDesarrolladorService progresoDesarrolladorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond progresoDesarrolladorService.list(params), model:[progresoDesarrolladorCount: progresoDesarrolladorService.count()]
    }

    def show(Long id) {
        respond progresoDesarrolladorService.get(id)
    }

    def create() {
        respond new ProgresoDesarrollador(params)
    }

    def save(ProgresoDesarrollador progresoDesarrollador) {
        if (progresoDesarrollador == null) {
            notFound()
            return
        }

        try {
            progresoDesarrolladorService.save(progresoDesarrollador)
        } catch (ValidationException e) {
            respond progresoDesarrollador.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'progresoDesarrollador.label', default: 'ProgresoDesarrollador'), progresoDesarrollador.id])
                redirect progresoDesarrollador
            }
            '*' { respond progresoDesarrollador, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond progresoDesarrolladorService.get(id)
    }

    def update(ProgresoDesarrollador progresoDesarrollador) {
        if (progresoDesarrollador == null) {
            notFound()
            return
        }

        try {
            progresoDesarrolladorService.save(progresoDesarrollador)
        } catch (ValidationException e) {
            respond progresoDesarrollador.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'progresoDesarrollador.label', default: 'ProgresoDesarrollador'), progresoDesarrollador.id])
                redirect progresoDesarrollador
            }
            '*'{ respond progresoDesarrollador, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        progresoDesarrolladorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'progresoDesarrollador.label', default: 'ProgresoDesarrollador'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'progresoDesarrollador.label', default: 'ProgresoDesarrollador'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
