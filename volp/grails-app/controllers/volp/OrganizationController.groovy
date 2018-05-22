package volp

import grails.validation.ValidationException

import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook

import static org.springframework.http.HttpStatus.*

class OrganizationController {

    OrganizationService organizationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond organizationService.list(params), model:[organizationCount: organizationService.count()]
    }

    def show(Long id) {
        respond organizationService.get(id)
    }

    def create() {
        respond new Organization(params)
    }

    def save(Organization organization) {
        if (organization == null) {
            notFound()
            return
        }

        try {
            organizationService.save(organization)
        } catch (ValidationException e) {
            respond organization.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'organization.label', default: 'Organization'), organization.id])
                redirect organization
            }
            '*' { respond organization, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond organizationService.get(id)
    }

    def update(Organization organization) {
        if (organization == null) {
            notFound()
            return
        }

        try {
            organizationService.save(organization)
        } catch (ValidationException e) {
            respond organization.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'organization.label', default: 'Organization'), organization.id])
                redirect organization
            }
            '*'{ respond organization, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        organizationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'organization.label', default: 'Organization'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'organization.label', default: 'Organization'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def organization()
    {
        println("I am in organization")
        Organization org = Organization.findByUid(session.uid)
        println("org:"+org)
        session.fname=org.organization_name
        println("session.fname::"+session.fname)
    }
    def addinstructor()
    {
        println("I am in addinstructor")
    }
    def saveinstructor()
    {
        println("I am in saveinstructor::" + params)
        String empcode="",fname="",mname="",lname="",designation="",program="",email=""
        def file = request.getFile('sheet')
        println("File:" + file.originalFilename)
        def (filename, ext) = (file.originalFilename).tokenize('.')
        if (ext.equals("xlsx") || ext.equals("xls"))
        {
            println("Extension is xls")
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0)
            println("Size"+sheet.getPhysicalNumberOfRows()-1)
            for (int row = 1; row < sheet.getPhysicalNumberOfRows(); row++)
            {
                empcode=""
                fname=""
                mname=""
                lname=""
                designation=""
                program=""
                email=""
                HSSFRow row1 = sheet.getRow(row)
                HSSFCell cellempcode = row1.getCell(0)
                if(cellempcode!=null)
                {
                    try {
                        empcode = cellempcode.getStringCellValue()
                    } catch (Exception e) {
                        int x = (int) cellempcode.getNumericCellValue()
                        empcode = "" + x
                    }
                }
                if(row1.getCell(1)!=null)
                {
                    fname = row1.getCell(1).getStringCellValue()
                }
                if(row1.getCell(2)!=null)
                {
                    mname = row1.getCell(2).getStringCellValue()
                }
                if(row1.getCell(3)!=null)
                {
                    lname = row1.getCell(3).getStringCellValue()
                }
                if(row1.getCell(4)!=null)
                {
                    designation = row1.getCell(4).getStringCellValue()
                }
                if(row1.getCell(5)!=null)
                {
                    program = row1.getCell(5).getStringCellValue()
                }
                if(row1.getCell(6)!=null)
                {
                    email = row1.getCell(6).getStringCellValue()
                }
                println(empcode+" "+fname+" "+mname+" "+lname+" "+designation+" "+program+" "+email)
                UserType ut=UserType.findByType("Instructor")
                println("UserType::"+ut)
                RoleType rt=RoleType.findByType("VOLP")
                println("RoleType::"+rt)
                Role role=Role.findByRoleAndRoletype("Instructor",rt)
                println("Role::"+role)
                Login login=Login.findByUsername(email)
                if(login==null)
                {
                    login=new Login()
                    login.username=email
                    login.password=email
                    login.creation_date=new java.util.Date()
                    login.updation_date=new java.util.Date()
                    login.creation_ip_address=request.getRemoteAddr()
                    login.updation_ip_address=request.getRemoteAddr()
                    login.isloginblocked=false
                    login.usertype=ut
                    login.socialaccounttype=null
                    login.addToRoles(role)
                    login.save(failOnError:true,flush:true)
                }
                else
                {
                    println("Login record already present..."+email)
                    login.isloginblocked=false
                    login.usertype=ut
                    login.addToRoles(role)
                    login.save(failOnError:true,flush:true)
                }
                Person person=Person.findByEmail(email)
                if(person==null)
                {
                   person=new Person()
                    person.email=email
                    person.firstName=fname
                    person.middleName=mname
                    person.lastName=lname
                    person.username=session.uid
                    person.creation_date=new java.util.Date()
                    person.updation_date=new java.util.Date()
                    person.creation_ip_address=request.getRemoteAddr()
                    person.updation_ip_address=request.getRemoteAddr()
                    person.gender=null
                    person.save(failOnError:true,flush:true)
                }
                else
                {
                    person.firstName=fname
                    person.middleName=mname
                    person.lastName=lname
                    person.save(failOnError:true,flush:true)
                    println("Person record already present..."+email)
                }
                Organization org=Organization.findByUid(session.uid)
                println("Organization::"+org)
                Designation desig=Designation.findByName(designation)
                println("Designation::"+desig)
                Program pg=Program.findByName(program)
                println("Program:"+pg)
                Instructor instructor=Instructor.findByUid(email)
                if(instructor==null)
                {
                    instructor=new Instructor()
                    instructor.uid=email
                    instructor.employee_code=empcode
                    instructor.username=session.uid
                    instructor.creation_date=new java.util.Date()
                    instructor.updation_date=new java.util.Date()
                    instructor.creation_ip_address=request.getRemoteAddr()
                    instructor.updation_ip_address=request.getRemoteAddr()
                    instructor.person=person
                    instructor.organization=org
                    instructor.designation=desig
                    instructor.program=pg
                    instructor.save(failOnError:true,flush:true)
                }
                else
                {
                    instructor.employee_code=empcode
                    instructor.organization=org
                    instructor.designation=desig
                    instructor.program=pg
                    instructor.save(failOnError:true,flush:true)
                    println("Instructor Record ALready Present...."+email)
                }
            }
        }
        flash.message="File imported Successfully..."
        redirect(controller:"organization",action:"organization")
       // redirect(controller:"Organization",action:"addinstructor")
    }
    def addlearner()
    {
        println("I am in addlearner")
    }
    def savelearner()
    {
        println("I am in savelearner..")
        String grnumber="",fname="",mname="",lname="",program="",year="",email=""
        def file = request.getFile('sheet')
        println("File:" + file.originalFilename)
        def (filename, ext) = (file.originalFilename).tokenize('.')
        if (ext.equals("xlsx") || ext.equals("xls"))
        {
            println("Extension is xls")
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0)
            println("Size" + sheet.getPhysicalNumberOfRows() - 1)
            for (int row = 1; row < sheet.getPhysicalNumberOfRows(); row++)
            {
                grnumber = ""
                fname = ""
                mname = ""
                lname = ""
                program = ""
                year = ""
                email = ""
                HSSFRow row1 = sheet.getRow(row)
                HSSFCell cellgrnumber = row1.getCell(0)
                if (cellgrnumber != null) {
                    try {
                        grnumber = cellgrnumber.getStringCellValue()
                    } catch (Exception e) {
                        int x = (int) cellgrnumber.getNumericCellValue()
                        grnumber = "" + x
                    }
                }
                if (row1.getCell(1) != null) {
                    fname = row1.getCell(1).getStringCellValue()
                }
                if (row1.getCell(2) != null) {
                    mname = row1.getCell(2).getStringCellValue()
                }
                if (row1.getCell(3) != null) {
                    lname = row1.getCell(3).getStringCellValue()
                }
                if (row1.getCell(4) != null) {
                    program = row1.getCell(4).getStringCellValue()
                }
                if (row1.getCell(5) != null) {
                    year = row1.getCell(5).getStringCellValue()
                }
                if (row1.getCell(6) != null) {
                    email = row1.getCell(6).getStringCellValue()
                }
                println(grnumber + " " + fname + " " + mname + " " + lname + " " + program + " " + year + " " + email)
                UserType ut=UserType.findByType("Learner")
                println("UserType::"+ut)
                RoleType rt=RoleType.findByType("VOLP")
                println("RoleType::"+rt)
                Role role=Role.findByRoleAndRoletype("Learner",rt)
                println("Role::"+role)
                Login login=Login.findByUsername(email)
                if(login==null)
                {
                  //  println("email::"+email)
                    login=new Login()
                    login.username=email
                    login.password=email
                    login.creation_date=new java.util.Date()
                    login.updation_date=new java.util.Date()
                    login.creation_ip_address=request.getRemoteAddr()
                    login.updation_ip_address=request.getRemoteAddr()
                    login.isloginblocked=false
                    login.usertype=ut
                    login.socialaccounttype=null
                    login.addToRoles(role)
                    login.save(failOnError:true,flush:true)
                }
                else
                {
                    login.isloginblocked=false
                    login.usertype=ut
                    login.addToRoles(role)
                    login.save(failOnError:true,flush:true)
                    println("Login record already present..."+email)
                }
                Person person=Person.findByEmail(email)
                if(person==null)
                {
                    person=new Person()
                    person.email=email
                    person.firstName=fname
                    person.middleName=mname
                    person.lastName=lname
                    person.username=session.uid
                    person.creation_date=new java.util.Date()
                    person.updation_date=new java.util.Date()
                    person.creation_ip_address=request.getRemoteAddr()
                    person.updation_ip_address=request.getRemoteAddr()
                    person.gender=null
                    person.save(failOnError:true,flush:true)
                }
                else
                {
                    person.firstName=fname
                    person.middleName=mname
                    person.lastName=lname
                    person.save(failOnError:true,flush:true)
                    println("Person record already present..."+email)
                }
                Organization org=Organization.findByUid(session.uid)
                println("Organization::"+org)
                Year y=Year.findByYear(year)
                println("Year::"+y)
                Program pg=Program.findByName(program)
                println("Program:"+pg)
                Learner learner=Learner.findByUid(email)
                if(learner==null)
                {
                    learner=new Learner()
                    learner.uid=email
                    learner.registration_number=grnumber
                    learner.username=session.uid
                    learner.creation_date=new java.util.Date()
                    learner.updation_date=new java.util.Date()
                    learner.creation_ip_address=request.getRemoteAddr()
                    learner.updation_ip_address=request.getRemoteAddr()
                    learner.person=person
                    learner.organization=org
                    learner.current_year=y
                    learner.program=pg
                    learner.save(failOnError:true,flush:true)
                }
                else
                {
                    learner.registration_number=grnumber
                    learner.organization=org
                    learner.current_year=y
                    learner.program=pg
                    learner.save(failOnError:true,flush:true)
                    println("Learner Record ALready Present...."+email)
                }

            }
        }
        flash.message="File imported Successfully..."
        redirect(controller:"organization",action:"organization")
    }
    def blockinstructor()
    {
        println("I am in blockinstructor...")
    }
    def saveblockinstructor()
    {
        println("I am in saveblockinstructor....")
        String empcode="",fname="",mname="",lname="",email=""
        def file = request.getFile('sheet')
        println("File:" + file.originalFilename)
        def (filename, ext) = (file.originalFilename).tokenize('.')
        if (ext.equals("xlsx") || ext.equals("xls"))
        {
            println("Extension is xls")
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0)
            println("Size" + sheet.getPhysicalNumberOfRows() - 1)
            for (int row = 1; row < sheet.getPhysicalNumberOfRows(); row++)
            {
                empcode = ""
                fname = ""
                mname = ""
                lname = ""
                email = ""
                HSSFRow row1 = sheet.getRow(row)
                HSSFCell cellempcode = row1.getCell(0)
                if (cellempcode != null)
                {
                    try {
                        empcode = cellempcode.getStringCellValue()
                    } catch (Exception e) {
                        int x = (int) cellempcode.getNumericCellValue()
                        empcode = "" + x
                    }
                }
                if (row1.getCell(1) != null) {
                    fname = row1.getCell(1).getStringCellValue()
                }
                if (row1.getCell(2) != null) {
                    mname = row1.getCell(2).getStringCellValue()
                }
                if (row1.getCell(3) != null) {
                    lname = row1.getCell(3).getStringCellValue()
                }
                if (row1.getCell(4) != null) {
                    email = row1.getCell(4).getStringCellValue()
                }
                println(empcode+" "+fname+" "+mname+" "+lname+" "+email)
                Login login=Login.findByUsername(email)
                if(login!=null)
                {
                    login.isloginblocked=true
                    login.save(failOnError:true,flush:true)
                }
                else
                {
                    println("User Not Present")
                }
            }
        }
        flash.message="File imported Successfully..."
        redirect(controller:"organization",action:"organization")
    }
    def blocklearner()
    {
        println("I am in blocklearner")
    }
    def saveblocklearner()
    {
        println("I am in saveblocklearner")
        String grnumber="",fname="",mname="",lname="",email=""
        def file = request.getFile('sheet')
        println("File:" + file.originalFilename)
        def (filename, ext) = (file.originalFilename).tokenize('.')
        if (ext.equals("xlsx") || ext.equals("xls"))
        {
            println("Extension is xls")
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0)
            println("Size" + sheet.getPhysicalNumberOfRows() - 1)
            for (int row = 1; row < sheet.getPhysicalNumberOfRows(); row++)
            {
                grnumber = ""
                fname = ""
                mname = ""
                lname = ""
                email = ""
                HSSFRow row1 = sheet.getRow(row)
                HSSFCell cellgrnumber = row1.getCell(0)
                if (cellgrnumber != null)
                {
                    try {
                        grnumber = cellgrnumber.getStringCellValue()
                    } catch (Exception e) {
                        int x = (int) cellgrnumber.getNumericCellValue()
                        grnumber = "" + x
                    }
                }
                if (row1.getCell(1) != null) {
                    fname = row1.getCell(1).getStringCellValue()
                }
                if (row1.getCell(2) != null) {
                    mname = row1.getCell(2).getStringCellValue()
                }
                if (row1.getCell(3) != null) {
                    lname = row1.getCell(3).getStringCellValue()
                }
                if (row1.getCell(4) != null) {
                    email = row1.getCell(4).getStringCellValue()
                }
                println(grnumber+" "+fname+" "+mname+" "+lname+" "+email)
                Login login=Login.findByUsername(email)
                if(login!=null)
                {
                    login.isloginblocked=true
                    login.save(failOnError:true,flush:true)
                }
                else
                {
                    println("User Not Present")
                }
            }
        }
        flash.message="File imported Successfully..."
        redirect(controller:"organization",action:"organization")
    }
    def viewinstructors()
    {
        println("I am in viewinstructors")
        Organization org=Organization.findByUid(session.uid)
        println("org:"+org)
        def instructorlist=Instructor.findAllByOrganization(org)
        println("instructorlist::"+instructorlist)
        [instructorlist:instructorlist]
    }
    def viewlearners()
    {
        println("I am in viewlearners")
        Organization org=Organization.findByUid(session.uid)
        println("org:"+org)
        def learnerlist=Learner.findAllByOrganization(org)
        println("learnerlist::"+learnerlist)
        [learnerlist:learnerlist]
    }
}
