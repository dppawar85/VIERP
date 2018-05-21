package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseRatingController {

    CourseRatingService courseRatingService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseRatingService.list(params), model:[courseRatingCount: courseRatingService.count()]
    }

    def show(Long id) {
        respond courseRatingService.get(id)
    }

    def create() {
        respond new CourseRating(params)
    }

    def save(CourseRating courseRating) {
        if (courseRating == null) {
            notFound()
            return
        }

        try {
            courseRatingService.save(courseRating)
        } catch (ValidationException e) {
            respond courseRating.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseRating.label', default: 'CourseRating'), courseRating.id])
                redirect courseRating
            }
            '*' { respond courseRating, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseRatingService.get(id)
    }

    def update(CourseRating courseRating) {
        if (courseRating == null) {
            notFound()
            return
        }

        try {
            courseRatingService.save(courseRating)
        } catch (ValidationException e) {
            respond courseRating.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseRating.label', default: 'CourseRating'), courseRating.id])
                redirect courseRating
            }
            '*'{ respond courseRating, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseRatingService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseRating.label', default: 'CourseRating'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseRating.label', default: 'CourseRating'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
