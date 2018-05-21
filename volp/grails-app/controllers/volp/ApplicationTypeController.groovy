package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ApplicationTypeController {

    ApplicationTypeService applicationTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond applicationTypeService.list(params), model:[applicationTypeCount: applicationTypeService.count()]
    }

    def show(Long id) {
        respond applicationTypeService.get(id)
    }

    def create() {
        respond new ApplicationType(params)
    }

    def save(ApplicationType applicationType) {
        if (applicationType == null) {
            notFound()
            return
        }

        try {
            applicationTypeService.save(applicationType)
        } catch (ValidationException e) {
            respond applicationType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), applicationType.id])
                redirect applicationType
            }
            '*' { respond applicationType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond applicationTypeService.get(id)
    }

    def update(ApplicationType applicationType) {
        if (applicationType == null) {
            notFound()
            return
        }

        try {
            applicationTypeService.save(applicationType)
        } catch (ValidationException e) {
            respond applicationType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), applicationType.id])
                redirect applicationType
            }
            '*'{ respond applicationType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        applicationTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
