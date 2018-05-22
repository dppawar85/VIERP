package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FeedbackGivenTypeController {

    FeedbackGivenTypeService feedbackGivenTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond feedbackGivenTypeService.list(params), model:[feedbackGivenTypeCount: feedbackGivenTypeService.count()]
    }

    def show(Long id) {
        respond feedbackGivenTypeService.get(id)
    }

    def create() {
        respond new FeedbackGivenType(params)
    }

    def save(FeedbackGivenType feedbackGivenType) {
        if (feedbackGivenType == null) {
            notFound()
            return
        }

        try {
            feedbackGivenTypeService.save(feedbackGivenType)
        } catch (ValidationException e) {
            respond feedbackGivenType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'feedbackGivenType.label', default: 'FeedbackGivenType'), feedbackGivenType.id])
                redirect feedbackGivenType
            }
            '*' { respond feedbackGivenType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond feedbackGivenTypeService.get(id)
    }

    def update(FeedbackGivenType feedbackGivenType) {
        if (feedbackGivenType == null) {
            notFound()
            return
        }

        try {
            feedbackGivenTypeService.save(feedbackGivenType)
        } catch (ValidationException e) {
            respond feedbackGivenType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'feedbackGivenType.label', default: 'FeedbackGivenType'), feedbackGivenType.id])
                redirect feedbackGivenType
            }
            '*'{ respond feedbackGivenType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        feedbackGivenTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'feedbackGivenType.label', default: 'FeedbackGivenType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedbackGivenType.label', default: 'FeedbackGivenType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
