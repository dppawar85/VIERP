package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseOfferingLearnerController {

    CourseOfferingLearnerService courseOfferingLearnerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseOfferingLearnerService.list(params), model:[courseOfferingLearnerCount: courseOfferingLearnerService.count()]
    }

    def show(Long id) {
        respond courseOfferingLearnerService.get(id)
    }

    def create() {
        respond new CourseOfferingLearner(params)
    }

    def save(CourseOfferingLearner courseOfferingLearner) {
        if (courseOfferingLearner == null) {
            notFound()
            return
        }

        try {
            courseOfferingLearnerService.save(courseOfferingLearner)
        } catch (ValidationException e) {
            respond courseOfferingLearner.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseOfferingLearner.label', default: 'CourseOfferingLearner'), courseOfferingLearner.id])
                redirect courseOfferingLearner
            }
            '*' { respond courseOfferingLearner, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseOfferingLearnerService.get(id)
    }

    def update(CourseOfferingLearner courseOfferingLearner) {
        if (courseOfferingLearner == null) {
            notFound()
            return
        }

        try {
            courseOfferingLearnerService.save(courseOfferingLearner)
        } catch (ValidationException e) {
            respond courseOfferingLearner.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseOfferingLearner.label', default: 'CourseOfferingLearner'), courseOfferingLearner.id])
                redirect courseOfferingLearner
            }
            '*'{ respond courseOfferingLearner, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseOfferingLearnerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseOfferingLearner.label', default: 'CourseOfferingLearner'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseOfferingLearner.label', default: 'CourseOfferingLearner'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def studentInfo(){
        println("in studentInfo params:"+params)
        println("in studentInfo session:"+session)
        CourseOffering coff = CourseOffering.findById(params.coffrid)
        def coffrleraner = CourseOfferingLearner.findAllByCourseoffering(coff)
        println("coffrleraner:"+coffrleraner)
        [coffrleraner:coffrleraner]
    }
    def studentDelete(){
        println("in studentDelete params:"+params)
        def cofflearnerid = CourseOfferingLearner.findById(params.cofflearnerid)
        println("coffs id:"+cofflearnerid)
        cofflearnerid.delete(flush:true)

       // def coffrleraner = CourseOfferingLearner.findAllByCourseoffering(coff)
       // println("coffrleraner:"+coffrleraner)
       // [coffrleraner:coffrleraner]
    }
}
