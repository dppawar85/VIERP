package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseOutlineController {

    CourseOutlineService courseOutlineService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseOutlineService.list(params), model:[courseOutlineCount: courseOutlineService.count()]
    }

    def show(Long id) {
        respond courseOutlineService.get(id)
    }

    def create() {
        respond new CourseOutline(params)
    }

    def save(CourseOutline courseOutline) {
        if (courseOutline == null) {
            notFound()
            return
        }

        try {
            courseOutlineService.save(courseOutline)
        } catch (ValidationException e) {
            respond courseOutline.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseOutline.label', default: 'CourseOutline'), courseOutline.id])
                redirect courseOutline
            }
            '*' { respond courseOutline, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseOutlineService.get(id)
    }

    def update(CourseOutline courseOutline) {
        if (courseOutline == null) {
            notFound()
            return
        }

        try {
            courseOutlineService.save(courseOutline)
        } catch (ValidationException e) {
            respond courseOutline.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseOutline.label', default: 'CourseOutline'), courseOutline.id])
                redirect courseOutline
            }
            '*'{ respond courseOutline, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseOutlineService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseOutline.label', default: 'CourseOutline'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseOutline.label', default: 'CourseOutline'), params.id])
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
    def addcourseoutline()
    {
        println("I am in addcourseoutline::"+params.coursecodecoursename)
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
        session.emai=email
        Instructor inst=Instructor.findByUid(email)
        println("ok..."+email+" "+coursecode+" "+coursename+" "+inst)
        Course course=Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)
        //Check whether offerings are present, so that we can block unit modification
        def bcoff=CourseOffering.findAllByCourse(course)
        Date bcurrentdate=new java.util.Date()
        int bflag=0
        for(CourseOffering coff:bcoff)
        {
            if(bcurrentdate>=coff.start_date && bcurrentdate<=coff.end_date)
            {
                bflag=1
                break
            }
        }
        if(bflag==1)
        {
            flash.message="OOPS..Course is already launched, You cannot modify course contents....."
            redirect(controller:"Instructor", action:"addNewCoursesDetails", params: [coursename:course.id])
            return
        }
        def courseOutlinelist=CourseOutline.findAllByCourseAndIsDeleted(course,false)
        courseOutlinelist.sort{it.outline_number}
        session.coursecodecoursename=coursecodecoursename
        [courseOutlinelist:courseOutlinelist,email:email,coursecode:coursecode,coursename:coursename,coursecodecoursename:coursecodecoursename]
    }
    def savecourseoutline()
    {
        println("I am in savecourseoutline:")
        String coursecodecoursename=session.coursecodecoursename
        println("Hi:"+coursecodecoursename)
        String coursecode=session.coursecode
        String coursename=session.coursename
        String email=session.uid
        Instructor inst=Instructor.findByUid(email)
        Course course=Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)

        String outline_number=params.outline_number
        outline_number = outline_number.replaceAll("\\s+","")   //remove all whitespaces
        String outline=params.outline
        println(outline_number+" "+outline+" "+course+" "+email+" "+request.getRemoteAddr()+" "+new java.util.Date())
        CourseOutline courseoutline=new CourseOutline()
        courseoutline.outline_number=Integer.parseInt(outline_number)
        courseoutline.outline=outline
        courseoutline.username=email
        courseoutline.creation_date=new java.util.Date()
        courseoutline.updation_date=new java.util.Date()
        courseoutline.creation_ip_address=request.getRemoteAddr()
        courseoutline.updation_ip_address=request.getRemoteAddr()
        courseoutline.course=course
        courseoutline.save(failOnError:true,flush:true)
        //redirect action:"addcourseoutline"
        redirect(action: "addcourseoutline", params: [coursecodecoursename:coursecodecoursename])
    }
    def updatecourseoutline()
    {
        println("I am in updatecourseoutline:"+params)
        CourseOutline courseoutline=CourseOutline.findById(params.id)
        println("courseoutline::"+courseoutline)
        int x=courseoutline.outline_number
        String outline_number=""+x
        String outline=courseoutline.outline
        String coursecodecoursename=session.coursecodecoursename
        String coursecode=session.coursecode
        String coursename=session.coursename
        String email=session.uid
        session.courseoutlineid=params.id
        [coursecode:coursecode,coursename:coursename,email:email,outline_number:outline_number,outline:outline,coursecodecoursename:coursecodecoursename]
    }
    def saveupdatecourseoutline()
    {
        println("I am in saveupdatecourseoutline:"+params)
        String coursecode=session.coursecode
        String coursename=session.coursename
        String email=session.uid
        Instructor inst=Instructor.findByUid(email)
        Course course=Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)
        String coursecodecoursename=session.coursecodecoursename
        String outline_number=params.outline_number
        outline_number = outline_number.replaceAll("\\s+","")   //remove all whitespaces
        String outline=params.outline
        println(outline_number+" "+outline+" "+course+" "+email)

        CourseOutline courseoutline=CourseOutline.findById(session.courseoutlineid)
        println("courseoutline:"+courseoutline)
        courseoutline.outline_number=Integer.parseInt(outline_number)
        courseoutline.outline=outline
        courseoutline.username=email
        courseoutline.updation_date=new java.util.Date()
        courseoutline.updation_ip_address=request.getRemoteAddr()
        courseoutline.save(failOnError:true,flush:true)
        //redirect action:"addcourseoutline"
        redirect(action: "addcourseoutline", params: [coursecodecoursename:coursecodecoursename])
    }
    def deletecourseoutline()
    {
        println("I am in deletecourseoutline:"+params)
        CourseOutline courseoutline=CourseOutline.findById(params.id)
        def ct = CourseTopic.findAllByCourseoutlineAndIsDeleted(courseoutline,false)
        for(CourseTopic c:ct){
            c.isDeleted = true
            c.username=session.uid
            c.updation_date=new java.util.Date()
            c.updation_ip_address=request.getRemoteAddr()
            c.save(failOnError:true,flush:true)
        }
        courseoutline.isDeleted = true
        courseoutline.username=session.uid
        courseoutline.updation_date=new java.util.Date()
        courseoutline.updation_ip_address=request.getRemoteAddr()
        courseoutline.save(failOnError:true,flush:true)
        String coursecodecoursename=session.coursecodecoursename

        redirect(action: "addcourseoutline", params: [coursecodecoursename:coursecodecoursename])
    }

}
