package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ForumThreadsController {

    ForumThreadsService forumThreadsService
    UplaodImageService uplaodImageService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond forumThreadsService.list(params), model:[forumThreadsCount: forumThreadsService.count()]
    }

    def show(Long id) {
        respond forumThreadsService.get(id)
    }

    def create() {
        respond new ForumThreads(params)
    }

    def save(ForumThreads forumThreads) {
        if (forumThreads == null) {
            notFound()
            return
        }

        try {
            forumThreadsService.save(forumThreads)
        } catch (ValidationException e) {
            respond forumThreads.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'forumThreads.label', default: 'ForumThreads'), forumThreads.id])
                redirect forumThreads
            }
            '*' { respond forumThreads, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond forumThreadsService.get(id)
    }

    def update(ForumThreads forumThreads) {
        if (forumThreads == null) {
            notFound()
            return
        }

        try {
            forumThreadsService.save(forumThreads)
        } catch (ValidationException e) {
            respond forumThreads.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'forumThreads.label', default: 'ForumThreads'), forumThreads.id])
                redirect forumThreads
            }
            '*'{ respond forumThreads, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        forumThreadsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'forumThreads.label', default: 'ForumThreads'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumThreads.label', default: 'ForumThreads'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def getThreads(){
        println("in getThreads params:"+params)
        ForumTopic ft = ForumTopic.findById(params.ftopics)
        def fthr = ForumThreads.findAllByForumtopic(ft)
        [fthr:fthr,topicid:params.ftopics,typeid:params.typeid,ft:ft]
    }

    def postThread(){
        println("in postThread params:"+params)
        println("in postThread session:"+session)

        ForumThreads forumthr = new ForumThreads()

        forumthr.username = session.uid
        forumthr.creation_date = new java.util.Date()
        forumthr.updation_date = new java.util.Date()
        forumthr.creation_ip_address = request.getRemoteAddr()
        forumthr.updation_ip_address = request.getRemoteAddr()

        ApplicationType at = ApplicationType.findByApplication_type("VOLP")
        UserType userType = UserType.findById(params.typeid)
        ForumTopic ft= ForumTopic.findById(params.topicid)
        if(userType.type=="Instructor") {
            print ("i m in if statement")
            Instructor inst = Instructor.findByUid(session.uid)
            forumthr.opid = inst.id
        }
        if(userType.type=="Learner") {

            Learner inst = Learner.findByUid(session.uid)
            forumthr.opid = inst.id
        }

        forumthr.comment = params.comment
        forumthr.type = userType
        forumthr.forumtopic=ft
        forumthr.save(flush: true,failOnError:true)
        // Saving image in a folder assets/channelImage/, in the web-app, with the name: baseImageName

        String baseImageName = "thr-"+forumthr.id

        //String baseImageName = params.type//params.crs_name+"_"+ins.id//java.util.UUID.randomUUID().toString();
        def downloadedFile = request.getFile("coursePic")
        String fileUploaded = uplaodImageService.uploadFile( downloadedFile, "${baseImageName}.jpg", "assets/images/forum/" )
        println("File:"+fileUploaded)
        if( fileUploaded ){
            forumthr.imagepath = "assets/images/forum"
            forumthr.imagename = baseImageName+".jpg"
        }
        else
        {
            forumthr.imagepath = null
            forumthr.imagename = null
        }
        forumthr.save(flush: true,failOnError:true)
        //end image

    }
    def getName(){
        print("i am in getName params:"+params)
        String name=""
        if(params.type=="Instructor"){
            Instructor inst = Instructor.findById(params.opid)
            name = inst.person.firstName+" "+inst.person.lastName+" {Instructor}"
        }
        if(params.type=="Learner"){
            Learner inst = Learner.findById(params.opid)
            name = inst.person.firstName+" "+inst.person.lastName+" {Learner}"
        }
        println("name:"+name)
        name = name.toLowerCase()
        [name:name]
    }
}
