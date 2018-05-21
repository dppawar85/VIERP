package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class InitialDataController {

    InitialDataService initialDataService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond initialDataService.list(params), model:[initialDataCount: initialDataService.count()]
    }

    def show(Long id) {
        respond initialDataService.get(id)
    }

    def create() {
        respond new InitialData(params)
    }

    def save(InitialData initialData) {
        if (initialData == null) {
            notFound()
            return
        }

        try {
            initialDataService.save(initialData)
        } catch (ValidationException e) {
            respond initialData.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'initialData.label', default: 'InitialData'), initialData.id])
                redirect initialData
            }
            '*' { respond initialData, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond initialDataService.get(id)
    }

    def update(InitialData initialData) {
        if (initialData == null) {
            notFound()
            return
        }

        try {
            initialDataService.save(initialData)
        } catch (ValidationException e) {
            respond initialData.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'initialData.label', default: 'InitialData'), initialData.id])
                redirect initialData
            }
            '*'{ respond initialData, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        initialDataService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'initialData.label', default: 'InitialData'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'initialData.label', default: 'InitialData'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
