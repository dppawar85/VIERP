package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DivisionController {

    DivisionService divisionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond divisionService.list(params), model:[divisionCount: divisionService.count()]
    }

    def show(Long id) {
        respond divisionService.get(id)
    }

    def create() {
        respond new Division(params)
    }

    def save(Division division) {
        if (division == null) {
            notFound()
            return
        }

        try {
            divisionService.save(division)
        } catch (ValidationException e) {
            respond division.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'division.label', default: 'Division'), division.id])
                redirect division
            }
            '*' { respond division, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond divisionService.get(id)
    }

    def update(Division division) {
        if (division == null) {
            notFound()
            return
        }

        try {
            divisionService.save(division)
        } catch (ValidationException e) {
            respond division.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'division.label', default: 'Division'), division.id])
                redirect division
            }
            '*'{ respond division, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        divisionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'division.label', default: 'Division'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'division.label', default: 'Division'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def adddivision()
    {
        println("I am in adddivision...")
        Organization org = Organization.findByUid(session.uid)
        println("org:"+org)
        def deptlist=Department.findAllByOrganization(org)
        println("deptlist::"+deptlist)
        def programlist=new ArrayList()
        def divisionlist=new ArrayList()
        def divlist=Division.list()
        println("divlist:"+divlist)
        for(Department d:deptlist)
        {
            def pg=Program.findAllByDepartment(d)
            for(Program p:pg)
            {
                if(p!=null)
                {
                    programlist.add(p)
                    if(divlist.size()!=0)
                    {
                        def di = Division.findAllByProgram(p)
                        for (Division d1 : di) {
                            if (d1 != null) {
                                println("xyz::" + d1)
                                divisionlist.add(d1)
                            }
                        }
                    }
                }
            }
        }
        println("programlist::"+programlist)
        programlist.sort{it.department}
        def yearlist=Year.list()
        println("yearlist:"+yearlist)
        //   println("divisionlist:"+divisionlist)
        [programlist:programlist,yearlist:yearlist,divisionlist:divisionlist]
    }
    def savedivision()
    {
        println("I am in savedivision..."+params)
        Year year=Year.findByYear(params.year)
        println("year:"+year)
        Program program=Program.findByName(params.program)
        println("program:"+program)
        String divisionname=params.division
        divisionname=divisionname.replaceAll("\\s+","")
        divisionname=divisionname.toUpperCase()
        Division d=Division.findByNameAndYearAndProgram(divisionname,year,program)
        println("Division:"+d)
        if(d==null)  //insert
        {
            d=new Division()
            d.name=divisionname
            d.username=session.uid
            d.creation_date=new java.util.Date()
            d.updation_date=new java.util.Date()
            d.creation_ip_address=request.getRemoteAddr()
            d.updation_ip_address=request.getRemoteAddr()
            d.year=year
            d.program=program
            d.save(failOnError:true,flush:true)
        }
        else   //update
        {
            d.username=session.uid
            d.updation_date=new java.util.Date()
            d.updation_ip_address=request.getRemoteAddr()
            d.name=divisionname
            d.year=year
            d.program=program
            d.save(failOnError:true,flush:true)
        }
        Organization org = Organization.findByUid(session.uid)
        println("org:"+org)
        def deptlist=Department.findAllByOrganization(org)
        println("deptlist::"+deptlist)
        def divlist=Division.list()
        println("divlist:"+divlist)
        def programlist=new ArrayList()
        def divisionlist=new ArrayList()

        for(Department dept:deptlist)
        {
            def pg=Program.findAllByDepartment(dept)
            for(Program p:pg)
            {
                if(p!=null)
                {
                    programlist.add(p)
                    if(divlist.size()!=0)
                    {
                        def di = Division.findAllByProgram(p)
                        for (Division d1 : di) {
                            if (d1 != null) {
                                println("xyz::" + d1)
                                divisionlist.add(d1)
                            }
                        }
                    }
                }
            }
        }
        println("programlist::"+programlist)
        programlist.sort{it.department}
        def yearlist=Year.list()
        println("yearlist:"+yearlist)
        //   println("divisionlist:"+divisionlist)
        [programlist:programlist,yearlist:yearlist,divisionlist:divisionlist]
    }
    def updatedivision()
    {
        println("I am in update division:"+params)
        Organization org = Organization.findByUid(session.uid)
        println("org:"+org)
        def deptlist=Department.findAllByOrganization(org)
        println("deptlist::"+deptlist)
        def divlist=Division.list()
        def programlist=new ArrayList()
        def divisionlist=new ArrayList()
        for(Department dept:deptlist)
        {
            def pg=Program.findAllByDepartment(dept)
            for(Program p:pg)
            {
                if(p!=null)
                {
                    programlist.add(p)
                    if(divlist.size()!=0)
                    {
                        def di = Division.findAllByProgram(p)
                        for (Division d1 : di) {
                            if (d1 != null) {
                                println("xyz::" + d1)
                                divisionlist.add(d1)
                            }
                        }
                    }
                }
            }
        }
        println("programlist::"+programlist)
        programlist.sort{it.department}
        def yearlist=Year.list()
        println("yearlist:"+yearlist)
        Division division=Division.findById(params.id)
        println("division::"+division)
        session.divisionchange=division
        [programlist:programlist,yearlist:yearlist,divisionlist:divisionlist,division:division]
    }
    def saveupdatedivision()
    {
        println("I am in saveupdatedivision")
        Organization org = Organization.findByUid(session.uid)
        println("org:"+org)
        def deptlist=Department.findAllByOrganization(org)
        println("deptlist::"+deptlist)
        def divlist=Division.list()
        def programlist=new ArrayList()
        def divisionlist=new ArrayList()
        for(Department dept:deptlist)
        {
            def pg=Program.findAllByDepartment(dept)
            for(Program p:pg)
            {
                if(p!=null)
                {
                    programlist.add(p)
                    if(divlist.size()!=0)
                    {
                        def di = Division.findAllByProgram(p)
                        for (Division d1 : di) {
                            if (d1 != null) {
                                println("xyz::" + d1)
                                divisionlist.add(d1)
                            }
                        }
                    }
                }
            }
        }
        println("programlist::"+programlist)
        programlist.sort{it.department}
        def yearlist=Year.list()
        println("yearlist:"+yearlist)

        Year year=Year.findByYear(params.year)
        println("year:"+year)
        Program program=Program.findByName(params.program)
        println("program:"+program)
        String divisionname=params.division
        divisionname=divisionname.replaceAll("\\s+","")
        divisionname=divisionname.toUpperCase()
        Division division=Division.findById(session.divisionchange.id)
        println("Division:"+division)

        division.username=session.uid
        division.updation_date=new java.util.Date()
        division.updation_ip_address=request.getRemoteAddr()
        division.name=divisionname
        division.year=year
        division.program=program
        division.save(failOnError:true,flush:true)

        [programlist:programlist,yearlist:yearlist,divisionlist:divisionlist,division:division]
    }
}
