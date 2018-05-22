package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PlatformFeedbackController {

    PlatformFeedbackService platformFeedbackService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond platformFeedbackService.list(params), model:[platformFeedbackCount: platformFeedbackService.count()]
    }

    def show(Long id) {
        respond platformFeedbackService.get(id)
    }

    def create() {
        respond new PlatformFeedback(params)
    }

    def save(PlatformFeedback platformFeedback) {
        if (platformFeedback == null) {
            notFound()
            return
        }

        try {
            platformFeedbackService.save(platformFeedback)
        } catch (ValidationException e) {
            respond platformFeedback.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'platformFeedback.label', default: 'PlatformFeedback'), platformFeedback.id])
                redirect platformFeedback
            }
            '*' { respond platformFeedback, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond platformFeedbackService.get(id)
    }

    def update(PlatformFeedback platformFeedback) {
        if (platformFeedback == null) {
            notFound()
            return
        }

        try {
            platformFeedbackService.save(platformFeedback)
        } catch (ValidationException e) {
            respond platformFeedback.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'platformFeedback.label', default: 'PlatformFeedback'), platformFeedback.id])
                redirect platformFeedback
            }
            '*'{ respond platformFeedback, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        platformFeedbackService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'platformFeedback.label', default: 'PlatformFeedback'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'platformFeedback.label', default: 'PlatformFeedback'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def givenPlatformFeedback()
    {
        println(" I am in givePlatformFeedback params:"+params);
        println(" I am in givePlatformFeedback session:"+session);
        [type:params.type]
    }
    def storePlatformFeedback()
    {
        println(" I am in storePlatformFeedback params:"+params);
        flash.message="Thank you for your valuable Feedback!!!"
        FeedbackGivenType type=FeedbackGivenType.findByType(params.type)
        int uid
        if(params.type=="Instructor")
        {
          uid = Instructor.findByUid(session.uid).id
        }
        if(params.type=="Learner")
        {
            uid = Learner.findByUid(session.uid).id
        }
        PlatformFeedback pff=PlatformFeedback.findByUseridAndFeedbackgiventype(uid,type)
        if(pff!=null)
        {
            pff.username=session.uid
           // pff.creation_date=new java.util.Date()
            pff.updation_date=new java.util.Date()
            //pff.creation_ip_address=request.getRemoteAddr()
            pff.updation_ip_address=request.getRemoteAddr()
            pff.description=params.description
            //pff.type=params.type
            //pff.userId=uid
            pff.save(failOnError:true,flush:true)
        }
        else {
            pff = new PlatformFeedback()
            pff.username = session.uid
            pff.creation_date = new java.util.Date()
            pff.updation_date = new java.util.Date()
            pff.creation_ip_address = request.getRemoteAddr()
            pff.updation_ip_address = request.getRemoteAddr()
            pff.description = params.description
            pff.feedbackgiventype = type
            pff.userid = uid
            pff.save(failOnError: true, flush: true)
        }
        if(params.type=="Instructor")
            redirect(controller: "instructor", action: "instructor")
        if(params.type=="Learner")
        redirect(controller: "learner", action: "learner")
    }
}
