package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SessionController {

    SessionService sessionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sessionService.list(params), model:[sessionCount: sessionService.count()]
    }

    def show(Long id) {
        respond sessionService.get(id)
    }

    def create() {
        respond new Session(params)
    }

    def save(Session session) {
        if (session == null) {
            notFound()
            return
        }

        try {
            sessionService.save(session)
        } catch (ValidationException e) {
            respond session.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'session.label', default: 'Session'), session.id])
                redirect session
            }
            '*' { respond session, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sessionService.get(id)
    }

    def update(Session session) {
        if (session == null) {
            notFound()
            return
        }

        try {
            sessionService.save(session)
        } catch (ValidationException e) {
            respond session.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'session.label', default: 'Session'), session.id])
                redirect session
            }
            '*'{ respond session, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sessionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'session.label', default: 'Session'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'session.label', default: 'Session'), params.id])
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
    def processession()
    {
        println("I am in processession....")
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
        [listOfLists:listOfLists,row:row]
    }
    def furtherprocesssession()
    {
        println("I am in furtherprocesssession...params:"+params)
        // String schedulemsg = params.schedulemsg
        //  println("I am in furtherprocesssession...session:"+session)
        /* int selectedrow=Integer.parseInt(params.id)
         session.furtherprocesssessionid=params.id
         println(session.listOfListsWeek[selectedrow])*/
        CourseOffering coffr=CourseOffering.findById(params.coffrid)
        //Check whether offerings are present, so that we can block modification of schedule
        Date bcurrentdate=new java.util.Date()
        if(bcurrentdate>=coffr.start_date && bcurrentdate<=coffr.end_date)
        {
            flash.message="OOPS..Course is already launched, You cannot modify Schedule....."
            redirect(controller:"CourseOffering", action:"menuOfSelectedCourseOffered", params: [coffrid:coffr.id])
            return
        }

        session.weekcoff=coffr
        println("Course Offering:"+coffr)
        Course course=coffr.course
        String startdate=coffr.start_date
        String enddate=coffr.end_date
        String batch=coffr.batch
        println(course)
        println(startdate)
        println(enddate)
        println(batch)
        int row=1
        /*  for(CourseOffering c:coff)
          {
              if(row==selectedrow)
              {
                  println("cff.course.course_code::"+c.course.course_code)
                  println("cff.course.course_name:"+c.course.course_name)
                  println(c.start_date.format('d-M-yyyy'))
                  println(c.end_date.format('d-M-yyyy'))
                  println(c.batch)
                  coffr= CourseOffering.findById(c.id)
                  session.weekcoff=coffr
                  println("c.courseofferingtype::"+coffr.courseofferingtype)
                  break
              }
              row++
          }*/
        InitialData id=InitialData.findByName("weeks")
        int number=id.number

        def week_list=Week.findAllByCourseoffering(coffr)
        week_list.sort{it.week_number}
        ArrayList<String> week_number_list=new ArrayList<String>();
        for(Week w:week_list)
        {
            week_number_list.add(""+w.week_number)
        }

        id=InitialData.findByName("sessions")
        number=id.number
        ArrayList<String> session_number_list=new ArrayList<String>();
        for(int i=1;i<=number;i++)
        {
            session_number_list.add(""+i)
        }
        println("week_number_list::"+week_number_list)
        println("session_number_list::"+session_number_list)
        println("coff:::"+coffr.courseofferingtype.name)
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        List templist= new ArrayList<String>()
        row=0
        def courseoutline=CourseOutline.findAllByCourseAndIsDeleted(coffr.course,false)
        if(courseoutline.size()>0)
            templist.add("Unit")
        templist.add("Topic")
        templist.add("Session Number")
        /*  if(coffr.courseofferingtype.name.equals("Weekwise"))
              templist.add("Week Number")
          if(coffr.courseofferingtype.name.equals("Daywise"))
              templist.add("Date")*/
        templist.add("Week Number")
        listOfLists.add(templist)
        row++


        courseoutline.sort{it.outline_number}
        ArrayList<String> previus_session_number_list=new ArrayList<String>();
        ArrayList<String> previus_week_number_list=new ArrayList<String>();
        ArrayList<String> previus_date_number_list=new ArrayList<String>();
        previus_session_number_list.add("")
        /*  if(coffr.courseofferingtype.name.equals("Weekwise"))
              previus_week_number_list.add("")
          if(coffr.courseofferingtype.name.equals("Daywise"))
              previus_date_number_list.add(new Date())  */
        previus_week_number_list.add("")
        int previous_row=0
        for(CourseOutline co:courseoutline)
        {
            def coursetopic=CourseTopic.findAllByCourseoutlineAndIsDeleted(co,false)
            println("topic::"+coursetopic)
            coursetopic.sort{it.topic_number}
            for(CourseTopic ct:coursetopic)
            {
                templist= new ArrayList<String>()
                templist.add(co.outline_number+"."+co.outline)
                templist.add(ct.topic_number+"."+ct.topic)
                listOfLists.add(templist)
                row++
                def sess=Session.findAllByCourseofferingAndCourseoutline(coffr,co)
                println("Session:"+sess)
                int flag=0
                for(Session s:sess)
                {
                    def cttopic = s.coursetopic   //session has multiple course topics
                    println("cttopic:"+cttopic)
                    for (CourseTopic ctnew : cttopic)
                    {
                        println("ctnew:"+ctnew)
                        if (ctnew.id == ct.id)
                        {
                            println("umesh")
                            previus_session_number_list.add(s.session_number)
                            if(coffr.courseofferingtype.name.equals("Weekwise"))
                            {
                                if(s.week==null)
                                    previus_week_number_list.add("")
                                else
                                    previus_week_number_list.add(s.week.week_number)
                            }
                            if(coffr.courseofferingtype.name.equals("Daywise"))
                            {
                                previus_date_number_list.add(s.session_date)
                            }
                            flag=1
                            break
                        }
                    }
                    if(flag==1)
                        break
                }
                println("vikasnew")
                if(flag==0)
                {
                    previus_session_number_list.add("1000")
                    if(coffr.courseofferingtype.name.equals("Weekwise"))
                        previus_week_number_list.add("1000")
                    if(coffr.courseofferingtype.name.equals("Daywise"))
                        previus_date_number_list.add(new Date())
                }
            }
        }
        if(courseoutline.size()==0)
        {
            def coursetopic=CourseTopic.findAllByCourseAndIsDeleted(course,false)
            println("topic::"+coursetopic)
            coursetopic.sort{it.topic_number}
            for(CourseTopic ct:coursetopic)
            {
                templist= new ArrayList<String>()
                // templist.add(co.outline_number+"."+co.outline)
                templist.add(ct.topic_number+"."+ct.topic)
                listOfLists.add(templist)
                row++
                def sess=Session.findAllByCourseoffering(coffr)
                println("Session:"+sess)
                int flag=0
                for(Session s:sess)
                {
                    def cttopic = s.coursetopic   //session has multiple course topics
                    println("cttopic:"+cttopic)
                    for (CourseTopic ctnew : cttopic)
                    {
                        println("ctnew:"+ctnew)
                        if (ctnew.id == ct.id)
                        {
                            println("umesh")
                            previus_session_number_list.add(s.session_number)
                            if(coffr.courseofferingtype.name.equals("Weekwise"))
                            {
                                if(s.week==null)
                                    previus_week_number_list.add("")
                                else
                                    previus_week_number_list.add(s.week.week_number)
                            }
                            if(coffr.courseofferingtype.name.equals("Daywise"))
                            {
                                previus_date_number_list.add(s.session_date)
                            }
                            flag=1
                            break
                        }
                    }
                    if(flag==1)
                        break
                }
                println("vikasnew")
                if(flag==0)
                {
                    previus_session_number_list.add("1000")
                    if(coffr.courseofferingtype.name.equals("Weekwise"))
                        previus_week_number_list.add("1000")
                    if(coffr.courseofferingtype.name.equals("Daywise"))
                        previus_date_number_list.add(new Date())
                }
            }

        }


        println("Display Data::\n")
        for(int i=0;i<row;i++)
        {
            println(listOfLists[i])
        }
        session.courseofferingtype=coffr.courseofferingtype.name
        println("msg:"+params.schedulemsg)
        println("previus_session_number_list::"+previus_session_number_list)
        println("previus_week_number_list::"+previus_week_number_list)
        [previus_session_number_list:previus_session_number_list,previus_week_number_list:previus_week_number_list,previus_date_number_list:previus_date_number_list,row:row,listOfLists:listOfLists,coff:session.weekcoff,week_number_list:week_number_list,week_list:week_list,session_number_list:session_number_list,msg:params.schedulemsg]
    }
    def addsavesessions()
    {
        println("I am in addsavesessions::"+params)
        String[] session_number=params.session_number
        /*for(int i=0;i<session_number.length;i++)
            println("Session::"+session_number[i])*/
        String[] start_date_day=params.start_date_day
        String[] start_date_month=params.start_date_month
        String[] start_date_year=params.start_date_year
        /*if(start_date_day!=null)
        {
            for (int i = 0; i < start_date_day.length; i++)
                println("Date::" + start_date_day[i] + "/" + start_date_month[i] + "/" + start_date_year[i])
        }*/
        String[] week_number=params.week_number
        if(week_number!=null)
        {
            for (int i = 0; i < week_number.length; i++)
                println("Week::" + week_number[i])
        }
        println("courseofferingtype::"+session.courseofferingtype)
        println(session.weekcoff)
        CourseOffering coffr=CourseOffering.findById(session.weekcoff.id)
        println("courseoffering::"+coffr)
        def delsession=Session.findAllByCourseoffering(coffr)
        //Remove all elements
        for(Session s:delsession)
            s.delete(failOnError:true,flush:true)
        println("Fetced Data::\n")
        def courseoutline=CourseOutline.findAllByCourseAndIsDeleted(coffr.course,false)
        courseoutline.sort{it.outline_number}
        int x=0
        for(CourseOutline co:courseoutline)
        {
            def coursetopic=CourseTopic.findAllByCourseoutlineAndIsDeleted(co,false)
            coursetopic.sort{it.topic_number}
            for(CourseTopic ct:coursetopic)
            {
                Session sess=Session.findByCourseofferingAndCourseoutlineAndSession_number(coffr,co,Integer.parseInt(session_number[x]))
                if(sess==null)  //insert
                {
                    sess=new Session()
                    sess.session_number=Integer.parseInt(session_number[x])
                    sess.username=session.uid
                    sess.creation_date=new java.util.Date()
                    sess.updation_date=new java.util.Date()
                    sess.creation_ip_address=request.getRemoteAddr()
                    sess.updation_ip_address=request.getRemoteAddr()
                    sess.courseoffering=coffr
                    sess.courseoutline=co
                    sess.addToCoursetopic(ct)
                    /*   if(session.courseofferingtype.toString().equals("Weekwise"))
                       {
                           println("week::"+week_number[x])
                           Week week=Week.findByWeek_numberAndCourseoffering(Integer.parseInt(week_number[x]),coffr)
                           sess.week=week
                       }
                       if(session.courseofferingtype.toString().equals("Daywise"))
                       {
                           println("Date::" + start_date_day[x] + "/" + start_date_month[x] + "/" + start_date_year[x])
                           String val=start_date_year[x]+"/"+start_date_month[x]+"/"+start_date_day[x]
                           sess.session_date=new Date().parse('yyyy/MM/dd',val)
                       }*/
                    println("week::"+week_number[x])
                    Week week=Week.findByWeek_numberAndCourseoffering(Integer.parseInt(week_number[x]),coffr)
                    sess.week=week
                    sess.save(failOnError:true,flush:true)
                }
                else      //update
                {
                    sess.addToCoursetopic(ct)
                    sess.save(failOnError:true,flush:true)
                }
                x++
                println("x::"+x)
            }
        }
        x=0
        if(courseoutline.size()==0)
        {
            println("birthday")
            def coursetopic=CourseTopic.findAllByCourseAndIsDeleted(coffr.course,false)
            coursetopic.sort{it.topic_number}
            for(CourseTopic ct:coursetopic)
            {
                Session sess=Session.findByCourseofferingAndSession_number(coffr,Integer.parseInt(session_number[x]))
                println("dpp:"+sess+" session number::"+Integer.parseInt(session_number[x]))
                if(sess==null)  //insert
                {
                    sess=new Session()
                    sess.session_number=Integer.parseInt(session_number[x])
                    sess.username=session.uid
                    sess.creation_date=new java.util.Date()
                    sess.updation_date=new java.util.Date()
                    sess.creation_ip_address=request.getRemoteAddr()
                    sess.updation_ip_address=request.getRemoteAddr()
                    sess.courseoffering=coffr
                    //sess.courseoutline=co
                    sess.addToCoursetopic(ct)
                    /*   if(session.courseofferingtype.toString().equals("Weekwise"))
                       {
                           println("week::"+week_number[x])
                           Week week=Week.findByWeek_numberAndCourseoffering(Integer.parseInt(week_number[x]),coffr)
                           sess.week=week
                       }
                       if(session.courseofferingtype.toString().equals("Daywise"))
                       {
                           println("Date::" + start_date_day[x] + "/" + start_date_month[x] + "/" + start_date_year[x])
                           String val=start_date_year[x]+"/"+start_date_month[x]+"/"+start_date_day[x]
                           sess.session_date=new Date().parse('yyyy/MM/dd',val)
                       }*/
                    println("week::"+week_number[x])
                    Week week=Week.findByWeek_numberAndCourseoffering(Integer.parseInt(week_number[x]),coffr)
                    sess.week=week
                    sess.save(failOnError:true,flush:true)
                }
                else      //update
                {
                    sess.addToCoursetopic(ct)
                    sess.save(failOnError:true,flush:true)
                }
                x++
                println("x::"+x)
            }

        }
        String schedulemsg="Schedule Updated Successfully..."
        // println("msg:"+flash.message)
        redirect(action: "furtherprocesssession", params: [coffrid:coffr.id,schedulemsg:schedulemsg])
    }
}
