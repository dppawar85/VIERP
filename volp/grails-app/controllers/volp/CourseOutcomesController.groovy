package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseOutcomesController {

    CourseOutcomesService courseOutcomesService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseOutcomesService.list(params), model:[courseOutcomesCount: courseOutcomesService.count()]
    }

    def show(Long id) {
        respond courseOutcomesService.get(id)
    }

    def create() {
        respond new CourseOutcomes(params)
    }

    def save(CourseOutcomes courseOutcomes) {
        if (courseOutcomes == null) {
            notFound()
            return
        }

        try {
            courseOutcomesService.save(courseOutcomes)
        } catch (ValidationException e) {
            respond courseOutcomes.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseOutcomes.label', default: 'CourseOutcomes'), courseOutcomes.id])
                redirect courseOutcomes
            }
            '*' { respond courseOutcomes, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseOutcomesService.get(id)
    }

    def update(CourseOutcomes courseOutcomes) {
        if (courseOutcomes == null) {
            notFound()
            return
        }

        try {
            courseOutcomesService.save(courseOutcomes)
        } catch (ValidationException e) {
            respond courseOutcomes.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseOutcomes.label', default: 'CourseOutcomes'), courseOutcomes.id])
                redirect courseOutcomes
            }
            '*'{ respond courseOutcomes, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseOutcomesService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseOutcomes.label', default: 'CourseOutcomes'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseOutcomes.label', default: 'CourseOutcomes'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def selectcourse()
    {
        println("I am in selectcourse.."+session.uid)
        String email=session.uid
        Instructor inst=Instructor.findByUid(email)
        println("Instructor:"+inst)
        def courses=Course.findAllByCourseowner(inst)
        ArrayList<String> courselist=new ArrayList<String>();
        for(Course c:courses)
        {
            courselist.add(c.course_code+":"+c.course_name)
        }
        println(courselist)
        [courselist:courselist]
    }
    def addcourseoutcomes()
    {
        println("I am in addcourseoutcomes::"+params.coursecodecoursename)
        String coursecodecoursename=params.coursecodecoursename
        def crs=null
        if(coursecodecoursename.equals(""))
        {
            coursecodecoursename = session.coursecodecoursename
            crs=coursecodecoursename.split(":")
        }
        else
            crs=coursecodecoursename.split(":")
        String coursecode=crs[0]
        String coursename=crs[1]
        String email=session.uid
        session.coursecode=coursecode
        session.coursename=coursename
        session.email=email
        Instructor inst=Instructor.findByUid(email)
        println("ok..."+email+" "+coursecode+" "+coursename+" "+inst)
        Course course=Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)
        def courseoutcomelist=CourseOutcomes.findAllByCourseAndIsDeleted(course,false)
        courseoutcomelist.sort{it.co_code}
        session.coursecodecoursename=coursecodecoursename
        ArrayList<String> aylist=new ArrayList<String>();
        def ay1=AcademicYear.list()
        for(AcademicYear a:ay1)
        {
            aylist.add(a.ay)
        }
        [aylist:aylist,courseoutcomelist:courseoutcomelist,email:email,coursecode:coursecode,coursename:coursename,coursecodecoursename:coursecodecoursename]
    }
    def savecourseoutcomes()
    {
        println("I am in savecourseoutcomes:")
        String coursecodecoursename=session.coursecodecoursename
        println("Hi:"+coursecodecoursename)
        String coursecode=session.coursecode
        String coursename=session.coursename
        String email=session.uid
        String ay=params.ay
        session.ay=ay
        Instructor inst=Instructor.findByUid(email)
        Course course=Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)

        String outcome_number=params.outcome_number
        outcome_number = outcome_number.replaceAll("\\s+","")   //remove all whitespaces
        String outcome_statement=params.outcome_statement
        AcademicYear ayobj=AcademicYear.findByAy(ay)
        CourseOutcomes courseoutcomes=new CourseOutcomes()
        courseoutcomes.co_code=Integer.parseInt(outcome_number)
        courseoutcomes.co_statement=outcome_statement
        courseoutcomes.isCurrent=true
        courseoutcomes.revisionyear=ayobj
        courseoutcomes.username=email
        courseoutcomes.creation_date=new java.util.Date()
        courseoutcomes.updation_date=new java.util.Date()
        courseoutcomes.creation_ip_address=request.getRemoteAddr()
        courseoutcomes.updation_ip_address=request.getRemoteAddr()
        courseoutcomes.course=course
        courseoutcomes.save(failOnError:true,flush:true)
        //redirect action:"addcourseoutline"
        redirect(action: "addcourseoutcomes", params: [coursecodecoursename:coursecodecoursename])
    }
    def updatecourseoutcome()
    {
        println("I am in updatecourseoutcome:"+params)
        CourseOutcomes courseoutcome=CourseOutcomes.findById(params.id)
        int x=courseoutcome.co_code
        String outcome_number=""+x
        String outcome_statement=courseoutcome.co_statement
        String coursecodecoursename=session.coursecodecoursename
        String coursecode=session.coursecode
        String coursename=session.coursename
        String email=session.uid
        session.courseoutcomeid=params.id
        String ay=courseoutcome.revisionyear
        boolean isLiveflag=courseoutcome.isCurrent
        String isLiveflagstr=""
        if(isLiveflag==true)
            isLiveflagstr="True"
        else
            isLiveflagstr="False"
        ArrayList<String> aylist=new ArrayList<String>();
        def ay1=AcademicYear.list()
        for(AcademicYear a:ay1)
        {
            aylist.add(a.ay)
        }
        ArrayList<String> isLive=new ArrayList<String>();
        isLive.add("True")
        isLive.add("False")
        [isLiveflagstr:isLiveflagstr,isLive:isLive,aylist:aylist,ay:ay,coursecode:coursecode,coursename:coursename,email:email,outcome_number:outcome_number,outcome_statement:outcome_statement,coursecodecoursename:coursecodecoursename]
    }
    def saveupdatecourseoutcome()
    {
        println("I am in saveupdatecourseoutcome:"+params)
        String coursecode=session.coursecode
        String coursename=session.coursename
        String email=session.uid
        Instructor inst=Instructor.findByUid(email)
        Course course=Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)
        String coursecodecoursename=session.coursecodecoursename
        String outcome_number=params.outcome_number
        outcome_number = outcome_number.replaceAll("\\s+","")   //remove all whitespaces
        String outcome_statement=params.outcome_statement
        String ay=params.ay
        String isCurrent=params.isCurrent
        boolean isCurrentFlag=false
        if(isCurrent.equals("True"))
            isCurrentFlag=true
        else
            isCurrentFlag=false
        CourseOutcomes courseoutcome=CourseOutcomes.findById(session.courseoutcomeid)
        courseoutcome.co_code=Integer.parseInt(outcome_number)
        courseoutcome.co_statement=outcome_statement
        courseoutcome.isCurrent=isCurrentFlag
        courseoutcome.username=email
        println("AY:"+ay)
        AcademicYear ayobj=AcademicYear.findByAy(ay)
        println("ayobj:"+ayobj)
        courseoutcome.revisionyear=ayobj
        courseoutcome.updation_date=new java.util.Date()
        courseoutcome.updation_ip_address=request.getRemoteAddr()
        courseoutcome.save(failOnError:true,flush:true)
        //redirect action:"addcourseoutline"
        redirect(action: " addcourseoutcomes", params: [coursecodecoursename:coursecodecoursename])
    }
    def deletecourseoutcome()
    {
        println("I am in deletecourseoutcome:"+params)
        CourseOutcomes courseoutcome=CourseOutcomes.findById(params.id)
        courseoutcome.isDeleted = true
        courseoutcome.username=session.uid
        //cv.creation_date=new java.util.Date()
        courseoutcome.updation_date=new java.util.Date()
        //cv.creation_ip_address=request.getRemoteAddr()
        courseoutcome.updation_ip_address=request.getRemoteAddr()
        courseoutcome.save(failOnError:true,flush:true)
        String coursecodecoursename=session.coursecodecoursename
        print("Seesion Content"+coursecodecoursename)
        redirect(action: "addcourseoutcomes", params: [coursecodecoursename:coursecodecoursename])
    }

}
