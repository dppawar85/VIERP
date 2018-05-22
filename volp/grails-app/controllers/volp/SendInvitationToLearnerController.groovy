package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import java.io.File
class SendInvitationToLearnerController {

    SendInvitationToLearnerService sendInvitationToLearnerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sendInvitationToLearnerService.list(params), model:[sendInvitationToLearnerCount: sendInvitationToLearnerService.count()]
    }

    def show(Long id) {
        respond sendInvitationToLearnerService.get(id)
    }

    def create() {
        respond new SendInvitationToLearner(params)
    }

    def save(SendInvitationToLearner sendInvitationToLearner) {
        if (sendInvitationToLearner == null) {
            notFound()
            return
        }

        try {
            sendInvitationToLearnerService.save(sendInvitationToLearner)
        } catch (ValidationException e) {
            respond sendInvitationToLearner.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sendInvitationToLearner.label', default: 'SendInvitationToLearner'), sendInvitationToLearner.id])
                redirect sendInvitationToLearner
            }
            '*' { respond sendInvitationToLearner, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sendInvitationToLearnerService.get(id)
    }

    def update(SendInvitationToLearner sendInvitationToLearner) {
        if (sendInvitationToLearner == null) {
            notFound()
            return
        }

        try {
            sendInvitationToLearnerService.save(sendInvitationToLearner)
        } catch (ValidationException e) {
            respond sendInvitationToLearner.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sendInvitationToLearner.label', default: 'SendInvitationToLearner'), sendInvitationToLearner.id])
                redirect sendInvitationToLearner
            }
            '*'{ respond sendInvitationToLearner, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sendInvitationToLearnerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sendInvitationToLearner.label', default: 'SendInvitationToLearner'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sendInvitationToLearner.label', default: 'SendInvitationToLearner'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def aceeptemails()
    {
        println("I am aceeptemails")
        println("ID::"+params.id)
        CourseOffering coff=CourseOffering.findById(params.coffrid)
        session.coffsendinvitation=coff
        println("coff::"+coff)
        [coff:coff]
    }
    def sendcodetoemails()
    {
        println("I am in sendcodetoemails..")
        CourseOffering coff=session.coffsendinvitation
        String emails=params.emails
        println(coff)
        println(emails)
        def emaillist=emails.split(",")
        String email=""
        for(int i=0;i<emaillist.length;i++)
        {
            email=emaillist[i]
            println("EMail::"+email)
            //Generate OTP
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
            }
            println("otp::"+otp)
            SendInvitationToLearner sitlobj=SendInvitationToLearner.findByEmailAndCourseoffering(email,coff)
            Date date=new java.util.Date()
            if(sitlobj==null)
            {
                //insert otp
                sitlobj=new SendInvitationToLearner()
                sitlobj.email=email
                sitlobj.courseoffering=coff
                sitlobj.code=otp
                sitlobj.isverified=false
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
            println("Now sending mail of otp...."+otp)
            sendMail {
                to email
                subject "Secret Code for Course Registration on VOLP Platfrom"
                text "Your secret code for registration of "+coff.course.course_name+" is "+otp+"\n"+"Thanks, Vishwakosh Team\n\n If ypu have not registered on VOLP platform please register. Registration link is http://localhost:8080/registration/verifyemail"
            }
            session.email=email
        }
        println("Secret codes sent successfully....")
        //   redirect(controller: "SendInvitationToLearner", action: "aceeptemails", params: [coffrid:coff.id])
    }
    def joinclassbycode()
    {
        boolean isbelongstoorganization=false      //true:belongto      false:Not belongsto
        println("I am in joinclassbycode...")
        Learner l=Learner.findByUid(session.uid)
        ArrayList<String> programlist=new ArrayList<String>();
        ArrayList<String> yearlist=new ArrayList<String>();
        ArrayList<String> divisionlist=new ArrayList<String>();
        if(l.organization!=null)
        {
            isbelongstoorganization=true
            def dept=Department.findAllByOrganization(l.organization)
            def program=null
            for(Department d:dept)
            {
                program=Program.findAllByDepartment(dept)
                for(Program p:program)
                {
                    programlist.add(p.name)
                }
            }
            def yearall=Year.list()
            for(Year y:yearall)
            {
                yearlist.add(y.year)
            }
            for(int i=65;i<=90;i++)
            {
                char x=(char)i
                divisionlist.add(""+x)
            }
        }
        session.isbelongstoorganization=isbelongstoorganization
        [isbelongstoorganization:isbelongstoorganization,programlist:programlist,yearlist:yearlist,divisionlist:divisionlist]
    }
    def comparecodejoinclass()
    {
        println("I am in comparecodejoinclass")
        String secretcode=params.secretcode
        String program=params.program
        String year=params.year
        String division=params.division
        int rollno=0
        if(params.rollno!=null)
            rollno=Integer.parseInt(params.rollno)
        Learner l=Learner.findByUid(session.uid)
        String email=l.person.email
        println("Email:"+email+"***"+"secret code::"+secretcode)
        SendInvitationToLearner sitl=null
        SendCommonInvitationToLearner scitl=null
        if (secretcode.matches(".*[a-z].*"))
            scitl=SendCommonInvitationToLearner.findByCode(secretcode)
        else
            sitl=SendInvitationToLearner.findByEmailAndCode(email,secretcode)
        if(sitl!=null)
        {
            //println("Joined course::"+sitl.courseoffering.course.course_name)
            CourseOfferingLearner cofflearner=null
            cofflearner=CourseOfferingLearner.findByCourseofferingAndLearner(sitl.courseoffering,l)
            if(cofflearner==null)
            {
                cofflearner=new CourseOfferingLearner()
                cofflearner.username=session.uid
                cofflearner.creation_date=new java.util.Date()
                cofflearner.updation_date=new java.util.Date()
                cofflearner.creation_ip_address=request.getRemoteAddr()
                cofflearner.updation_ip_address=request.getRemoteAddr()
                cofflearner.courseoffering=sitl.courseoffering
                cofflearner.learner=l
                if(session.isbelongstoorganization==true || session.isbelongstoorganization=="true" )
                {
                    println("I am org insert...")
                    Program pg=Program.findByName(program)
                    Year y=Year.findByYear(year)
                    Division div=Division.findByNameAndProgramAndYear(division,pg,y)
                    cofflearner.rollnumber=rollno
                    cofflearner.program=pg
                    cofflearner.year=y
                    if(div==null)
                    {
                        println("I am in insert...")
                        div=new Division()
                        div.name=division
                        div.username=session.uid
                        div.creation_date=new java.util.Date()
                        div.updation_date=new java.util.Date()
                        div.creation_ip_address=request.getRemoteAddr()
                        div.updation_ip_address=request.getRemoteAddr()
                        div.year=y
                        div.program=pg
                        div.save(failOnError:true,flush:true)
                        cofflearner.division=div
                    }
                    else
                        cofflearner.division=div
                }
                else
                {
                    cofflearner.rollnumber=0
                    cofflearner.program=null
                    cofflearner.year=null
                    cofflearner.division=null
                }
                cofflearner.save(failOnError:true,flush:true)
            }
            sitl.isverified=true
            def fpath=FolderPath.list()
            FolderPath fp=fpath[0]
            println("folder path:"+fp.path)
            println("/courseoffering/"+sitl.courseoffering.id+"/"+cofflearner.id+"/")
            new File(fp.path+"/courseoffering/"+sitl.courseoffering.id+"/"+cofflearner.id+"/").mkdirs()
            println("Folder Created Successfully.....")

            sitl.save(failOnError:true,flush:true)
        }
        if(scitl!=null)
        {
            //println("Joined course::"+sitl.courseoffering.course.course_name)
            CourseOfferingLearner cofflearner=null
            cofflearner=CourseOfferingLearner.findByCourseofferingAndLearner(scitl.courseoffering,l)
            if(cofflearner==null)
            {
                cofflearner=new CourseOfferingLearner()
                cofflearner.username=session.uid
                cofflearner.creation_date=new java.util.Date()
                cofflearner.updation_date=new java.util.Date()
                cofflearner.creation_ip_address=request.getRemoteAddr()
                cofflearner.updation_ip_address=request.getRemoteAddr()
                cofflearner.courseoffering=scitl.courseoffering
                cofflearner.learner=l
                if(session.isbelongstoorganization==true || session.isbelongstoorganization=="true" )
                {
                    println("I am org insert...")
                    Program pg=Program.findByName(program)
                    Year y=Year.findByYear(year)
                    Division div=Division.findByNameAndProgramAndYear(division,pg,y)
                    cofflearner.rollnumber=rollno
                    cofflearner.program=pg
                    cofflearner.year=y
                    if(div==null)
                    {
                        println("I am in insert...")
                        div=new Division()
                        div.name=division
                        div.username=session.uid
                        div.creation_date=new java.util.Date()
                        div.updation_date=new java.util.Date()
                        div.creation_ip_address=request.getRemoteAddr()
                        div.updation_ip_address=request.getRemoteAddr()
                        div.year=y
                        div.program=pg
                        div.save(failOnError:true,flush:true)
                        cofflearner.division=div
                    }
                    else
                        cofflearner.division=div
                }
                else
                {
                    cofflearner.rollnumber=0
                    cofflearner.program=null
                    cofflearner.year=null
                    cofflearner.division=null
                }
                cofflearner.save(failOnError:true,flush:true)
            }
            def fpath=FolderPath.list()
            FolderPath fp=fpath[0]
            println("folder path:"+fp.path)
            println("/courseoffering/"+scitl.courseoffering.id+"/"+cofflearner.id+"/")
            new File(fp.path+"/courseoffering/"+scitl.courseoffering.id+"/"+cofflearner.id+"/").mkdirs()
            println("Folder Created Successfully.....")
        }
        redirect(controller: "learner", action: "learner")
    }
}
