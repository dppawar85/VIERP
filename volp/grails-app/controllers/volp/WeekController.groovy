package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class WeekController {

    WeekService weekService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond weekService.list(params), model:[weekCount: weekService.count()]
    }

    def show(Long id) {
        respond weekService.get(id)
    }

    def create() {
        respond new Week(params)
    }

    def save(Week week) {
        if (week == null) {
            notFound()
            return
        }

        try {
            weekService.save(week)
        } catch (ValidationException e) {
            respond week.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'week.label', default: 'Week'), week.id])
                redirect week
            }
            '*' { respond week, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond weekService.get(id)
    }

    def update(Week week) {
        if (week == null) {
            notFound()
            return
        }

        try {
            weekService.save(week)
        } catch (ValidationException e) {
            respond week.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'week.label', default: 'Week'), week.id])
                redirect week
            }
            '*'{ respond week, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        weekService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'week.label', default: 'Week'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'week.label', default: 'Week'), params.id])
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
        courselist.add("Select Your Course")
        for(Course c:courses)
        {
            courselist.add(c.course_code+":"+c.course_name)
        }
        println(courselist)
        [courselist:courselist]
    }
    def processweek()
    {
        println("I am in processweek.."+params)
        String coursecodecoursename=params.coursecodecoursename
        def crs=null
        if(coursecodecoursename.equals(""))
        {
            coursecodecoursename = session.coursecodecoursename
            crs=coursecodecoursename.split(":")
        }
        else
            crs=coursecodecoursename.split(":")
        session.coursecodecoursename=coursecodecoursename
        String coursecode=crs[0]
        String coursename=crs[1]
        String email=session.uid
        session.coursecode=coursecode
        session.coursename=coursename
        session.email=email
        Instructor inst=Instructor.findByUid(email)
        println("ok..."+email+" "+coursecode+" "+coursename+" "+inst)
        Course course=Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)
        println("Course::"+course)
        session.course=course
        def coff=CourseOffering.findAllByCourseAndIsActive(course,true)
        println("coff::"+coff)
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        int row=0
        List templist=new ArrayList<String>()
        templist.add("Course Code")
        templist.add("Course Name")
        templist.add("Start Date")
        templist.add("End Date")
        templist.add("Batch")
        listOfLists.add(templist)
        row++
        for(CourseOffering cff:coff)
        {
            templist= new ArrayList<String>()
            templist.add(cff.course.course_code)
            templist.add(cff.course.course_name)
            templist.add(cff.start_date.format('d-M-yyyy'))
            templist.add(cff.end_date.format('d-M-yyyy'))
            templist.add(cff.batch)
            listOfLists.add(templist)
            row++
        }
        println("listOfLists:"+listOfLists)
        session.listOfListsWeek=listOfLists
        session.weekcoff=coff
        session.coffid=1
        [listOfLists:listOfLists,row:row]
    }
    def furtherprocessweek()
    {
        println("I am in furtherprocessweek...params"+params)
        println("I am in furtherprocessweek...session"+session)
        /* CourseOffering coff
         if(params.coffid!=null)
             coff=CourseOffering.findById(params.coffid)
         else
             coff=session.coffid
        println("coff:"+coff)*/
        CourseOffering coff = CourseOffering.findById(params.coffrid)
        println("coff:"+coff)
        //Check whether offerings are present, so that we can block modification of weeks
        Date bcurrentdate=new java.util.Date()
        if(bcurrentdate>=coff.start_date && bcurrentdate<=coff.end_date)
        {
            flash.message="OOPS..Course is already launched, You cannot modify Weeks....."
            redirect(controller:"CourseOffering", action:"menuOfSelectedCourseOffered", params: [coffrid:coff.id])
            return
        }
        Course course=coff.course
        String startdate=coff.start_date
        String enddate=coff.end_date
        String batch=coff.batch
        /*int selectedrow=Integer.parseInt(params.id)
        println(session.listOfListsWeek[selectedrow])
        Course course=session.course
        String startdate=session.listOfListsWeek[selectedrow][2]
        String enddate=session.listOfListsWeek[selectedrow][3]
        String batch=session.listOfListsWeek[selectedrow][4]
        println(course)
        println(startdate)
        println(enddate)
        println(batch)*/
        //def coff=session.weekcoff
        /* int row=1
         for(CourseOffering c:coff)
         {
             if(row==selectedrow)
             {
                 println("cff.course.course_code::"+c.course.course_code)
                 println("cff.course.course_name:"+c.course.course_name)
                 println(c.start_date.format('d-M-yyyy'))
                 println(c.end_date.format('d-M-yyyy'))
                 println(c.batch)
                 session.weekcoff=c
                 break
             }
             row++
         }*/
        session.weekcoff=coff
        //coff=session.weekcoff
        InitialData id=InitialData.findByName("weeks")
        int number=id.number
        ArrayList<String> week_number_list=new ArrayList<String>();
        for(int i=1;i<=number;i++)
        {
            week_number_list.add(""+i)
        }
        def week_list=Week.findAllByCourseoffering(coff)

        [coff:coff,week_number_list:week_number_list,week_list:week_list]
    }
    def addweeks()
    {
        CourseOffering coff=session.weekcoff
        println("coff:"+coff)
        session.coff=coff
        InitialData id=InitialData.findByName("weeks")
        int number=id.number
        ArrayList<String> week_number_list=new ArrayList<String>();
        for(int i=1;i<=number;i++)
        {
            week_number_list.add(""+i)
        }
        def week_list=Week.findAllByCourseoffering(coff)
        [coff:coff,week_number_list:week_number_list,week_list:week_list]
    }
    def addsaveweeks()
    {
        println("I am in addsaveweeks..."+params)
        int week_number=Integer.parseInt(params.week_number.toString())
        Date start_date=params.date('start_date','dd-MM-yyyy')
        Date end_date=params.date('end_date','dd-MM-yyyy')
        Week week=new Week()
        week.week_number=week_number
        week.start_date=start_date
        week.end_date=end_date
        week.username=session.uid
        week.creation_ip_address=request.getRemoteAddr()
        week.updation_ip_address=request.getRemoteAddr()
        week.creation_date=new java.util.Date()
        week.updation_date=new java.util.Date()
        week.courseoffering=session.weekcoff
        week.save(failOnError:true,flush:true)
        InitialData id=InitialData.findByName("weeks")
        int number=id.number
        ArrayList<String> week_number_list=new ArrayList<String>();
        for(int i=1;i<=number;i++)
        {
            week_number_list.add("" + i)
        }
        def week_list=Week.findAllByCourseoffering(session.weekcoff)
        [coffrid:session.weekcoff,coff:session.weekcoff,week_number_list:week_number_list,week_list:week_list]
        //redirect(action: "furtherprocessweek", params: [coff:session.weekcoff,week_number_list:week_number_list,week_list:week_list])
    }
    def updateweeks()
    {
        println("I am in updateweeks..."+params)
        Week week=Week.findById(params.id)
        println("week::"+week)
        InitialData id=InitialData.findByName("weeks")
        int number=id.number
        ArrayList<String> week_number_list=new ArrayList<String>();
        for(int i=1;i<=number;i++)
        {
            week_number_list.add("" + i)
        }
        session.weekid=params.id
        [week_number_list:week_number_list,week:week,coff:session.weekcoff]
    }
    def updatesaveweeks()
    {
        println("I am in updatesaveweeks..."+params)
        int week_number=Integer.parseInt(params.week_number.toString())
        Date start_date=params.date('start_date','dd-MM-yyyy')
        Date end_date=params.date('end_date','dd-MM-yyyy')
        Week week=Week.findById(session.weekid)
        week.week_number=week_number
        week.start_date=start_date
        week.end_date=end_date
        week.save(failOnError:true,flush:true)
        InitialData id=InitialData.findByName("weeks")
        int number=id.number
        ArrayList<String> week_number_list=new ArrayList<String>();
        for(int i=1;i<=number;i++)
        {
            week_number_list.add("" + i)
        }
        def week_list=Week.findAllByCourseoffering(session.weekcoff)
        [coffrid:session.weekcoff,coff:session.weekcoff,week_number_list:week_number_list,week_list:week_list]
        //redirect(action: "addweeks", params: [coff:session.weekcoff,week_number_list:week_number_list,week_list:week_list])
    }
    def deleteweeks()
    {
        println("I am in deleteweeks::")
        Week week=Week.findById(params.id)
        week.delete(failOnError:true,flush:true)
        InitialData id=InitialData.findByName("weeks")
        int number=id.number
        ArrayList<String> week_number_list=new ArrayList<String>();
        for(int i=1;i<=number;i++)
        {
            week_number_list.add("" + i)
        }
        def week_list=Week.findAllByCourseoffering(session.coff)
        redirect(action: "addweeks", params: [coff:session.weekcoff,week_number_list:week_number_list,week_list:week_list])
    }
    def deleteweek()//by vikas
    {
        println("i am deleteweek:"+params)
        Week w = Week.findById(params.id)
        println("w:"+w)
        def sess = Session.findAllByWeek(w)

        for(Session s:sess){
            println("s:"+s)
            s.delete(failOnError:true,flush:true)
        }
        w.delete(failOnError:true,flush:true)

/*
        w.isDeleted = true
        w.username=session.uid
        //cv.creation_date=new java.util.Date()
        w.updation_date=new java.util.Date()
        //cv.creation_ip_address=request.getRemoteAddr()
        w.updation_ip_address=request.getRemoteAddr()
        w.save(failOnError:true,flush:true)
        */
        redirect(controller:"courseOffering", action: "menuOfSelectedCourseOffered", params:[coffrid:params.coffrid])
    }
}
