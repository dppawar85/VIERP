package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseVisibilityController {

    CourseVisibilityService courseVisibilityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseVisibilityService.list(params), model:[courseVisibilityCount: courseVisibilityService.count()]
    }

    def show(Long id) {
        respond courseVisibilityService.get(id)
    }

    def create() {
        respond new CourseVisibility(params)
    }

    def save(CourseVisibility courseVisibility) {
        if (courseVisibility == null) {
            notFound()
            return
        }

        try {
            courseVisibilityService.save(courseVisibility)
        } catch (ValidationException e) {
            respond courseVisibility.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseVisibility.label', default: 'CourseVisibility'), courseVisibility.id])
                redirect courseVisibility
            }
            '*' { respond courseVisibility, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseVisibilityService.get(id)
    }

    def update(CourseVisibility courseVisibility) {
        if (courseVisibility == null) {
            notFound()
            return
        }

        try {
            courseVisibilityService.save(courseVisibility)
        } catch (ValidationException e) {
            respond courseVisibility.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseVisibility.label', default: 'CourseVisibility'), courseVisibility.id])
                redirect courseVisibility
            }
            '*'{ respond courseVisibility, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseVisibilityService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseVisibility.label', default: 'CourseVisibility'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseVisibility.label', default: 'CourseVisibility'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
