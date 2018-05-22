package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EnquiryController {

    EnquiryService enquiryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond enquiryService.list(params), model:[enquiryCount: enquiryService.count()]
    }

    def show(Long id) {
        respond enquiryService.get(id)
    }

    def create() {
        respond new Enquiry(params)
    }

    def save(Enquiry enquiry) {
        if (enquiry == null) {
            notFound()
            return
        }

        try {
            enquiryService.save(enquiry)
        } catch (ValidationException e) {
            respond enquiry.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'enquiry.label', default: 'Enquiry'), enquiry.id])
                redirect enquiry
            }
            '*' { respond enquiry, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond enquiryService.get(id)
    }

    def update(Enquiry enquiry) {
        if (enquiry == null) {
            notFound()
            return
        }

        try {
            enquiryService.save(enquiry)
        } catch (ValidationException e) {
            respond enquiry.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'enquiry.label', default: 'Enquiry'), enquiry.id])
                redirect enquiry
            }
            '*'{ respond enquiry, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        enquiryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'enquiry.label', default: 'Enquiry'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'enquiry.label', default: 'Enquiry'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def saveContactDetails(){
        println("in saveContactDetails:"+params)
    }
}
