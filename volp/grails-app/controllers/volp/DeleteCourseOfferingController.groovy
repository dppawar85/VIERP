package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DeleteCourseOfferingController {

    DeleteCourseOfferingService deleteCourseOfferingService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond deleteCourseOfferingService.list(params), model:[deleteCourseOfferingCount: deleteCourseOfferingService.count()]
    }

    def show(Long id) {
        respond deleteCourseOfferingService.get(id)
    }

    def create() {
        respond new DeleteCourseOffering(params)
    }

    def save(DeleteCourseOffering deleteCourseOffering) {
        if (deleteCourseOffering == null) {
            notFound()
            return
        }

        try {
            deleteCourseOfferingService.save(deleteCourseOffering)
        } catch (ValidationException e) {
            respond deleteCourseOffering.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'deleteCourseOffering.label', default: 'DeleteCourseOffering'), deleteCourseOffering.id])
                redirect deleteCourseOffering
            }
            '*' { respond deleteCourseOffering, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond deleteCourseOfferingService.get(id)
    }

    def update(DeleteCourseOffering deleteCourseOffering) {
        if (deleteCourseOffering == null) {
            notFound()
            return
        }

        try {
            deleteCourseOfferingService.save(deleteCourseOffering)
        } catch (ValidationException e) {
            respond deleteCourseOffering.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'deleteCourseOffering.label', default: 'DeleteCourseOffering'), deleteCourseOffering.id])
                redirect deleteCourseOffering
            }
            '*'{ respond deleteCourseOffering, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        deleteCourseOfferingService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'deleteCourseOffering.label', default: 'DeleteCourseOffering'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'deleteCourseOffering.label', default: 'DeleteCourseOffering'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def viewrequesttodeletecourse()
    {

        println("Visiting viewrequesttodeletecourse")
        println("Session"+session)
        def listcourse=DeleteCourseOffering.findAll()
        println("listcourse"+listcourse.courseoffering.course.course_name)
        println("listcourse"+listcourse.courseoffering.id)
        println("listcourse"+listcourse.instructor.id)
        println("listcourse"+listcourse.organization.id)
        //coff.delete(flush , true)
        [listcourse:listcourse]
    }
    def deleteCourseOffering(){
        println("Visiting deleteCourseOffering")
        println("Params"+params)
        CourseOffering coff=CourseOffering.findById(params.coffrid)
        if(coff.isDeleted==true)
        {
            render "Already Deleted"
        }
        else
        {
            coff.isDeleted=true
            render "Sucessfully Deleted"
            println("Coff "+coff.isDeleted)
            coff.save(flush:true,failOnError:true)
        }
    }
}
