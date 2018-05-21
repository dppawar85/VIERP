package volp

class EnrollController {

    def index() { }
    def enrollLogin(){
        println("in enrollLogin params:"+params)
        [coffr:params.coffr]
    }
    def processLogin() {
        println("in processLogin params:" + params)
        Login user = Login.findByUsernameAndPassword(params.email, params.pwd)
        println("USer:" + user)
        if (user == null) {
            flash.message = params.email + " Invalid User name or Password!!!"
            redirect(action: "login")
            return
        } else {
            if (user.isloginblocked) {
                flash.message = params.email + " blocked!!!"
                redirect(action: "login")
                return
            }

            if (user.usertype.type == "Learner" && user.usertype.application_type.application_type == "VOLP")
                redirect(controller: "enroll", action: "payFee" ,params: [coffr: params.coffr])
            session.uid = user.username
            return
        }
    }
    def payFee(){
        println("in payFee params:" + params)
        println("in payFee session:" + session)
        CourseOffering coffr = CourseOffering.findById(params.coffr)
        boolean enrollflag=false
        if(session.uid.toString().equals(""))
        {
            print("SESSION UID "+session.uid.toString())
            flash.message="Please Login First......"
            enrollflag=true
            redirect(controller: "Login", action: "login",params: [enrollflag: enrollflag])
        }
        CourseCertificateType coursecertificatetype=CourseCertificateType.findByType("With Certificate")
        println("Course Certificate Type::"+coursecertificatetype)
        CourseFees coursefees=CourseFees.findByCourseofferingAndCoursecertificatetype(coffr,coursecertificatetype)
        println("Course Fees:"+coursefees.fees)
        session.coffr=coffr
        [coffr:coffr,cf:coursefees]
    }
    def viewDetails(){
        println("in viewDetails params:" + params)
        println("in viewDetails session:" + session)

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
    def savepayfee()
    {
        println("I am in savepayfee:"+params)
        Learner learner=Learner.findByUid(session.uid)
        println("Learner:"+learner)
        CourseOffering coffr=session.coffr
        println("coffr:"+coffr)
        Double coursefees=Double.parseDouble(params.coursefees)

        //we need to divert to payment gateway...

        //after payment is succseful ... do registration
        CourseOfferingLearner coffrlearner=CourseOfferingLearner.findByCourseofferingAndLearner(coffr,learner)
        if(coffrlearner==null)
        {
            coffrlearner=new CourseOfferingLearner()
            coffrlearner.username=session.uid
            coffrlearner.creation_date=new java.util.Date()
            coffrlearner.updation_date=new java.util.Date()
            coffrlearner.creation_ip_address=request.getRemoteAddr()
            coffrlearner.updation_ip_address=request.getRemoteAddr()
            coffrlearner.courseoffering=coffr
            coffrlearner.learner=learner
            coffrlearner.save(flush: true,failOnError:true)
            println("coffrlearner::"+coffrlearner)
            //let us find all study material
            def cv=CourseVideos.findAllByCourse(coffr.course)
            println("Course VIdeos:"+cv)
            for(CourseVideos c:cv)
            {
                LearnerCourseProgress lcp=new LearnerCourseProgress()
                lcp.isViewed=false
                lcp.courseofferinglearner=coffrlearner
                lcp.coursevideos=c
                lcp.save(flush: true,failOnError:true)
                println("LearnerCourseProgress::"+lcp)
            }
        }
        else
            println("Learner ALready Registered,,,")
        redirect(controller:"Login",action: "homeDash")

    }
}
