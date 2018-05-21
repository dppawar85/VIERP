package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProgramController {

    ProgramService programService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond programService.list(params), model:[programCount: programService.count()]
    }

    def show(Long id) {
        respond programService.get(id)
    }

    def create() {
        respond new Program(params)
    }

    def save(Program program) {
        if (program == null) {
            notFound()
            return
        }

        try {
            programService.save(program)
        } catch (ValidationException e) {
            respond program.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'program.label', default: 'Program'), program.id])
                redirect program
            }
            '*' { respond program, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond programService.get(id)
    }

    def update(Program program) {
        if (program == null) {
            notFound()
            return
        }

        try {
            programService.save(program)
        } catch (ValidationException e) {
            respond program.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'program.label', default: 'Program'), program.id])
                redirect program
            }
            '*'{ respond program, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        programService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'program.label', default: 'Program'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'program.label', default: 'Program'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def addprogram()
    {
        println("I am in addprogram")
        Organization org = Organization.findByUid(session.uid)
        println("org:"+org)
        def deptlist=Department.findAllByOrganization(org)
        println("deptlist::"+deptlist)
        def programlist=Program.list()
        println("programlist::"+programlist)
        programlist.sort{it.department}
        [deptlist:deptlist,programlist:programlist]
    }
    def saveprogram()
    {
        println("I am in saveprogram...."+params)
        Organization org = Organization.findByUid(session.uid)
        println("org:"+org)
        Department department=Department.findByNameAndOrganization(params.dept,org)
        println("Department::"+department)
        Program program=Program.findByNameAndDepartment(params.name,department)
        println("Program:"+program)
        if(program==null)
        {
            program=new Program()
            program.name=params.name
            program.department=department
            program.username=session.uid
            program.creation_date=new java.util.Date()
            program.updation_date=new java.util.Date()
            program.creation_ip_address=request.getRemoteAddr()
            program.updation_ip_address=request.getRemoteAddr()
            program.save(failOnError:true,flush:true)
            flash.message="Program Added Successfully....."
            def deptlist=Department.findAllByOrganization(org)
            println("deptlist::"+deptlist)
            def programlist=Program.list()
            println("programlist::"+programlist)
            programlist.sort{it.department}
            [deptlist:deptlist,programlist:programlist]
        }
        else
        {
            flash.message="Program Already Present....."
            def deptlist=Department.findAllByOrganization(org)
            println("deptlist::"+deptlist)
            def programlist=Program.list()
            println("programlist::"+programlist)
            programlist.sort{it.department}
            [deptlist:deptlist,programlist:programlist]
        }
    }
    def updateprogram()
    {
        println("I am in updateprogram.."+params)
        Program program=Program.findById(params.id)
        session.program=program
        println("Program:"+program)
        Organization org = Organization.findByUid(session.uid)
        println("org:"+org)
        def deptlist=Department.findAllByOrganization(org)
        println("deptlist::"+deptlist)
        def programlist=Program.list()
        println("programlist::"+programlist)
        programlist.sort{it.department}
        [deptlist:deptlist,programlist:programlist,program:program]
    }
    def saveupdateprogram()
    {
        println("I am in saveupdateprogram::"+params)
        Organization org = Organization.findByUid(session.uid)
        println("org:"+org)
        Department department=Department.findByNameAndOrganization(params.dept,org)
        println("Department::"+department)
        Program program=session.program
        program.name=params.name
        program.department=department
        program.save(failOnError:true,flush:true)
        def deptlist=Department.findAllByOrganization(org)
        println("deptlist::"+deptlist)
        def programlist=Program.list()
        println("programlist::"+programlist)
        programlist.sort{it.department}
        [deptlist:deptlist,programlist:programlist,program:program]
    }
}
