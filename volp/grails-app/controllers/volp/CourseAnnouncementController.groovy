package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseAnnouncementController {

    CourseAnnouncementService courseAnnouncementService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseAnnouncementService.list(params), model:[courseAnnouncementCount: courseAnnouncementService.count()]
    }

    def show(Long id) {
        respond courseAnnouncementService.get(id)
    }

    def create() {
        respond new CourseAnnouncement(params)
    }

    def save(CourseAnnouncement courseAnnouncement) {
        if (courseAnnouncement == null) {
            notFound()
            return
        }

        try {
            courseAnnouncementService.save(courseAnnouncement)
        } catch (ValidationException e) {
            respond courseAnnouncement.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseAnnouncement.label', default: 'CourseAnnouncement'), courseAnnouncement.id])
                redirect courseAnnouncement
            }
            '*' { respond courseAnnouncement, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseAnnouncementService.get(id)
    }

    def update(CourseAnnouncement courseAnnouncement) {
        if (courseAnnouncement == null) {
            notFound()
            return
        }

        try {
            courseAnnouncementService.save(courseAnnouncement)
        } catch (ValidationException e) {
            respond courseAnnouncement.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseAnnouncement.label', default: 'CourseAnnouncement'), courseAnnouncement.id])
                redirect courseAnnouncement
            }
            '*'{ respond courseAnnouncement, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseAnnouncementService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseAnnouncement.label', default: 'CourseAnnouncement'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseAnnouncement.label', default: 'CourseAnnouncement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def setCourseAnnouncement(){
        println("in setCourseAnnouncement params:"+params)
        CourseOffering coffr = CourseOffering.findById(params.coffrid)
        def ca = CourseAnnouncement.findAllByCourseoffering(coffr)
        [coffr:coffr,ca:ca]

    }
    def processCourseAnnouncement(){
        println("in processCourseAnnouncement params:"+params)
        println("date:"+params.myDate)
        CourseOffering coffr = CourseOffering.findById(params.coffrid)
        CourseAnnouncement ca = new CourseAnnouncement()
        ca.notice = params.notice
        ca.schedule_date =params.myDate
        ca.username = session.uid
        ca.creation_date = new java.util.Date()
        ca.updation_date = new java.util.Date()
        ca.creation_ip_address = request.getRemoteAddr()
        ca.updation_ip_address = request.getRemoteAddr()
        ca.courseoffering = coffr
        ca.save(flush: true,failOnError:true)
        flash.message="Updated Successfully"
        //CourseOffering coffr = CourseOffering.findById(params.coffrid)
        def ca1 = CourseAnnouncement.findAllByCourseoffering(coffr)
        [coffr:coffr,ca:ca1]
    }

    def editAnnouncement()
    {
        println("in editAnnouncement params:"+params)
        CourseOffering coffr = CourseOffering.findById(params.coffrid)
        CourseAnnouncement ca = CourseAnnouncement.findById(params.caid)
        [ca:ca,coffr:coffr]
    }
    def saveEditAnnouncement(){
        println("in saveEditAnnouncement params..:"+params)
        CourseOffering coffr = CourseOffering.findById(params.coffrid)
        CourseAnnouncement ca = CourseAnnouncement.findById(params.caid)
        ca.notice = params.notice
        ca.schedule_date = params.myDate
        ca.username = session.uid
        // ca.creation_date = new java.util.Date()
        ca.updation_date = new java.util.Date()
        // ca.creation_ip_address = request.getRemoteAddr()
        ca.updation_ip_address = request.getRemoteAddr()
        ca.save(flush: true,failOnError:true)
        flash.message="Updated Successfully"
        //String msg = "Updated Successfully"
        //println("msg:"+msg)
        //CourseOffering coffr = CourseOffering.findById(params.coffrid)
        def ca1 = CourseAnnouncement.findAllByCourseoffering(coffr)
        [coffr:coffr,ca:ca1]
    }
    def getAnnouncement(){
        println("in courseAnnouncement:"+params)
        CourseOffering co = CourseOffering.findById(params.coffrid)
        def ca = CourseAnnouncement.findAllByCourseoffering(co)
        [ca:ca]
    }

}
