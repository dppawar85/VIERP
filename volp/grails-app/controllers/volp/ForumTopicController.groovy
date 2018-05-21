package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ForumTopicController {

    ForumTopicService forumTopicService
    UplaodImageService uplaodImageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond forumTopicService.list(params), model:[forumTopicCount: forumTopicService.count()]
    }

    def show(Long id) {
        respond forumTopicService.get(id)
    }

    def create() {
        respond new ForumTopic(params)
    }

    def save(ForumTopic forumTopic) {
        if (forumTopic == null) {
            notFound()
            return
        }

        try {
            forumTopicService.save(forumTopic)
        } catch (ValidationException e) {
            respond forumTopic.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'forumTopic.label', default: 'ForumTopic'), forumTopic.id])
                redirect forumTopic
            }
            '*' { respond forumTopic, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond forumTopicService.get(id)
    }

    def update(ForumTopic forumTopic) {
        if (forumTopic == null) {
            notFound()
            return
        }

        try {
            forumTopicService.save(forumTopic)
        } catch (ValidationException e) {
            respond forumTopic.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'forumTopic.label', default: 'ForumTopic'), forumTopic.id])
                redirect forumTopic
            }
            '*'{ respond forumTopic, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        forumTopicService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'forumTopic.label', default: 'ForumTopic'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'forumTopic.label', default: 'ForumTopic'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def discussionForum(){
        print("I am in discussionForum session:"+session)
        print("I am in discussionForum params:"+params)
        ApplicationType at = ApplicationType.findByApplication_type("VOLP")
        UserType userType = UserType.findByTypeAndApplication_type(params.type,at)
        //println("UT:"+ut)
        if(params.type=="Instructor"){
            CourseOffering coff = CourseOffering.findById(params.coffrid)
            Instructor inst = Instructor.findByUid(session.uid)
            // def forumTopics = ForumTopic.findAllByOpidAndCourseofferingAndType(inst.id,coff,userType)
            def forumTopics = ForumTopic.findAllByCourseoffering(coff)
            [forumTopics:forumTopics,coffrid:coff.id,ut:userType,opid:inst.id]
        }
        else if(params.type=="Learner"){
            CourseOffering coff = CourseOffering.findById(params.coffrid)
            Learner l = Learner.findByUid(session.uid)
            // def forumTopics = ForumTopic.findAllByOpidAndCourseofferingAndType(inst.id,coff,userType)
            def forumTopics = ForumTopic.findAllByCourseoffering(coff)
            [forumTopics:forumTopics,coffrid:coff.id,ut:userType,opid:l.id]
        }
    }
    def addTopic()
    {
        print("I am in addTopic session:"+session)
        print("I am in addTopic params:"+params)

//lOGIC FOR aDD TOPIC
        ForumTopic forumtopic = new ForumTopic()

        forumtopic.username = session.uid
        forumtopic.creation_date = new java.util.Date()
        forumtopic.updation_date = new java.util.Date()
        forumtopic.creation_ip_address = request.getRemoteAddr()
        forumtopic.updation_ip_address = request.getRemoteAddr()
// Saving image in a folder assets/channelImage/, in the web-app, with the name: baseImageName

        String baseImageName = params.ftopic
        baseImageName.replace(' ', '-');
        baseImageName=baseImageName+"-"+params.coffrid+"-"+params.opid
        //String baseImageName = params.type//params.crs_name+"_"+ins.id//java.util.UUID.randomUUID().toString();
        def downloadedFile = request.getFile("coursePic")
        String fileUploaded = uplaodImageService.uploadFile( downloadedFile, "${baseImageName}.jpg", "assets/images/forum/" )
        println("File:"+fileUploaded)
        if( fileUploaded ){
            forumtopic.imagepath = "assets/images/forum"
            forumtopic.imagename = baseImageName+".jpg"
        }
        else
        {
            forumtopic.imagepath = null
            forumtopic.imagename = null
        }
        //end image
        ApplicationType at = ApplicationType.findByApplication_type("VOLP")
        UserType userType = UserType.findByTypeAndApplication_type(params.type,at)
        CourseOffering coff = CourseOffering.findById(params.coffrid)
        forumtopic.opid = params.opid //could be lid or instid
        forumtopic.isClosed = false
        forumtopic.description = params.fdesc
        forumtopic.title = params.ftopic
        forumtopic.type = userType
        forumtopic.courseoffering = coff
        forumtopic.save(flush: true,failOnError:true)
        flash.message = "Posted Successfully..."
        println("----")
        redirect(controller:"forumTopic",action: "discussionForum",params: [coffrid: coff.id,type:params.type])
    }
/*
    def redirect(){
        println("in redirect:"+params)
        forward(controller:"forumTopic",action:'discussionForum',method:"POST")
    }*/
}
