package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import java.text.SimpleDateFormat
class CourseCertificateController {

    CourseCertificateService courseCertificateService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseCertificateService.list(params), model:[courseCertificateCount: courseCertificateService.count()]
    }

    def show(Long id) {
        respond courseCertificateService.get(id)
    }

    def create() {
        respond new CourseCertificate(params)
    }

    def save(CourseCertificate courseCertificate) {
        if (courseCertificate == null) {
            notFound()
            return
        }

        try {
            courseCertificateService.save(courseCertificate)
        } catch (ValidationException e) {
            respond courseCertificate.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseCertificate.label', default: 'CourseCertificate'), courseCertificate.id])
                redirect courseCertificate
            }
            '*' { respond courseCertificate, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseCertificateService.get(id)
    }

    def update(CourseCertificate courseCertificate) {
        if (courseCertificate == null) {
            notFound()
            return
        }

        try {
            courseCertificateService.save(courseCertificate)
        } catch (ValidationException e) {
            respond courseCertificate.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseCertificate.label', default: 'CourseCertificate'), courseCertificate.id])
                redirect courseCertificate
            }
            '*'{ respond courseCertificate, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseCertificateService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseCertificate.label', default: 'CourseCertificate'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseCertificate.label', default: 'CourseCertificate'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def getcertificate()
    {
        println("I am in getcertificate::"+params)
        CourseOfferingLearner coffrlearner=CourseOfferingLearner.findById(params.coffrlearner)
        println("coffrlearner::"+coffrlearner)
        InitialData id=InitialData.findByName("certificate_number")
        CourseCertificate cc=CourseCertificate.findByCourseofferinglearner(coffrlearner)
        if(cc==null)
        {
            cc=new CourseCertificate()
            cc.certificate_date=new java.util.Date()
            cc.certificate_number="VOLP"+id.number
            if(coffrlearner.courseoffering.courseofferingtype.name.equals("Weekwise"))
            {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy")
                String period=sdf.format(coffrlearner.courseoffering.start_date)+" to "+sdf.format(coffrlearner.courseoffering.end_date)
                println("period::"+period)
                cc.period=period
                def week=Week.findAllByCourseoffering(coffrlearner.courseoffering)
                if(week.size()!=0)
                    cc.number_of_weeks=week.size()
            }
            else if(coffrlearner.courseoffering.courseofferingtype.name.equals("Self-Pace"))
            {
                def cv=CourseVideos.findAllByCourse(coffrlearner.courseoffering.course)
                int duration_in_minutes=0
                for(CourseVideos c:cv)
                {
                    duration_in_minutes=duration_in_minutes+c.duration_in_minutes
                }
                double temp=duration_in_minutes/60.0
                cc.number_of_hours=(int)Math.round(temp)*10
            }
            cc.courseofferinglearner=coffrlearner
            cc.save(flush: true,failOnError: true)
            //Increment certificate value by 1
            id.number=id.number+1
            id.save(flush: true,failOnError: true)
            println("Certificate Generated")
        }
        else
            println("Certificate Already Genereated....")
        String coursetype=""
        if(cc.courseofferinglearner.courseoffering.courseofferingtype.name.equals("Weekwise"))
        {
            coursetype="Weekwise"
        }
        else if (cc.courseofferinglearner.courseoffering.courseofferingtype.name.equals("Self-Pace"))
        {
            coursetype="Self-Pace"
        }
        [cc:cc,coursetype:coursetype]
    }
}
