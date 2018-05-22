package volp
import javax.imageio.ImageIO

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar
import java.awt.image.BufferedImage
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class InstructorController {

    InstructorService instructorService
    UplaodImageService uplaodImageService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond instructorService.list(params), model:[instructorCount: instructorService.count()]
    }

    def show(Long id) {
        respond instructorService.get(id)
    }

    def create() {
        respond new Instructor(params)
    }

    def save(Instructor instructor) {
        if (instructor == null) {
            notFound()
            return
        }

        try {
            instructorService.save(instructor)
        } catch (ValidationException e) {
            respond instructor.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'instructor.label', default: 'Instructor'), instructor.id])
                redirect instructor
            }
            '*' { respond instructor, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond instructorService.get(id)
    }

    def update(Instructor instructor) {
        if (instructor == null) {
            notFound()
            return
        }

        try {
            instructorService.save(instructor)
        } catch (ValidationException e) {
            respond instructor.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'instructor.label', default: 'Instructor'), instructor.id])
                redirect instructor
            }
            '*'{ respond instructor, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        instructorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'instructor.label', default: 'Instructor'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'instructor.label', default: 'Instructor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def instructor(){
        //session.uid=

        // println("instructor Session:"+session)
        Instructor ins = Instructor.findByUid(session.uid)
        session.fname = ins.person.firstName
        println("Ins:"+ins)
        ArrayList instructorcourses= new ArrayList()
        def tempcourses=Course.findAllByUsername(session.uid)
        instructorcourses.addAll(tempcourses)
        //let us find whteher this instrcutor is collaborative instructor of other course
        def courses=Course.list()
        for(Course crs:courses)
        {
            def instructors=crs.courseinstructor
            for(Instructor inst:instructors)
            {
                if(inst==ins)
                {
                    //match found add this course as collaborative instructor
                    println("Collaborative instructor of "+crs.course_name)
                    instructorcourses.add(crs)
                }
            }
        }
        println("----instructorcourses"+instructorcourses)
        //render("welcome")
        //image
//        String pathimg = grailsAttributes.getApplicationContext().getResource("/assets/images/CN123_2.jpg").getFile()
//        println("pathimg:"+pathimg)
        //end
        [ins:ins,instructorcourses:instructorcourses]
    }
    def addCourse(){
        Instructor ins = Instructor.findByUid(session.uid)
        session.msg = ""
        def crs_cat = CourseCategory.findAllByCoursecategoryIsNull()//CourseCategory.list()
        def dept = Department.list()
        def prog = Program.list()
        def cv = CourseVisibility.list()
        //def ints = Instructor.list()
        def ints = Instructor.findAllByUidNotEqual(session.uid)
        println("cat:"+crs_cat)


        [cc:crs_cat,dept:dept,prog:prog,ints:ints,cv:cv]
    }

    def subCat(){
        println("in subCat:"+params)
        if(params.msgflg=="0")
            session.msg=""
        CourseCategory cat = CourseCategory.findById(params.cat)
        session.cat = cat
        if(session.msg=="") {
            session.msg =  cat.name
        }
        else
            session.msg = session.msg + ":" + cat.name
        def scat = CourseCategory.findAllByCoursecategory(cat)
        //def check =
        String msg=""
        int check = 0
        if(scat.size==0)
        {
            check = 1;
            //msg=session.msg+" [Reached To Last Category]"
            msg=session.msg
            [scat:cat,msg:msg,check:check]
        }
        else {
            msg=session.msg
            [scat: scat, msg: msg,check:check]
        }
    }
    def processCourse(){

        println("in processCourse params..:"+params)
        println("in processCourse session:"+session)
        Instructor ins = Instructor.findByUid(session.uid)
        Department dept
        Program prg
        if(params.dept!="null")
            dept = Department.findById(params.dept)
        if(params.prog!="null")
            prg = Program.findById(params.prog)
        CourseVisibility crsv = CourseVisibility.findById(params.cv)
        def crs = Course.findAllByCourse_codeAndCourse_nameAndCourseowner(params.crs_code,params.crs_name,ins)
        println("CRS:"+crs)
        if(crs.size==0){
            Course c = new Course()
            c.course_code = params.crs_code
            c.course_name = params.crs_name
            String credit = params.credit
            println("credit:"+credit.trim().length())
            if(credit.trim().length()!=0)
                c.credit = Integer.parseInt(credit)
            else
                c.credit = 0
            c.username = session.uid
            c.creation_date = new java.util.Date()
            c.updation_date = new java.util.Date()
            c.creation_ip_address = request.getRemoteAddr()
            c.updation_ip_address = request.getRemoteAddr()
            c.coursecategory = session.cat
            c.department = dept
            c.program = prg
            c.coursevisibility = crsv
            c.courseowner = ins
            c.rating = -1.0

            String baseImageName = params.crs_name+"_"+ins.id//java.util.UUID.randomUUID().toString();
// Saving image in a folder assets/channelImage/, in the web-app, with the name: baseImageName
            def downloadedFile = request.getFile( "coursePic" )
            String fileUploaded = uplaodImageService.uploadFile( downloadedFile, "${baseImageName}.jpg", "assets/images/course/" )
            println("File:"+fileUploaded)
            if( fileUploaded ){
                c.imgpath = "assets/images/course"
                c.imgname = baseImageName+".jpg"
            }
            else
            {
                c.imgpath = "assets/images/course"
                c.imgname = "default.jpg"
            }
            //end image
            println("done")
            if(params.ins!=null) {
                if (params.ins.getClass().isArray()) {
                    println("multi ins")
                    for (int i = 0; i < params.ins.size(); i++) {
                        Instructor inss = Instructor.findById(params.ins[i])
                        c.addToCourseinstructor(inss)
                    }
                } else {
                    println("one ins")
                    Instructor inss = Instructor.findById(params.ins)
                    c.addToCourseinstructor(inss)
                    // c.save(flush: true,failOnError:true)
                }
            }
            if(params.feature!=null) {
                if (params.feature.getClass().isArray()) {
                    println("multi f")
                    for (int i = 0; i < params.feature.size(); i++) {
                        Features f = new Features()
                        f.feature_number = i+1//Integer.parseInt(params.featureno[i])
                        f.feature = params.feature[i]
                        f.username = session.uid
                        f.creation_date = new java.util.Date()
                        f.updation_date = new java.util.Date()
                        f.creation_ip_address = request.getRemoteAddr()
                        f.updation_ip_address = request.getRemoteAddr()
                        //f.addToFeatures(inss)
                        f.save(flush: true, failOnError: true)
                        c.addToFeatures(f)
                    }
                } else {
                    println("one f")
                    Features f = new Features()
                    f.feature_number = 1//Integer.parseInt(params.featureno)
                    f.feature = params.feature
                    f.username = session.uid
                    f.creation_date = new java.util.Date()
                    f.updation_date = new java.util.Date()
                    f.creation_ip_address = request.getRemoteAddr()
                    f.updation_ip_address = request.getRemoteAddr()
                    //f.addToFeatures(inss)
                    f.save(flush: true, failOnError: true)
                    c.addToFeatures(f)
                }
            }
            if(params.descno!=null) {
                if (params.descno.getClass().isArray()) {
                    println("multi d")
                    for (int i = 0; i < params.descno.size(); i++) {
                        Description d = new Description()
                        d.number = Integer.parseInt(params.descno[i])
                        d.description = params.description[i]
                        d.username = session.uid
                        d.creation_date = new java.util.Date()
                        d.updation_date = new java.util.Date()
                        d.creation_ip_address = request.getRemoteAddr()
                        d.updation_ip_address = request.getRemoteAddr()
                        //f.addToFeatures(inss)
                        d.save(flush: true, failOnError: true)
                        c.addToDescription(d)
                    }
                } else {
                    println("one d")
                    Description d = new Description()
                    d.number = Integer.parseInt(params.descno)
                    d.description = params.description
                    d.username = session.uid
                    d.creation_date = new java.util.Date()
                    d.updation_date = new java.util.Date()
                    d.creation_ip_address = request.getRemoteAddr()
                    d.updation_ip_address = request.getRemoteAddr()
                    //f.addToFeatures(inss)
                    d.save(flush: true, failOnError: true)
                    c.addToDescription(d)
                }

            }
            if(params.prerequisite!=null) {
                if (params.prerequisite.getClass().isArray()) {
                    println("multi p")
                    for (int i = 0; i < params.prerequisite.size(); i++) {
                        Prerequisite p = new Prerequisite()
                        p.number = i+1//Integer.parseInt(params.preno[i])
                        p.prerequisite = params.prerequisite[i]
                        p.username = session.uid
                        p.creation_date = new java.util.Date()
                        p.updation_date = new java.util.Date()
                        p.creation_ip_address = request.getRemoteAddr()
                        p.updation_ip_address = request.getRemoteAddr()
                        //f.addToFeatures(inss)
                        p.save(flush: true, failOnError: true)
                        c.addToPrerequisite(p)
                    }
                } else {
                    println("one p")
                    Prerequisite p = new Prerequisite()
                    p.number = 1//Integer.parseInt(params.preno)
                    p.prerequisite = params.prerequisite
                    p.username = session.uid
                    p.creation_date = new java.util.Date()
                    p.updation_date = new java.util.Date()
                    p.creation_ip_address = request.getRemoteAddr()
                    p.updation_ip_address = request.getRemoteAddr()
                    //f.addToFeatures(inss)
                    p.save(flush: true, failOnError: true)
                    c.addToPrerequisite(p)
                }
            }
            if(params.url!=null) {
                if (params.url.getClass().isArray()) {
                    println("multi u")
                    for (int i = 0; i < params.url.size(); i++) {
                        SoftwareURL u = new SoftwareURL()
                        u.url = params.url[i]
                        u.username = session.uid
                        u.creation_date = new java.util.Date()
                        u.updation_date = new java.util.Date()
                        u.creation_ip_address = request.getRemoteAddr()
                        u.updation_ip_address = request.getRemoteAddr()
                        u.save(flush: true, failOnError: true)
                        c.addToSoftwareurl(u)

                    }
                } else {
                    println("one u")
                    SoftwareURL u = new SoftwareURL()
                    u.url = params.url
                    u.username = session.uid
                    u.creation_date = new java.util.Date()
                    u.updation_date = new java.util.Date()
                    u.creation_ip_address = request.getRemoteAddr()
                    u.updation_ip_address = request.getRemoteAddr()
                    u.save(flush: true, failOnError: true)
                    c.addToSoftwareurl(u)
                }
            }
            c.save(flush: true,failOnError:true)

        }
        else
        {
            //flash msg and redireect
            flash.message = "Course Already Exist"
            redirect(action: "instructor")
            return
            println("present")
        }
        flash.message = "Course Added Successfully"
        redirect(action: "instructor")
        return
        //session.cat=""
        // session.msg=""
    }

    def editCourse(){
        println("in editCourse:"+session)
        Instructor ins = Instructor.findByUid(session.uid)
        def crs = Course.findAllByCourseowner(ins)
        println("crs:"+crs)
        [crs:crs]
    }
    def getCourseDetails()
    {
        println("in getCourseDetails:"+params)
        Course crs = Course.findById(params.cid)
        CourseCategory ccat = crs.coursecategory
        def features = crs.features
        println("feature:"+features)
        def cc = CourseCategory.list()
        def ins = crs.courseinstructor
        println("Ins:"+crs.courseinstructor)
        def newins = Instructor.findAllByUidNotEqual(session.uid)//Instructor.list()
        def cv = CourseVisibility.list()
        def desp = crs.description
        def url = crs.softwareurl
        def pre = crs.prerequisite
        println("image:"+crs.imgpath+crs.imgname)
        [cc:cc,ccat:ccat,crs:crs,ins:ins,newins:newins,features:features,cv:cv,desp:desp,url:url,pre:pre,cid:params.cid]
    }
    def processEditCourse(){
        println("in processEditCourse params:"+params)
        CourseCategory cat = CourseCategory.findById(params.cc)
        CourseVisibility cv = CourseVisibility.findById(params.cv)
        Course crs = Course.findById(params.cid)
        crs.course_code = params.code
        crs.course_name = params.name
        crs.credit = Integer.parseInt(params.credit)
        crs.username = session.uid
        crs.creation_date = new java.util.Date()
        crs.updation_date = new java.util.Date()
        crs.creation_ip_address = request.getRemoteAddr()
        crs.updation_ip_address = request.getRemoteAddr()
        crs.coursecategory = cat
        crs.coursevisibility = cv

        //image
        String baseImageName = params.name+"_"+crs.courseowner.id//java.util.UUID.randomUUID().toString();
// Saving image in a folder assets/channelImage/, in the web-app, with the name: baseImageName
        def downloadedFile = request.getFile( "coursePic" )
        String fileUploaded = uplaodImageService.uploadFile( downloadedFile, "${baseImageName}.jpg", "assets/images/course/" )
        println("File:"+fileUploaded)
        if( fileUploaded ){
            crs.imgpath = "assets/images/course"
            crs.imgname = baseImageName+".jpg"
        }
        //end image
        //hasmany

//deleting if any
        if(params.featuresdel!=null)
        {
            if(params.featuresdel.getClass().isArray())
            {
                for(int i=0;i<params.featuresdel.size();i++)
                {
                    Features f = Features.findById(params.featuresdel[i])
                    crs.removeFromFeatures(f)
                    f.delete()
                }
            }
            else {
                Features f = Features.findById(params.featuresdel)
                crs.removeFromFeatures(f)
                f.delete()
            }
        }
        else
            println("no feat")

        if(params.urldel!=null)
        {
            if(params.urldel.getClass().isArray())
            {
                for(int i=0;i<params.urldel.size();i++)
                {
                    SoftwareURL f = SoftwareURL.findById(params.urldel[i])
                    crs.removeFromSoftwareurl(f)
                    f.delete()
                }
            }
            else {
                SoftwareURL f = SoftwareURL.findById(params.urldel)
                crs.removeFromSoftwareurl(f)
                f.delete()
            }
        }
        else
            println("no url")

        if(params.predel!=null)
        {
            if(params.predel.getClass().isArray())
            {
                for(int i=0;i<params.predel.size();i++)
                {
                    Prerequisite f = Prerequisite.findById(params.predel[i])
                    crs.removeFromSoftwareurl(f)
                    f.delete()
                }
            }
            else {
                Prerequisite f = Prerequisite.findById(params.predel)
                crs.removeFromPrerequisite(f)
                f.delete()
            }
        }
        else
            println("no pre")

        if(params.despdel!=null)
        {
            if(params.despdel.getClass().isArray())
            {
                for(int i=0;i<params.despdel.size();i++)
                {
                    Description f = Description.findById(params.despdel[i])
                    crs.removeFromDescription(f)
                    f.delete()
                }
            }
            else {
                Description f = Description.findById(params.despdel)
                crs.removeFromDescription(f)
                f.delete()
            }
        }
        else
            println("no des")

        crs.courseinstructor.clear()

//saving has many if any
        if(params.ins!=null)
            if(params.ins.getClass().isArray())
            {
                println("multi ins")
                for(int i=0;i<params.ins.size();i++) {
                    Instructor inss = Instructor.findById(params.ins[i])
                    crs.addToCourseinstructor(inss)
                }
            }
            else
            {
                println("one ins")
                Instructor inss = Instructor.findById(params.ins)
                crs.addToCourseinstructor(inss)
                // c.save(flush: true,failOnError:true)
            }
        if(params.feature!=null)
            if(params.feature.getClass().isArray())
            {
                println("multi f")
                for(int i=0;i<params.feature.size();i++) {
                    Features f = new Features()
                    f.feature_number = Integer.parseInt(params.featureno[i])
                    f.feature = params.feature[i]
                    f.username = session.uid
                    f.creation_date = new java.util.Date()
                    f.updation_date = new java.util.Date()
                    f.creation_ip_address = request.getRemoteAddr()
                    f.updation_ip_address = request.getRemoteAddr()
                    //f.addToFeatures(inss)
                    f.save(flush: true,failOnError:true)
                    crs.addToFeatures(f)
                }
            }
            else
            {
                println("one f")
                Features f = new Features()
                f.feature_number = Integer.parseInt(params.featureno)
                f.feature = params.feature
                f.username = session.uid
                f.creation_date = new java.util.Date()
                f.updation_date = new java.util.Date()
                f.creation_ip_address = request.getRemoteAddr()
                f.updation_ip_address = request.getRemoteAddr()
                //f.addToFeatures(inss)
                f.save(flush: true,failOnError:true)
                crs.addToFeatures(f)
            }
        if(params.description!=null)
            if(params.description.getClass().isArray())
            {
                println("multi d")
                for(int i=0;i<params.description.size();i++) {
                    Description d = new Description()
                    d.number = Integer.parseInt(params.descno[i])
                    d.description = params.description[i]
                    d.username = session.uid
                    d.creation_date = new java.util.Date()
                    d.updation_date = new java.util.Date()
                    d.creation_ip_address = request.getRemoteAddr()
                    d.updation_ip_address = request.getRemoteAddr()
                    //f.addToFeatures(inss)
                    d.save(flush: true,failOnError:true)
                    crs.addToDescription(d)
                }
            }
            else
            {
                println("one d")
                Description d = new Description()
                d.number = Integer.parseInt(params.descno)
                d.description = params.description
                d.username = session.uid
                d.creation_date = new java.util.Date()
                d.updation_date = new java.util.Date()
                d.creation_ip_address = request.getRemoteAddr()
                d.updation_ip_address = request.getRemoteAddr()
                //f.addToFeatures(inss)
                d.save(flush: true,failOnError:true)
                crs.addToDescription(d)
            }
        if(params.prerequisite!=null)
            if(params.prerequisite.getClass().isArray())
            {
                println("multi p")
                for(int i=0;i<params.prerequisite.size();i++) {
                    Prerequisite p = new Prerequisite()
                    p.number = Integer.parseInt(params.preno[i])
                    p.prerequisite = params.prerequisite[i]
                    p.username = session.uid
                    p.creation_date = new java.util.Date()
                    p.updation_date = new java.util.Date()
                    p.creation_ip_address = request.getRemoteAddr()
                    p.updation_ip_address = request.getRemoteAddr()
                    //f.addToFeatures(inss)
                    p.save(flush: true,failOnError:true)
                    crs.addToPrerequisite(p)
                }
            }
            else
            {
                println("one p")
                Prerequisite p = new Prerequisite()
                p.number = Integer.parseInt(params.preno)
                p.prerequisite = params.prerequisite
                p.username = session.uid
                p.creation_date = new java.util.Date()
                p.updation_date = new java.util.Date()
                p.creation_ip_address = request.getRemoteAddr()
                p.updation_ip_address = request.getRemoteAddr()
                //f.addToFeatures(inss)
                p.save(flush: true,failOnError:true)
                crs.addToPrerequisite(p)
            }
        if(params.url!=null)
            if(params.url.getClass().isArray())
            {
                println("multi u")
                for(int i=0;i<params.url.size();i++) {
                    SoftwareURL u = new SoftwareURL()
                    u.url = params.url[i]
                    u.username = session.uid
                    u.creation_date = new java.util.Date()
                    u.updation_date = new java.util.Date()
                    u.creation_ip_address = request.getRemoteAddr()
                    u.updation_ip_address = request.getRemoteAddr()
                    u.save(flush: true,failOnError:true)
                    crs.addToSoftwareurl(u)

                }
            }
            else
            {
                println("one u")
                SoftwareURL u = new SoftwareURL()
                u.url = params.url
                u.username = session.uid
                u.creation_date = new java.util.Date()
                u.updation_date = new java.util.Date()
                u.creation_ip_address = request.getRemoteAddr()
                u.updation_ip_address = request.getRemoteAddr()
                u.save(flush: true,failOnError:true)
                crs.addToSoftwareurl(u)
            }
//saving
        crs.save(flush: true,failOnError:true)
        redirect(action: "addNewCoursesDetails", params: [uname: crs.courseowner.id,coursename:crs.id])
    }

    def CourseSubMenu(){

    }
    def instrcutorHelp(){

    }
    def addNewCoursesDetails()
    {
        println("in addNewCoursesDetails params:"+params)
        println("in addNewCoursesDetails session:"+session)

        Course cid=Course.findById(params.coursename)
        session.cid = cid.id
        def coffr = CourseOffering.findAllByCourse(cid)
        println("cid:"+cid)
        println("coffr:"+coffr)

        //for progress
        ArrayList<Integer> progress= new ArrayList() //0-outcomes/1-outline/2-topic/3-material/4-vdo
        def co = CourseOutcomes.findAllByCourseAndIsDeleted(cid,false)
        if(co.size()==0)
            progress.add(0)
        else
            progress.add(1)
        def col = CourseOutline.findAllByCourseAndIsDeleted(cid,false)
        if(col.size()==0)
            progress.add(0)
        else
            progress.add(1)
        int flag=0
        for(CourseOutline c:col)
        {
            def ct = CourseTopic.findAllByCourseoutlineAndIsDeleted(c,false)
            if(ct.size()>0)
            {
                flag=1
                break;
            }
        }

        if(flag==0)
            progress.add(0)
        else
            progress.add(1)

        def cm = CourseMaterial.findAllByCourseAndIsDeleted(cid,false)
        println("CMbC:"+cm.size())
        flag=0
        if(cm.size()==0) {
            for(CourseOutline c:col)
            {
                def cm1 = CourseMaterial.findAllByCourseoutlineAndIsDeleted(c,false)
                println("CMbCol:"+cm1.size())
                if(cm1.size()>0){
                    flag=1
                    break
                }
                def ct = CourseTopic.findAllByCourseoutlineAndIsDeleted(c,false)
                for(CourseTopic cc:ct){
                    def cm2 = CourseMaterial.findAllByCoursetopicAndIsDeleted(cc,false)
                    println("CMbCt:"+cm2.size())
                    if(cm2.size()>0){
                        flag=1
                        break
                    }
                }
            }

        }
        if(flag==0){
            if(cm.size()>0)
                progress.add(1)
            else
                progress.add(0)
        }
        else
            progress.add(1)

        def cv = CourseVideos.findAllByCourseAndIsDeleted(cid,false)
        flag=0
        if(cv.size()==0) {
            for(CourseOutline c:col)
            {
                def cm1 = CourseVideos.findAllByCourseoutlineAndIsDeleted(c,false)
                if(cm1.size()>0){
                    flag=1
                    break
                }
                def ct = CourseTopic.findAllByCourseoutlineAndIsDeleted(c,false)
                for(CourseTopic cc:ct){
                    def cm2 = CourseVideos.findAllByCoursetopicAndIsDeleted(cc,false)
                    if(cm2.size()>0){
                        flag=1
                        break
                    }
                }
            }

        }
        if(flag==0){
            if(cv.size()>0)
                progress.add(1)
            else
                progress.add(0)
        }
        else
            progress.add(1)

        println("Progress:(0-outcomes/1-outline/2-topic/3-material/4-vdo):"+progress)
        ArrayList commoncode=new ArrayList()
        for(CourseOffering coff:coffr)
        {
            SendCommonInvitationToLearner scitl=SendCommonInvitationToLearner.findByCourseoffering(coff)
            commoncode.add(scitl)
        }
        println("commoncode::"+commoncode)
        [cid:cid,coffr:coffr,progress:progress,commoncode:commoncode]
    }

    def courseAssessment(){

    }
    def getCourse(){
        println("in getCourse params:"+params)
        String []crsid = (params.crslst).split(",")
        ArrayList<Course> crslst = new ArrayList()
        for(int i=0;i<crsid.size();i++){
            Course c = Course.findById(crsid[i])
            crslst.add(c)
        }

        println(crslst)
        ArrayList<Course> listClone = new ArrayList()
        for (Course cc : crslst) {
            if(cc.course_name.matches("(?i)("+params.crs+").*")){
                listClone.add(cc);
            }
        }
        println(listClone)
        [listClone:listClone]
    }
    def profile() {
        def username = session.uid
        println("I am in Instructor profile")
        //collect gender information
        def genderInfo = Gender.list()
        println("genderInfo"+genderInfo)
        //pass gender information to profile page
        //Contact information
        Contact contactInfo
        //Address information
        def addressInfo
        def insData
        def yearInfo
        def orgType
        def userInfo = Login.findByUsername(username)
        String role = userInfo.usertype.type
        println("role"+role)
        if(userInfo.usertype.type == "Instructor")
        {
            println("i am in instructor")
            insData = Instructor.findByUid(userInfo.username)
            if(!insData)
            {
                println("if insData:::"+insData)
            }
            else
            {
                println("else insData:::"+insData)
                def oldPersonInfo = insData.person
                println("oldPersonInfo"+oldPersonInfo)
                contactInfo = Contact.findByPerson(oldPersonInfo)
                println("contactInfo"+contactInfo)
                addressInfo = Address.findAllByPerson(oldPersonInfo)
            }
        }
        if(userInfo.usertype.type == "Learner")
        {
            println("I am learner")
            insData = Learner.findByUid(userInfo.username)
            yearInfo = Year.list()
            println("yearInfo"+yearInfo.year)
            if(!insData)
            {
                println("if Learner insData:::"+insData)
            }
            else
            {
                println("else Learner insData:::"+insData)
                def oldPersonInfo = insData.person
                println("oldPersonInfo"+oldPersonInfo)
                contactInfo = Contact.findByPerson(oldPersonInfo)
                println("contactInfo"+contactInfo)
                addressInfo = Address.findAllByPerson(oldPersonInfo)
            }
        }
        if(userInfo.usertype.type == "Organization")
        {
            println("I am Organization")
            insData = Organization.findByUid(userInfo.username)
            orgType =  OrganizationType.list();
            if(!insData)
            {
                println("if Org insData:::"+insData)
            }
            else
            {
                println("else Org insData:::"+insData)
                contactInfo = Contact.findByOrganization(insData)
                println("contactInfo"+contactInfo)
                addressInfo = Address.findAllByOrganization(insData)
            }
        }
        [username:username,genderInfo:genderInfo,orgType:orgType,insData:insData,role:role,contactInfo: contactInfo,addressInfo:addressInfo,yearInfo:yearInfo]

    }
    def storeInstructorContactData()
    {
        println("i am in storeInstructorContactData params"+params)
        def username = session.uid
        def userInfo = Login.findByUsername(username)
        if(userInfo.usertype.type == "Instructor")
        {
            Instructor insData = Instructor.findByUid(userInfo.username)
            if(!insData) {
                println("Data is not avilable ")
                flash.message="Please fill up Personal information"
            }
            else
            {
                println("Data is avilable")
                Person oldPersonData = insData.person
                Contact oldContactInfo = Contact.findByPerson(oldPersonData)
                if(!oldContactInfo) {
                    println("Contact is null")
                    Contact insContact = new Contact()
                    insContact.mobile_no = params.mobile_no
                    insContact.ulternate_mobile_no = params.ulternate_mobile_no
                    insContact.alternate_email = params.alternate_email
                    insContact.telephone_no = params.telephone_no
                    insContact.fax = params.fax
                    insContact.website_url = params.website_url
                    insContact.username = session.uid
                    insContact.creation_date = new java.util.Date()
                    insContact.updation_date = new java.util.Date()
                    insContact.creation_ip_address = request.getRemoteAddr()
                    insContact.updation_ip_address = request.getRemoteAddr()
                    insContact.person = oldPersonData
                    //Oragnization Data
                    Organization personOrgData = null
                    insContact.organization = personOrgData
                    insContact.save(flush: true,failOnError: true)
                }
                else
                {
                    println("Update cantact")
                    oldContactInfo.mobile_no = params.mobile_no
                    oldContactInfo.ulternate_mobile_no = params.ulternate_mobile_no
                    oldContactInfo.alternate_email = params.alternate_email
                    oldContactInfo.telephone_no = params.telephone_no
                    oldContactInfo.fax = params.fax
                    oldContactInfo.website_url = params.website_url
                    oldContactInfo.username = session.uid
                    oldContactInfo.updation_date = new java.util.Date()
                    oldContactInfo.updation_ip_address = request.getRemoteAddr()
                    oldContactInfo.save(flush: true,failOnError: true)
                }
            }
        }
        if(userInfo.usertype.type == "Learner")
        {
            Learner insData = Learner.findByUid(userInfo.username)
            if(!insData) {
                println("Data is not avilable learner ")
                flash.message="Please fill up Personal information"
            }
            else
            {
                println("Data is avilable learner")
                Person oldPersonData = insData.person
                Contact oldContactInfo = Contact.findByPerson(oldPersonData)
                if(!oldContactInfo) {
                    println("learner Contact is null")
                    Contact insContact = new Contact()
                    insContact.mobile_no = params.mobile_no
                    insContact.ulternate_mobile_no = params.ulternate_mobile_no
                    insContact.alternate_email = params.alternate_email
                    insContact.telephone_no = params.telephone_no
                    insContact.fax = params.fax
                    insContact.website_url = params.website_url
                    insContact.username = session.uid
                    insContact.creation_date = new java.util.Date()
                    insContact.updation_date = new java.util.Date()
                    insContact.creation_ip_address = request.getRemoteAddr()
                    insContact.updation_ip_address = request.getRemoteAddr()
                    insContact.person = oldPersonData
                    //Oragnization Data
                    Organization personOrgData = null
                    insContact.organization = personOrgData
                    insContact.save(flush: true,failOnError: true)
                }
                else
                {
                    println("Update learner contact")
                    oldContactInfo.mobile_no = params.mobile_no
                    oldContactInfo.ulternate_mobile_no = params.ulternate_mobile_no
                    oldContactInfo.alternate_email = params.alternate_email
                    oldContactInfo.telephone_no = params.telephone_no
                    oldContactInfo.fax = params.fax
                    oldContactInfo.website_url = params.website_url
                    oldContactInfo.username = session.uid
                    oldContactInfo.updation_date = new java.util.Date()
                    oldContactInfo.updation_ip_address = request.getRemoteAddr()
                    oldContactInfo.save(flush: true,failOnError: true)
                }
            }
        }
        if(userInfo.usertype.type == "Organization")
        {
            Organization insData = Organization.findByUid(userInfo.username)
            if(!insData) {
                println("Data is not avilable ")
                flash.message="Please fill up Personal information"
            }
            else
            {
                println("org Data is avilable")
                Contact oldContactInfo = Contact.findByOrganization(insData)
                if(!oldContactInfo) {
                    println("Contact is null")
                    Contact insContact = new Contact()
                    insContact.mobile_no = params.mobile_no
                    insContact.ulternate_mobile_no = params.ulternate_mobile_no
                    insContact.alternate_email = params.alternate_email
                    insContact.telephone_no = params.telephone_no
                    insContact.fax = params.fax
                    insContact.website_url = params.website_url
                    insContact.username = session.uid
                    insContact.creation_date = new java.util.Date()
                    insContact.updation_date = new java.util.Date()
                    insContact.creation_ip_address = request.getRemoteAddr()
                    insContact.updation_ip_address = request.getRemoteAddr()
                    insContact.organization = insData
                    //Oragnization Data
                    Person personOrgData = null
                    insContact.person = personOrgData
                    insContact.save(flush: true,failOnError: true)
                }
                else
                {
                    println("Update cantact")
                    oldContactInfo.mobile_no = params.mobile_no
                    oldContactInfo.ulternate_mobile_no = params.ulternate_mobile_no
                    oldContactInfo.alternate_email = params.alternate_email
                    oldContactInfo.telephone_no = params.telephone_no
                    oldContactInfo.fax = params.fax
                    oldContactInfo.website_url = params.website_url
                    oldContactInfo.username = session.uid
                    oldContactInfo.updation_date = new java.util.Date()
                    oldContactInfo.updation_ip_address = request.getRemoteAddr()
                    oldContactInfo.save(flush: true,failOnError: true)
                }
            }
        }
        redirect(controller: "instructor", action: "profile")
        //render("Hi")
    }
    def storeInstructorAddressData()
    {
        println("i am in storeInstructorAddressData params"+params)
        def username = session.uid
        def userInfo = Login.findByUsername(username)
        if(userInfo.usertype.type == "Instructor")
        {
            Instructor insData = Instructor.findByUid(userInfo.username)
            if(!insData) {
                println("Data is not avilable ")
                flash.message="Please fill up Personal information"
            }
            else
            {
                println("Data is avilable")
                Person oldPersonData = insData.person
                Address oldAddressInfo = Address.findByPerson(oldPersonData)
                if(!oldAddressInfo) {

                    println("Contact is null")
                    Address insAddress = new Address()
                    insAddress.country = params.country
                    insAddress.state = params.state
                    insAddress.city = params.city
                    insAddress.district = params.district
                    insAddress.street = params.street
                    insAddress.pin = params.pin
                    insAddress.username = session.uid
                    insAddress.creation_date = new java.util.Date()
                    insAddress.updation_date = new java.util.Date()
                    insAddress.creation_ip_address = request.getRemoteAddr()
                    insAddress.updation_ip_address = request.getRemoteAddr()
                    insAddress.person = oldPersonData
                    // find AddressType local or pemannent
                    AddressType addType = AddressType.findByType("Local")
                    insAddress.addresstype = addType
                    //Oragnization Data
                    Organization personOrgData = null
                    insAddress.organization = personOrgData
                    insAddress.save(flush: true,failOnError: true)
                    if(params.billingtoo =="on")
                    {
                        println("i am in perment address")
                        Address insAddress1 = new Address()
                        insAddress1.country = params.altcountry
                        insAddress1.state = params.altstate
                        insAddress1.city = params.altcity
                        insAddress1.district = params.altdistrict
                        insAddress1.street = params.altstreet
                        insAddress1.pin = params.altpin
                        insAddress1.username = session.uid
                        insAddress1.creation_date = new java.util.Date()
                        insAddress1.updation_date = new java.util.Date()
                        insAddress1.creation_ip_address = request.getRemoteAddr()
                        insAddress1.updation_ip_address = request.getRemoteAddr()
                        insAddress1.person = oldPersonData
                        AddressType addType1 = AddressType.findByType("Permanent")
                        insAddress1.addresstype = addType1
                        //Oragnization Data
                        Organization personOrgData1 = null
                        insAddress1.organization = personOrgData1
                        insAddress1.save(flush: true,failOnError: true)
                    }
                    else
                    {
                        Address insAddress1 = new Address()
                        insAddress1.country = params.altcountry
                        insAddress1.state = params.altstate
                        insAddress1.city = params.altcity
                        insAddress1.district = params.altdistrict
                        insAddress1.street = params.altstreet
                        insAddress1.pin = params.altpin
                        insAddress1.username = session.uid
                        insAddress1.creation_date = new java.util.Date()
                        insAddress1.updation_date = new java.util.Date()
                        insAddress1.creation_ip_address = request.getRemoteAddr()
                        insAddress1.updation_ip_address = request.getRemoteAddr()
                        insAddress1.person = oldPersonData
                        AddressType addType1 = AddressType.findByType("Permanent")
                        insAddress1.addresstype = addType1
                        //Oragnization Data
                        Organization personOrgData1 = null
                        insAddress1.organization = personOrgData1
                        insAddress1.save(flush: true,failOnError: true)
                    }
                    //insAddress.save(flush: true, failOnError: true)

                }
                else {
                    println("Update instructor cantact" + params)
                    def oldAddressInfo1 = Address.findAllByPerson(oldPersonData)
                    println("oldAddressInfo1" + oldAddressInfo1.size())
                    if (oldAddressInfo1.size() <= 1) {
                        println("change permanent address instructor")
                        if(params.billingtoo =="on")
                        {
                            println("i am in learner perment address")
                            Address insAddress1 = new Address()
                            insAddress1.country = params.altcountry
                            insAddress1.state = params.altstate
                            insAddress1.city = params.altcity
                            insAddress1.district = params.altdistrict
                            insAddress1.street = params.altstreet
                            insAddress1.pin = params.altpin
                            insAddress1.username = session.uid
                            insAddress1.creation_date = new java.util.Date()
                            insAddress1.updation_date = new java.util.Date()
                            insAddress1.creation_ip_address = request.getRemoteAddr()
                            insAddress1.updation_ip_address = request.getRemoteAddr()
                            insAddress1.person = oldPersonData
                            AddressType addType1 = AddressType.findByType("Permanent")
                            insAddress1.addresstype = addType1
                            //Oragnization Data
                            Organization personOrgData1 = null
                            insAddress1.organization = personOrgData1
                            insAddress1.save(flush: true,failOnError: true)
                        }
                    }
                    else{
                        for (int c = 0; c < 2; c++) {
                            oldAddressInfo1[c].country = params.country[c]
                            oldAddressInfo1[c].state = params.state[c]
                            oldAddressInfo1[c].city = params.city[c]
                            oldAddressInfo1[c].district = params.district[c]
                            oldAddressInfo1[c].street = params.street[c]
                            oldAddressInfo1[c].pin = params.pin[c]
                            AddressType addType
                            if (c == 0) {
                                addType = AddressType.findByType("Local")
                            } else {
                                addType = AddressType.findByType("Permanent")
                            }
                            oldAddressInfo1[c].addresstype = addType
                            oldAddressInfo1[c].username = session.uid
                            oldAddressInfo1[c].updation_date = new java.util.Date()
                            oldAddressInfo1[c].updation_ip_address = request.getRemoteAddr()
                            oldAddressInfo1[c].save(flush: true, failOnError: true)
                        }
                    }
                }
            }
        }
        if(userInfo.usertype.type == "Learner")
        {
            Learner insData = Learner.findByUid(userInfo.username)
            if(!insData) {
                println("Data is not avilable learner ")
                flash.message="Please fill up Personal information"
            }
            else
            {
                println("Data is avilable")
                Person oldPersonData = insData.person
                Address oldAddressInfo = Address.findByPerson(oldPersonData)
                if(!oldAddressInfo) {
                    println("Contact is null")
                    Address insAddress = new Address()
                    insAddress.country = params.country
                    insAddress.state = params.state
                    insAddress.city = params.city
                    insAddress.district = params.district
                    insAddress.street = params.street
                    insAddress.pin = params.pin
                    insAddress.username = session.uid
                    insAddress.creation_date = new java.util.Date()
                    insAddress.updation_date = new java.util.Date()
                    insAddress.creation_ip_address = request.getRemoteAddr()
                    insAddress.updation_ip_address = request.getRemoteAddr()
                    insAddress.person = oldPersonData
                    // find AddressType local or pemannent
                    AddressType addType = AddressType.findByType("Local")
                    insAddress.addresstype = addType
                    //Oragnization Data
                    Organization personOrgData = null
                    insAddress.organization = personOrgData
                    insAddress.save(flush: true,failOnError: true)
                    if(params.billingtoo =="on")
                    {
                        println("i am in learner perment address")
                        Address insAddress1 = new Address()
                        insAddress1.country = params.altcountry
                        insAddress1.state = params.altstate
                        insAddress1.city = params.altcity
                        insAddress1.district = params.altdistrict
                        insAddress1.street = params.altstreet
                        insAddress1.pin = params.altpin
                        insAddress1.username = session.uid
                        insAddress1.creation_date = new java.util.Date()
                        insAddress1.updation_date = new java.util.Date()
                        insAddress1.creation_ip_address = request.getRemoteAddr()
                        insAddress1.updation_ip_address = request.getRemoteAddr()
                        insAddress1.person = oldPersonData
                        AddressType addType1 = AddressType.findByType("Permanent")
                        insAddress1.addresstype = addType1
                        //Oragnization Data
                        Organization personOrgData1 = null
                        insAddress1.organization = personOrgData1
                        insAddress1.save(flush: true,failOnError: true)
                    }
                    else
                    {
                        println("i am in learner perment address")
                        Address insAddress1 = new Address()
                        insAddress1.country = params.altcountry
                        insAddress1.state = params.altstate
                        insAddress1.city = params.altcity
                        insAddress1.district = params.altdistrict
                        insAddress1.street = params.altstreet
                        insAddress1.pin = params.altpin
                        insAddress1.username = session.uid
                        insAddress1.creation_date = new java.util.Date()
                        insAddress1.updation_date = new java.util.Date()
                        insAddress1.creation_ip_address = request.getRemoteAddr()
                        insAddress1.updation_ip_address = request.getRemoteAddr()
                        insAddress1.person = oldPersonData
                        AddressType addType1 = AddressType.findByType("Permanent")
                        insAddress1.addresstype = addType1
                        //Oragnization Data
                        Organization personOrgData1 = null
                        insAddress1.organization = personOrgData1
                        insAddress1.save(flush: true,failOnError: true)
                    }
                    insAddress.save(flush: true, failOnError: true)

                }
                else
                {
                    println("Update Learner cantact"+params)
                    def oldAddressInfo1 = Address.findAllByPerson(oldPersonData)
                    println("length"+oldAddressInfo1.size)
                    if(oldAddressInfo1.size==1){
                        oldAddressInfo1[0].country = params.country
                        oldAddressInfo1[0].state = params.state
                        oldAddressInfo1[0].city = params.city
                        oldAddressInfo1[0].district = params.district
                        oldAddressInfo1[0].street = params.street
                        oldAddressInfo1[0].pin = params.pin
                        AddressType addType = AddressType.findByType("Local")
                        oldAddressInfo1[0].addresstype = addType
                        oldAddressInfo1[0].username = session.uid
                        oldAddressInfo1[0].updation_date = new java.util.Date()
                        oldAddressInfo1[0].updation_ip_address = request.getRemoteAddr()
                        oldAddressInfo1[0].save(flush: true, failOnError: true)
                        if(params.billingtoo =="on")
                        {
                            println("i am in learner perment address")
                            Address insAddress1 = new Address()
                            insAddress1.country = params.altcountry
                            insAddress1.state = params.altstate
                            insAddress1.city = params.altcity
                            insAddress1.district = params.altdistrict
                            insAddress1.street = params.altstreet
                            insAddress1.pin = params.altpin
                            insAddress1.username = session.uid
                            insAddress1.creation_date = new java.util.Date()
                            insAddress1.updation_date = new java.util.Date()
                            insAddress1.creation_ip_address = request.getRemoteAddr()
                            insAddress1.updation_ip_address = request.getRemoteAddr()
                            insAddress1.person = oldPersonData
                            AddressType addType1 = AddressType.findByType("Permanent")
                            insAddress1.addresstype = addType1
                            //Oragnization Data
                            Organization personOrgData1 = null
                            insAddress1.organization = personOrgData1
                            insAddress1.save(flush: true,failOnError: true)
                        }
                    }
                    else {
                        for (int c = 0; c < 2; c++) {
                            oldAddressInfo1[c].country = params.country[c]
                            oldAddressInfo1[c].state = params.state[c]
                            oldAddressInfo1[c].city = params.city[c]
                            oldAddressInfo1[c].district = params.district[c]
                            oldAddressInfo1[c].street = params.street[c]
                            oldAddressInfo1[c].pin = params.pin[c]
                            AddressType addType
                            if (c == 0) {
                                addType = AddressType.findByType("Local")
                            } else {
                                addType = AddressType.findByType("Permanent")
                            }
                            oldAddressInfo1[c].addresstype = addType
                            oldAddressInfo1[c].username = session.uid
                            oldAddressInfo1[c].updation_date = new java.util.Date()
                            oldAddressInfo1[c].updation_ip_address = request.getRemoteAddr()
                            oldAddressInfo1[c].save(flush: true, failOnError: true)
                        }
                    }

                }
            }
        }
        if(userInfo.usertype.type == "Organization")
        {
            Organization insData = Organization.findByUid(userInfo.username)
            if(!insData) {
                println("Data is not avilable learner ")
                flash.message="Please fill up Personal information"
            }
            else
            {
                println("Data is avilable")
                Address oldAddressInfo = Address.findByOrganization(insData)
                if(!oldAddressInfo) {
                    println("Contact is null")
                    Address insAddress = new Address()
                    insAddress.country = params.country
                    insAddress.state = params.state
                    insAddress.city = params.city
                    insAddress.district = params.district
                    insAddress.street = params.street
                    insAddress.pin = params.pin
                    insAddress.username = session.uid
                    insAddress.creation_date = new java.util.Date()
                    insAddress.updation_date = new java.util.Date()
                    insAddress.creation_ip_address = request.getRemoteAddr()
                    insAddress.updation_ip_address = request.getRemoteAddr()
                    insAddress.organization = insData
                    // find AddressType local or pemannent
                    AddressType addType = AddressType.findByType("Local")
                    insAddress.addresstype = addType
                    //Oragnization Data
                    Person personOrgData = null
                    insAddress.person = personOrgData
                    //insAddress.save(flush: true,failOnError: true)
                    if(params.billingtoo =="on")
                    {
                        println("i am in learner perment address")
                        Address insAddress1 = new Address()
                        insAddress1.country = params.altcountry
                        insAddress1.state = params.altstate
                        insAddress1.city = params.altcity
                        insAddress1.district = params.altdistrict
                        insAddress1.street = params.altstreet
                        insAddress1.pin = params.altpin
                        insAddress1.username = session.uid
                        insAddress1.creation_date = new java.util.Date()
                        insAddress1.updation_date = new java.util.Date()
                        insAddress1.creation_ip_address = request.getRemoteAddr()
                        insAddress1.updation_ip_address = request.getRemoteAddr()
                        insAddress1.organization = insData
                        AddressType addType1 = AddressType.findByType("Permanent")
                        insAddress1.addresstype = addType1
                        //Oragnization Data
                        Person personOrgData1 = null
                        insAddress1.person = personOrgData1
                        insAddress1.save(flush: true,failOnError: true)
                    }
                    else
                    {
                        println("i am in learner perment address")
                        Address insAddress1 = new Address()
                        insAddress1.country = params.altcountry
                        insAddress1.state = params.altstate
                        insAddress1.city = params.altcity
                        insAddress1.district = params.altdistrict
                        insAddress1.street = params.altstreet
                        insAddress1.pin = params.altpin
                        insAddress1.username = session.uid
                        insAddress1.creation_date = new java.util.Date()
                        insAddress1.updation_date = new java.util.Date()
                        insAddress1.creation_ip_address = request.getRemoteAddr()
                        insAddress1.updation_ip_address = request.getRemoteAddr()
                        insAddress1.organization = insData
                        AddressType addType1 = AddressType.findByType("Permanent")
                        insAddress1.addresstype = addType1
                        //Oragnization Data
                        Person personOrgData1 = null
                        insAddress1.person = personOrgData1
                        insAddress1.save(flush: true,failOnError: true)
                    }
                    insAddress.save(flush: true, failOnError: true)

                }
                else
                {
                    println("Update Org cantact"+params)
                    def oldAddressInfo1 = Address.findAllByOrganization(insData)
                    println("length"+oldAddressInfo1.size)
                    if(oldAddressInfo1.size==1){
                        oldAddressInfo1[0].country = params.country
                        oldAddressInfo1[0].state = params.state
                        oldAddressInfo1[0].city = params.city
                        oldAddressInfo1[0].district = params.district
                        oldAddressInfo1[0].street = params.street
                        oldAddressInfo1[0].pin = params.pin
                        AddressType addType = AddressType.findByType("Local")
                        oldAddressInfo1[0].addresstype = addType
                        oldAddressInfo1[0].username = session.uid
                        oldAddressInfo1[0].updation_date = new java.util.Date()
                        oldAddressInfo1[0].updation_ip_address = request.getRemoteAddr()
                        oldAddressInfo1[0].save(flush: true, failOnError: true)
                        if(params.billingtoo =="on")
                        {
                            println("i am in learner perment address")
                            Address insAddress1 = new Address()
                            insAddress1.country = params.altcountry
                            insAddress1.state = params.altstate
                            insAddress1.city = params.altcity
                            insAddress1.district = params.altdistrict
                            insAddress1.street = params.altstreet
                            insAddress1.pin = params.altpin
                            insAddress1.username = session.uid
                            insAddress1.creation_date = new java.util.Date()
                            insAddress1.updation_date = new java.util.Date()
                            insAddress1.creation_ip_address = request.getRemoteAddr()
                            insAddress1.updation_ip_address = request.getRemoteAddr()
                            insAddress1.organization = insData
                            AddressType addType1 = AddressType.findByType("Permanent")
                            insAddress1.addresstype = addType1
                            //Oragnization Data
                            Person personOrgData1 = null
                            insAddress1.person = personOrgData1
                            insAddress1.save(flush: true,failOnError: true)
                        }
                    }
                    else {
                        for (int c = 0; c < 2; c++) {
                            oldAddressInfo1[c].country = params.country[c]
                            oldAddressInfo1[c].state = params.state[c]
                            oldAddressInfo1[c].city = params.city[c]
                            oldAddressInfo1[c].district = params.district[c]
                            oldAddressInfo1[c].street = params.street[c]
                            oldAddressInfo1[c].pin = params.pin[c]
                            AddressType addType
                            if (c == 0) {
                                addType = AddressType.findByType("Local")
                            } else {
                                addType = AddressType.findByType("Permanent")
                            }
                            oldAddressInfo1[c].addresstype = addType
                            oldAddressInfo1[c].username = session.uid
                            oldAddressInfo1[c].updation_date = new java.util.Date()
                            oldAddressInfo1[c].updation_ip_address = request.getRemoteAddr()
                            oldAddressInfo1[c].save(flush: true, failOnError: true)
                        }
                    }

                }
            }
        }
        redirect(controller: "instructor", action: "profile")
        //render("Hi")
    }
    def storeInstructorData()
    {
        println("i am in storeInstructorData params"+params)
        def username = session.uid
        def userInfo = Login.findByUsername(username)
        if(userInfo.usertype.type == "Instructor")
        {
            Instructor insData = Instructor.findByUid(userInfo.username)
            if(!insData)
            {
                println("Data is avilable ")
                Instructor insData1 = new Instructor()
                insData1.employee_code=""
                insData1.uid=session.uid
                insData1.username=session.uid
                insData1.rating=0
                insData1.creation_date=new java.util.Date()
                insData1.updation_date=new java.util.Date()
                insData1.creation_ip_address=request.getRemoteAddr()
                insData1.updation_ip_address=request.getRemoteAddr()
                String sDate1 = params.date_of_birth

                SimpleDateFormat formatter1
                if(sDate1.size()>10)
                {
                    println("Date change.......test")
                    formatter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                }
                else {

                    formatter1 = new SimpleDateFormat("dd/MM/yyyy");

                }
                Person instructorPersonData = new Person()
                instructorPersonData.email=session.uid
                instructorPersonData.firstName = params.firstName
                instructorPersonData.middleName = params.middleName
                instructorPersonData.lastName = params.lastName
                formatter1=new SimpleDateFormat("dd/MM/yy");
                instructorPersonData.date_of_birth = formatter1.parse(params.date_of_birth)
                instructorPersonData.highest_qualification = ''
                instructorPersonData.short_description=''
                instructorPersonData.skills=''
                instructorPersonData.username=session.uid
                instructorPersonData.creation_date= new java.util.Date()
                instructorPersonData.updation_date= new java.util.Date()
                instructorPersonData.creation_ip_address= request.getRemoteAddr()
                instructorPersonData.updation_ip_address= request.getRemoteAddr()
                println("save data")
                //Gender information
                def insGenderInfo = Gender.findById(params.gender)
                println("insGenderInfo"+insGenderInfo.type)
                instructorPersonData.gender = insGenderInfo

                //Logo information
                int flag =0
                def fp=FolderPath.list()
                def file = request.getFile("profilePic")
                print("file"+file + "fp"+fp.path)
                String image_path=fp[0].path
                String image_file_name=""
                String fullPath="";
                if(file.empty)
                {
                    flash.message = "File cannot be empty"
                    println("File cannot be empty")
                    flag=1
                }
                else
                {
                    image_file_name=file.originalFilename
                    fullPath=image_path+"//"+image_file_name
                    file.transferTo(new File(fullPath))
                    println("File uploaded successfully..")
                }

                Logo personLogo = new Logo()
                if(flag==1) {
                }
                else{
                    personLogo.username =session.uid
                    personLogo.creation_ip_address=request.getRemoteAddr()
                    personLogo.updation_ip_address=request.getRemoteAddr()
                    personLogo.creation_date = new java.util.Date()
                    personLogo.updation_date = new java.util.Date()
                    personLogo.logo_name = image_file_name
                    personLogo.logo_path = fullPath
                    instructorPersonData.addToLogos(personLogo)
                    personLogo.save(flush: true,failOnError: true)
                }

                //Social account is null
                /*               Social instructorSocial =new Social()
                               instructorSocial.site_url=''
                               instructorSocial.username=session.uid
                               instructorSocial.updation_date=new java.util.Date()
                               instructorSocial.creation_date=new java.util.Date()
                               instructorSocial.creation_ip_address=request.getRemoteAddr()
                               instructorSocial.updation_ip_address=request.getRemoteAddr()
                               instructorSocial.save(flush: true,failOnError: true)
                               instructorPersonData.addToSocial(instructorSocial)
                               instructorPersonData.save(flush: true,failOnError: true)*/
                println("instructorPersonData"+instructorPersonData)
                //instructor data save
                println("session.uid"+session.uid)
                println("insData"+insData)
                insData1.person = instructorPersonData

                //Organization data is null
                Organization instructorOrgData =null
                //Designation data is null
                Designation instructorDesignationData =null
                //Program is null
                Program instructorProgData = null

                insData1.organization = instructorOrgData
                insData1.designation = instructorDesignationData
                insData1.program = instructorProgData
                insData1.save(flush: true, failOnError: true)
                println("if insData:::"+insData)
            }
            else
            {
                println("insData is modified :::"+insData)
                Person oldPersonData = insData.person
                oldPersonData.firstName=params.firstName
                oldPersonData.middleName=params.middleName
                oldPersonData.lastName=params.lastName
                oldPersonData.updation_date =new java.util.Date()
                oldPersonData.updation_ip_address=request.getRemoteAddr()
                String sDate1 = params.date_of_birth
                println("sDate1"+sDate1.size())
                SimpleDateFormat formatter1
                if(sDate1.size()>10)
                {
                    formatter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                }
                else {
                    formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                }
                oldPersonData.date_of_birth = formatter1.parse(sDate1)
                def insGenderInfo = Gender.findById(params.gender)
                println("insGenderInfo"+insGenderInfo.type)
                oldPersonData.gender = insGenderInfo

                if(oldPersonData.logos.logo_path.empty)
                {
                    println("image path empty")
                    int flag = 0
                    def fp = FolderPath.list()
                    def file = request.getFile("profilePic")
                    print("file" + file + "fp" + fp.path)
                    String image_path = fp[0].path
                    String image_file_name = ""
                    String fullPath = "";
                    if (file.empty) {
                        flash.message = "File cannot be empty"
                        println("File cannot be empty")
                        flag = 1
                    } else {
                        image_file_name = file.originalFilename
                        fullPath = image_path + "//" + image_file_name
                        file.transferTo(new File(fullPath))
                        println("File uploaded successfully..")
                    }
                    if(!flag) {
                        Logo personLogo = new Logo()
                        personLogo.username = session.uid//insert username
                        personLogo.creation_ip_address = request.getRemoteAddr()
                        personLogo.updation_ip_address = request.getRemoteAddr()
                        personLogo.creation_date = new java.util.Date()
                        personLogo.updation_date = new java.util.Date()
                        personLogo.logo_name = image_file_name
                        personLogo.logo_path = fullPath
                        oldPersonData.addToLogos(personLogo)
                    }
                }
                else
                {
                    println("i am in image modify")
                    println("params"+params)
                    if(params.changePicture=="pictureChange") {
                        println("params"+params)

                        int flag = 0
                        def fp = FolderPath.list()
                        def file = request.getFile("profilePic")
                        print("file" + file + "fp" + fp.path)
                        String image_path = fp[0].path
                        String image_file_name = ""
                        String fullPath = "";
                        if (file.empty) {
                            flash.message = "File cannot be empty"
                            println("File cannot be empty")
                            flag = 1
                        } else {
                            image_file_name = file.originalFilename
                            fullPath = image_path + "//" + image_file_name
                            file.transferTo(new File(fullPath))
                            println("File uploaded successfully..")
                        }
                        if(!flag) {
                            Logo f = oldPersonData.logos[0]
                            println("logo" + f)
                            oldPersonData.removeFromLogos(f)
                            f.delete()
                            Logo personLogo = new Logo()
                            personLogo.username = session.uid//insert username
                            personLogo.creation_ip_address = request.getRemoteAddr()
                            personLogo.updation_ip_address = request.getRemoteAddr()
                            personLogo.creation_date = new java.util.Date()
                            personLogo.updation_date = new java.util.Date()
                            personLogo.logo_name = image_file_name
                            personLogo.logo_path = fullPath
                            oldPersonData.addToLogos(personLogo)
                        }
                    }
                }
                insData.person = oldPersonData
                insData.save(flush: true,failOnError: true)
            }
        }
        if(userInfo.usertype.type == "Learner")
        {
            println("Lerner data")
            Learner insData = Learner.findByUid(userInfo.username)
            println("learn information"+insData)
            if(!insData)
            {
                println("Learner Data is avilable ")
                Learner insData1 = new Learner()
                insData1.registration_number=params.registration_number
                insData1.uid=session.uid
                insData1.username=session.uid
                insData1.creation_date=new java.util.Date()
                insData1.updation_date=new java.util.Date()
                insData1.creation_ip_address=request.getRemoteAddr()
                insData1.updation_ip_address=request.getRemoteAddr()
                String sDate1 = params.date_of_birth
                SimpleDateFormat formatter1
                if(sDate1.size()>10)
                {
                    formatter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                }
                else {
                    formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                }

                Person instructorPersonData = new Person()
                instructorPersonData.email=session.uid
                instructorPersonData.firstName = params.firstName
                instructorPersonData.middleName = params.middleName
                instructorPersonData.lastName = params.lastName
                instructorPersonData.date_of_birth = formatter1.parse(sDate1)
                instructorPersonData.highest_qualification = ''
                instructorPersonData.short_description=''
                instructorPersonData.skills=''
                instructorPersonData.username=session.uid
                instructorPersonData.creation_date= new java.util.Date()
                instructorPersonData.updation_date= new java.util.Date()
                instructorPersonData.creation_ip_address= request.getRemoteAddr()
                instructorPersonData.updation_ip_address= request.getRemoteAddr()
                println("save data")
                //Gender information
                def insGenderInfo = Gender.findById(params.gender)
                println("insGenderInfo"+insGenderInfo.type)
                instructorPersonData.gender = insGenderInfo
                //year information
                def learnYearInfo = Year.findById(params.year)
                //logo information
                int flag =0
                def fp=FolderPath.list()
                def file = request.getFile("profilePic")
                print("file"+file + "fp"+fp.path)
                String image_path=fp[0].path
                String image_file_name=""
                String fullPath="";
                if(file.empty)
                {
                    flash.message = "File cannot be empty"
                    println("File cannot be empty")
                    flag=1
                }
                else
                {
                    image_file_name=file.originalFilename
                    fullPath=image_path+"//"+image_file_name
                    file.transferTo(new File(fullPath))
                    println("File uploaded successfully..")
                }

                Logo personLogo = new Logo()
                if(flag==1) {
                    println("i am in flag")
                }
                else{
                    personLogo.username =session.uid
                    personLogo.creation_ip_address=request.getRemoteAddr()
                    personLogo.updation_ip_address=request.getRemoteAddr()
                    personLogo.creation_date = new java.util.Date()
                    personLogo.updation_date = new java.util.Date()
                    personLogo.logo_name = image_file_name
                    personLogo.logo_path = fullPath
                    instructorPersonData.addToLogos(personLogo)
                    personLogo.save(flush: true,failOnError: true)
                }


                //Social account is null
                /*Social instructorSocial =new Social()
                instructorSocial.site_url=''
                instructorSocial.username=session.uid
                instructorSocial.updation_date=new java.util.Date()
                instructorSocial.creation_date=new java.util.Date()
                instructorSocial.creation_ip_address=request.getRemoteAddr()
                instructorSocial.updation_ip_address=request.getRemoteAddr()
                instructorSocial.save(flush: true,failOnError: true)
                instructorPersonData.addToSocial(instructorSocial)
                instructorPersonData.save(flush: true,failOnError: true)*/
                println("instructorPersonData"+instructorPersonData)
                //instructor data save
                println("session.uid"+session.uid)
                println("insData"+insData)
                insData1.person = instructorPersonData

                //Organization data is null
                Organization instructorOrgData =null
                //Designation data is null
                Module learnerModule =null
                //Program is null
                Program instructorProgData = null

                insData1.organization = instructorOrgData
                insData1.current_module = learnerModule
                insData1.program = instructorProgData
                insData1.current_year = learnYearInfo
                insData1.save(flush: true, failOnError: true)
                println("if Learner insData:::"+insData)
            }
            else
            {
                println("Learner insData is modified :::"+insData)
                Person oldPersonData = insData.person
                oldPersonData.firstName=params.firstName
                oldPersonData.middleName=params.middleName
                oldPersonData.lastName=params.lastName
                String sDate1 = params.date_of_birth
                SimpleDateFormat formatter1
                if(sDate1.size()>10)
                {
                    formatter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                }
                else{
                    formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                }
                oldPersonData.updation_date =new java.util.Date()
                oldPersonData.date_of_birth = formatter1.parse(sDate1)
                oldPersonData.updation_ip_address=request.getRemoteAddr()
                def insGenderInfo = Gender.findById(params.gender)
                println("insGenderInfo"+insGenderInfo.type)
                oldPersonData.gender = insGenderInfo
                insData.registration_number = params.registration_number
                def learnYearInfo = Year.findById(params.year)
                if(oldPersonData.logos.logo_path.empty)
                {
                    int flag = 0
                    def fp = FolderPath.list()
                    def file = request.getFile("profilePic")
                    print("file" + file + "fp" + fp.path)
                    String image_path = fp[0].path
                    String image_file_name = ""
                    String fullPath = "";
                    if (file.empty) {
                        flash.message = "File cannot be empty"
                        println("File cannot be empty")
                        flag = 1
                    } else {
                        image_file_name = file.originalFilename
                        fullPath = image_path + "//" + image_file_name
                        file.transferTo(new File(fullPath))
                        println("File uploaded successfully..")
                    }
                    if(!flag)
                    {
                        Logo personLogo = new Logo()
                        personLogo.username =session.uid//insert username
                        personLogo.creation_ip_address=request.getRemoteAddr()
                        personLogo.updation_ip_address=request.getRemoteAddr()
                        personLogo.creation_date = new java.util.Date()
                        personLogo.updation_date = new java.util.Date()
                        personLogo.logo_name = image_file_name
                        personLogo.logo_path = fullPath
                        oldPersonData.addToLogos(personLogo)
                        personLogo.save(flush: true,failOnError: true)}
                }
                else{
                    println("params"+params)
                    if(params.changePicture=="pictureChange") {
                        println("params"+params)

                        int flag = 0
                        def fp = FolderPath.list()
                        def file = request.getFile("profilePic")
                        print("file" + file + "fp" + fp.path)
                        String image_path = fp[0].path
                        String image_file_name = ""
                        String fullPath = "";
                        if (file.empty) {
                            flash.message = "File cannot be empty"
                            println("File cannot be empty")
                            flag = 1
                        } else {
                            image_file_name = file.originalFilename
                            fullPath = image_path + "//" + image_file_name
                            file.transferTo(new File(fullPath))
                            println("File uploaded successfully..")
                        }
                        if(!flag) {
                            Logo f = oldPersonData.logos[0]
                            println("logo" + f)
                            oldPersonData.removeFromLogos(f)
                            f.delete()
                            Logo personLogo = new Logo()
                            personLogo.username = session.uid//insert username
                            personLogo.creation_ip_address = request.getRemoteAddr()
                            personLogo.updation_ip_address = request.getRemoteAddr()
                            personLogo.creation_date = new java.util.Date()
                            personLogo.updation_date = new java.util.Date()
                            personLogo.logo_name = image_file_name
                            personLogo.logo_path = fullPath
                            oldPersonData.addToLogos(personLogo)
                        }
                    }
                }
                insData.current_year = learnYearInfo
                insData.person = oldPersonData
                insData.username = session.id
                insData.updation_date = new java.util.Date()
                insData.updation_ip_address=request.getRemoteAddr()
                insData.save(flush: true,failOnError: true)
            }
        }
        if(userInfo.usertype.type == "Organization")
        {
            Organization org
            println("Org data"+params)
            Organization insData = Organization.findByUid(userInfo.username)
            if(!insData)
            {
                println("org Data is avilable"+params.allUniver)
                Organization insData1 = new Organization()
                insData1.organization_name=params.organization_name
                insData1.organization_code=params.organization_code
                insData1.registration_number=params.registration_number
                insData1.display_name=params.display_name
                insData1.email=params.email
                insData1.uid=session.uid
                insData1.username=session.uid
                insData1.creation_date=new java.util.Date()
                insData1.updation_date=new java.util.Date()
                insData1.creation_ip_address=request.getRemoteAddr()
                insData1.updation_ip_address=request.getRemoteAddr()
                //Gender information
                def orgInfo = OrganizationType.findById(params.orgtype)
                println("orgInfo"+orgInfo.name)
                //Logo information
                int flag =0
                def fp=FolderPath.list()
                def file = request.getFile("profilePic")
                print("file"+file + "fp"+fp.path)
                String image_path=fp[0].path
                String image_file_name=""
                String fullPath="";
                if(file.empty)
                {
                    flash.message = "File cannot be empty"
                    println("File cannot be empty")
                    flag=1
                }
                else
                {
                    image_file_name=file.originalFilename
                    fullPath=image_path+"//"+image_file_name
                    file.transferTo(new File(fullPath))
                    println("File uploaded successfully..")
                }

                Logo personLogo = new Logo()
                if(flag==1) {
                }
                else{
                    personLogo.username =session.uid
                    personLogo.creation_ip_address=request.getRemoteAddr()
                    personLogo.updation_ip_address=request.getRemoteAddr()
                    personLogo.creation_date = new java.util.Date()
                    personLogo.updation_date = new java.util.Date()
                    personLogo.logo_name = image_file_name
                    personLogo.logo_path = fullPath
                    insData1.addToLogos(personLogo)
                    personLogo.save(flush: true,failOnError: true)
                }

                //Social account is null
                /*               Social instructorSocial =new Social()
                               instructorSocial.site_url=''
                               instructorSocial.username=session.uid
                               instructorSocial.updation_date=new java.util.Date()
                               instructorSocial.creation_date=new java.util.Date()
                               instructorSocial.creation_ip_address=request.getRemoteAddr()
                               instructorSocial.updation_ip_address=request.getRemoteAddr()
                               instructorSocial.save(flush: true,failOnError: true)
                               instructorPersonData.addToSocial(instructorSocial)
                               instructorPersonData.save(flush: true,failOnError: true)*/

                //Org data save

                println("session.uid"+session.uid)
                println("insData1"+insData1)
                if((orgInfo.name =="University") && (orgInfo.name =="Autonomus" ))
                {
                    insData1.organization = null
                }
                else
                {
                    println("params.allUniver"+params.allUniver)
                    if(params.allUniver)
                    {
                        org = Organization.findById(params.allUniver)
                        insData1.organization =org
                        println("i am not null params.allUniver")
                    }
                }

                //insData1.organization = insData1//errorrrrrrrrrrrrrrrrrrrrrrrrr
                insData1.organizationtype=orgInfo
                insData1.save(flush: true, failOnError: true)
                println("if insData:::"+insData1)
            }
            else
            {
                println("insData is modified :::"+insData)
                println("org Data is avilable"+params.allUniver)
                //Organization insData1 = new Organization()
                insData.organization_name=params.organization_name
                insData.organization_code=params.organization_code
                insData.registration_number=params.registration_number
                insData.display_name=params.display_name
                insData.email=params.email
                insData.uid=session.uid
                insData.username=session.uid
                //insData.creation_date=new java.util.Date()
                insData.updation_date=new java.util.Date()
                //insData.creation_ip_address=request.getRemoteAddr()
                insData.updation_ip_address=request.getRemoteAddr()
                //Gender information
                def orgInfo = OrganizationType.findById(params.orgtype)
                println("orgInfo"+orgInfo.name)
                //

                if(insData.logos.logo_path.empty)
                {
                    println("image path empty")
                    int flag = 0
                    def fp = FolderPath.list()
                    def file = request.getFile("profilePic")
                    print("file" + file + "fp" + fp.path)
                    String image_path = fp[0].path
                    String image_file_name = ""
                    String fullPath = "";
                    if (file.empty) {
                        flash.message = "File cannot be empty"
                        println("File cannot be empty")
                        flag = 1
                    } else {
                        image_file_name = file.originalFilename
                        fullPath = image_path + "//" + image_file_name
                        file.transferTo(new File(fullPath))
                        println("File uploaded successfully..")
                    }

                    Logo personLogo = new Logo()
                    personLogo.username =session.uid//insert username
                    personLogo.creation_ip_address=request.getRemoteAddr()
                    personLogo.updation_ip_address=request.getRemoteAddr()
                    personLogo.creation_date = new java.util.Date()
                    personLogo.updation_date = new java.util.Date()
                    personLogo.logo_name = image_file_name
                    personLogo.logo_path = fullPath
                    insData.addToLogos(personLogo)
                }
                else
                {
                    println("i am in image modify")
                    println("params"+params)
                    if(params.changePicture=="pictureChange") {
                        println("params"+params)

                        int flag = 0
                        def fp = FolderPath.list()
                        def file = request.getFile("profilePic")
                        print("file" + file + "fp" + fp.path)
                        String image_path = fp[0].path
                        String image_file_name = ""
                        String fullPath = "";
                        if (file.empty) {
                            flash.message = "File cannot be empty"
                            println("File cannot be empty")
                            flag = 1
                        } else {
                            image_file_name = file.originalFilename
                            fullPath = image_path + "//" + image_file_name
                            file.transferTo(new File(fullPath))
                            println("File uploaded successfully..")
                        }
                        if(!flag) {
                            Logo f = insData.logos[0]
                            println("logo" + f)
                            insData.removeFromLogos(f)
                            f.delete()
                            Logo personLogo = new Logo()
                            personLogo.username = session.uid//insert username
                            personLogo.creation_ip_address = request.getRemoteAddr()
                            personLogo.updation_ip_address = request.getRemoteAddr()
                            personLogo.creation_date = new java.util.Date()
                            personLogo.updation_date = new java.util.Date()
                            personLogo.logo_name = image_file_name
                            personLogo.logo_path = fullPath
                            insData.addToLogos(personLogo)
                        }
                    }
                }
                org = Organization.findById(params.allUniver)
                insData.organization =org
                insData.organizationtype = orgInfo
                insData.save(flush: true,failOnError: true)
            }
        }
        //render(params)
        redirect(controller: "instructor", action: "profile")
    }
    def findAllUniver()
    {
        println("I am in univer"+params)
        OrganizationType orgType = OrganizationType.findById(params.univerId)
        OrganizationType orgTypeU = OrganizationType.findByName("University")
        println("orgTypeU.name"+orgTypeU.name)
        def allUniver
        println("orgType.name"+orgType.name)
        if(orgType.name=="Institute") {
            println("Hi institute eng")
            allUniver = Organization.findAllByOrganizationtype(orgTypeU)
        }
        println("allUniver"+allUniver)
        [allUniver:allUniver]
    }
    def storeInstructorProfessionalData()
    {
        println("storeInstructorProfessionalData"+params)
        println("i am in storeInstructorContactData params"+params)
        def username = session.uid
        def userInfo = Login.findByUsername(username)
        if(userInfo.usertype.type == "Instructor")
        {
            Instructor insData = Instructor.findByUid(userInfo.username)
            if(!insData) {
                println("Data is not avilable ")
                flash.message="Please fill up Personal information"
            }
            else
            {
                println("Data is avilable")
                Person oldPersonData = insData.person
                if(oldPersonData)
                {
                    oldPersonData.highest_qualification=params.highest_qualification
                    oldPersonData.skills=params.skills
                    oldPersonData.short_description=params.short_description
                    oldPersonData.username=session.uid
                    oldPersonData.updation_date=new java.util.Date()
                    oldPersonData.updation_ip_address=request.getRemoteAddr()
                    oldPersonData.save(flush: true,failOnError: true)
                }
            }
        }
        if(userInfo.usertype.type == "Learner")
        {
            Learner insData = Learner.findByUid(userInfo.username)
            if(!insData) {
                println("Data is not avilable ")
                flash.message="Please fill up Personal information"
            }
            else
            {
                println("Data is avilable")
                Person oldPersonData = insData.person
                if(oldPersonData)
                {
                    oldPersonData.highest_qualification=params.highest_qualification
                    oldPersonData.skills=params.skills
                    oldPersonData.short_description=params.short_description
                    oldPersonData.username=session.uid
                    oldPersonData.updation_date=new java.util.Date()
                    oldPersonData.updation_ip_address=request.getRemoteAddr()
                    oldPersonData.save(flush: true,failOnError: true)
                }
            }
        }
        //render("hi")
        redirect(controller: "instructor", action: "profile")
    }
    def renderImage () {
        println("I am in render"+params)
        File imageFile
        //def profileImagePath = FolderPath.list()
        //println("profileImagePath"+profileImagePath.path[0])
        def instructorInformation = Person.findById(params.id)
        if(instructorInformation)
        {
            println("instructorInformation" + instructorInformation.logos.logo_path[0])
            imageFile = new File(instructorInformation.logos.logo_path[0])
            BufferedImage originalImage = ImageIO.read(imageFile)
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
            ImageIO.write(originalImage, "jpg", outputStream)
            byte[] imageInByte = outputStream.toByteArray()
            response.setHeader("Content-Length", imageInByte.length.toString())
            response.contentType = "image/jpg"
            response.outputStream << imageInByte
            response.outputStream.flush()
        }
        else {
            flash.message="Image not found!!"
            println("image not found")
            render("Error")

        }
        //render("Ok")
    }

    def summary(){
        println("in summary params:"+params)
        println("in summary session:"+session)
        Course crs = Course.findById(params.cid)
        def cos = CourseOutcomes.findAllByCourseAndIsDeleted(crs,false)
        def unit = CourseOutline.findAllByCourseAndIsDeleted(crs,false)
      //  println("COS:"+cos.co_statement)
      //  println("Unit:"+unit.outline)
        ArrayList<CourseTopic> topics = new ArrayList()
        ArrayList<CourseMaterial> materials = new ArrayList()
        ArrayList<CourseVideos> videos = new ArrayList()

        materials.addAll(CourseMaterial.findAllByCourseAndCourseoutlineAndCoursetopicAndIsDeleted(crs,null,null,false))
        videos.addAll(CourseVideos.findAllByCourseAndCourseoutlineAndCoursetopicAndIsDeleted(crs,null,null,false))
       // println("Material C:"+materials.material_name)
       // println("Videos:"+videos.video_name)

        for(CourseOutline co:unit){
            topics.addAll(CourseTopic.findAllByCourseoutlineAndIsDeleted(co,false))
            materials.addAll(CourseMaterial.findAllByCourseAndCourseoutlineAndCoursetopicAndIsDeleted(crs,co,null,false))
            videos.addAll(CourseVideos.findAllByCourseAndCourseoutlineAndCoursetopicAndIsDeleted(crs,co,null,false))
            def cc = CourseTopic.findAllByCourseoutlineAndIsDeleted(co,false)
            for(CourseTopic ct:cc){
                materials.addAll(CourseMaterial.findAllByCourseAndCourseoutlineAndCoursetopicAndIsDeleted(crs,co,ct,false))
                videos.addAll(CourseVideos.findAllByCourseAndCourseoutlineAndCoursetopicAndIsDeleted(crs,co,ct,false))
            }
        }
      //  println("Material Unit:"+materials.material_name)
      //  println("Videos:"+videos.video_name)

      //  println("Topics:"+topics.topic)

      //  println("Material T:"+materials.material_name)
      //  println("Videos:"+videos.video_name)

        ArrayList menu = new ArrayList()
        ArrayList submenu = new ArrayList()

        submenu.add("Outcomes Added")
        submenu.add(cos.size())
        if(cos.size()==0)
            submenu.add("--")
        else
            submenu.add(cos.co_statement)
        menu.add(submenu)
        submenu = new ArrayList()
        submenu.add("Units Added")
        submenu.add(unit.size())
        if(unit.size()==0)
            submenu.add("--")
        else
            submenu.add(unit.outline)
        menu.add(submenu)
        submenu = new ArrayList()
        submenu.add("Topics Added")
        submenu.add(topics.size())
        if(topics.size()==0)
            submenu.add("--")
        else
            submenu.add(topics.topic)
        menu.add(submenu)
        submenu = new ArrayList()
        submenu.add("Materials Added")
        submenu.add(materials.size())
        if(materials.size()==0)
            submenu.add("--")
        else
            submenu.add(materials.material_name)
        menu.add(submenu)
        submenu = new ArrayList()
        submenu.add("Videos Added")
        submenu.add(videos.size())
        if(videos.size()==0)
            submenu.add("--")
        else
            submenu.add(videos.video_name)
        menu.add(submenu)

        [menu:menu]
    }
    def requestToDeleteCourseOffering(){
        println("Visiting requestToDeleteCourseOffering:"+params)
        CourseOffering dcu = CourseOffering.findById(params.coffrid)
        println(" CourseOffering :"+dcu)
        [dcu:dcu]
        /*
        println("Visiting processDeleteRequest:"+params)
        println("Delete Id :"+params.delCoffrId)
        dcu.isDeleteRequestDone = true

        if(dcu.isDeleteRequestDone == true)
        {
            println("data present  " + dcu.isDeleteRequestDone)
            render "Your Response is already Recorded"
        }
        render "OKKKK"*/
    }
    def processDeleteRequest(){
        println("Visiting processDeleteRequest:"+params)
        CourseOffering dcu = CourseOffering.findById(params.coffrid)
        println(" CourseOffering :"+dcu)
        println(" reason :"+params.reason)
        if(dcu.isDeleteRequestDone == true)
        {
            println("data present  " + dcu.isDeleteRequestDone)
            render "Your Response is already Recorded"
        }
        else
        {
            DeleteCourseOffering dco =new DeleteCourseOffering()
            println("data NOT present")
            dcu.isDeleteRequestDone = true
            dco.username=session.uid
            dco.isDone=true
            dco.reason=params.reason
            dco.creation_date= new java.util.Date()
            dco.updation_date= new java.util.Date()
            dco.creation_ip_address= request.getRemoteAddr()
            dco.updation_ip_address= request.getRemoteAddr()

            CourseOffering cofd = CourseOffering.findById(params.coffrid)
            Instructor ins= Instructor.findByUid(session.uid)

            dco.courseoffering=cofd
            dco.organization=ins.organization
            dco.instructor=ins
            session.msg = ""

            dco.save(flush:true,failOnError: true)
            dcu.save(flush:true,failOnError: true)
            println("dco  "+dco)
            println("Last Print")
            [dco:dco,cofd:cofd]

        }
    }
    def job(){
        println("job..")
    }
}

