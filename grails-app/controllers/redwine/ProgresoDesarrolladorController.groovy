package redwine

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RestController

import groovy.json.JsonSlurper

import groovy.json.JsonOutput

class ProgresoDesarrolladorController {

    ProgresoDesarrolladorService progresoDesarrolladorService
    DesarrolloService desarrolloService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @PostMapping("/progreso-desarrollador/ejecutar-codigo")
    def ejecutarPruebasAutomatizadas() {
        try {
            // Parseamos el request, habría que obtener además: id de desarrollador & id de desarrollo 
            def requestBody = request.getInputStream().text
            def jsonSlurper = new JsonSlurper()
            def jsonObject = jsonSlurper.parseText(requestBody)
            
            def codigo = jsonObject.codigo
            def desarrolladorId = jsonObject.desarrolladorId
            def desarrolloId = jsonObject.desarrolloId

            def codigoDesarrollador = new CodigoDesarrollador(codigo, desarrolladorId)

            def resultado = desarrolloService.ejecutarPruebasAutomatizadasPorDesarrolloId(codigoDesarrollador, desarrolloId)
            
            // TODO: implementar este método
            progresoDesarrolladorService.actualizarProgresoPorResultadoDesarrollo(resultado)

            // TODO: serializar el resultado para su manejo desde el front
            render JsonOutput.toJson(resultado)
        } catch (Exception e) {
            render JsonOutput.toJson([error: e.message])
        }
    }

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
