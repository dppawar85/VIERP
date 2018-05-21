package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DepartmentController {

    DepartmentService departmentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond departmentService.list(params), model:[departmentCount: departmentService.count()]
    }

    def show(Long id) {
        respond departmentService.get(id)
    }

    def create() {
        respond new Department(params)
    }

    def save(Department department) {
        if (department == null) {
            notFound()
            return
        }

        try {
            departmentService.save(department)
        } catch (ValidationException e) {
            respond department.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'department.label', default: 'Department'), department.id])
                redirect department
            }
            '*' { respond department, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond departmentService.get(id)
    }

    def update(Department department) {
        if (department == null) {
            notFound()
            return
        }

        try {
            departmentService.save(department)
        } catch (ValidationException e) {
            respond department.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'department.label', default: 'Department'), department.id])
                redirect department
            }
            '*'{ respond department, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        departmentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'department.label', default: 'Department'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'department.label', default: 'Department'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def adddepartment()
    {
        println("I am in adddepartment")
        Organization org = Organization.findByUid(session.uid)
        println("org:"+org)
        def deptlist=Department.findAllByOrganization(org)
        println("deptlist::"+deptlist)
        [deptlist:deptlist]
    }
    def savedepartment()
    {
        println("I am in savedepartment")
        Organization org = Organization.findByUid(session.uid)
        println("org:"+org)
        Department department=Department.findByNameAndOrganization(params.name,org)
        if(department==null)
        {
            department=new Department()
            department.name=params.name
            department.username=session.uid
            department.creation_date=new java.util.Date()
            department.updation_date=new java.util.Date()
            department.creation_ip_address=request.getRemoteAddr()
            department.updation_ip_address=request.getRemoteAddr()
            department.organization=org
            department.save(failOnError:true,flush:true)
            flash.message="Department Added Successfully....."
            def deptlist=Department.findAllByOrganization(org)
            println("deptlist::"+deptlist)
            [deptlist:deptlist]
            //redirect(controller:"Department",action:"adddepartment")
        }
        else
        {
            flash.message="Department Already Present....."

            def deptlist=Department.findAllByOrganization(org)
            println("deptlist::"+deptlist)
            [deptlist:deptlist]
            //redirect(controller:"Department",action:"adddepartment")
        }
    }
    def updatedepartment()
    {
        println("I am in updatedepartment...")
        Department department=Department.findById(params.id)
        Organization org = Organization.findByUid(session.uid)
        println("org:"+org)
        def deptlist=Department.findAllByOrganization(org)
        session.department=department
        println("deptlist::"+deptlist)
        [deptlist:deptlist,department:department]
    }
    def saveupdatedepartment()
    {
        println("I am in saveupdatedepartment")
        Department department=session.department
        String name=params.name
        department.name=name
        department.save(failOnError:true,flush:true)
        Organization org = Organization.findByUid(session.uid)
        println("org:"+org)
        def deptlist=Department.findAllByOrganization(org)
        println("deptlist::"+deptlist)
        flash.message="Department Updated Successfully....."
        //  redirect(controller:"Department",action:"adddepartment")
        [deptlist:deptlist]
    }
}
