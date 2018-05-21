package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AssignmentSubmissionController {

    AssignmentSubmissionService assignmentSubmissionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond assignmentSubmissionService.list(params), model:[assignmentSubmissionCount: assignmentSubmissionService.count()]
    }

    def show(Long id) {
        respond assignmentSubmissionService.get(id)
    }

    def create() {
        respond new AssignmentSubmission(params)
    }

    def save(AssignmentSubmission assignmentSubmission) {
        if (assignmentSubmission == null) {
            notFound()
            return
        }

        try {
            assignmentSubmissionService.save(assignmentSubmission)
        } catch (ValidationException e) {
            respond assignmentSubmission.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'assignmentSubmission.label', default: 'AssignmentSubmission'), assignmentSubmission.id])
                redirect assignmentSubmission
            }
            '*' { respond assignmentSubmission, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond assignmentSubmissionService.get(id)
    }

    def update(AssignmentSubmission assignmentSubmission) {
        if (assignmentSubmission == null) {
            notFound()
            return
        }

        try {
            assignmentSubmissionService.save(assignmentSubmission)
        } catch (ValidationException e) {
            respond assignmentSubmission.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'assignmentSubmission.label', default: 'AssignmentSubmission'), assignmentSubmission.id])
                redirect assignmentSubmission
            }
            '*'{ respond assignmentSubmission, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        assignmentSubmissionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'assignmentSubmission.label', default: 'AssignmentSubmission'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignmentSubmission.label', default: 'AssignmentSubmission'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def listallassignments()
    {
        print("I am In listallassignments:"+params)
        int id=Integer.parseInt(params.coffrid)
        CourseOffering coffr = CourseOffering.findById(params.coffrid)
        def lst=AssignmentOffering.findAll()
        def list=AssignmentOffering.findAllByCourseofferingAndIsDeleted(coffr,false)
        /*ArrayList li = new ArrayList();
        for(def l:list)
            {
                print("out")
              if(l.courseofferingId==id)
              {
                  print("INSIDE")
                  li.add(l)
              }


        }*/
        //println("LST:"+lst.size())
        //print("list"+list.size())
        //println("LST:"+lst)
        //print("list"+list)
        //CourseOffering co= list.findAllById(params.coffrid)

//def list=AssignmentOffering.findAllById(ao.id)
        [li:list]
    }
    def listallstudents()
    {
        print("I am in listallstudents:params "+params)
        AssignmentOffering ao= AssignmentOffering.findById(params.asoid)
        def list =AssignmentSubmission.findAllByAssignmentoffering(ao)
        [li:list]
    }
    def savemarks()
    {
        print("I am in savemarks :params "+params)
        AssignmentSubmission as1=AssignmentSubmission.findById(params.asid)
        as1.marks=Double.parseDouble(params.marks)
        as1.save(flush: true,failOnError:true)
        [as1:as1]
    }
    def download(long id) {
        AssignmentSubmission documentInstance = AssignmentSubmission.get(id)
        if ( documentInstance == null) {
            flash.message = "Document not found."
            redirect (action:'list')
        } else {
            //response.setContentType("APPLICATION/OCTET-STREAM")
            response.setContentType("text/html; charset=UTF-8")
            response.setHeader("Content-Disposition", "Attachment;Filename=\"${documentInstance.student_answer_file_name}\"")
            def file = new File(documentInstance.student_answer_file_path+documentInstance.student_answer_file_name)
            def fileInputStream = new FileInputStream(file)
            def outputStream = response.getOutputStream()
            byte[] buffer = new byte[4096];
            int len;
            while ((len = fileInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush()
            outputStream.close()
            fileInputStream.close()
        }
    }
}
