package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import java.io.File

class CourseOfferingController {

    CourseOfferingService courseOfferingService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseOfferingService.list(params), model:[courseOfferingCount: courseOfferingService.count()]
    }

    def show(Long id) {
        respond courseOfferingService.get(id)
    }

    def create() {
        respond new CourseOffering(params)
    }

    def save(CourseOffering courseOffering) {
        if (courseOffering == null) {
            notFound()
            return
        }

        try {
            courseOfferingService.save(courseOffering)
        } catch (ValidationException e) {
            respond courseOffering.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseOffering.label', default: 'CourseOffering'), courseOffering.id])
                redirect courseOffering
            }
            '*' { respond courseOffering, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseOfferingService.get(id)
    }

    def update(CourseOffering courseOffering) {
        if (courseOffering == null) {
            notFound()
            return
        }

        try {
            courseOfferingService.save(courseOffering)
        } catch (ValidationException e) {
            respond courseOffering.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseOffering.label', default: 'CourseOffering'), courseOffering.id])
                redirect courseOffering
            }
            '*'{ respond courseOffering, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseOfferingService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseOffering.label', default: 'CourseOffering'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseOffering.label', default: 'CourseOffering'), params.id])
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
    def processcourseoffering()
    {
        println("I am processcourseoffering..."+params)
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
        session.course=course
        def yearlist=Year.list()
        def aylist=AcademicYear.list()
        def semlist=Semester.list()
        def coursetypelist=CourseType.findAllByOrganization(inst.organization)
        def courseofferingtypelist=CourseOfferingType.list()
        println("course:"+course+" "+"Instructor:"+inst+" "+yearlist+" "+aylist+" "+semlist+" "+coursetypelist+" "+courseofferingtypelist)
        Organization organization
        boolean isuserbelongstoorganization=false
      /*  if(inst.organization==null)
        {
            isuserbelongstoorganization=false
        }
        else
        {
            organization=inst.organization
            isuserbelongstoorganization=true
        }*/
        println(isuserbelongstoorganization)
        session.isuserbelongstoorganization=isuserbelongstoorganization
        //  session.isuserbelongstoorganization=false
        def courseofferinglist=CourseOffering.findAllByCourse(course)
        session.coursecodecoursename=coursecodecoursename
        [courseofferinglist:courseofferinglist,courseofferingtypelist:courseofferingtypelist,coursetypelist:coursetypelist,course:course,yearlist:yearlist,aylist:aylist,semlist:semlist]
    }
    def savecourseoffering()
    {
        println("I am in savecourseoffering..."+params)
        println("Hi::"+params.startdate)
        Date start_date=params.date('startdate','dd-MM-yyyy')
        Date end_date=params.date('enddate','dd-MM-yyyy')
        String batch=params.batch
        Year year=Year.findByYear(params.year)
        AcademicYear ay=AcademicYear.findByAy(params.ay)
        Semester sem=Semester.findBySem(params.sem)
        CourseType coursetype=CourseType.findByType(params.coursetype)
        CourseOfferingType courseofferingtype=CourseOfferingType.findByName(params.courseofferingtype)
        println(start_date)
        println(end_date)
        println(batch)
        println(year)
        CourseOffering coff=CourseOffering.findByStart_dateAndEnd_dateAndBatchAndCourseAndYearAndAcademicyearAndSemesterAndCoursetypeAndCourseofferingtype(start_date,end_date,batch,session.course,year,ay,sem,coursetype,courseofferingtype)
        println("coff::"+coff)
        if(coff==null)
        {
            coff=new CourseOffering()
            coff.start_date=start_date
            coff.end_date=end_date
            coff.threshold=40
            coff.isActive=true
            coff.isDeleted=false
            coff.batch=batch
            coff.username=session.uid
            coff.creation_date=new java.util.Date()
            coff.updation_date=new java.util.Date()
            coff.creation_ip_address=request.getRemoteAddr()
            coff.updation_ip_address=request.getRemoteAddr()
            coff.course=session.course
            coff.year=year
            coff.academicyear=ay
            coff.semester=sem
            coff.coursetype=coursetype
            coff.courseofferingtype=courseofferingtype
            coff.save(failOnError:true,flush:true)
            //Copy previous assignments into new offering
            def assignment=Assignment.findAllByCourse(coff.course)
            AssignmentOffering ao=null
            for(Assignment a:assignment)
            {
                ao=new AssignmentOffering()
                ao.assignment=a
                ao.courseoffering=coff
                ao.courseassesmentscheme=null
                ao.assignment_offering_number=a.assignment_number
                ao.schedule_date=null
                ao.due_date=null
                ao.username=session.uid
                ao.creation_date=new java.util.Date()
                ao.updation_date=new java.util.Date()
                ao.creation_ip_address=request.getRemoteAddr()
                ao.updation_ip_address=request.getRemoteAddr()
                ao.save(failOnError:true,flush:true)
                println("Assignment Offering saved successfully....")
            }
            def fpath=FolderPath.list()
            FolderPath fp=fpath[0]
            println("folder path:"+fp.path)
            println("/courseoffering/"+coff.id)
            new File(fp.path+"/courseoffering/"+coff.id).mkdirs()
            println("Folder Created Successfully.....")

            //Create secret code for offered course; which is common secret code like google classroom
            java.util.Random random=new java.util.Random()
            String otp=""
            int min=0,max=0,n
            for(int j=1;j<=6;j++)
            {
                if(j==1)
                    min=1
                else
                    min=0
                max=9
                n=random.nextInt(max)+min
                otp=otp+n
                if(j==3) //insert letters in between
                {
                    for(int k=1;k<=2;k++)
                    {
                        min = 1
                        max = 26
                        n = random.nextInt(max) + min
                        n--
                        char temp = (char) (97 + n)
                        otp = otp + temp
                    }
                }
                if(j==6)
                {
                    SendCommonInvitationToLearner obj=SendCommonInvitationToLearner.findByCode(otp)
                    if(obj!=null)
                    {
                        j = 1
                        temp=""
                    }
                }
            }
            println("otp::"+otp)
            SendCommonInvitationToLearner sitlobj=SendCommonInvitationToLearner.findByCourseoffering(coff)
            Date date=new java.util.Date()
            if(sitlobj==null)
            {
                //insert otp
                sitlobj=new SendCommonInvitationToLearner()
                sitlobj.courseoffering=coff
                sitlobj.code=otp
                sitlobj.creation_date=date
                sitlobj.updation_date=date
                sitlobj.creation_ip_address=request.getRemoteAddr()
                sitlobj.updation_ip_address=request.getRemoteAddr()
                sitlobj.username=session.uid
                sitlobj.save(failOnError:true,flush:true)
            }
            else
            {
                //update otp
                sitlobj.code=otp
                sitlobj.updation_date=date
                sitlobj.updation_ip_address=request.getRemoteAddr()
                sitlobj.username=session.uid
                sitlobj.save(failOnError:true,flush:true)
            }
            println("secret code svaed successfully......")

            flash.message="Course Offered Successfully......"
            redirect(action: "processcourseoffering", params: [coursecodecoursename:session.coursecodecoursename])
            return
        }
        else
        {
            flash.message="Course Already Offered..."
            redirect(action: "processcourseoffering", params: [coursecodecoursename:session.coursecodecoursename])
            return
        }
        redirect(action: "processcourseoffering", params: [coursecodecoursename:session.coursecodecoursename])
    }
    def updatecourseoffering()
    {
        println("I am in updatecourseoffering::"+params.id)
        CourseOffering coff=CourseOffering.findById(params.id)
        session.coffid=coff.id
        println("Course Offering :"+coff)
        def yearlist=Year.list()
        def aylist=AcademicYear.list()
        def semlist=Semester.list()
        Instructor inst=Instructor.findByUid(session.uid)
        def coursetypelist=CourseType.findAllByOrganization(inst.organization)
        def courseofferingtypelist=CourseOfferingType.list()
        ArrayList<String> archivelist=new ArrayList<String>();
        archivelist.add("Yes")
        archivelist.add("No")
        String archive=""
        if(coff.isActive==true)
            archive="No"
        else
            archive="Yes"
        [archivelist:archivelist,archive:archive,coff:coff,yearlist:yearlist,aylist:aylist,semlist:semlist,coursetypelist:coursetypelist,courseofferingtypelist:courseofferingtypelist]
    }
    def saveupdatecourseoffering()
    {
        println("I am in saveupdatecourseoffering..."+params)
        CourseOffering coff=CourseOffering.findById(session.coffid)
        println("coff::"+coff)
        if(coff.courseofferingtype.name!='Self-Pace')
        {
            Date start_date = params.date('startdate', 'dd-MM-yyyy')
            Date end_date = params.date('enddate', 'dd-MM-yyyy')
            coff.start_date = start_date
            coff.end_date = end_date
        }
        coff.batch=params.batch
        Year year=Year.findByYear(params.year)
        coff.year=year
        AcademicYear ay=AcademicYear.findByAy(params.ay)
        coff.academicyear=ay
        Semester sem=Semester.findBySem(params.sem)
        coff.semester=sem
        CourseType coursetype=CourseType.findByType(params.coursetype)
        coff.coursetype=coursetype
        // CourseOfferingType courseofferingtype=CourseOfferingType.findByName(params.courseofferingtype)
        // coff.courseofferingtype=courseofferingtype
        if(params.archive.equals("Yes"))
            coff.isActive=false
        else
            coff.isActive=true
        coff.save(failOnError:true,flush:true)
        redirect(action: "processcourseoffering", params: [coursecodecoursename:session.coursecodecoursename])
    }
    def getCourseOfferingForGrade(){
        println("in getCourseOffering params:"+params)
        println("in getCourseOffering session:"+session)
        Course crs = Course.findById(params.cid)
        def coffr = CourseOffering.findAllByCourse(crs)
        println("Coffr:"+coffr)
        [coffr:coffr]
    }
    def menuOfSelectedCourseOffered(){
        println("in menuOfSelectedCourseOffered params:"+params)
        println("in menuOfSelectedCourseOffered session:"+session)
        CourseOffering coffr = CourseOffering.findById(params.coffrid)
        Course crs = coffr.course
        def co = CourseOutline.findAllByCourseAndIsDeleted(crs,false)
        ArrayList menuSubmenu = new ArrayList()
        //ArrayList ct = new ArrayList<CourseTopic>()
        for(CourseOutline c: co) {
            ArrayList<CourseOutline> mm = new ArrayList()
            mm.add(c)
            ArrayList<CourseTopic> submenu = CourseTopic.findAllByCourseoutlineAndIsDeleted(c,false)
            //submenu.addAll(crstop)
            mm.add(submenu)
            menuSubmenu.add(mm)
            println("C:"+c)
            //println("crstop:"+crstop)
            // ct.addAll(crstop)
        }
        println("Menu:"+menuSubmenu)
        //    println("CO:"+co)
        //    println("CT:"+ct)
        [menu:menuSubmenu,coffr:coffr]
    }
}
