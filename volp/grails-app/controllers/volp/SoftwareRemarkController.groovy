package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SoftwareRemarkController {

    SoftwareRemarkService softwareRemarkService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond softwareRemarkService.list(params), model:[softwareRemarkCount: softwareRemarkService.count()]
    }

    def show(Long id) {
        respond softwareRemarkService.get(id)
    }

    def create() {
        respond new SoftwareRemark(params)
    }

    def save(SoftwareRemark softwareRemark) {
        if (softwareRemark == null) {
            notFound()
            return
        }

        try {
            softwareRemarkService.save(softwareRemark)
        } catch (ValidationException e) {
            respond softwareRemark.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'softwareRemark.label', default: 'SoftwareRemark'), softwareRemark.id])
                redirect softwareRemark
            }
            '*' { respond softwareRemark, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond softwareRemarkService.get(id)
    }

    def update(SoftwareRemark softwareRemark) {
        if (softwareRemark == null) {
            notFound()
            return
        }

        try {
            softwareRemarkService.save(softwareRemark)
        } catch (ValidationException e) {
            respond softwareRemark.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'softwareRemark.label', default: 'SoftwareRemark'), softwareRemark.id])
                redirect softwareRemark
            }
            '*'{ respond softwareRemark, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        softwareRemarkService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'softwareRemark.label', default: 'SoftwareRemark'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'softwareRemark.label', default: 'SoftwareRemark'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
