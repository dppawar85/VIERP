package volp

import grails.validation.ValidationException

import javax.validation.constraints.Null

import static org.springframework.http.HttpStatus.*
import grails.converters.*
class CourseCategoryController {

    CourseCategoryService courseCategoryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def searchCourse()
    {
        println(params)

    }
    def showCourses()
    {
        println("in showCourses:"+params)
        CourseCategory cc = CourseCategory.findById(params.ccid)
        println("cc:"+cc.name)
        def crslst = Course.findAllByCoursecategory(cc)
        [instructorcourses:crslst]
    }
    def searchCatagory()
    {
        println("hi alankar ")
        def mainCourse = CourseCategory.findAllByCoursecategoryIsNull()
        println("mainCourse:"+mainCourse.name)
        [mainCourse:mainCourse]
    }

    def findSubCourses()
    {

        String catid = params.cid
        CourseCategory obj= CourseCategory.get(catid)
        def subCourses =  CourseCategory.findAllByCoursecategory(obj)
        if(subCourses.empty)
            println("null")
        else
            println(subCourses)
        [subCourses:subCourses]
    }
    def ajaxCourseFinder = {
        def mainCourse =[]
        String regExp = params.term
        println("crs param"+ params)
        println("regExp"+regExp.size())
        def MainCourse =[]
        def main
        if(regExp.size()==0) {
            flash.message ="Please enter course in search box"
            return
        }
        else {
            mainCourse= Course.list()
            for (Course c : mainCourse) {
                if(CourseOffering.findAllByCourse(c)) {
                    String crs = c.getCourse_name()
                    println("c::" + crs)
                    if (crs.toLowerCase().contains(regExp.toLowerCase())) {
                        flash.message = ''
                        MainCourse.add(c)
                    }
                }
            }
        }
        println("ajax finder mainCourse"+MainCourse.course_name)
        render (MainCourse.course_name as JSON)
    }

