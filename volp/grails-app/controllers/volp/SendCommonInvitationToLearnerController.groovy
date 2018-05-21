package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SendCommonInvitationToLearnerController {

    SendCommonInvitationToLearnerService sendCommonInvitationToLearnerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sendCommonInvitationToLearnerService.list(params), model:[sendCommonInvitationToLearnerCount: sendCommonInvitationToLearnerService.count()]
    }

    def show(Long id) {
        respond sendCommonInvitationToLearnerService.get(id)
    }

    def create() {
        respond new SendCommonInvitationToLearner(params)
    }

    def save(SendCommonInvitationToLearner sendCommonInvitationToLearner) {
        if (sendCommonInvitationToLearner == null) {
            notFound()
            return
        }

        try {
            sendCommonInvitationToLearnerService.save(sendCommonInvitationToLearner)
        } catch (ValidationException e) {
            respond sendCommonInvitationToLearner.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sendCommonInvitationToLearner.label', default: 'SendCommonInvitationToLearner'), sendCommonInvitationToLearner.id])
                redirect sendCommonInvitationToLearner
            }
            '*' { respond sendCommonInvitationToLearner, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sendCommonInvitationToLearnerService.get(id)
    }

    def update(SendCommonInvitationToLearner sendCommonInvitationToLearner) {
        if (sendCommonInvitationToLearner == null) {
            notFound()
            return
        }

        try {
            sendCommonInvitationToLearnerService.save(sendCommonInvitationToLearner)
        } catch (ValidationException e) {
            respond sendCommonInvitationToLearner.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sendCommonInvitationToLearner.label', default: 'SendCommonInvitationToLearner'), sendCommonInvitationToLearner.id])
                redirect sendCommonInvitationToLearner
            }
            '*'{ respond sendCommonInvitationToLearner, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sendCommonInvitationToLearnerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sendCommonInvitationToLearner.label', default: 'SendCommonInvitationToLearner'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sendCommonInvitationToLearner.label', default: 'SendCommonInvitationToLearner'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
