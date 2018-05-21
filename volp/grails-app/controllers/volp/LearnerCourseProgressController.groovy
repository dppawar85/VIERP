package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class LearnerCourseProgressController {

    LearnerCourseProgressService learnerCourseProgressService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond learnerCourseProgressService.list(params), model:[learnerCourseProgressCount: learnerCourseProgressService.count()]
    }

    def show(Long id) {
        respond learnerCourseProgressService.get(id)
    }

    def create() {
        respond new LearnerCourseProgress(params)
    }

    def save(LearnerCourseProgress learnerCourseProgress) {
        if (learnerCourseProgress == null) {
            notFound()
            return
        }

        try {
            learnerCourseProgressService.save(learnerCourseProgress)
        } catch (ValidationException e) {
            respond learnerCourseProgress.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'learnerCourseProgress.label', default: 'LearnerCourseProgress'), learnerCourseProgress.id])
                redirect learnerCourseProgress
            }
            '*' { respond learnerCourseProgress, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond learnerCourseProgressService.get(id)
    }

    def update(LearnerCourseProgress learnerCourseProgress) {
        if (learnerCourseProgress == null) {
            notFound()
            return
        }

        try {
            learnerCourseProgressService.save(learnerCourseProgress)
        } catch (ValidationException e) {
            respond learnerCourseProgress.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'learnerCourseProgress.label', default: 'LearnerCourseProgress'), learnerCourseProgress.id])
                redirect learnerCourseProgress
            }
            '*'{ respond learnerCourseProgress, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        learnerCourseProgressService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'learnerCourseProgress.label', default: 'LearnerCourseProgress'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'learnerCourseProgress.label', default: 'LearnerCourseProgress'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def learnerProgress()
    {
        println("learner Progress params"+ params)
        println("learner Progress params"+session)
        CourseOfferingLearner crsLearner = CourseOfferingLearner.findById(params.regCrs)
        println("crsLearner" +crsLearner)
        CourseVideos crsVideo = CourseVideos.findById(params.videoId)
        println("crsVideo"+crsVideo.id)
        def allVideo = LearnerCourseProgress.findByCourseofferinglearnerAndCoursevideos(crsLearner,crsVideo)
        allVideo.isViewed = true
        allVideo.save(flush:true,failOnError:true)
        def completeVideo = LearnerCourseProgress.findAllByCourseofferinglearnerAndIsViewed(crsLearner,true)
        def incompleteVideo = LearnerCourseProgress.findAllByCourseofferinglearnerAndIsViewed(crsLearner,false)
        int incompletevideo = incompleteVideo.size
        int completevideo = completeVideo.size
        int total = incompletevideo+completevideo
        println("incompleteVideo"+incompleteVideo)
        println("completeVideo"+completeVideo)
        println("incompletevideo count"+incompleteVideo.size)
        println("completevideo count"+completeVideo.size)
        println("allViedo"+allVideo.id)
        println("total"+total)
        println("allVideo test.....0"+completevideo)
        session.prog = completevideo
        [total:total,completevideo:completevideo]
    }
}
