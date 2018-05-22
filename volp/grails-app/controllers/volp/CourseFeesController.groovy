package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseFeesController {

    CourseFeesService courseFeesService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        println("umesh")
        params.max = Math.min(max ?: 10, 100)
        respond courseFeesService.list(params), model: [courseFeesCount: courseFeesService.count()]
    }

    def show(Long id) {
        respond courseFeesService.get(id)
    }

    def create() {
        respond new CourseFees(params)
    }

    def save(CourseFees courseFees) {
        if (courseFees == null) {
            notFound()
            return
        }

        try {
            courseFeesService.save(courseFees)
        } catch (ValidationException e) {
            respond courseFees.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseFees.label', default: 'CourseFees'), courseFees.id])
                redirect courseFees
            }
            '*' { respond courseFees, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseFeesService.get(id)
    }

    def update(CourseFees courseFees) {
        if (courseFees == null) {
            notFound()
            return
        }

        try {
            courseFeesService.save(courseFees)
        } catch (ValidationException e) {
            respond courseFees.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseFees.label', default: 'CourseFees'), courseFees.id])
                redirect courseFees
            }
            '*' { respond courseFees, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseFeesService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseFees.label', default: 'CourseFees'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseFees.label', default: 'CourseFees'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def setCourseFee() {
        println("Set course fee"+session.cid +"params"+params)
        def coursesInstructor = Instructor.findByUid(session.uid)
        println("session.uid"+session.uid)
        println("coursesInstructor"+coursesInstructor)
        println(coursesInstructor)
        def insCourses = []
        def instCourses = Course.findAllByCourseowner(coursesInstructor)
        println("instructorCourses" + instCourses)
        println("instructorCourses" + instCourses)
        def savedCourseFees1 =[]
        def courseOffering = CourseOffering.findById(params.coffr)
        println(courseOffering.course.course_name)

        //Check whether offerings are present, so that we can block modification of fees
        Date bcurrentdate=new java.util.Date()
        if(bcurrentdate>=courseOffering.start_date && bcurrentdate<=courseOffering.end_date)
        {
            flash.message="OOPS..Course is already launched, You cannot modify Course Fees....."
            redirect(controller:"CourseOffering", action:"menuOfSelectedCourseOffered", params: [coffrid:courseOffering.id])
            return
        }

        /*for (Course c : instCourses) {
            CourseOffering c1 = CourseOffering.findByCourse(c)
            if (c1) {
                insCourses.add(c)
                savedCourseFees1.add(c1)
            }
        }*/
        def savedCourseFees = CourseFees.findAllByCourseoffering(courseOffering)
      /* def savedCourseFees =[]
        for(CourseOffering c:savedCourseFees1)
        {
            println("alan test"+c)
            def c1 = CourseFees.findAllByCourseoffering(c)
            println("alan...."+c1)
            if(c1)
            {
                for (CourseFees item : c1) {
                    savedCourseFees.add(item)
                }

            }
        }*/
        def courseFeeType = CourseFeesType.findAllByIscoursefeestypeset(true)
        println("courseFeeType" + courseFeeType)
        println("insCourses" + insCourses)
        def CertificateType = CourseCertificateType.findAllByIscoursecertificatetypeset(true)

        println("savedCourseFees" + savedCourseFees)
        [insCourses:courseOffering,courseFeeType: courseFeeType, CertificateType: CertificateType, savedCourseFees: savedCourseFees]
        //redirect(controller: "courseFees", action: "setCourseFee")
        /*
        def coursesInstructor = Instructor.findByUsername(session.uid)
        println("session.uid"+session.uid)
        println("coursesInstructor"+coursesInstructor)
        println(coursesInstructor)

        def insCourses = []
        def instCourses = Course.findAllByCourseowner(coursesInstructor)
        println("instructorCourses" + instCourses)
        def savedCourseFees1 =[]
        for (Course c : instCourses) {
            CourseOffering c1 = CourseOffering.findByCourse(c)
            if (c1) {
                insCourses.add(c)
                savedCourseFees1.add(c1)
            }
        }
        def savedCourseFees =[]
        for(CourseOffering c:savedCourseFees1)
        {
            println("alan test"+c)
            def c1 = CourseFees.findAllByCourseoffering(c)
            println("alan...."+c1)
            if(c1)
            {
                for (CourseFees item : c1) {
                    savedCourseFees.add(item)
                }

            }
        }

        println("savedCourseFees"+savedCourseFees)
        def courseFeeType = CourseFeesType.findAllByIscoursefeestypeset(true)
        println("courseFeeType" + courseFeeType)
        println("insCourses" + insCourses)
        def CertificateType = CourseCertificateType.findAllByIscoursecertificatetypeset(true)
        println("savedCourseFees" + savedCourseFees)
        [insCourses: insCourses, courseFeeType: courseFeeType, CertificateType: CertificateType, savedCourseFees: savedCourseFees]
    */}
    def changeFees(Long id)
    {
        println("params in change fee"+params)
        //println("i am in update fees"+id)
        CourseFees changeFee = CourseFees.findById(params.Doller)
        println("change fees"+changeFee)
        def coursesInstructor = Instructor.findByUsername(session.uid)
        println("session.uid"+session.uid)
        println("coursesInstructor"+coursesInstructor)
        println(coursesInstructor)
        def insCourses = []
        def instCourses = Course.findAllByCourseowner(coursesInstructor)
        println("instructorCourses" + instCourses)
        //def coursesInstructor = Instructor.findByUid(session.uid)
        println("session.uid"+session.uid)
        println("coursesInstructor"+coursesInstructor)
        println(coursesInstructor)
        //def insCourses = []
        //def instCourses = Course.findAllByCourseowner(coursesInstructor)
        println("instructorCourses" + instCourses)
        println("instructorCourses" + instCourses)
        def courseOffering = CourseOffering.findById(params.insCourses)
        def savedCourseFees = CourseFees.findAllByCourseoffering(courseOffering)
        /*def savedCourseFees1 =[]
        for (Course c : instCourses) {
            CourseOffering c1 = CourseOffering.findByCourse(c)
            if (c1) {
                insCourses.add(c)
                savedCourseFees1.add(c1)
            }
        }
        def savedCourseFees =[]
        for(CourseOffering c:savedCourseFees1)
        {
            println("alan test"+c)
            def c1 = CourseFees.findAllByCourseoffering(c)
            println("alan...."+c1)
            if(c1)
            {
                for (CourseFees item : c1) {
                    savedCourseFees.add(item)
                }

            }
        }*/
        def courseFeeType = CourseFeesType.findAllByIscoursefeestypeset(true)
        println("courseFeeType" + courseFeeType)
        println("insCourses" + insCourses)
        def CertificateType = CourseCertificateType.findAllByIscoursecertificatetypeset(true)

        println("savedCourseFees" + savedCourseFees)
        [insCourses: insCourses, courseFeeType: courseFeeType, CertificateType: CertificateType, savedCourseFees: savedCourseFees,changeFee:changeFee]
        //render("Change Feess")
    }
    def saveUpdatedFees()
    {
        println("save changes in params"+params)
        CourseFees cfess = CourseFees.findById(params.changeFeeId)
        println("cfess"+cfess)
        cfess.updation_ip_address = request.getRemoteAddr()
        cfess.updation_date =  new java.util.Date()
        cfess.fees = Double.parseDouble(params.chfees)
        cfess.save(flush:true,failOnError:true)
        def coursesInstructor = Instructor.findByUsername(session.uid)
        println("session.uid"+session.uid)
        println("coursesInstructor"+coursesInstructor)
        println(coursesInstructor)
        def insCourses = []
        def instCourses = Course.findAllByCourseowner(coursesInstructor)
        println("instructorCourses" + instCourses)
        println("instructorCourses" + instCourses)
        /*def savedCourseFees1 =[]
        for (Course c : instCourses) {
            CourseOffering c1 = CourseOffering.findByCourse(c)
            if (c1) {
                insCourses.add(c)
                savedCourseFees1.add(c1)
            }
        }
        def savedCourseFees =[]
        for(CourseOffering c:savedCourseFees1)
        {
            println("alan test"+c)
            def c1 = CourseFees.findAllByCourseoffering(c)
            println("alan...."+c1)
            if(c1)
            {
                for (CourseFees item : c1) {
                    savedCourseFees.add(item)
                }

            }
        }*/
        def courseOffering = CourseOffering.findById(params.insCourses)
        def savedCourseFees = CourseFees.findAllByCourseoffering(courseOffering)
        def courseFeeType = CourseFeesType.findAllByIscoursefeestypeset(true)
        println("courseFeeType" + courseFeeType)
        println("insCourses" + insCourses)
        def CertificateType = CourseCertificateType.findAllByIscoursecertificatetypeset(true)

        println("savedCourseFees" + savedCourseFees)
        [insCourses: insCourses, courseFeeType: courseFeeType, CertificateType: CertificateType, savedCourseFees: savedCourseFees]
        //redirect(controller: "courseFees", action: "setCourseFee")
    }
    def saveCourseFee() {
        println("params" + params)
        if (params.insCourses.empty | params.courseFeeType.empty | params.CertificateType.empty) {
            flash.message="Please select options"
            redirect(controller: "courseFees", action: "setCourseFee")

        } else {
           /* println(params.insCourses)
            Course crs = Course.findById(params.insCourses)
            println("crs :: " + crs)
            println("crs" + crs.course_name)
            CourseOffering crsOff = CourseOffering.findByCourse(crs)
            println("crsOff" + crsOff.course.course_name)*/
            //println("crsOff" + crsOff.course.course_name)
            def crsOff = CourseOffering.findById(params.insCourses)
            CourseCertificateType cerType = CourseCertificateType.findById(params.CertificateType)
            println("cerType" + cerType)
            CoursePackage crsPkg = null
            CourseFeesType crsffety = CourseFeesType.findById(params.courseFeeType)
            println("crsffety" + crsffety)
            CourseFees crsf = CourseFees.findByCourseofferingAndCoursecertificatetypeAndCoursepackage(crsOff, cerType, crsPkg)
            println("crsf" + crsf)
            def crsfeet = CourseFeesType.list()
            println("crsfeet" + crsfeet)
            if (!crsf) {
                println("i am in if")
                CourseFees ob = new CourseFees();
                ob.username = session.uid
                ob.fees = Double.parseDouble(params.Doller)
                ob.updation_date = new java.util.Date()
                ob.creation_date = new java.util.Date()
                ob.creation_ip_address = request.getRemoteAddr()
                ob.updation_ip_address = request.getRemoteAddr()
                ob.courseoffering = crsOff
                ob.coursecertificatetype = cerType
                ob.coursepackage = crsPkg
                ob.addToCoursefeestype(crsffety)
                ob.save(flush: true, failOnError: true)
                println("if null")
                println("crsf" + crsf)
            } else {
                int flag = 1;
                println("not null")
                println("alllallllll"+crsf.coursefeestype.type)
                def allFee = CourseFees.findAllByCourseoffering(crsOff)
                for(CourseFees c:allFee)
                {
                    def crt = CourseCertificateType.findById(cerType.id)
                    def cft = CourseFeesType.findById(crsffety.id)
                    if(c.coursefeestype.type[0].equals(cft.type) && c.coursecertificatetype.type.equals(crt.type)) {
                        println("match")
                        println("certificate type"+crt.type+":::"+c.coursecertificatetype.type)
                        println("fees type"+cft.type +":::"+c.coursefeestype.type[0])
                        flag =0
                        break;
                    }
                }
                if(flag == 1)
                {
                    flag=1
                }

                if (flag) {
                    CourseFees ob = new CourseFees();
                    ob.username = session.uid
                    ob.fees = Double.parseDouble(params.Doller)
                    ob.updation_date = new java.util.Date()
                    ob.creation_date = new java.util.Date()
                    ob.creation_ip_address = request.getRemoteAddr()
                    ob.updation_ip_address = request.getRemoteAddr()
                    ob.courseoffering = crsOff
                    ob.coursecertificatetype = cerType
                    ob.coursepackage = crsPkg
                    ob.addToCoursefeestype(crsffety)
                    ob.save(flush: true, failOnError: true)
                }
            }
           // redirect(controller: "courseFees", action: "setCourseFee")
            //render(view: "instructor/instructor")
            //ajd
            def coursesInstructor = Instructor.findByUsername(session.uid)
            println("session.uid"+session.uid)
            println("coursesInstructor"+coursesInstructor)
            println(coursesInstructor)
            def insCourses = []
            def instCourses = Course.findAllByCourseowner(coursesInstructor)
            println("instructorCourses" + instCourses)
            println("instructorCourses" + instCourses)
            /*def savedCourseFees1 =[]
            for (Course c : instCourses) {
                CourseOffering c1 = CourseOffering.findByCourse(c)
                if (c1) {
                    insCourses.add(c)
                    savedCourseFees1.add(c1)
                }
            }
            def savedCourseFees =[]
            for(CourseOffering c:savedCourseFees1)
            {
                println("alan test"+c)
                def c1 = CourseFees.findAllByCourseoffering(c)
                println("alan...."+c1)
                if(c1)
                {
                    for (CourseFees item : c1) {
                        savedCourseFees.add(item)
                    }

                }
            }*/
            def savedCourseFees = CourseFees.findAllByCourseoffering(crsOff)
            def courseFeeType = CourseFeesType.findAllByIscoursefeestypeset(true)
            println("courseFeeType" + courseFeeType)
            println("insCourses" + insCourses)
            def CertificateType = CourseCertificateType.findAllByIscoursecertificatetypeset(true)
            println("savedCourseFees" + savedCourseFees)
            [insCourses: insCourses, courseFeeType: courseFeeType, CertificateType: CertificateType, savedCourseFees: savedCourseFees]
        }
    }
    def setFees()
    {
        println(" set fees params"+session);
        def coursesInstructor = Instructor.findByUid(session.uid)
        render("ok")
    }
}
