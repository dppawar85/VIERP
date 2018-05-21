package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class LearnerController {

    LearnerService learnerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond learnerService.list(params), model:[learnerCount: learnerService.count()]
    }

    def show(Long id) {
        respond learnerService.get(id)
    }

    def create() {
        respond new Learner(params)
    }

    def save(Learner learner) {
        if (learner == null) {
            notFound()
            return
        }

        try {
            learnerService.save(learner)
        } catch (ValidationException e) {
            respond learner.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'learner.label', default: 'Learner'), learner.id])
                redirect learner
            }
            '*' { respond learner, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond learnerService.get(id)
    }

    def update(Learner learner) {
        if (learner == null) {
            notFound()
            return
        }

        try {
            learnerService.save(learner)
        } catch (ValidationException e) {
            respond learner.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'learner.label', default: 'Learner'), learner.id])
                redirect learner
            }
            '*'{ respond learner, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        learnerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'learner.label', default: 'Learner'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'learner.label', default: 'Learner'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def learner()
    {
        println("I am in learner session:"+session)
        Learner l = Learner.findByUid(session.uid)
        println("alan test"+l)
        session.fname = l.person.firstName
        //  println("L:"+l.person.firstName)
        def regCrs = CourseOfferingLearner.findAllByLearner(l)
        println("Reg:"+regCrs)
        [l:l,regCrs:regCrs]
    }
    def leranerCourse(){
        println("I am in leranerCourse session:"+session)
        println("I am in leranerCourse params:"+params)
        String unittopic=""
        Learner learner = Learner.findByUid(session.uid)
        ArrayList assignmentsubmissionlist=new ArrayList()
        CourseOfferingLearner regCrs = CourseOfferingLearner.findById(params.crsLearner)
        session.crsLearner=params.crsLearner
        CourseOffering coffr = regCrs.courseoffering
        if(coffr.courseofferingtype.name=="Self-Pace")
        {
            redirect(controller:"learner",action: "showtopicdata",params: [regCrs:params.crsLearner])
            return
        }
        Course crs = coffr.course
        def co = CourseOutline.findAllByCourseAndIsDeleted(crs,false)
        co.sort{it.outline_number}
        def topiclist=null
        if(co.size()==0)
        {
            unittopic = "topic"
            topiclist=CourseTopic.findAllByCourseAndIsDeleted(crs,false)
        }
        ArrayList menuSubmenu = new ArrayList()
        ArrayList ct = new ArrayList<CourseTopic>()
        for(CourseOutline c: co) {
            ArrayList submenu = new ArrayList()
            ArrayList mainmenu = new ArrayList()
            mainmenu.add(c)
            def crstop = CourseTopic.findAllByCourseoutlineAndIsDeleted(c,false)
            crstop.sort{it.topic_number}
            submenu.addAll(crstop)
            menuSubmenu.add(mainmenu)
            menuSubmenu.add(submenu)
            println("C:"+c)
            println("crstop:"+crstop)
            ct.addAll(crstop)
        }
        println("Menu:"+menuSubmenu)
        println("CO:"+co)
        println("CT:"+ct)

        //dpp code
        ArrayList menuSubmenuweek = new ArrayList()
        ArrayList sess = new ArrayList<CourseTopic>()
        def weeklist=Week.findAllByCourseoffering(coffr)
        weeklist.sort{it.week_number}
        for(Week w:weeklist)
        {
            ArrayList mainmenu = new ArrayList()
            ArrayList submenu = new ArrayList()
            def s=Session.findAllByWeek(w)
            if(s.size()==0)
                continue
            mainmenu.add(w)
            s.sort{it.session_number}
            submenu.addAll(s)
            menuSubmenuweek.add(mainmenu)
            menuSubmenuweek.add(submenu)
        }
        println("my list::"+menuSubmenuweek)
        def coursematerial=CourseMaterial.findAllByCourseAndCoursetopicAndCourseoutlineAndIsDeleted(coffr.course,null,null,false)
        def coursevideos=CourseVideos.findAllByCourseAndCoursetopicAndCourseoutlineAndIsDeleted(coffr.course,null,null,false)
        def vplist=VideoProperties.list()
        def vp=null
        for(VideoProperties v:vplist)
        {
            vp=v
        }
        def courseoutcomes=CourseOutcomes.findAllByCourseAndIsDeleted(coffr.course,false)
        println("courseoutcomes:"+courseoutcomes)
        //let us find assignments linked to course
        ArrayList assignmentlist=new ArrayList()
        ArrayList MCQOptionslist=null
        ArrayList MCQOptionslistoflist=new ArrayList()
        def assignment=Assignment.findAllByCourseAndCourseoutlineAndCoursetopic(coffr.course,null,null)
        for(Assignment a:assignment)
        {
            def assignmentoffering=AssignmentOffering.findAllByAssignmentAndCourseofferingAndIsDeleted(a,coffr,false)
            for(AssignmentOffering ao:assignmentoffering)
            {
                assignmentlist.add(ao)
                if(ao.assignment.assignmenttype.type=="MCQ")
                {
                    MCQOptionslist = new ArrayList()
                    def mcqoptions = MCQOptions.findAllByAssignment(ao.assignment)
                    MCQOptionslist.addAll(mcqoptions)
                    MCQOptionslistoflist.add(MCQOptionslist)
                    AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                    if(assignmentsubmission==null)
                        assignmentsubmissionlist.add(null)
                    else
                        assignmentsubmissionlist.add(assignmentsubmission)
                }
                else
                {
                    AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                    if(assignmentsubmission==null)
                        assignmentsubmissionlist.add(null)
                    else
                        assignmentsubmissionlist.add(assignmentsubmission)
                }
            }
        }
        session.courseofferingtypelearner=coffr.courseofferingtype.name
        Date date=new java.util.Date()
        def allVideo1 = LearnerCourseProgress.findAllByCourseofferinglearner(regCrs)
        def allVideo =[]
        println("allVideo"+allVideo.coursevideos.id)
        println("coursevideos"+coursevideos)
        int i = 0
        for(LearnerCourseProgress matchVideo:allVideo1)
        {
            println(matchVideo.coursevideos.id)
            if(coursevideos[i] != null)
            {
                if(coursevideos[i].id == matchVideo.coursevideos.id ) {
                    allVideo.add(matchVideo)
                    i++
                }
            }
        }
        def incompleteVideo = LearnerCourseProgress.findAllByCourseofferinglearnerAndIsViewed(regCrs,false)
        def completeVideo = LearnerCourseProgress.findAllByCourseofferinglearnerAndIsViewed(regCrs,true)
        println("incompleteVideo"+incompleteVideo)
        println("completeVideo"+completeVideo)
        println("incompletevideo count"+incompleteVideo.size)
        println("incompletevideo count"+completeVideo.size)
        int incompletevideo = incompleteVideo.size
        int completevideo = completeVideo.size
        int total = incompletevideo+completevideo
        println("total"+total)
        session.prog = completevideo
        if(total==0) {
            total = 1
            completevideo=0
        }
        [incompletevideo:incompletevideo,completevideo:completevideo,total:total,allVideo:allVideo,unittopic:unittopic,topiclist:topiclist,regCrs:params.crsLearner,assignmentsubmissionlist:assignmentsubmissionlist,date:date,assignmentlist:assignmentlist,MCQOptionslistoflist:MCQOptionslistoflist,menuSubmenuweek:menuSubmenuweek,menu:menuSubmenu,coffr:coffr,coursematerial:coursematerial,coursevideos:coursevideos,vp:vp,courseoutcomes:courseoutcomes]
    }
    def showCourseData(){
        String unitwisetopicwise=""
        println("in showCourseData params:"+params)
        ArrayList listOflistOfLists=null
        ArrayList MCQOptionslistoflist=null
        ArrayList assignmentsubmissionlist=new ArrayList()
        session.ot=params.ot
        session.sessionid=params.sessionid
        if(params.ot=="w")   //week
        {
            println("Clicked on week::"+params.weekid)
        }
        if(params.ot=="s")   //session
        {
            println("Clicked on session:"+params.sessionid)
            Learner learner = Learner.findByUid(session.uid)
            listOflistOfLists= new ArrayList()
            ArrayList listOfLists = new ArrayList()
            ArrayList list = new ArrayList()
            MCQOptionslistoflist=new ArrayList()
            ArrayList MCQOptionslist=new ArrayList()
            /* ArrayList lll = new ArrayList()
             ArrayList ll = new ArrayList()
             ArrayList l = new ArrayList()
             l.add(4)
             l.add(5)
             l.add(6)
             ll.add(l)
             l = new ArrayList()
             l.add(1)
             l.add(2)
             l.add(3)
             ll.add(l)
             lll.add(ll)
             ll = new ArrayList()
             l = new ArrayList()
             l.add(7)
             l.add(8)
             l.add(9)
             ll.add(l)
             l = new ArrayList()
             l.add(10)
             l.add(11)
             l.add(12)
             ll.add(l)
             lll.add(ll)
             println("lll:"+lll)*/
            Session sess=Session.findById(params.sessionid)

            if(sess.courseoutline==null)
                unitwisetopicwise="topic"
            println("session_number::"+sess.session_number)
            println("Course Offering::"+sess.courseoffering)
            list = new ArrayList()
            list.add(sess)
            listOfLists.add(list)
            if(sess.courseoutline!=null)
            {
                //find course videos and material for course unit
                def courseoutlinem = CourseMaterial.findAllByCourseoutlineAndCourseAndCoursetopicAndIsDeleted(sess.courseoutline, sess.courseoffering.course, null,false)
                def courseoutlinev = CourseVideos.findAllByCourseoutlineAndCourseAndCoursetopicAndIsDeleted(sess.courseoutline, sess.courseoffering.course, null,false)
                println("courseoutlinem:" + courseoutlinem)
                println("courseoutlinev:" + courseoutlinev)
                list = new ArrayList()
                list.addAll(courseoutlinev)
                listOfLists.add(list)
                list = new ArrayList()
                list.addAll(courseoutlinem)
                listOfLists.add(list)

                //find assignments related to unit
                session.courseofferingtypelearner = sess.courseoffering.courseofferingtype.name
                println("Course Offering Type::" + session.courseofferingtypelearner)
                def assignment = Assignment.findAllByCourseoutlineAndCourse(sess.courseoutline, sess.courseoffering.course)
                println("Assignments Outline::" + assignment)
                //let us find wheteher these assignments are offered or not
                def assoffering = null
                list = new ArrayList()
                for (Assignment a : assignment) {
                    assoffering = AssignmentOffering.findAllByAssignmentAndCourseofferingAndIsDeleted(a, sess.courseoffering,false)
                    for (AssignmentOffering ao : assoffering) {
                        println("Offered Assignments:" + ao)
                        list.add(ao)
                        if (ao.assignment.assignmenttype.type == "MCQ") {
                            MCQOptionslist = new ArrayList()
                            def mcqoptions = MCQOptions.findAllByAssignment(ao.assignment)
                            MCQOptionslist.addAll(mcqoptions)
                            MCQOptionslistoflist.add(MCQOptionslist)
                            AssignmentSubmission assignmentsubmission = AssignmentSubmission.findByLearnerAndAssignmentoffering(learner, ao)
                            if (assignmentsubmission == null)
                                assignmentsubmissionlist.add(null)
                            else
                                assignmentsubmissionlist.add(assignmentsubmission)
                        } else {
                            AssignmentSubmission assignmentsubmission = AssignmentSubmission.findByLearnerAndAssignmentoffering(learner, ao)
                            if (assignmentsubmission == null)
                                assignmentsubmissionlist.add(null)
                            else
                                assignmentsubmissionlist.add(assignmentsubmission)
                        }
                    }
                }
                listOfLists.add(list)
                listOflistOfLists.add(listOfLists)
            }

            def ctlist=sess.coursetopic
            for(CourseTopic ct:ctlist)
            {
                listOfLists = new ArrayList()
                println("Topic hrishi::"+ct.topic)
                list = new ArrayList()
                list.add(ct)
                listOfLists.add(list)
                def coursetopicm
                def coursetopicv
                if(sess.courseoutline!=null)
                {
                    coursetopicm = CourseMaterial.findAllByCourseoutlineAndCourseAndCoursetopicAndIsDeleted(sess.courseoutline, sess.courseoffering.course, ct,false)
                    coursetopicv = CourseVideos.findAllByCourseoutlineAndCourseAndCoursetopicAndIsDeleted(sess.courseoutline, sess.courseoffering.course, ct,false)                }
                else
                {
                    coursetopicm = CourseMaterial.findAllByCourseAndCoursetopicAndIsDeleted(sess.courseoffering.course, ct,false)
                    coursetopicv = CourseVideos.findAllByCourseAndCoursetopicAndIsDeleted(sess.courseoffering.course, ct,false)
                }
                println("coursetopicm:"+coursetopicm)
                println("coursetopicv:"+coursetopicv)
                list = new ArrayList()
                list.addAll(coursetopicv)
                listOfLists.add(list)
                list = new ArrayList()
                list.addAll(coursetopicm)
                listOfLists.add(list)
                def assignment
                if(sess.courseoutline!=null)
                    assignment = Assignment.findAllByCourseoutlineAndCourseAndCoursetopic(sess.courseoutline, sess.courseoffering.course, ct)
                else
                    assignment = Assignment.findAllByCourseAndCoursetopic(sess.courseoffering.course, ct)

                println("Assignments Topic::"+assignment)
                def assoffering=null
                list = new ArrayList()
                for(Assignment a:assignment)
                {
                    assoffering=AssignmentOffering.findAllByAssignmentAndCourseofferingAndIsDeleted(a,sess.courseoffering,false)
                    for(AssignmentOffering ao:assoffering)
                    {
                        println("Offered Assignments:"+ao)
                        list.add(ao)
                        if(ao.assignment.assignmenttype.type=="MCQ")
                        {
                            MCQOptionslist = new ArrayList()
                            def mcqoptions = MCQOptions.findAllByAssignment(ao.assignment)
                            MCQOptionslist.addAll(mcqoptions)
                            MCQOptionslistoflist.add(MCQOptionslist)
                            AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                            if(assignmentsubmission==null)
                                assignmentsubmissionlist.add(null)
                            else
                                assignmentsubmissionlist.add(assignmentsubmission)
                        }
                        else
                        {
                            AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                            if(assignmentsubmission==null)
                                assignmentsubmissionlist.add(null)
                            else
                                assignmentsubmissionlist.add(assignmentsubmission)
                        }
                    }
                }
                listOfLists.add(list)
                listOflistOfLists.add(listOfLists)
            }
            println("listOflistOfLists::"+listOflistOfLists)
            println("MCQOptionslistoflist::"+MCQOptionslistoflist)

        }
        if(params.ot=="o") { //if clicked on outline
            println("outline")
            CourseOutline co = CourseOutline.findById(params.olid)
            def cm = CourseMaterial.findAllByCourseoutlineAndIsDeleted(co,false)
            def cv = CourseVideos.findAllByCourseoutlineAndIsDeleted(co,false)

            session.co = co
            session.ot = "o"
            println("CM:" + cm)
            println("CV:" + cv)
            [co:co,cm:cm,cv:cv,ot:"o"]
        }
        if(params.ot=="t")
        {//clicked on topic
            println("topic")
            CourseTopic ct= CourseTopic.findById(params.topicid)
            println("CT:"+ct)
            def cm = CourseMaterial.findAllByCoursetopicAndIsDeleted(ct,false)
            def cv = CourseVideos.findAllByCoursetopicAndIsDeleted(ct,false)

            session.co = ct
            session.ot = "t"
            println("CM:" + cm)
            println("CV:" + cv)
            [co:ct,cm:cm,cv:cv,ot:"t"]
        }
        def vplist=VideoProperties.list()
        def vp=null
        for(VideoProperties v:vplist)
        {
            vp=v
        }
        Date date=new java.util.Date()
        println("my test::>>"+listOflistOfLists)
        def allVideo1 = LearnerCourseProgress.findAllByCourseofferinglearner(regCrs)
        def allVideo =[]
        println("allVideo"+allVideo.coursevideos.id)
        println("coursevideos"+coursevideos)
        int i = 0
        for(LearnerCourseProgress matchVideo:allVideo1)
        {
            println(matchVideo.coursevideos.id)
            if(coursevideos[i] != null)
            {
                if(coursevideos[i].id == matchVideo.coursevideos.id ) {
                    allVideo.add(matchVideo)
                    i++
                }
            }
        }
        def incompleteVideo = LearnerCourseProgress.findAllByCourseofferinglearnerAndIsViewed(regCrs,false)
        def completeVideo = LearnerCourseProgress.findAllByCourseofferinglearnerAndIsViewed(regCrs,true)
        println("incompleteVideo"+incompleteVideo)
        println("completeVideo"+completeVideo)
        println("incompletevideo count"+incompleteVideo.size)
        println("incompletevideo count"+completeVideo.size)
        int incompletevideo = incompleteVideo.size
        int completevideo = completeVideo.size
        int total = incompletevideo+completevideo
        println("total"+total)
        session.prog = completevideo
        if(total==0) {
            total = 1
            completevideo=0
        }
        [total:total,incompletevideo:incompletevideo,completevideo:completevideo,allVideo:allVideo,unitwisetopicwise:unitwisetopicwise,listOflistOfLists:listOflistOfLists,vp:vp,date:date,MCQOptionslistoflist:MCQOptionslistoflist,assignmentsubmissionlist:assignmentsubmissionlist]
    }
    def submitassignment()
    {
        AssignmentOffering assignmentoffering=AssignmentOffering.findById(params.ao)
        println("AssignmentOffering::"+assignmentoffering)
        def fp=FolderPath.list()
        CourseOffering coff=assignmentoffering.courseoffering
        //let us find courseoffering learner
        Learner learner=Learner.findByUid(session.uid)
        println("Learner::"+learner)
        CourseOfferingLearner cofflearner=CourseOfferingLearner.findByCourseofferingAndLearner(coff,learner)
        println("cofflearner::"+cofflearner)
        String student_answer_file_path=fp[0].path+"/courseoffering/"+coff.id+"/"+cofflearner.id+"/"
        println("student_answer_file_path::"+student_answer_file_path)

        String student_answer_file_name=""
        String fullPath="";
        println("I am in submitassignment....."+params)
        Date due_date=assignmentoffering.due_date
        Date current_date=new java.util.Date()
        def file = request.getFile("assignment")
        if(file.empty)
        {
            flash.message = "File cannot be empty"
            println("File cannot be empty")
        }
        else
        {
            student_answer_file_name=file.originalFilename
          //  Learner learner=Learner.findByUid(session.uid)
            println("Lerner::"+learner)
            AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,assignmentoffering)
            if(assignmentsubmission==null)  //insert assignment information
            {
                assignmentsubmission=new AssignmentSubmission()
                assignmentsubmission.student_answer_file_path=student_answer_file_path
                assignmentsubmission.student_answer_file_name=student_answer_file_name
                assignmentsubmission.submission_date=new java.util.Date()
                assignmentsubmission.marks=-1
                assignmentsubmission.teacher_remark=""
                assignmentsubmission.student_answer_text=""
                assignmentsubmission.username=session.uid
                assignmentsubmission.creation_date=new java.util.Date()
                assignmentsubmission.updation_date=new java.util.Date()
                assignmentsubmission.creation_ip_address=request.getRemoteAddr()
                assignmentsubmission.updation_ip_address=request.getRemoteAddr()
                assignmentsubmission.learner=learner
                assignmentsubmission.assignmentoffering=assignmentoffering
                assignmentsubmission.assessmentgrade=null
                SoftwareRemark sr=null
                if(current_date<=due_date)
                {
                    sr=SoftwareRemark.findByRemark("On Time")
                    assignmentsubmission.softwareremark=sr
                }
                else
                {
                    sr=SoftwareRemark.findByRemark("Late")
                    assignmentsubmission.softwareremark=sr
                    assignmentsubmission.username=session.uid
                }
                assignmentsubmission.save(failOnError:true,flush:true)
            }
            else    //update assignment information
            {
                println("assignmentsubmission::"+assignmentsubmission)
                File f=new File(student_answer_file_path+assignmentsubmission.student_answer_file_name)
                if(f.exists())
                {
                    f.delete()
                    println("File Deleted Successfully...")
                }
                assignmentsubmission.student_answer_file_path=student_answer_file_path
                assignmentsubmission.student_answer_file_name=student_answer_file_name
                assignmentsubmission.submission_date=new java.util.Date()
                assignmentsubmission.updation_date=new java.util.Date()
                assignmentsubmission.updation_ip_address=request.getRemoteAddr()
                SoftwareRemark sr=null
                if(current_date<=due_date)
                {
                    sr=SoftwareRemark.findByRemark("On Time")
                    assignmentsubmission.softwareremark=sr
                }
                else
                {
                    sr=SoftwareRemark.findByRemark("Late")
                    assignmentsubmission.softwareremark=sr
                    assignmentsubmission.username=session.uid
                }
                assignmentsubmission.save(failOnError:true,flush:true)
            }
            //File uploading code
            fullPath=student_answer_file_path+student_answer_file_name
            file.transferTo(new File(fullPath))
            println("File uploaded successfully..")
        }
        //redirect(controller:"learner",action: "showCourseData",params: [sessionid:session.sessionid,ot:session.ot])
        flash.message="Assignment Submitted Successfully..."
        redirect(controller:"learner",action: "leranerCourse",params: [crsLearner:session.crsLearner])
    }
    def submitmcq()
    {
        println("I am in submitmcq")
        String selectedoption=params.selectedoption
        AssignmentOffering assignmentoffering=AssignmentOffering.findById(params.ao)
        println("AssignmentOffering::"+assignmentoffering+" selectedoption::"+selectedoption)
        Date due_date=assignmentoffering.due_date
        Date current_date=new java.util.Date()
        Learner learner=Learner.findByUid(session.uid)
        println("Lerner::"+learner)
        MCQOptions mcqoption=MCQOptions.findByAssignmentAndOption_name(assignmentoffering.assignment,selectedoption)
        println("mcqoption::"+mcqoption)
        AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,assignmentoffering)
        if(assignmentsubmission==null)  //insert assignment information
        {
            assignmentsubmission=new AssignmentSubmission()
            assignmentsubmission.submission_date=new java.util.Date()
            assignmentsubmission.marks=-1
            assignmentsubmission.teacher_remark=""
            assignmentsubmission.student_answer_text=""
            assignmentsubmission.username=session.uid
            assignmentsubmission.creation_date=new java.util.Date()
            assignmentsubmission.updation_date=new java.util.Date()
            assignmentsubmission.creation_ip_address=request.getRemoteAddr()
            assignmentsubmission.updation_ip_address=request.getRemoteAddr()
            assignmentsubmission.learner=learner
            assignmentsubmission.assignmentoffering=assignmentoffering
            assignmentsubmission.assessmentgrade=null
            assignmentsubmission.mcqoptions=mcqoption
            SoftwareRemark sr=null
            if(current_date<=due_date)
            {
                sr=SoftwareRemark.findByRemark("On Time")
                assignmentsubmission.softwareremark=sr
            }
            else
            {
                sr=SoftwareRemark.findByRemark("Late")
                assignmentsubmission.softwareremark=sr
            }
            assignmentsubmission.save(failOnError:true,flush:true)
        }
        else    //update assignment information
        {
            println("assignmentsubmission::"+assignmentsubmission)
            assignmentsubmission.mcqoptions=mcqoption
            assignmentsubmission.submission_date=new java.util.Date()
            assignmentsubmission.updation_date=new java.util.Date()
            assignmentsubmission.updation_ip_address=request.getRemoteAddr()
            SoftwareRemark sr=null
            if(current_date<=due_date)
            {
                sr=SoftwareRemark.findByRemark("On Time")
                assignmentsubmission.softwareremark=sr
            }
            else
            {
                sr=SoftwareRemark.findByRemark("Late")
                assignmentsubmission.softwareremark=sr
            }
            assignmentsubmission.save(failOnError:true,flush:true)
        }
        flash.message="Assignment Submitted Successfully..."
        redirect(controller:"learner",action: "leranerCourse",params: [crsLearner:session.crsLearner])
    }
    def submitcoursemcq()
    {
        println("I am in submitcoursemcq..."+params)
        String selectedoption=params.selectedoption
        AssignmentOffering assignmentoffering=AssignmentOffering.findById(params.ao)
        println("AssignmentOffering::"+assignmentoffering+" selectedoption::"+selectedoption)
        Date due_date=assignmentoffering.due_date
        Date current_date=new java.util.Date()
        Learner learner=Learner.findByUid(session.uid)
        println("Lerner::"+learner)
        MCQOptions mcqoption=MCQOptions.findByAssignmentAndOption_name(assignmentoffering.assignment,selectedoption)
        println("mcqoption::"+mcqoption)
        AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,assignmentoffering)
        if(assignmentsubmission==null)  //insert assignment information
        {
            assignmentsubmission=new AssignmentSubmission()
            assignmentsubmission.submission_date=new java.util.Date()
            assignmentsubmission.marks=-1
            assignmentsubmission.teacher_remark=""
            assignmentsubmission.student_answer_text=""
            assignmentsubmission.username=session.uid
            assignmentsubmission.creation_date=new java.util.Date()
            assignmentsubmission.updation_date=new java.util.Date()
            assignmentsubmission.creation_ip_address=request.getRemoteAddr()
            assignmentsubmission.updation_ip_address=request.getRemoteAddr()
            assignmentsubmission.learner=learner
            assignmentsubmission.assignmentoffering=assignmentoffering
            assignmentsubmission.assessmentgrade=null
            assignmentsubmission.mcqoptions=mcqoption
            SoftwareRemark sr=null
            if(current_date<=due_date)
            {
                sr=SoftwareRemark.findByRemark("On Time")
                assignmentsubmission.softwareremark=sr
            }
            else
            {
                sr=SoftwareRemark.findByRemark("Late")
                assignmentsubmission.softwareremark=sr
            }
            assignmentsubmission.save(failOnError:true,flush:true)
        }
        else    //update assignment information
        {
            println("assignmentsubmission::"+assignmentsubmission)
            assignmentsubmission.mcqoptions=mcqoption
            assignmentsubmission.submission_date=new java.util.Date()
            assignmentsubmission.updation_date=new java.util.Date()
            assignmentsubmission.updation_ip_address=request.getRemoteAddr()
            SoftwareRemark sr=null
            if(current_date<=due_date)
            {
                sr=SoftwareRemark.findByRemark("On Time")
                assignmentsubmission.softwareremark=sr
            }
            else
            {
                sr=SoftwareRemark.findByRemark("Late")
                assignmentsubmission.softwareremark=sr
            }
            assignmentsubmission.save(failOnError:true,flush:true)
        }
        flash.message="Assignment Submitted Successfully..."
        redirect(controller:"learner",action: "leranerCourse",params: [crsLearner:session.crsLearner])
    }
    def submitcourseassignment()
    {
        println("I am in submitcourseassignment....")
        AssignmentOffering assignmentoffering=AssignmentOffering.findById(params.ao)
        println("AssignmentOffering::"+assignmentoffering)
        def fp=FolderPath.list()
        CourseOffering coff=assignmentoffering.courseoffering
        //let us find courseoffering learner
        Learner learner=Learner.findByUid(session.uid)
        println("Learner::"+learner)
        CourseOfferingLearner cofflearner=CourseOfferingLearner.findByCourseofferingAndLearner(coff,learner)
        println("cofflearner::"+cofflearner)
        String student_answer_file_path=fp[0].path+"/courseoffering/"+coff.id+"/"+cofflearner.id+"/"
        println("student_answer_file_path::"+student_answer_file_path)

        String student_answer_file_name=""
        String fullPath="";
        println("I am in submitassignment....."+params)
        Date due_date=assignmentoffering.due_date
        Date current_date=new java.util.Date()
        def file = request.getFile("assignment")
        if(file.empty)
        {
            flash.message = "File cannot be empty"
            println("File cannot be empty")
        }
        else
        {
            student_answer_file_name=file.originalFilename
          //  Learner learner=Learner.findByUid(session.uid)
            println("Lerner::"+learner)
            AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,assignmentoffering)
            if(assignmentsubmission==null)  //insert assignment information
            {
                assignmentsubmission=new AssignmentSubmission()
                assignmentsubmission.student_answer_file_path=student_answer_file_path
                assignmentsubmission.student_answer_file_name=student_answer_file_name
                assignmentsubmission.submission_date=new java.util.Date()
                assignmentsubmission.marks=-1
                assignmentsubmission.teacher_remark=""
                assignmentsubmission.student_answer_text=""
                assignmentsubmission.username=session.uid
                assignmentsubmission.creation_date=new java.util.Date()
                assignmentsubmission.updation_date=new java.util.Date()
                assignmentsubmission.creation_ip_address=request.getRemoteAddr()
                assignmentsubmission.updation_ip_address=request.getRemoteAddr()
                assignmentsubmission.learner=learner
                assignmentsubmission.assignmentoffering=assignmentoffering
                assignmentsubmission.assessmentgrade=null
                SoftwareRemark sr=null
                if(current_date<=due_date)
                {
                    sr=SoftwareRemark.findByRemark("On Time")
                    assignmentsubmission.softwareremark=sr
                }
                else
                {
                    sr=SoftwareRemark.findByRemark("Late")
                    assignmentsubmission.softwareremark=sr
                    assignmentsubmission.username=session.uid
                }
                assignmentsubmission.save(failOnError:true,flush:true)
            }
            else    //update assignment information
            {
                println("assignmentsubmission::"+assignmentsubmission)
                File f=new File(student_answer_file_path+assignmentsubmission.student_answer_file_name)
                if(f.exists())
                {
                    f.delete()
                    println("File Deleted Successfully...")
                }
                assignmentsubmission.student_answer_file_path=student_answer_file_path
                assignmentsubmission.student_answer_file_name=student_answer_file_name
                assignmentsubmission.submission_date=new java.util.Date()
                assignmentsubmission.updation_date=new java.util.Date()
                assignmentsubmission.updation_ip_address=request.getRemoteAddr()
                SoftwareRemark sr=null
                if(current_date<=due_date)
                {
                    sr=SoftwareRemark.findByRemark("On Time")
                    assignmentsubmission.softwareremark=sr
                }
                else
                {
                    sr=SoftwareRemark.findByRemark("Late")
                    assignmentsubmission.softwareremark=sr
                    assignmentsubmission.username=session.uid
                }
                assignmentsubmission.save(failOnError:true,flush:true)
            }
            //File uploading code
            fullPath=student_answer_file_path+student_answer_file_name
            file.transferTo(new File(fullPath))
            println("File uploaded successfully..")
        }
        //redirect(controller:"learner",action: "showCourseData",params: [sessionid:session.sessionid,ot:session.ot])
        flash.message="Assignment Submitted Successfully..."
        redirect(controller:"learner",action: "leranerCourse",params: [crsLearner:session.crsLearner])
    }
    def viewassignmentmarks()
    {
        println("I am in viewassignmentmarks:"+params)
        CourseOffering coffr=CourseOffering.findById(params.coffr)
        println("CourseOffering:"+coffr)
        //println("session::"+session)
        Learner learner=Learner.findByUid(session.uid)
        println("Learner:"+learner.id)
        def ao=AssignmentOffering.findAllByCourseofferingAndIsDeleted(coffr,false)
        println("Assignment offering:"+ao)
        ArrayList assignmentsubmissionlist = new ArrayList()
        for(AssignmentOffering a:ao)
        {
            AssignmentSubmission asub=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,a)
            if(asub!=null)
                assignmentsubmissionlist.add(asub)
        }
        println("Assignment list::"+assignmentsubmissionlist)
        [assignmentsubmissionlist:assignmentsubmissionlist,coffr:coffr]
    }
    def assigninfo(){
        println("in assigninfo "+params)
        Assignment ass = Assignment.findById(params.id)
        [ass:ass]
    }
    def showtopicdata()
    {
        println("I am in showtopicdata...."+params)
        String unittopic=""
        Learner learner = Learner.findByUid(session.uid)
        ArrayList assignmentsubmissionlist=new ArrayList()
        CourseOfferingLearner regCrs = CourseOfferingLearner.findById(params.regCrs)
        session.crsLearner=params.crsLearner
        CourseOffering coffr = regCrs.courseoffering
        Course crs = coffr.course
        def co = CourseOutline.findAllByCourseAndIsDeleted(crs,false)
        co.sort{it.outline_number}
        def topiclist=null
        if(co.size()==0)
        {
            unittopic = "topic"
            topiclist=CourseTopic.findAllByCourseAndIsDeleted(crs,false)
        }
        //find topicwise menu
        def crstop
        ArrayList menutopic= new ArrayList()
        boolean flag=false
        if(co.size()==0)   //if there is no unit
        {
            crstop = CourseTopic.findAllByCourseAndIsDeleted(crs,false)
            crstop.sort{it.topic_number}
            menutopic.addAll(crstop)
            flag=true
        }
        else   //if there is unit
        {
           /* for(CourseOutline c: co)
            {
                ArrayList menu= new ArrayList()
                crstop = CourseTopic.findAllByCourseoutlineAndIsDeleted(c,false)
                crstop.sort{it.topic_number}
                //menutopic.addAll(crstop)

                ArrayList menu1= new ArrayList()
                menu1.add(c)

                menu.add(menu1)

                menu.addAll(crstop)
                menutopic.add(menu)
              //  menutopic.add("co:"+c.id)
            }*/
            for(CourseOutline c: co) {
                ArrayList<CourseOutline> mm = new ArrayList()
                mm.add(c)
                ArrayList<CourseTopic> submenu = CourseTopic.findAllByCourseoutlineAndIsDeleted(c,false)
                //submenu.addAll(crstop)
                mm.add(submenu)
                menutopic.add(mm)
                println("C:"+c)
                //println("crstop:"+crstop)
                // ct.addAll(crstop)
            }
        }
        println("menutpic::"+menutopic)

        //find coursewise material
        def coursematerial=CourseMaterial.findAllByCourseAndCoursetopicAndCourseoutlineAndIsDeleted(coffr.course,null,null,false)
        def coursevideos=CourseVideos.findAllByCourseAndCoursetopicAndCourseoutlineAndIsDeleted(coffr.course,null,null,false)
        def vplist=VideoProperties.list()
        def vp=null
        for(VideoProperties v:vplist)
        {
            vp=v
        }
        def courseoutcomes=CourseOutcomes.findAllByCourseAndIsDeleted(coffr.course,false)
        println("courseoutcomes:"+courseoutcomes)
        //let us find assignments linked to course
        ArrayList assignmentlist=new ArrayList()
        ArrayList MCQOptionslist=null
        ArrayList MCQOptionslistoflist=new ArrayList()
        def assignment=Assignment.findAllByCourseAndCourseoutlineAndCoursetopic(coffr.course,null,null)
        for(Assignment a:assignment)
        {
            def assignmentoffering=AssignmentOffering.findAllByAssignmentAndCourseofferingAndIsDeleted(a,coffr,false)
            for(AssignmentOffering ao:assignmentoffering)
            {
                assignmentlist.add(ao)
                if(ao.assignment.assignmenttype.type=="MCQ")
                {
                    MCQOptionslist = new ArrayList()
                    def mcqoptions = MCQOptions.findAllByAssignment(ao.assignment)
                    MCQOptionslist.addAll(mcqoptions)
                    MCQOptionslistoflist.add(MCQOptionslist)
                    AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                    if(assignmentsubmission==null)
                        assignmentsubmissionlist.add(null)
                    else
                        assignmentsubmissionlist.add(assignmentsubmission)
                }
                else
                {
                    AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                    if(assignmentsubmission==null)
                        assignmentsubmissionlist.add(null)
                    else
                        assignmentsubmissionlist.add(assignmentsubmission)
                }
            }
        }
        session.courseofferingtypelearner=coffr.courseofferingtype.name
        Date date=new java.util.Date()
        println("my topic list::"+topiclist)
        def allVideo1 = LearnerCourseProgress.findAllByCourseofferinglearner(regCrs)
        def allVideo =[]
        println("allVideo"+allVideo.coursevideos.id)
        println("coursevideos"+coursevideos)
        int i = 0
        for(LearnerCourseProgress matchVideo:allVideo1)
        {
            println(matchVideo.coursevideos.id)
            if(coursevideos[i] != null)
            {
                if(coursevideos[i].id == matchVideo.coursevideos.id ) {
                    allVideo.add(matchVideo)
                    i++
                }
            }
        }
        def incompleteVideo = LearnerCourseProgress.findAllByCourseofferinglearnerAndIsViewed(regCrs,false)
        def completeVideo = LearnerCourseProgress.findAllByCourseofferinglearnerAndIsViewed(regCrs,true)
        println("incompleteVideo"+incompleteVideo)
        println("completeVideo"+completeVideo)
        println("incompletevideo count"+incompleteVideo.size)
        println("incompletevideo count"+completeVideo.size)
        int incompletevideo = incompleteVideo.size
        int completevideo = completeVideo.size
        int total = incompletevideo+completevideo
        println("total"+total)
        println("allVideo test.....0"+allVideo)
        session.prog = completevideo
        if(total==0) {
            total = 1
            completevideo=0
        }
        [total:total,incompletevideo:incompletevideo,allVideo:allVideo,completevideo:completevideo,flag:flag,unittopic:unittopic,topiclist:topiclist,regCrs:params.regCrs,assignmentsubmissionlist:assignmentsubmissionlist,date:date,assignmentlist:assignmentlist,MCQOptionslistoflist:MCQOptionslistoflist,menutopic:menutopic,coffr:coffr,coursematerial:coursematerial,coursevideos:coursevideos,vp:vp,courseoutcomes:courseoutcomes]
    }
    def showTopicDataInner()
    {
        println("I am in showTopicDataInner..."+params)
        Learner learner = Learner.findByUid(session.uid)
        CourseTopic ct=CourseTopic.findById(params.topicid)
        println("course topic:"+ct)
        ArrayList assignmentsubmissionlist=new ArrayList()
        CourseOfferingLearner regCrs = CourseOfferingLearner.findById(params.regCrs)
        CourseOffering coffr = regCrs.courseoffering
        Course crs = coffr.course

        //let us find topic videos and materials
        def coursevideos=CourseVideos.findAllByCourseAndCoursetopic(coffr.course,ct)
        def coursematerial=CourseMaterial.findAllByCourseAndCoursetopicAndIsDeleted(coffr.course,ct,false)
        def vplist=VideoProperties.list()
        def vp=null
        for(VideoProperties v:vplist)
        {
            vp=v
        }
        //let us find assignments linked to course
        ArrayList assignmentlist=new ArrayList()
        ArrayList MCQOptionslist=null
        ArrayList MCQOptionslistoflist=new ArrayList()
        def assignment=Assignment.findAllByCourseAndCourseoutlineAndCoursetopic(coffr.course,null,null)
        for(Assignment a:assignment)
        {
            def assignmentoffering=AssignmentOffering.findAllByAssignmentAndCourseofferingAndIsDeleted(a,coffr,false)
            for(AssignmentOffering ao:assignmentoffering)
            {
                assignmentlist.add(ao)
                if(ao.assignment.assignmenttype.type=="MCQ")
                {
                    MCQOptionslist = new ArrayList()
                    def mcqoptions = MCQOptions.findAllByAssignment(ao.assignment)
                    MCQOptionslist.addAll(mcqoptions)
                    MCQOptionslistoflist.add(MCQOptionslist)
                    AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                    if(assignmentsubmission==null)
                        assignmentsubmissionlist.add(null)
                    else
                        assignmentsubmissionlist.add(assignmentsubmission)
                }
                else
                {
                    AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                    if(assignmentsubmission==null)
                        assignmentsubmissionlist.add(null)
                    else
                        assignmentsubmissionlist.add(assignmentsubmission)
                }
            }
        }
        session.courseofferingtypelearner=coffr.courseofferingtype.name
        Date date=new java.util.Date()
        def allVideo1 = LearnerCourseProgress.findAllByCourseofferinglearner(regCrs)
        def allVideo =[]
        println("allVideo"+allVideo.coursevideos.id)
        println("coursevideos"+coursevideos)
        int i = 0
        for(LearnerCourseProgress matchVideo:allVideo1)
        {
            println(matchVideo.coursevideos.id)
            if(coursevideos[i] != null)
            {
                if(coursevideos[i].id == matchVideo.coursevideos.id ) {
                    allVideo.add(matchVideo)
                    i++
                }
            }
        }
        def incompleteVideo = LearnerCourseProgress.findAllByCourseofferinglearnerAndIsViewed(regCrs,false)
        def completeVideo = LearnerCourseProgress.findAllByCourseofferinglearnerAndIsViewed(regCrs,true)
        println("incompleteVideo"+incompleteVideo)
        println("completeVideo"+completeVideo)
        println("incompletevideo count"+incompleteVideo.size)
        println("incompletevideo count"+completeVideo.size)
        int incompletevideo = incompleteVideo.size
        int completevideo = completeVideo.size
        int total = incompletevideo+completevideo
        println("total"+total)
        session.prog = completevideo
        if(total==0) {
            total = 1
            completevideo=0
        }
        [total:total,incompletevideo:incompletevideo,completevideo:completevideo,allVideo:allVideo,regCrs:params.regCrs,coursevideos:coursevideos,vp:vp,coursematerial:coursematerial,assignmentsubmissionlist:assignmentsubmissionlist,date:date,assignmentlist:assignmentlist,MCQOptionslistoflist:MCQOptionslistoflist]
    }
    def showUnitDataInner()
    {
        println("I am in showUnitDataInner..."+params)
        Learner learner = Learner.findByUid(session.uid)
        CourseOutline ct=CourseOutline.findById(params.unitid)
        println("course unit:"+ct)
        ArrayList assignmentsubmissionlist=new ArrayList()
        CourseOfferingLearner regCrs = CourseOfferingLearner.findById(params.regCrs)
        CourseOffering coffr = regCrs.courseoffering
        Course crs = coffr.course

        //let us find topic videos and materials
        def coursevideos=CourseVideos.findAllByCourseAndCourseoutline(coffr.course,ct)
        def coursematerial=CourseMaterial.findAllByCourseAndCourseoutlineAndIsDeleted(coffr.course,ct,false)
        def vplist=VideoProperties.list()
        def vp=null
        for(VideoProperties v:vplist)
        {
            vp=v
        }
        //let us find assignments linked to course
        ArrayList assignmentlist=new ArrayList()
        ArrayList MCQOptionslist=null
        ArrayList MCQOptionslistoflist=new ArrayList()
        def assignment=Assignment.findAllByCourseAndCourseoutline(coffr.course,ct)
        for(Assignment a:assignment)
        {
            def assignmentoffering=AssignmentOffering.findAllByAssignmentAndCourseofferingAndIsDeleted(a,coffr,false)
            for(AssignmentOffering ao:assignmentoffering)
            {
                assignmentlist.add(ao)
                if(ao.assignment.assignmenttype.type=="MCQ")
                {
                    MCQOptionslist = new ArrayList()
                    def mcqoptions = MCQOptions.findAllByAssignment(ao.assignment)
                    MCQOptionslist.addAll(mcqoptions)
                    MCQOptionslistoflist.add(MCQOptionslist)
                    AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                    if(assignmentsubmission==null)
                        assignmentsubmissionlist.add(null)
                    else
                        assignmentsubmissionlist.add(assignmentsubmission)
                }
                else
                {
                    AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                    if(assignmentsubmission==null)
                        assignmentsubmissionlist.add(null)
                    else
                        assignmentsubmissionlist.add(assignmentsubmission)
                }
            }
        }
        session.courseofferingtypelearner=coffr.courseofferingtype.name
        Date date=new java.util.Date()
        def allVideo1 = LearnerCourseProgress.findAllByCourseofferinglearner(regCrs)
        def allVideo =[]
        println("allVideo"+allVideo1.coursevideos.id)
        println("coursevideos"+coursevideos)
        int i = 0
        for(LearnerCourseProgress matchVideo:allVideo1)
        {
            println(matchVideo.coursevideos.id)
            if(coursevideos[i] != null)
            {
                if(coursevideos[i].id == matchVideo.coursevideos.id ) {
                    allVideo.add(matchVideo)
                    i++
                }
            }
        }
        def incompleteVideo = LearnerCourseProgress.findAllByCourseofferinglearnerAndIsViewed(regCrs,false)
        def completeVideo = LearnerCourseProgress.findAllByCourseofferinglearnerAndIsViewed(regCrs,true)
        println("incompleteVideo"+incompleteVideo)
        println("completeVideo"+completeVideo)
        println("incompletevideo count"+incompleteVideo.size)
        println("incompletevideo count"+completeVideo.size)
        int incompletevideo = incompleteVideo.size
        int completevideo = completeVideo.size
        int total = incompletevideo+completevideo
        println("total"+total)
        println("allVideo test.....0"+allVideo)
        session.prog = completevideo
        if(total==0) {
            total = 1
            completevideo=0
        }
        [total:total,incompletevideo:incompletevideo,completevideo:completevideo,allVideo:allVideo,regCrs:params.regCrs,coursevideos:coursevideos,vp:vp,coursematerial:coursematerial,assignmentsubmissionlist:assignmentsubmissionlist,date:date,assignmentlist:assignmentlist,MCQOptionslistoflist:MCQOptionslistoflist]
    }

    def rateCourse(){
        println("in rateCourse: session"+session)
        Learner l = Learner.findByUid(session.uid)
        Set lcrs = CourseOfferingLearner.findAllByLearner(l).courseoffering.course
        println("crss:"+lcrs)
        [lcrs:lcrs]
    }
    def changeRating(){
        println("changeRating:"+params)
        Course crs = Course.findById(params.id)
        double rating = Double.parseDouble(params.rating)
        println("rating:"+rating)
        Learner l = Learner.findByUid(session.uid)
        CourseRating cr = CourseRating.findByCourseAndLearner(crs,l)
        if(cr==null){
            cr = new CourseRating()
            cr.username = session.uid
            cr.creation_date = new java.util.Date()
            cr.updation_date = new java.util.Date()
            cr.creation_ip_address = request.getRemoteAddr()
            cr.updation_ip_address = request.getRemoteAddr()

            cr.learner = l
            cr.course = crs
            cr.rating = rating
        }
        else{//update
            cr.username = session.uid
            //cr.creation_date = new java.util.Date()
            cr.updation_date = new java.util.Date()
            cr.creation_ip_address = request.getRemoteAddr()
            cr.updation_ip_address = request.getRemoteAddr()
            cr.rating = rating
        }
        cr.save(flush: true,failOnError: true)
        def crat = CourseRating.findAllByCourse(crs)
        double rat = 0.0
        for(CourseRating c:crat){
            rat += c.rating
        }
        println("Rate:"+rat+" t:"+crat.size())
        crs.rating = rat / crat.size()
        crs.save(flush: true,failOnError: true)
        //Learner l = Learner.findByUid(session.uid)
        Set lcrs = CourseOfferingLearner.findAllByLearner(l).courseoffering.course
        println("crss:"+lcrs)
        [lcrs:lcrs]
    }
    def stars(){
        println("in stars:"+params)
        Course crs = Course.findById(params.cid)
        def ratedBy = CourseRating.findAllByCourse(crs).size()
        def val
        if(params.rating1=="-1")
            val = 0
        else
            val = Math.round(Double.parseDouble(params.rating1))

        [val:val,ratedBy:ratedBy]
    }
}