    def findCourses()
    {
        println("i am in find course")
        def mainCourse = CourseCategory.findAllByCoursecategoryIsNotNull()
        String message
        def fee =[]
        int count =0
        def MainCourse =[]
        String regExp = params.searchTxt
        println("crs param"+ params)
        println("regExp"+regExp.size())
        println("main coyurse"+mainCourse)
        if(regExp.size()==0) {
            flash.message ="Please enter course in search box"
            return
        }
        else {
            //mainCourse = CourseCategory.findAllByCoursecategoryIsNotNullAndNameIlike("%${regExp}%")
            //results = Book.findAllByTitleIlike("%Hobbit%") // ignore case
            //mainCourse = Course.findAllByCourse_name(regExp,[ignoreCase: true])//("oop")
            //mainCourse = Course.list()
            mainCourse = Course.list()
            println("mainCourse"+mainCourse)

            if(mainCourse.empty) {
                println("mainCourse"+mainCourse)
                flash.message = "Course is not available"
                return
            }
            else {
                for (Course c : mainCourse) {
                    if(CourseOffering.findByCourse(c)) {

                        String crs = c.getCourse_name()
                        println("c::" + crs)
                        if (crs.toLowerCase().contains(regExp.toLowerCase())) {
                            flash.message = ''
                            MainCourse.add(c)
                            count++
                            println("course_name"+c.course_name)
                            println("course_code"+c.course_code)
                            // println("Department"+c.department.name)
                            //println("program"+c.program.name)
                            //println("CourseOwner"+c.courseowner.person.firstName)
                            //println("prerequisite"+c.prerequisite.prerequisite)

                            def crcOff = CourseOffering.findByCourse(c)
                            def certType = CourseCertificateType.list()
                            def CourseFeesType = CourseFeesType.findByType("OutsideDepartment")
                            def all = CourseFees.findAllByCourseoffering(crcOff)
                            for(CourseFees cft:all)
                            {
                                if(cft.coursefeestype.type[0].equals("OutsideDepartment")) {
                                    println("x" + cft.coursefeestype.type[0] + "fee" + all.coursefeestype.type)
                                    fee.add(cft.fees)
                                }
                            }
                            for(CourseCertificateType crct:certType) {
                                def crsfees = CourseFees.findAllByCourseofferingAndCoursecertificatetype(crcOff,crct)
                                println("fee" + crsfees.fees+"outside"+crsfees.coursefeestype.type)
                                fee.add(crsfees)
                            }
                        }
                    }
                }
            }
            if(count==0)
            {
                //flash.message="Course is not available"
                redirect(controller:"login",action:"coursenotfound")
            }
        }
        println("MainCourse"+MainCourse.courseinstructor.person.firstName)
        //println("fee"+fee[0].fees)
        //render ("hi")
        [mainCourse: MainCourse,fee:fee]
    }
    def register()
    {
        render("Successfully Registered")
    }
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseCategoryService.list(params), model:[courseCategoryCount: courseCategoryService.count()]
    }

    def show(Long id) {
        respond courseCategoryService.get(id)
    }

    def create() {
        respond new CourseCategory(params)
    }

    def save(CourseCategory courseCategory) {
        if (courseCategory == null) {
            notFound()
            return
        }

        try {
            courseCategoryService.save(courseCategory)
        } catch (ValidationException e) {
            respond courseCategory.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseCategory.label', default: 'CourseCategory'), courseCategory.id])
                redirect courseCategory
            }
            '*' { respond courseCategory, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseCategoryService.get(id)
    }

    def update(CourseCategory courseCategory) {
        if (courseCategory == null) {
            notFound()
            return
        }

        try {
            courseCategoryService.save(courseCategory)
        } catch (ValidationException e) {
            respond courseCategory.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseCategory.label', default: 'CourseCategory'), courseCategory.id])
                redirect courseCategory
            }
            '*'{ respond courseCategory, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseCategoryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseCategory.label', default: 'CourseCategory'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseCategory.label', default: 'CourseCategory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def addcategory()
    {
        ArrayList menu=new ArrayList()
        println("I am in addcategory....")
        def maincategory=CourseCategory.findAllByCoursecategory(null)
        maincategory.sort{it.name}
        for(CourseCategory mc:maincategory)
        {
            def subcategory=CourseCategory.findAllByCoursecategory(mc)
            subcategory.sort{it.name}
            // println("Main Category::"+mc)
            // println("Sub Category:"+subcategory)
            ArrayList submenu=new ArrayList()
            menu.add(mc)
            submenu.addAll(subcategory)
            menu.add(submenu)
        }
        // println("menu::"+menu)
        [menu:menu]
    }
    def addmaincategory()
    {
        ArrayList menu=new ArrayList()
        println("I am in addmaincategory....")
        def maincategory=CourseCategory.findAllByCoursecategory(null)
        maincategory.sort{it.name}
        for(CourseCategory mc:maincategory)
        {
            def subcategory=CourseCategory.findAllByCoursecategory(mc)
            subcategory.sort{it.name}
            // println("Main Category::"+mc)
            // println("Sub Category:"+subcategory)
            ArrayList submenu=new ArrayList()
            menu.add(mc)
            submenu.addAll(subcategory)
            menu.add(submenu)
        }
        // println("menu::"+menu)
        [menu:menu]
    }
    def savemaincategory()
    {
        println("I am in savemaincategory.."+params)
        CourseCategory cc=CourseCategory.findByNameAndCoursecategory(params.maincategory,null)
        if(cc==null)
        {
            cc=new CourseCategory()
            cc.name=params.maincategory
            cc.coursecategory=null
            cc.username=session.uid
            cc.creation_date=new java.util.Date()
            cc.updation_date=new java.util.Date()
            cc.creation_ip_address=request.getRemoteAddr()
            cc.updation_ip_address=request.getRemoteAddr()
            cc.save(failOnError:true,flush:true)
            flash.message="Category Added Successfully....."
        }
        else
        {
            flash.message="Category Already Present...."
        }
        ////////Copied from addcategory
        ArrayList menu=new ArrayList()
        println("I am in addcategory....")
        def maincategory=CourseCategory.findAllByCoursecategory(null)
        maincategory.sort{it.name}
        for(CourseCategory mc:maincategory)
        {
            def subcategory=CourseCategory.findAllByCoursecategory(mc)
            subcategory.sort{it.name}
            // println("Main Category::"+mc)
            // println("Sub Category:"+subcategory)
            ArrayList submenu=new ArrayList()
            menu.add(mc)
            submenu.addAll(subcategory)
            menu.add(submenu)
        }
        // println("menu::"+menu)
        [menu:menu]
        // redirect(controller:"CourseCategory",action:"addcategory")
    }
    def addsubcategory()
    {
        ArrayList menu=new ArrayList()
        println("I am in addsubcategory....")
        def maincategory=CourseCategory.findAllByCoursecategory(null)
        maincategory.sort{it.name}
        for(CourseCategory mc:maincategory)
        {
            def subcategory=CourseCategory.findAllByCoursecategory(mc)
            subcategory.sort{it.name}
            // println("Main Category::"+mc)
            // println("Sub Category:"+subcategory)
            ArrayList submenu=new ArrayList()
            menu.add(mc)
            submenu.addAll(subcategory)
            menu.add(submenu)
        }
        CourseCategory cc=new CourseCategory()
        cc.name="Select Course Category"
        ArrayList maincategorylist=new ArrayList()
        maincategorylist.add(cc)
        maincategorylist.addAll(maincategory)
        [menu:menu,maincategorylist:maincategorylist]
    }
    def addfurthersubcategory()
    {
        println("I am in addfurthersubcategory...."+params)
        ArrayList menu=new ArrayList()
        def maincategory=CourseCategory.findAllByCoursecategory(null)
        maincategory.sort{it.name}
        for(CourseCategory mc:maincategory)
        {
            def subcategory=CourseCategory.findAllByCoursecategory(mc)
            subcategory.sort{it.name}
            // println("Main Category::"+mc)
            // println("Sub Category:"+subcategory)
            ArrayList submenu=new ArrayList()
            menu.add(mc)
            submenu.addAll(subcategory)
            menu.add(submenu)
        }
        [maincategory:params.maincategory,menu:menu]
    }
    def savesubcategory()
    {
        println("I am in savesubcategory...."+params)
        CourseCategory cc=CourseCategory.findByNameAndCoursecategory(params.maincategory,null)
        if(cc!=null)
        {
            CourseCategory cc1=CourseCategory.findByNameAndCoursecategory(params.subcategory,cc)
            if(cc1==null)
            {
                cc1 = new CourseCategory()
                cc1.name = params.subcategory
                cc1.coursecategory = cc
                cc1.username = session.uid
                cc1.creation_date = new java.util.Date()
                cc1.updation_date = new java.util.Date()
                cc1.creation_ip_address = request.getRemoteAddr()
                cc1.updation_ip_address = request.getRemoteAddr()
                cc1.save(failOnError: true, flush: true)
                flash.message = "Category Added Successfully....."
            }
            else
                flash.message="Sub-Category Already Present"
        }
        else
            flash.message="Main-Category NOT Present"
        ////////Copied from addcategory
        ArrayList menu=new ArrayList()
        println("I am in addcategory....")
        def maincategory=CourseCategory.findAllByCoursecategory(null)
        maincategory.sort{it.name}
        for(CourseCategory mc:maincategory)
        {
            def subcategory=CourseCategory.findAllByCoursecategory(mc)
            subcategory.sort{it.name}
            // println("Main Category::"+mc)
            // println("Sub Category:"+subcategory)
            ArrayList submenu=new ArrayList()
            menu.add(mc)
            submenu.addAll(subcategory)
            menu.add(submenu)
        }
        // println("menu::"+menu)
        [menu:menu]

    }
    def changesubcategorylinking()
    {
        println("I am in changesubcategorylinking..")
        ArrayList menu=new ArrayList()
        def maincategory=CourseCategory.findAllByCoursecategory(null)
        maincategory.sort{it.name}
        ArrayList subcategorylist=new ArrayList()
        for(CourseCategory mc:maincategory)
        {
            def subcategory=CourseCategory.findAllByCoursecategory(mc)
            subcategory.sort{it.name}
            subcategorylist.addAll(subcategory)
            // println("Main Category::"+mc)
            // println("Sub Category:"+subcategory)
            ArrayList submenu=new ArrayList()
            menu.add(mc)
            submenu.addAll(subcategory)
            menu.add(submenu)
        }
        subcategorylist.sort{it.name}
        [menu:menu,maincategorylist:maincategory,subcategorylist:subcategorylist]
    }
    def savechangesubcategorylinking()
    {
        println("I am in savechangesubcategorylinking...."+params)
        CourseCategory cc=CourseCategory.findByNameAndCoursecategory(params.maincategory,null)
        if(cc!=null)
        {
            CourseCategory cc1=CourseCategory.findByName(params.subcategory)
            if(cc1!=null)
            {
                cc1.coursecategory = cc
                cc1.username = session.uid
                cc1.updation_date = new java.util.Date()
                cc1.updation_ip_address = request.getRemoteAddr()
                cc1.save(failOnError: true, flush: true)
                flash.message = "Sub-Category Linking Changed Successfully....."
            }
        }
        else
            flash.message="Main Category NOT Present.."

        ArrayList menu=new ArrayList()
        def maincategory=CourseCategory.findAllByCoursecategory(null)
        maincategory.sort{it.name}
        for(CourseCategory mc:maincategory)
        {
            def subcategory=CourseCategory.findAllByCoursecategory(mc)
            subcategory.sort{it.name}
            // println("Main Category::"+mc)
            // println("Sub Category:"+subcategory)
            ArrayList submenu=new ArrayList()
            menu.add(mc)
            submenu.addAll(subcategory)
            menu.add(submenu)
        }
        // println("menu::"+menu)
        [menu:menu]
    }
    def editmaincategory()
    {
        println("I am in editmaincategory:"+params)
        CourseCategory cc=CourseCategory.findById(params.categoryid)
        println("Course Category::"+cc)
        cc.name=params.categoryname
        cc.username = session.uid
        cc.updation_date = new java.util.Date()
        cc.updation_ip_address = request.getRemoteAddr()
        cc.save(failOnError: true, flush: true)
        flash.message = "Category Name Changed Successfully....."
        ArrayList menu=new ArrayList()
        def maincategory=CourseCategory.findAllByCoursecategory(null)
        maincategory.sort{it.name}
        for(CourseCategory mc:maincategory)
        {
            def subcategory=CourseCategory.findAllByCoursecategory(mc)
            subcategory.sort{it.name}
            // println("Main Category::"+mc)
            // println("Sub Category:"+subcategory)
            ArrayList submenu=new ArrayList()
            menu.add(mc)
            submenu.addAll(subcategory)
            menu.add(submenu)
        }
        // println("menu::"+menu)
        [menu:menu]
    }
}
