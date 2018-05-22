package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AssignmentTypeController {

    AssignmentTypeService assignmentTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond assignmentTypeService.list(params), model:[assignmentTypeCount: assignmentTypeService.count()]
    }

    def show(Long id) {
        respond assignmentTypeService.get(id)
    }

    def create() {
        respond new AssignmentType(params)
    }

    def save(AssignmentType assignmentType) {
        if (assignmentType == null) {
            notFound()
            return
        }

        try {
            assignmentTypeService.save(assignmentType)
        } catch (ValidationException e) {
            respond assignmentType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'assignmentType.label', default: 'AssignmentType'), assignmentType.id])
                redirect assignmentType
            }
            '*' { respond assignmentType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond assignmentTypeService.get(id)
    }

    def update(AssignmentType assignmentType) {
        if (assignmentType == null) {
            notFound()
            return
        }

        try {
            assignmentTypeService.save(assignmentType)
        } catch (ValidationException e) {
            respond assignmentType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'assignmentType.label', default: 'AssignmentType'), assignmentType.id])
                redirect assignmentType
            }
            '*'{ respond assignmentType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        assignmentTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'assignmentType.label', default: 'AssignmentType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignmentType.label', default: 'AssignmentType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
