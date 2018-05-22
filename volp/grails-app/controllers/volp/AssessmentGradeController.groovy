package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AssessmentGradeController {

    AssessmentGradeService assessmentGradeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond assessmentGradeService.list(params), model:[assessmentGradeCount: assessmentGradeService.count()]
    }

    def show(Long id) {
        respond assessmentGradeService.get(id)
    }

    def create() {
        respond new AssessmentGrade(params)
    }

    def save(AssessmentGrade assessmentGrade) {
        if (assessmentGrade == null) {
            notFound()
            return
        }

        try {
            assessmentGradeService.save(assessmentGrade)
        } catch (ValidationException e) {
            respond assessmentGrade.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'assessmentGrade.label', default: 'AssessmentGrade'), assessmentGrade.id])
                redirect assessmentGrade
            }
            '*' { respond assessmentGrade, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond assessmentGradeService.get(id)
    }

    def update(AssessmentGrade assessmentGrade) {
        if (assessmentGrade == null) {
            notFound()
            return
        }

        try {
            assessmentGradeService.save(assessmentGrade)
        } catch (ValidationException e) {
            respond assessmentGrade.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'assessmentGrade.label', default: 'AssessmentGrade'), assessmentGrade.id])
                redirect assessmentGrade
            }
            '*'{ respond assessmentGrade, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        assessmentGradeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'assessmentGrade.label', default: 'AssessmentGrade'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'assessmentGrade.label', default: 'AssessmentGrade'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def setGrade()
    {
        println("in setGrade params:"+params)
        println("in setGrade session:"+session)
        CourseOffering coffr = CourseOffering.findById(params.coffr)
        def ag = AssessmentGrade.findAllByCourseoffering(coffr)
        [ag:ag,coffr:coffr]
    }
    def processGrade(){
        println("in processGrade params:"+params)
        println("in processGrade session:"+session)
        CourseOffering coffr = CourseOffering.findById(params.coffr)
        def a = AssessmentGrade.findAllByCourseofferingAndGrade_name(coffr,params.grade)
        println("A:"+a)
        if(a.size()>0){
            def aa = AssessmentGrade.findAllByCourseoffering(coffr)
            [ag:aa,coffr:coffr]
        }
        else {
            AssessmentGrade ag = new AssessmentGrade()
            ag.grade_name = params.grade
            ag.grade_lower_value = Integer.parseInt(params.from)
            ag.grade_higher_value = Integer.parseInt(params.to)
            ag.description = params.desp
            ag.username = session.uid
            ag.creation_date = new java.util.Date()
            ag.updation_date = new java.util.Date()
            ag.creation_ip_address = request.getRemoteAddr()
            ag.updation_ip_address = request.getRemoteAddr()
            ag.courseoffering = coffr
            ag.save(flush: true, failOnError: true)
            def ag1 = AssessmentGrade.findAllByCourseoffering(coffr)
            [ag: ag1, coffr: coffr]
        }
    }
    def editGrade(){
        println("in editGrade params:"+params)
        println("in editGrade session:"+session)
        AssessmentGrade ag = AssessmentGrade.findById(params.ag)
        CourseOffering coffr = CourseOffering.findById(params.coffr)
        [ag:ag,coffr:coffr]
    }
    def processEditGrade(){
        println("in processEditGrade params:"+params)
        println("in processEditGrade session:"+session)
        AssessmentGrade ag = AssessmentGrade.findById(params.ag)
        ag.grade_name = params.grade
        ag.grade_lower_value = Integer.parseInt(params.from)
        ag.grade_higher_value = Integer.parseInt(params.to)
        ag.description = params.desp
        ag.username = session.uid
        ag.creation_date = new java.util.Date()
        ag.updation_date = new java.util.Date()
        ag.creation_ip_address = request.getRemoteAddr()
        ag.updation_ip_address = request.getRemoteAddr()
        //ag.courseoffering = coffr
        ag.save(flush: true,failOnError:true)
        CourseOffering coffr = CourseOffering.findById(params.coffr)
        def ag1 = AssessmentGrade.findAllByCourseoffering(coffr)
        [ag:ag1,coffr:coffr]
    }
    def deleteGrade(){
        println("in deleteGrade:"+params)
        AssessmentGrade ag = AssessmentGrade.findById(params.ag)
        ag.delete(flush: true,failOnError:true)
        CourseOffering coffr = CourseOffering.findById(params.coffr)
        def ag1 = AssessmentGrade.findAllByCourseoffering(coffr)
        [ag:ag1,coffr:coffr]
    }
}
