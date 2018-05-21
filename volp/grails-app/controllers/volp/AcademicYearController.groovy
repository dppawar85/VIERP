package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AcademicYearController {

    AcademicYearService academicYearService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond academicYearService.list(params), model:[academicYearCount: academicYearService.count()]
    }

    def show(Long id) {
        respond academicYearService.get(id)
    }

    def create() {
        respond new AcademicYear(params)
    }

    def save(AcademicYear academicYear) {
        if (academicYear == null) {
            notFound()
            return
        }

        try {
            academicYearService.save(academicYear)
        } catch (ValidationException e) {
            respond academicYear.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'academicYear.label', default: 'AcademicYear'), academicYear.id])
                redirect academicYear
            }
            '*' { respond academicYear, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond academicYearService.get(id)
    }

    def update(AcademicYear academicYear) {
        if (academicYear == null) {
            notFound()
            return
        }

        try {
            academicYearService.save(academicYear)
        } catch (ValidationException e) {
            respond academicYear.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'academicYear.label', default: 'AcademicYear'), academicYear.id])
                redirect academicYear
            }
            '*'{ respond academicYear, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        academicYearService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'academicYear.label', default: 'AcademicYear'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'academicYear.label', default: 'AcademicYear'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
