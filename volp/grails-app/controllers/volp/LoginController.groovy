package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import com.google.gson.*
import grails.converters.JSON
class LoginController {

    LoginService loginService
    def RegistrationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond loginService.list(params), model:[loginCount: loginService.count()]
    }

    def show(Long id) {
        respond loginService.get(id)
    }

    def create() {
        respond new Login(params)
    }

    def save(Login login) {
        if (login == null) {
            notFound()
            return
        }

        try {
            loginService.save(login)
        } catch (ValidationException e) {
            respond login.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'login.label', default: 'Login'), login.id])
                redirect login
            }
            '*' { respond login, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond loginService.get(id)
    }

    def update(Login login) {
        if (login == null) {
            notFound()
            return
        }

        try {
            loginService.save(login)
        } catch (ValidationException e) {
            respond login.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'login.label', default: 'Login'), login.id])
                redirect login
            }
            '*'{ respond login, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        loginService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'login.label', default: 'Login'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'login.label', default: 'Login'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def test1()
    {

        println("test1")

    }
    def login(){
        session.redirect =""
        session.redirect_msg =""
        session.uid= ""
        ApplicationType at = ApplicationType.findByApplication_type("VOLP")
        def usertype = UserType.findAllByApplication_typeAndTypeNotEqualAndTypeNotEqual(at,"Organization","Administrator")
        println("login"+usertype.type)
        def fbkey=2080629828838897
        [usertype:usertype,fbkey:fbkey]
    }
    def setusertype(){
        println("in setusertype params:"+params)
        UserType ut = UserType.findById(params.utid)
        session.usertypo = ut
        String s = ""
        [s:s]
    }
    //google
    def processlogin(){ //google
        println("in processlogin session:"+session)
        println("in processlogin params:"+params)
        GooglePojo data
        String uid
        System.out.println("entering doGet");
        try {
            // get code
            String code = params.code//request.getParameter("code");
            // format parameters to post
           // String urlParameters = "code="+ code + "&client_id=1044538822480-rp49uql6hntc6vk2eessltv99gecpfqp.apps.googleusercontent.com" + "&client_secret=ejzi69HPA9l0wzsxUFRtJVXS"+ "&redirect_uri=http://localhost:8080/login/processlogin"+ "&grant_type=authorization_code";
            String urlParameters = "code="+ code + "&client_id=1044538822480-rp49uql6hntc6vk2eessltv99gecpfqp.apps.googleusercontent.com" + "&client_secret=ejzi69HPA9l0wzsxUFRtJVXS"+ "&redirect_uri=http://www.volp.in/login/processlogin"+ "&grant_type=authorization_code";

            //post parameters
            URL url = new URL("https://accounts.google.com/o/oauth2/token");
            //URL url = new URL("https://www.googleapis.com/oauth2/v2/userinfo");
            URLConnection urlConn = url.openConnection();
            urlConn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(
                    urlConn.getOutputStream());
            writer.write(urlParameters);
            writer.flush();

            //get output in outputString
            String line, outputString = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString += line;
            }
            System.out.println(outputString);
            //get Access Token
            JsonObject json = (JsonObject)new JsonParser().parse(outputString);
            String access_token = json.get("access_token").getAsString();
            println(access_token);

            //get User Info
            url = new URL(
                    "https://www.googleapis.com/oauth2/v1/userinfo?access_token="
                            + access_token);
            urlConn = url.openConnection();
            outputString = "";
            reader = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString += line;
            }
            println("OP:"+outputString);

            // Convert JSON response into Pojo class
            data = new Gson().fromJson(outputString, GooglePojo.class);
            println("Date:"+data);
            writer.close();
            reader.close();

        } catch (MalformedURLException e) {
            System.out.println( e);
        } catch (ProtocolException e) {
            System.out.println( e);
        } catch (IOException e) {
            System.out.println( e);
        }
        println("Mail:"+data.email)
        println("F.Name:"+data.given_name)
        println("Last.Name:"+data.family_name)
        println("Gender:"+data.gender)
        Login user = Login.findByUsername(data.email)
        println("USer:"+user)
        if(user==null) {
            String fbid = data.email
            String fname = data.given_name
            String lname = data.family_name
            String type = session.usertypo.type
            String ip = request.getRemoteAddr()
            int f = RegistrationService.registeruserapi(fbid, "", fname, lname, type, ip,"Google")
            println("Reg:" + f)
            println("Type:" + session.usertypo.type)
            println("AT:" + session.usertypo.id)
            UserType ut = UserType.findById(session.usertypo.id)

            session.uid = data.email
            if (session.usertypo.type == "Instructor" && ut.application_type.application_type == "VOLP") {
                session.redirect = "Instructor"
                session.redirect_msg = ""
                //return
            } else if (session.usertypo.type == "Learner" && ut.application_type.application_type == "VOLP") {
                session.redirect = "Learner"
                session.redirect_msg = ""
                //return
            }
            redirect(action: "redirect")
            return
        }
        else{
            session.uid=user.username
            if(user.isloginblocked){
                session.redirect = "login"
                session.redirect_msg ="User Blocked!!!."
               // return
            }
            if (user.usertype.type=="Instructor" && user.usertype.application_type.application_type=="VOLP")
            {
                session.redirect = "Instructor"
                session.redirect_msg =""
               // return
            }
            else if(user.usertype.type=="Learner"  && user.usertype.application_type.application_type=="VOLP")
            {
                session.redirect = "Learner"
                session.redirect_msg =""
               // return
            }
            redirect(action: "redirect")
            return
            //return
        }
        [data:data]
    }
    //No API
    def processsignin() { //No API

        println("processsignin params:" + params)
        println("processreg seesion:"+session)

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
//            if (!user.isVerified) {
//                flash.message = params.email + " not verified!!!"
//                redirect(action: "login")
//                return
//            }
            if (user.usertype.type == "Instructor"  && user.usertype.application_type.application_type=="VOLP")
                redirect(controller: "login", action: "homeDashInstructor")
            else if (user.usertype.type == "Learner"  && user.usertype.application_type.application_type=="VOLP")
                redirect(controller: "login", action: "homeDash")
            else if (user.usertype.type == "Organization"  && user.usertype.application_type.application_type=="VOLP")
                redirect(controller: "organization", action: "organization")
            session.uid=user.username
            return
        }
    }
    def registration(){

    }
    //not used
    def processreg(){ //google
        println("processreg params:"+params)
        println("processreg seesion:"+session)
        GooglePojo data
        String uid
        System.out.println("entering doGet");
        try {
            // get code
            String code = params.code//request.getParameter("code");
            // format parameters to post
           // String urlParameters = "code="+ code + "&client_id=1044538822480-rp49uql6hntc6vk2eessltv99gecpfqp.apps.googleusercontent.com" + "&client_secret=ejzi69HPA9l0wzsxUFRtJVXS"+ "&redirect_uri=http://localhost:8080/login/processreg"+ "&grant_type=authorization_code";
            String urlParameters = "code="+ code + "&client_id=1044538822480-rp49uql6hntc6vk2eessltv99gecpfqp.apps.googleusercontent.com" + "&client_secret=ejzi69HPA9l0wzsxUFRtJVXS"+ "&redirect_uri=http://www.volp.in/login/processlogin"+ "&grant_type=authorization_code";

            //post parameters
            URL url = new URL("https://accounts.google.com/o/oauth2/token");
            //URL url = new URL("https://www.googleapis.com/oauth2/v2/userinfo");
            URLConnection urlConn = url.openConnection();
            urlConn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(
                    urlConn.getOutputStream());
            writer.write(urlParameters);
            writer.flush();

            //get output in outputString
            String line, outputString = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString += line;
            }
            System.out.println(outputString);
            //get Access Token
            JsonObject json = (JsonObject)new JsonParser().parse(outputString);
            String access_token = json.get("access_token").getAsString();
            println(access_token);

            //get User Info
            url = new URL(
                    "https://www.googleapis.com/oauth2/v1/userinfo?access_token="
                            + access_token);
            urlConn = url.openConnection();
            outputString = "";
            reader = new BufferedReader(new InputStreamReader(
                    urlConn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString += line;
            }
            println("OP:"+outputString);

            // Convert JSON response into Pojo class
            data = new Gson().fromJson(outputString, GooglePojo.class);
            println("Date:"+data);
            writer.close();
            reader.close();

        } catch (MalformedURLException e) {
            System.out.println( e);
        } catch (ProtocolException e) {
            System.out.println( e);
        } catch (IOException e) {
            System.out.println( e);
        }
        println("Mail:"+data.email)
        println("F.Name:"+data.given_name)
        println("Last.Name:"+data.family_name)
        println("Gender:"+data.gender)

    }
    def processsignlingdin(){ // linkedin
        println("in processsignlingdin params:"+params)
        println("session:"+session)
        Login user = Login.findByUsername(params.id)
        println("USer:"+user)
        if(user==null) {
            String fbid = params.id
            String fname = params.fname
            String lname = params.lname
            String type = session.usertypo.type
            String ip = request.getRemoteAddr()
            int f = RegistrationService.registeruserapi(fbid,"",fname,lname,type,ip,"LinkedIn")
            println("Reg:"+f)
            println("Type:"+session.usertypo.type)
            println("AT:"+session.usertypo.id)
            UserType ut = UserType.findById(session.usertypo.id)

            session.uid=params.id
            if (session.usertypo.type=="Instructor" && ut.application_type.application_type=="VOLP")
            {
                session.redirect = "Instructor"
                session.redirect_msg =""
                return
            }
            else if(session.usertypo.type=="Learner"  && ut.application_type.application_type=="VOLP")
            {
                session.redirect = "Learner"
                session.redirect_msg =""
                return
            }

        }
        else{
            session.uid=user.username
            if(user.isloginblocked){
                session.redirect = "login"
                session.redirect_msg ="User Blocked!!!."
                return
            }
            if (user.usertype.type=="Instructor" && user.usertype.application_type.application_type=="VOLP")
            {
                session.redirect = "Instructor"
                session.redirect_msg =""
                return
            }
            else if(user.usertype.type=="Learner"  && user.usertype.application_type.application_type=="VOLP")
            {
                session.redirect = "Learner"
                session.redirect_msg =""
                return
            }
            return
        }
        /*if(user==null) {
            session.redirect = "login"
            session.redirect_msg ="Registered!!!. Login Again."
            return
        }
        else{
            session.uid=user.username
            if(user.isloginblocked){
                session.redirect = "login"
                session.redirect_msg ="User Blocked!!!."
                return
            }
            if (user.usertype.type=="Instructor" && user.usertype.application_type.application_type=="VOLP")
            {
                session.redirect = "Instructor"
                session.redirect_msg =""
                return
            }
            else if(user.usertype.type=="Learner"  && user.usertype.application_type.application_type=="VOLP")
            {
                session.redirect = "Learner"
                session.redirect_msg =""
                return
            }
            return
        }*/
    }
    def lind(){

    }
    //FB
    def processfblogin(){
        println("in processfblogin:"+params)

        println("session:"+session)
        Login user = Login.findByUsername(params.id)
        println("USer:"+user)
        if(user==null) {
            String fbid = params.id
            String fname = params.fname
            String lname = params.lname
            String type = session.usertypo.type
            String ip = request.getRemoteAddr()
            int f = RegistrationService.registeruserapi(fbid,"",fname,lname,type,ip,"Facebook")
            println("Reg:"+f)
            println("Type:"+session.usertypo.type)
            println("AT:"+session.usertypo.id)
            UserType ut = UserType.findById(session.usertypo.id)

            session.uid=params.id
            if (session.usertypo.type=="Instructor" && ut.application_type.application_type=="VOLP")
            {
                session.redirect = "Instructor"
                session.redirect_msg =""
                return
            }
            else if(session.usertypo.type=="Learner"  && ut.application_type.application_type=="VOLP")
            {
                session.redirect = "Learner"
                session.redirect_msg =""
                return
            }

        }
        else{
            session.uid=user.username
            if(user.isloginblocked){
                session.redirect = "login"
                session.redirect_msg ="User Blocked!!!."
                return
            }
            if (user.usertype.type=="Instructor" && user.usertype.application_type.application_type=="VOLP")
            {
                session.redirect = "Instructor"
                session.redirect_msg =""
                return
            }
            else if(user.usertype.type=="Learner"  && user.usertype.application_type.application_type=="VOLP")
            {
                session.redirect = "Learner"
                session.redirect_msg =""
                return
            }
            return
        }
    }

    //redirect User
    def redirect(){
        println("in redirect params:"+params)
        println("in redirect session:"+session)
        if(session.redirect=="login") {
            flash.message = session.redirect_msg
            redirect(action: "login")
        }
        if(session.redirect=="Instructor") {
            flash.message = session.redirect_msg
            redirect(controller:"instructor", action: "instructor")
        }
        if(session.redirect=="Learner") {
            flash.message = session.redirect_msg
            redirect(controller:"learner", action: "learner")
        }
    }
    def test2()
    {
        session.type = "Instructor"
        session.type = "Learner"
        println("test2...")
    }
    // code by PHR

    def forgotPassword(){

    }
    def logout(){
        println("Loged out Successfully")
        session.uid=""
        session.invalidate()
        redirect(controller: "login", action: "home1")
    }
    // end of PHR code
    def home1(){
//        ArrayList<Course> crsList = Course.list()
//        crsList.sort{it.rating}
        println("path:"+request.getContextPath())
        final int top = InitialData.findByName("top").number
        final int topcatcrs = InitialData.findByName("topcatcrs").number
        def c = Course.createCriteria()
        ArrayList<Course> results = c.list {
            order("rating", "desc")
        }
        println("result:"+results.size())
        ArrayList topCrs = new ArrayList<Course>()
        int top20 = 0;
        for(Course c1: results){
            if(top20==top)
                break;
            topCrs.add(c1.id)
            top20++
        }
        println("topCrs:"+topCrs)
        top20=0;
        ArrayList<CourseCategory> subCat = CourseCategory.findAllByCoursecategoryIsNotNull()
        //TreeMap<Double,CourseCategory> topCat = new TreeMap<Double,CourseCategory>()
        ArrayList topCat = new ArrayList()
        for(CourseCategory cc: subCat){

            def allCrsCat = Course.findAllByCoursecategory(cc)

            double x = 0.0
            for(Course c2:allCrsCat){
                //println("b4--CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
                if(top20>=topcatcrs)
                    break;
                x += c2.rating
                top20++
                //     println("CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
            }
            //   println("Rating:"+x)
            top20=0
            topCat.add(x)
            topCat.add(cc)
            //println("B4::topCat:"+topCat)
            for(int j=topCat.size()-2;j>0;){
                //  println("j:"+j)
                // println("r:"+topCat[j-2])
                // println("cc:"+topCat[j-1])
                if(topCat[j]>topCat[j-2]){
                    int t = topCat[j-2]
                    CourseCategory tc = topCat[j-1]
                    topCat[j-2] = topCat[j]
                    topCat[j-1] = topCat[j+1]
                    topCat[j] = t
                    topCat[j+1] = tc
                }
                j=j-2
            }
            // println("After::topCat:"+topCat)

        }
        println("topCat:"+topCat)
        top20=0;



        ArrayList<CourseCategory> topSubCat = new ArrayList()
        for(int i=1;i<topCat.size();i+=2)
        {
            topSubCat.add(topCat[i])
        }

        /* for(Map.Entry<Double,CourseCategory> tm:topCat.entrySet()){
             topSubCat.add(tm.getValue())
         }*/
        //println("topSubCat:"+topSubCat.name)
        //Collections.reverse(topSubCat)
        println("topSubCat:"+topSubCat.name)
        int showTopCat = 0
        ArrayList catCrs = new ArrayList()
        ArrayList cat1 = new ArrayList<Course>()
        ArrayList cat2 = new ArrayList<Course>()
        ArrayList cat3 = new ArrayList<Course>()
        ArrayList cat4 = new ArrayList<Course>()
        for(CourseCategory cc: topSubCat){
            println("CC:"+cc.name)
            def cnt = Course.findAllByCoursecategory(cc)
            if(cnt.size==0)
                continue
            if(showTopCat==4){
                break;
            }
            def cat = Course.createCriteria().list {
                coursecategory{
                    eq ('id', cc.id)
                }
                order('rating', 'desc')
                maxResults topcatcrs
            }
            catCrs.add(cc.id)
            catCrs.add(cat.id)
            showTopCat++
        }
        def catname1=catCrs[0]
        cat1=catCrs[1]
        def catname2=catCrs[2]
        cat2=catCrs[3]
        def catname3=catCrs[4]
        cat3=catCrs[5]
        def catname4=catCrs[6]
        cat4=catCrs[7]
        println("cat:"+cat1+"\n"+cat2+"\n"+cat3+"\n"+cat4)
        println("catCrs:"+catCrs)
        topCrs as JSON
        cat1 as JSON
        cat2 as JSON
        cat3 as JSON
        cat4 as JSON
        ArrayList<CourseCategory> mainCat = CourseCategory.findAllByCoursecategoryIsNull()
        ArrayList<CourseCategory> mainmenu = new ArrayList()
        for(CourseCategory mc:mainCat){
            ArrayList<CourseCategory> mm = new ArrayList()
            ArrayList<CourseCategory> sm = CourseCategory.findAllByCoursecategory(mc)
            ArrayList<CourseCategory> ssm = new ArrayList()
            boolean f = false
            println("CC:"+mc.name)
            for(CourseCategory cco : sm){
                def crs = Course.findAllByCoursecategory(cco)
                if(crs.size()>0){
                    f=true
                    //break
                    ssm.add(cco)
                }
            }
            def crs = Course.findAllByCoursecategory(mc)
            println("crs:"+crs.course_name)
            if(crs.size()>0){
                f=true
                //break
            }
            if(f) {
                mm.add(mc)
            }
            else
                continue
            mm.add(ssm)
            mainmenu.add(mm)
            println("CC added:"+mc.name)

        }

        println("Categories MM SM:"+mainmenu.name)
        // session.catCrs = catCrs
        [crs:topCrs,cat1:cat1,cat2:cat2,cat3:cat3,cat4:cat4,cats1:cat1.size(),cats2:cat2.size(),cats3:cat3.size(),cats4:cat4.size(),catname1:catname1,catname2:catname2,catname3:catname3,catname4:catname4,menus:mainmenu]
    }
    def crsSlider(){
        println("in crsSlider:"+params)
        String[] ids = params.crsids.split(",")
        println("IDS:"+ids)
        ArrayList crs = new ArrayList()
        for(int i=0;i<ids.size();i++)
        {
            if(ids[i]=="")
                continue
            Course c = Course.findById(ids[i])
            crs.add(c)
        }
        [crs:crs]
    }
    def catSlider1(){
        println("in catSlider1:"+params)
        CourseCategory cc = CourseCategory.findById(params.cc)
        // println("cc crsSlider1:"+cc)
        String[] ids = params.crsids.split(",")
        println("IDS:"+ids)
        ArrayList crs = new ArrayList()
        for(int i=0;i<ids.size();i++)
        {
            if(ids[i]=="")
                continue
            Course c = Course.findById(ids[i])
            crs.add(c)
        }
        //int csize=Integer.parseInt(params.csize)
        // println("crs: crsSlider1"+crs)
        [crs:crs,cc:cc]
    }
    def catSlider4(){
        println("in catSlider4:"+params)
        CourseCategory cc = CourseCategory.findById(params.cc)
        String[] ids = params.crsids.split(",")
        println("IDS:"+ids)
        ArrayList crs = new ArrayList()
        for(int i=0;i<ids.size();i++)
        {
            if(ids[i]=="")
                continue
            Course c = Course.findById(ids[i])
            crs.add(c)
        }
        [crs:crs,cc:cc]
    }
    def catSlider2(){
        println("in crsSlider2:"+params)
        CourseCategory cc = CourseCategory.findById(params.cc)
        String[] ids = params.crsids.split(",")
        println("IDS:"+ids)
        ArrayList crs = new ArrayList()
        for(int i=0;i<ids.size();i++)
        {
            if(ids[i]=="")
                continue
            Course c = Course.findById(ids[i])
            crs.add(c)
        }
        [crs:crs,cc:cc]
    }
    def catSlider3(){
        println("in crsSlider3:"+params)
        CourseCategory cc = CourseCategory.findById(params.cc)
        String[] ids = params.crsids.split(",")
        println("IDS:"+ids)
        ArrayList crs = new ArrayList()
        for(int i=0;i<ids.size();i++)
        {
            if(ids[i]=="")
                continue
            Course c = Course.findById(ids[i])
            crs.add(c)
        }
        [crs:crs,cc:cc]
    }
    def course(){
        println("in course:"+params)
        Course c = Course.findById(params.cid)
        def coffrlst = CourseOffering.findAllByCourse(c)
        println("coffrlst:"+coffrlst)
        ArrayList coffrlnrlst = new ArrayList()

        for(CourseOffering co:coffrlst){
            ArrayList learnercnt = new ArrayList()
            learnercnt.add(co)
            learnercnt.add(CourseOfferingLearner.findAllByCourseoffering(co).size())
            coffrlnrlst.add(learnercnt)
            //learnerlst.add(CourseOfferingLearner.findAllByCourseoffering(co))
        }
        println("Learner:"+coffrlnrlst)
        [coffrlnrlst:coffrlnrlst,c:c]
    }
    def catcrsSlider(){
        /*    println("in catcrsSlider params:"+params)
            println("in catcrsSlider session:"+session)

            [catCrs: session.catCrs]
            */
    }
    def changepassword()
    {
        println("I am in changepassword")
    }
    def savechangepassword()
    {
        println("I am in savechangepassword::"+params)
        String newpassword=params.newpassword
        String confirmpassword=params.confirmpassword
        Login login=Login.findByUsername(session.uid)
        println("login::"+login)
        if(newpassword.equals(confirmpassword))
        {
            login.password=newpassword
            login.save(failOnError:true,flush:true)
            flash.message="Password Changed Successfully....."
            redirect(controller:"Login",action: "login")
            return
        }
        else
        {
            flash.message="New And Confirm Passwords are not matching..."
            redirect(controller:"Login",action: "login")
            return
        }
    }
    // Home page Menu
    def home2(){

//        ArrayList<Course> crsList = Course.list()
//        crsList.sort{it.rating}
        println("path:"+request.getContextPath())
        final int top = InitialData.findByName("top").number
        final int topcatcrs = InitialData.findByName("topcatcrs").number
        def c = Course.createCriteria()
        ArrayList<Course> results = c.list {
            order("rating", "desc")
        }
        println("result:"+results.size())
        ArrayList topCrs = new ArrayList<Course>()
        int top20 = 0;
        for(Course c1: results){
            if(top20==top)
                break;
            topCrs.add(c1.id)
            top20++
        }
        println("topCrs:"+topCrs)
        top20=0;
        ArrayList<CourseCategory> subCat = CourseCategory.findAllByCoursecategoryIsNotNull()
        //TreeMap<Double,CourseCategory> topCat = new TreeMap<Double,CourseCategory>()
        ArrayList topCat = new ArrayList()
        for(CourseCategory cc: subCat){

            def allCrsCat = Course.findAllByCoursecategory(cc)

            double x = 0.0
            for(Course c2:allCrsCat){
                //println("b4--CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
                if(top20>=topcatcrs)
                    break;
                x += c2.rating
                top20++
                //     println("CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
            }
            //   println("Rating:"+x)
            top20=0
            topCat.add(x)
            topCat.add(cc)
            //println("B4::topCat:"+topCat)
            for(int j=topCat.size()-2;j>0;){
                //  println("j:"+j)
                // println("r:"+topCat[j-2])
                // println("cc:"+topCat[j-1])
                if(topCat[j]>topCat[j-2]){
                    int t = topCat[j-2]
                    CourseCategory tc = topCat[j-1]
                    topCat[j-2] = topCat[j]
                    topCat[j-1] = topCat[j+1]
                    topCat[j] = t
                    topCat[j+1] = tc
                }
                j=j-2
            }
            // println("After::topCat:"+topCat)

        }
        println("topCat:"+topCat)
        top20=0;



        ArrayList<CourseCategory> topSubCat = new ArrayList()
        for(int i=1;i<topCat.size();i+=2)
        {
            topSubCat.add(topCat[i])
        }

        /* for(Map.Entry<Double,CourseCategory> tm:topCat.entrySet()){
             topSubCat.add(tm.getValue())
         }*/
        //println("topSubCat:"+topSubCat.name)
        //Collections.reverse(topSubCat)
        println("topSubCat:"+topSubCat.name)
        int showTopCat = 0
        ArrayList catCrs = new ArrayList()
        ArrayList cat1 = new ArrayList<Course>()
        ArrayList cat2 = new ArrayList<Course>()
        ArrayList cat3 = new ArrayList<Course>()
        ArrayList cat4 = new ArrayList<Course>()
        for(CourseCategory cc: topSubCat){
            println("CC:"+cc.name)
            def cnt = Course.findAllByCoursecategory(cc)
            if(cnt.size==0)
                continue
            if(showTopCat==4){
                break;
            }
            def cat = Course.createCriteria().list {
                coursecategory{
                    eq ('id', cc.id)
                }
                order('rating', 'desc')
                maxResults topcatcrs
            }
            catCrs.add(cc.id)
            catCrs.add(cat.id)
            showTopCat++
        }
        def catname1=catCrs[0]
        cat1=catCrs[1]
        def catname2=catCrs[2]
        cat2=catCrs[3]
        def catname3=catCrs[4]
        cat3=catCrs[5]
        def catname4=catCrs[6]
        cat4=catCrs[7]
        println("cat:"+cat1+"\n"+cat2+"\n"+cat3+"\n"+cat4)
        println("catCrs:"+catCrs)
        topCrs as JSON
        cat1 as JSON
        cat2 as JSON
        cat3 as JSON
        cat4 as JSON
        ArrayList<CourseCategory> mainCat = CourseCategory.findAllByCoursecategoryIsNull()
        ArrayList<CourseCategory> mainmenu = new ArrayList()
        for(CourseCategory mc:mainCat){
            ArrayList<CourseCategory> mm = new ArrayList()
            mm.add(mc)
            ArrayList<CourseCategory> sm = CourseCategory.findAllByCoursecategory(mc)
            mm.add(sm)
            mainmenu.add(mm)
        }

        println("Categories MM SM:"+mainmenu.name)
        // session.catCrs = catCrs
        [crs:topCrs,cat1:cat1,cat2:cat2,cat3:cat3,cat4:cat4,cats1:cat1.size(),cats2:cat2.size(),cats3:cat3.size(),cats4:cat4.size(),catname1:catname1,catname2:catname2,catname3:catname3,catname4:catname4,menus:mainmenu]

    }
    def downloads(){

    }
    def forum(){

    }
    def faq(){

    }
    def partener(){
//        ArrayList<Course> crsList = Course.list()
//        crsList.sort{it.rating}
        println("path:"+request.getContextPath())
        final int top = InitialData.findByName("top").number
        final int topcatcrs = InitialData.findByName("topcatcrs").number
        def c = Course.createCriteria()
        ArrayList<Course> results = c.list {
            order("rating", "desc")
        }
        println("result:"+results.size())
        ArrayList topCrs = new ArrayList<Course>()
        int top20 = 0;
        for(Course c1: results){
            if(top20==top)
                break;
            topCrs.add(c1.id)
            top20++
        }
        println("topCrs:"+topCrs)
        top20=0;
        ArrayList<CourseCategory> subCat = CourseCategory.findAllByCoursecategoryIsNotNull()
        //TreeMap<Double,CourseCategory> topCat = new TreeMap<Double,CourseCategory>()
        ArrayList topCat = new ArrayList()
        for(CourseCategory cc: subCat){

            def allCrsCat = Course.findAllByCoursecategory(cc)

            double x = 0.0
            for(Course c2:allCrsCat){
                //println("b4--CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
                if(top20>=topcatcrs)
                    break;
                x += c2.rating
                top20++
                //     println("CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
            }
            //   println("Rating:"+x)
            top20=0
            topCat.add(x)
            topCat.add(cc)
            //println("B4::topCat:"+topCat)
            for(int j=topCat.size()-2;j>0;){
                //  println("j:"+j)
                // println("r:"+topCat[j-2])
                // println("cc:"+topCat[j-1])
                if(topCat[j]>topCat[j-2]){
                    int t = topCat[j-2]
                    CourseCategory tc = topCat[j-1]
                    topCat[j-2] = topCat[j]
                    topCat[j-1] = topCat[j+1]
                    topCat[j] = t
                    topCat[j+1] = tc
                }
                j=j-2
            }
            // println("After::topCat:"+topCat)

        }
        println("topCat:"+topCat)
        top20=0;



        ArrayList<CourseCategory> topSubCat = new ArrayList()
        for(int i=1;i<topCat.size();i+=2)
        {
            topSubCat.add(topCat[i])
        }

        /* for(Map.Entry<Double,CourseCategory> tm:topCat.entrySet()){
             topSubCat.add(tm.getValue())
         }*/
        //println("topSubCat:"+topSubCat.name)
        //Collections.reverse(topSubCat)
        println("topSubCat:"+topSubCat.name)
        int showTopCat = 0
        ArrayList catCrs = new ArrayList()
        ArrayList cat1 = new ArrayList<Course>()
        ArrayList cat2 = new ArrayList<Course>()
        ArrayList cat3 = new ArrayList<Course>()
        ArrayList cat4 = new ArrayList<Course>()
        for(CourseCategory cc: topSubCat){
            println("CC:"+cc.name)
            def cnt = Course.findAllByCoursecategory(cc)
            if(cnt.size==0)
                continue
            if(showTopCat==4){
                break;
            }
            def cat = Course.createCriteria().list {
                coursecategory{
                    eq ('id', cc.id)
                }
                order('rating', 'desc')
                maxResults topcatcrs
            }
            catCrs.add(cc.id)
            catCrs.add(cat.id)
            showTopCat++
        }
        def catname1=catCrs[0]
        cat1=catCrs[1]
        def catname2=catCrs[2]
        cat2=catCrs[3]
        def catname3=catCrs[4]
        cat3=catCrs[5]
        def catname4=catCrs[6]
        cat4=catCrs[7]
        println("cat:"+cat1+"\n"+cat2+"\n"+cat3+"\n"+cat4)
        println("catCrs:"+catCrs)
        topCrs as JSON
        cat1 as JSON
        cat2 as JSON
        cat3 as JSON
        cat4 as JSON
        ArrayList<CourseCategory> mainCat = CourseCategory.findAllByCoursecategoryIsNull()
        ArrayList<CourseCategory> mainmenu = new ArrayList()
        for(CourseCategory mc:mainCat){
            ArrayList<CourseCategory> mm = new ArrayList()
            ArrayList<CourseCategory> sm = CourseCategory.findAllByCoursecategory(mc)
            ArrayList<CourseCategory> ssm = new ArrayList()
            boolean f = false
            println("CC:"+mc.name)
            for(CourseCategory cco : sm){
                def crs = Course.findAllByCoursecategory(cco)
                if(crs.size()>0){
                    f=true
                    //break
                    ssm.add(cco)
                }
            }
            def crs = Course.findAllByCoursecategory(mc)
            println("crs:"+crs.course_name)
            if(crs.size()>0){
                f=true
                //break
            }
            if(f) {
                mm.add(mc)
            }
            else
                continue
            mm.add(ssm)
            mainmenu.add(mm)
            println("CC added:"+mc.name)

        }

        println("Categories MM SM:"+mainmenu.name)
        // session.catCrs = catCrs
        [crs:topCrs,cat1:cat1,cat2:cat2,cat3:cat3,cat4:cat4,cats1:cat1.size(),cats2:cat2.size(),cats3:cat3.size(),cats4:cat4.size(),catname1:catname1,catname2:catname2,catname3:catname3,catname4:catname4,menus:mainmenu]
    }
    def pricing(){
//        ArrayList<Course> crsList = Course.list()
//        crsList.sort{it.rating}
        println("path:"+request.getContextPath())
        final int top = InitialData.findByName("top").number
        final int topcatcrs = InitialData.findByName("topcatcrs").number
        def c = Course.createCriteria()
        ArrayList<Course> results = c.list {
            order("rating", "desc")
        }
        println("result:"+results.size())
        ArrayList topCrs = new ArrayList<Course>()
        int top20 = 0;
        for(Course c1: results){
            if(top20==top)
                break;
            topCrs.add(c1.id)
            top20++
        }
        println("topCrs:"+topCrs)
        top20=0;
        ArrayList<CourseCategory> subCat = CourseCategory.findAllByCoursecategoryIsNotNull()
        //TreeMap<Double,CourseCategory> topCat = new TreeMap<Double,CourseCategory>()
        ArrayList topCat = new ArrayList()
        for(CourseCategory cc: subCat){

            def allCrsCat = Course.findAllByCoursecategory(cc)

            double x = 0.0
            for(Course c2:allCrsCat){
                //println("b4--CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
                if(top20>=topcatcrs)
                    break;
                x += c2.rating
                top20++
                //     println("CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
            }
            //   println("Rating:"+x)
            top20=0
            topCat.add(x)
            topCat.add(cc)
            //println("B4::topCat:"+topCat)
            for(int j=topCat.size()-2;j>0;){
                //  println("j:"+j)
                // println("r:"+topCat[j-2])
                // println("cc:"+topCat[j-1])
                if(topCat[j]>topCat[j-2]){
                    int t = topCat[j-2]
                    CourseCategory tc = topCat[j-1]
                    topCat[j-2] = topCat[j]
                    topCat[j-1] = topCat[j+1]
                    topCat[j] = t
                    topCat[j+1] = tc
                }
                j=j-2
            }
            // println("After::topCat:"+topCat)

        }
        println("topCat:"+topCat)
        top20=0;



        ArrayList<CourseCategory> topSubCat = new ArrayList()
        for(int i=1;i<topCat.size();i+=2)
        {
            topSubCat.add(topCat[i])
        }

        /* for(Map.Entry<Double,CourseCategory> tm:topCat.entrySet()){
             topSubCat.add(tm.getValue())
         }*/
        //println("topSubCat:"+topSubCat.name)
        //Collections.reverse(topSubCat)
        println("topSubCat:"+topSubCat.name)
        int showTopCat = 0
        ArrayList catCrs = new ArrayList()
        ArrayList cat1 = new ArrayList<Course>()
        ArrayList cat2 = new ArrayList<Course>()
        ArrayList cat3 = new ArrayList<Course>()
        ArrayList cat4 = new ArrayList<Course>()
        for(CourseCategory cc: topSubCat){
            println("CC:"+cc.name)
            def cnt = Course.findAllByCoursecategory(cc)
            if(cnt.size==0)
                continue
            if(showTopCat==4){
                break;
            }
            def cat = Course.createCriteria().list {
                coursecategory{
                    eq ('id', cc.id)
                }
                order('rating', 'desc')
                maxResults topcatcrs
            }
            catCrs.add(cc.id)
            catCrs.add(cat.id)
            showTopCat++
        }
        def catname1=catCrs[0]
        cat1=catCrs[1]
        def catname2=catCrs[2]
        cat2=catCrs[3]
        def catname3=catCrs[4]
        cat3=catCrs[5]
        def catname4=catCrs[6]
        cat4=catCrs[7]
        println("cat:"+cat1+"\n"+cat2+"\n"+cat3+"\n"+cat4)
        println("catCrs:"+catCrs)
        topCrs as JSON
        cat1 as JSON
        cat2 as JSON
        cat3 as JSON
        cat4 as JSON
        ArrayList<CourseCategory> mainCat = CourseCategory.findAllByCoursecategoryIsNull()
        ArrayList<CourseCategory> mainmenu = new ArrayList()
        for(CourseCategory mc:mainCat){
            ArrayList<CourseCategory> mm = new ArrayList()
            ArrayList<CourseCategory> sm = CourseCategory.findAllByCoursecategory(mc)
            ArrayList<CourseCategory> ssm = new ArrayList()
            boolean f = false
            println("CC:"+mc.name)
            for(CourseCategory cco : sm){
                def crs = Course.findAllByCoursecategory(cco)
                if(crs.size()>0){
                    f=true
                    //break
                    ssm.add(cco)
                }
            }
            def crs = Course.findAllByCoursecategory(mc)
            println("crs:"+crs.course_name)
            if(crs.size()>0){
                f=true
                //break
            }
            if(f) {
                mm.add(mc)
            }
            else
                continue
            mm.add(ssm)
            mainmenu.add(mm)
            println("CC added:"+mc.name)

        }

        println("Categories MM SM:"+mainmenu.name)
        // session.catCrs = catCrs
        [crs:topCrs,cat1:cat1,cat2:cat2,cat3:cat3,cat4:cat4,cats1:cat1.size(),cats2:cat2.size(),cats3:cat3.size(),cats4:cat4.size(),catname1:catname1,catname2:catname2,catname3:catname3,catname4:catname4,menus:mainmenu]
    }
    def contactus(){
//        ArrayList<Course> crsList = Course.list()
//        crsList.sort{it.rating}
        println("path:"+request.getContextPath())
        final int top = InitialData.findByName("top").number
        final int topcatcrs = InitialData.findByName("topcatcrs").number
        def c = Course.createCriteria()
        ArrayList<Course> results = c.list {
            order("rating", "desc")
        }
        println("result:"+results.size())
        ArrayList topCrs = new ArrayList<Course>()
        int top20 = 0;
        for(Course c1: results){
            if(top20==top)
                break;
            topCrs.add(c1.id)
            top20++
        }
        println("topCrs:"+topCrs)
        top20=0;
        ArrayList<CourseCategory> subCat = CourseCategory.findAllByCoursecategoryIsNotNull()
        //TreeMap<Double,CourseCategory> topCat = new TreeMap<Double,CourseCategory>()
        ArrayList topCat = new ArrayList()
        for(CourseCategory cc: subCat){

            def allCrsCat = Course.findAllByCoursecategory(cc)

            double x = 0.0
            for(Course c2:allCrsCat){
                //println("b4--CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
                if(top20>=topcatcrs)
                    break;
                x += c2.rating
                top20++
                //     println("CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
            }
            //   println("Rating:"+x)
            top20=0
            topCat.add(x)
            topCat.add(cc)
            //println("B4::topCat:"+topCat)
            for(int j=topCat.size()-2;j>0;){
                //  println("j:"+j)
                // println("r:"+topCat[j-2])
                // println("cc:"+topCat[j-1])
                if(topCat[j]>topCat[j-2]){
                    int t = topCat[j-2]
                    CourseCategory tc = topCat[j-1]
                    topCat[j-2] = topCat[j]
                    topCat[j-1] = topCat[j+1]
                    topCat[j] = t
                    topCat[j+1] = tc
                }
                j=j-2
            }
            // println("After::topCat:"+topCat)

        }
        println("topCat:"+topCat)
        top20=0;



        ArrayList<CourseCategory> topSubCat = new ArrayList()
        for(int i=1;i<topCat.size();i+=2)
        {
            topSubCat.add(topCat[i])
        }

        /* for(Map.Entry<Double,CourseCategory> tm:topCat.entrySet()){
             topSubCat.add(tm.getValue())
         }*/
        //println("topSubCat:"+topSubCat.name)
        //Collections.reverse(topSubCat)
        println("topSubCat:"+topSubCat.name)
        int showTopCat = 0
        ArrayList catCrs = new ArrayList()
        ArrayList cat1 = new ArrayList<Course>()
        ArrayList cat2 = new ArrayList<Course>()
        ArrayList cat3 = new ArrayList<Course>()
        ArrayList cat4 = new ArrayList<Course>()
        for(CourseCategory cc: topSubCat){
            println("CC:"+cc.name)
            def cnt = Course.findAllByCoursecategory(cc)
            if(cnt.size==0)
                continue
            if(showTopCat==4){
                break;
            }
            def cat = Course.createCriteria().list {
                coursecategory{
                    eq ('id', cc.id)
                }
                order('rating', 'desc')
                maxResults topcatcrs
            }
            catCrs.add(cc.id)
            catCrs.add(cat.id)
            showTopCat++
        }
        def catname1=catCrs[0]
        cat1=catCrs[1]
        def catname2=catCrs[2]
        cat2=catCrs[3]
        def catname3=catCrs[4]
        cat3=catCrs[5]
        def catname4=catCrs[6]
        cat4=catCrs[7]
        println("cat:"+cat1+"\n"+cat2+"\n"+cat3+"\n"+cat4)
        println("catCrs:"+catCrs)
        topCrs as JSON
        cat1 as JSON
        cat2 as JSON
        cat3 as JSON
        cat4 as JSON
        ArrayList<CourseCategory> mainCat = CourseCategory.findAllByCoursecategoryIsNull()
        ArrayList<CourseCategory> mainmenu = new ArrayList()
        for(CourseCategory mc:mainCat){
            ArrayList<CourseCategory> mm = new ArrayList()
            ArrayList<CourseCategory> sm = CourseCategory.findAllByCoursecategory(mc)
            ArrayList<CourseCategory> ssm = new ArrayList()
            boolean f = false
            println("CC:"+mc.name)
            for(CourseCategory cco : sm){
                def crs = Course.findAllByCoursecategory(cco)
                if(crs.size()>0){
                    f=true
                    //break
                    ssm.add(cco)
                }
            }
            def crs = Course.findAllByCoursecategory(mc)
            println("crs:"+crs.course_name)
            if(crs.size()>0){
                f=true
                //break
            }
            if(f) {
                mm.add(mc)
            }
            else
                continue
            mm.add(ssm)
            mainmenu.add(mm)
            println("CC added:"+mc.name)

        }

        println("Categories MM SM:"+mainmenu.name)
        // session.catCrs = catCrs
        [crs:topCrs,cat1:cat1,cat2:cat2,cat3:cat3,cat4:cat4,cats1:cat1.size(),cats2:cat2.size(),cats3:cat3.size(),cats4:cat4.size(),catname1:catname1,catname2:catname2,catname3:catname3,catname4:catname4,menus:mainmenu]
    }
    def reviews(){

    }
    def aboutvolp()
        {
//        ArrayList<Course> crsList = Course.list()
//        crsList.sort{it.rating}
            println("path:"+request.getContextPath())
            final int top = InitialData.findByName("top").number
            final int topcatcrs = InitialData.findByName("topcatcrs").number
            def c = Course.createCriteria()
            ArrayList<Course> results = c.list {
                order("rating", "desc")
            }
            println("result:"+results.size())
            ArrayList topCrs = new ArrayList<Course>()
            int top20 = 0;
            for(Course c1: results){
                if(top20==top)
                    break;
                topCrs.add(c1.id)
                top20++
            }
            println("topCrs:"+topCrs)
            top20=0;
            ArrayList<CourseCategory> subCat = CourseCategory.findAllByCoursecategoryIsNotNull()
            //TreeMap<Double,CourseCategory> topCat = new TreeMap<Double,CourseCategory>()
            ArrayList topCat = new ArrayList()
            for(CourseCategory cc: subCat){

                def allCrsCat = Course.findAllByCoursecategory(cc)

                double x = 0.0
                for(Course c2:allCrsCat){
                    //println("b4--CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
                    if(top20>=topcatcrs)
                        break;
                    x += c2.rating
                    top20++
                    //     println("CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
                }
                //   println("Rating:"+x)
                top20=0
                topCat.add(x)
                topCat.add(cc)
                //println("B4::topCat:"+topCat)
                for(int j=topCat.size()-2;j>0;){
                    //  println("j:"+j)
                    // println("r:"+topCat[j-2])
                    // println("cc:"+topCat[j-1])
                    if(topCat[j]>topCat[j-2]){
                        int t = topCat[j-2]
                        CourseCategory tc = topCat[j-1]
                        topCat[j-2] = topCat[j]
                        topCat[j-1] = topCat[j+1]
                        topCat[j] = t
                        topCat[j+1] = tc
                    }
                    j=j-2
                }
                // println("After::topCat:"+topCat)

            }
            println("topCat:"+topCat)
            top20=0;



            ArrayList<CourseCategory> topSubCat = new ArrayList()
            for(int i=1;i<topCat.size();i+=2)
            {
                topSubCat.add(topCat[i])
            }

            /* for(Map.Entry<Double,CourseCategory> tm:topCat.entrySet()){
                 topSubCat.add(tm.getValue())
             }*/
            //println("topSubCat:"+topSubCat.name)
            //Collections.reverse(topSubCat)
            println("topSubCat:"+topSubCat.name)
            int showTopCat = 0
            ArrayList catCrs = new ArrayList()
            ArrayList cat1 = new ArrayList<Course>()
            ArrayList cat2 = new ArrayList<Course>()
            ArrayList cat3 = new ArrayList<Course>()
            ArrayList cat4 = new ArrayList<Course>()
            for(CourseCategory cc: topSubCat){
                println("CC:"+cc.name)
                def cnt = Course.findAllByCoursecategory(cc)
                if(cnt.size==0)
                    continue
                if(showTopCat==4){
                    break;
                }
                def cat = Course.createCriteria().list {
                    coursecategory{
                        eq ('id', cc.id)
                    }
                    order('rating', 'desc')
                    maxResults topcatcrs
                }
                catCrs.add(cc.id)
                catCrs.add(cat.id)
                showTopCat++
            }
            def catname1=catCrs[0]
            cat1=catCrs[1]
            def catname2=catCrs[2]
            cat2=catCrs[3]
            def catname3=catCrs[4]
            cat3=catCrs[5]
            def catname4=catCrs[6]
            cat4=catCrs[7]
            println("cat:"+cat1+"\n"+cat2+"\n"+cat3+"\n"+cat4)
            println("catCrs:"+catCrs)
            topCrs as JSON
            cat1 as JSON
            cat2 as JSON
            cat3 as JSON
            cat4 as JSON
            ArrayList<CourseCategory> mainCat = CourseCategory.findAllByCoursecategoryIsNull()
            ArrayList<CourseCategory> mainmenu = new ArrayList()
            for(CourseCategory mc:mainCat){
                ArrayList<CourseCategory> mm = new ArrayList()
                ArrayList<CourseCategory> sm = CourseCategory.findAllByCoursecategory(mc)
                ArrayList<CourseCategory> ssm = new ArrayList()
                boolean f = false
                println("CC:"+mc.name)
                for(CourseCategory cco : sm){
                    def crs = Course.findAllByCoursecategory(cco)
                    if(crs.size()>0){
                        f=true
                        //break
                        ssm.add(cco)
                    }
                }
                def crs = Course.findAllByCoursecategory(mc)
                println("crs:"+crs.course_name)
                if(crs.size()>0){
                    f=true
                    //break
                }
                if(f) {
                    mm.add(mc)
                }
                else
                    continue
                mm.add(ssm)
                mainmenu.add(mm)
                println("CC added:"+mc.name)

            }

            println("Categories MM SM:"+mainmenu.name)
            // session.catCrs = catCrs
            [crs:topCrs,cat1:cat1,cat2:cat2,cat3:cat3,cat4:cat4,cats1:cat1.size(),cats2:cat2.size(),cats3:cat3.size(),cats4:cat4.size(),catname1:catname1,catname2:catname2,catname3:catname3,catname4:catname4,menus:mainmenu]
        }

    def homeDash(){
//        ArrayList<Course> crsList = Course.list()
//        crsList.sort{it.rating}
        Learner ins =Learner.findByUid(session.uid)
        session.firstName=ins.person.firstName
        println("path:"+request.getContextPath())
        final int top = InitialData.findByName("top").number
        final int topcatcrs = InitialData.findByName("topcatcrs").number
        def c = Course.createCriteria()
        ArrayList<Course> results = c.list {
            order("rating", "desc")
        }
        println("result:"+results.size())
        ArrayList topCrs = new ArrayList<Course>()
        int top20 = 0;
        for(Course c1: results){
            if(top20==top)
                break;
            topCrs.add(c1.id)
            top20++
        }
        println("topCrs:"+topCrs)
        top20=0;
        ArrayList<CourseCategory> subCat = CourseCategory.findAllByCoursecategoryIsNotNull()
        //TreeMap<Double,CourseCategory> topCat = new TreeMap<Double,CourseCategory>()
        ArrayList topCat = new ArrayList()
        for(CourseCategory cc: subCat){

            def allCrsCat = Course.findAllByCoursecategory(cc)

            double x = 0.0
            for(Course c2:allCrsCat){
                //println("b4--CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
                if(top20>=topcatcrs)
                    break;
                x += c2.rating
                top20++
                //     println("CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
            }
            //   println("Rating:"+x)
            top20=0
            topCat.add(x)
            topCat.add(cc)
            //println("B4::topCat:"+topCat)
            for(int j=topCat.size()-2;j>0;){
                //  println("j:"+j)
                // println("r:"+topCat[j-2])
                // println("cc:"+topCat[j-1])
                if(topCat[j]>topCat[j-2]){
                    int t = topCat[j-2]
                    CourseCategory tc = topCat[j-1]
                    topCat[j-2] = topCat[j]
                    topCat[j-1] = topCat[j+1]
                    topCat[j] = t
                    topCat[j+1] = tc
                }
                j=j-2
            }
            // println("After::topCat:"+topCat)

        }
        println("topCat:"+topCat)
        top20=0;



        ArrayList<CourseCategory> topSubCat = new ArrayList()
        for(int i=1;i<topCat.size();i+=2)
        {
            topSubCat.add(topCat[i])
        }

        /* for(Map.Entry<Double,CourseCategory> tm:topCat.entrySet()){
             topSubCat.add(tm.getValue())
         }*/
        //println("topSubCat:"+topSubCat.name)
        //Collections.reverse(topSubCat)
        println("topSubCat:"+topSubCat.name)
        int showTopCat = 0
        ArrayList catCrs = new ArrayList()
        ArrayList cat1 = new ArrayList<Course>()
        ArrayList cat2 = new ArrayList<Course>()
        ArrayList cat3 = new ArrayList<Course>()
        ArrayList cat4 = new ArrayList<Course>()
        for(CourseCategory cc: topSubCat){
            println("CC:"+cc.name)
            def cnt = Course.findAllByCoursecategory(cc)
            if(cnt.size==0)
                continue
            if(showTopCat==4){
                break;
            }
            def cat = Course.createCriteria().list {
                coursecategory{
                    eq ('id', cc.id)
                }
                order('rating', 'desc')
                maxResults topcatcrs
            }
            catCrs.add(cc.id)
            catCrs.add(cat.id)
            showTopCat++
        }
        def catname1=catCrs[0]
        cat1=catCrs[1]
        def catname2=catCrs[2]
        cat2=catCrs[3]
        def catname3=catCrs[4]
        cat3=catCrs[5]
        def catname4=catCrs[6]
        cat4=catCrs[7]
        println("cat:"+cat1+"\n"+cat2+"\n"+cat3+"\n"+cat4)
        println("catCrs:"+catCrs)
        topCrs as JSON
        cat1 as JSON
        cat2 as JSON
        cat3 as JSON
        cat4 as JSON
        ArrayList<CourseCategory> mainCat = CourseCategory.findAllByCoursecategoryIsNull()
        ArrayList<CourseCategory> mainmenu = new ArrayList()
        for(CourseCategory mc:mainCat){
            ArrayList<CourseCategory> mm = new ArrayList()
            mm.add(mc)
            ArrayList<CourseCategory> sm = CourseCategory.findAllByCoursecategory(mc)
            mm.add(sm)
            mainmenu.add(mm)
        }

        println("Categories MM SM:"+mainmenu.name)
        // session.catCrs = catCrs
        [crs:topCrs,cat1:cat1,cat2:cat2,cat3:cat3,cat4:cat4,cats1:cat1.size(),cats2:cat2.size(),cats3:cat3.size(),cats4:cat4.size(),catname1:catname1,catname2:catname2,catname3:catname3,catname4:catname4,menus:mainmenu]
    }
    def homeDashInstructor(){
//        ArrayList<Course> crsList = Course.list()
//        crsList.sort{it.rating}
        Instructor ins =Instructor.findByUid(session.uid)
        session.firstName=ins.person.firstName
        println("path:"+request.getContextPath())
        final int top = InitialData.findByName("top").number
        final int topcatcrs = InitialData.findByName("topcatcrs").number
        def c = Course.createCriteria()
        ArrayList<Course> results = c.list {
            order("rating", "desc")
        }
        println("result:"+results.size())
        ArrayList topCrs = new ArrayList<Course>()
        int top20 = 0;
        for(Course c1: results){
            if(top20==top)
                break;
            topCrs.add(c1.id)
            top20++
        }
        println("topCrs:"+topCrs)
        top20=0;
        ArrayList<CourseCategory> subCat = CourseCategory.findAllByCoursecategoryIsNotNull()
        //TreeMap<Double,CourseCategory> topCat = new TreeMap<Double,CourseCategory>()
        ArrayList topCat = new ArrayList()
        for(CourseCategory cc: subCat){

            def allCrsCat = Course.findAllByCoursecategory(cc)

            double x = 0.0
            for(Course c2:allCrsCat){
                //println("b4--CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
                if(top20>=topcatcrs)
                    break;
                x += c2.rating
                top20++
                //     println("CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
            }
            //   println("Rating:"+x)
            top20=0
            topCat.add(x)
            topCat.add(cc)
            //println("B4::topCat:"+topCat)
            for(int j=topCat.size()-2;j>0;){
                //  println("j:"+j)
                // println("r:"+topCat[j-2])
                // println("cc:"+topCat[j-1])
                if(topCat[j]>topCat[j-2]){
                    int t = topCat[j-2]
                    CourseCategory tc = topCat[j-1]
                    topCat[j-2] = topCat[j]
                    topCat[j-1] = topCat[j+1]
                    topCat[j] = t
                    topCat[j+1] = tc
                }
                j=j-2
            }
            // println("After::topCat:"+topCat)

        }
        println("topCat:"+topCat)
        top20=0;



        ArrayList<CourseCategory> topSubCat = new ArrayList()
        for(int i=1;i<topCat.size();i+=2)
        {
            topSubCat.add(topCat[i])
        }

        /* for(Map.Entry<Double,CourseCategory> tm:topCat.entrySet()){
             topSubCat.add(tm.getValue())
         }*/
        //println("topSubCat:"+topSubCat.name)
        //Collections.reverse(topSubCat)
        println("topSubCat:"+topSubCat.name)
        int showTopCat = 0
        ArrayList catCrs = new ArrayList()
        ArrayList cat1 = new ArrayList<Course>()
        ArrayList cat2 = new ArrayList<Course>()
        ArrayList cat3 = new ArrayList<Course>()
        ArrayList cat4 = new ArrayList<Course>()
        for(CourseCategory cc: topSubCat){
            println("CC:"+cc.name)
            def cnt = Course.findAllByCoursecategory(cc)
            if(cnt.size==0)
                continue
            if(showTopCat==4){
                break;
            }
            def cat = Course.createCriteria().list {
                coursecategory{
                    eq ('id', cc.id)
                }
                order('rating', 'desc')
                maxResults topcatcrs
            }
            catCrs.add(cc.id)
            catCrs.add(cat.id)
            showTopCat++
        }
        def catname1=catCrs[0]
        cat1=catCrs[1]
        def catname2=catCrs[2]
        cat2=catCrs[3]
        def catname3=catCrs[4]
        cat3=catCrs[5]
        def catname4=catCrs[6]
        cat4=catCrs[7]
        println("cat:"+cat1+"\n"+cat2+"\n"+cat3+"\n"+cat4)
        println("catCrs:"+catCrs)
        topCrs as JSON
        cat1 as JSON
        cat2 as JSON
        cat3 as JSON
        cat4 as JSON
        ArrayList<CourseCategory> mainCat = CourseCategory.findAllByCoursecategoryIsNull()
        ArrayList<CourseCategory> mainmenu = new ArrayList()
        for(CourseCategory mc:mainCat){
            ArrayList<CourseCategory> mm = new ArrayList()
            mm.add(mc)
            ArrayList<CourseCategory> sm = CourseCategory.findAllByCoursecategory(mc)
            mm.add(sm)
            mainmenu.add(mm)
        }

        println("Categories MM SM:"+mainmenu.name)
        // session.catCrs = catCrs
        [crs:topCrs,cat1:cat1,cat2:cat2,cat3:cat3,cat4:cat4,cats1:cat1.size(),cats2:cat2.size(),cats3:cat3.size(),cats4:cat4.size(),catname1:catname1,catname2:catname2,catname3:catname3,catname4:catname4,menus:mainmenu]
    }
    def coursenotfound()
    {
        println("path:"+request.getContextPath())
        final int top = InitialData.findByName("top").number
        final int topcatcrs = InitialData.findByName("topcatcrs").number
        def c = Course.createCriteria()
        ArrayList<Course> results = c.list {
            order("rating", "desc")
        }
        println("result:"+results.size())
        ArrayList topCrs = new ArrayList<Course>()
        int top20 = 0;
        for(Course c1: results){
            if(top20==top)
                break;
            topCrs.add(c1.id)
            top20++
        }
        println("topCrs:"+topCrs)
        top20=0;
        ArrayList<CourseCategory> subCat = CourseCategory.findAllByCoursecategoryIsNotNull()
        //TreeMap<Double,CourseCategory> topCat = new TreeMap<Double,CourseCategory>()
        ArrayList topCat = new ArrayList()
        for(CourseCategory cc: subCat){

            def allCrsCat = Course.findAllByCoursecategory(cc)

            double x = 0.0
            for(Course c2:allCrsCat){
                //println("b4--CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
                if(top20>=topcatcrs)
                    break;
                x += c2.rating
                top20++
                //     println("CC:"+cc.name+" T:"+top20+" final top:"+topcatcrs)
            }
            //   println("Rating:"+x)
            top20=0
            topCat.add(x)
            topCat.add(cc)
            //println("B4::topCat:"+topCat)
            for(int j=topCat.size()-2;j>0;){
                //  println("j:"+j)
                // println("r:"+topCat[j-2])
                // println("cc:"+topCat[j-1])
                if(topCat[j]>topCat[j-2]){
                    int t = topCat[j-2]
                    CourseCategory tc = topCat[j-1]
                    topCat[j-2] = topCat[j]
                    topCat[j-1] = topCat[j+1]
                    topCat[j] = t
                    topCat[j+1] = tc
                }
                j=j-2
            }
            // println("After::topCat:"+topCat)

        }
        println("topCat:"+topCat)
        top20=0;



        ArrayList<CourseCategory> topSubCat = new ArrayList()
        for(int i=1;i<topCat.size();i+=2)
        {
            topSubCat.add(topCat[i])
        }

        /* for(Map.Entry<Double,CourseCategory> tm:topCat.entrySet()){
             topSubCat.add(tm.getValue())
         }*/
        //println("topSubCat:"+topSubCat.name)
        //Collections.reverse(topSubCat)
        println("topSubCat:"+topSubCat.name)
        int showTopCat = 0
        ArrayList catCrs = new ArrayList()
        ArrayList cat1 = new ArrayList<Course>()
        ArrayList cat2 = new ArrayList<Course>()
        ArrayList cat3 = new ArrayList<Course>()
        ArrayList cat4 = new ArrayList<Course>()
        for(CourseCategory cc: topSubCat){
            println("CC:"+cc.name)
            def cnt = Course.findAllByCoursecategory(cc)
            if(cnt.size==0)
                continue
            if(showTopCat==4){
                break;
            }
            def cat = Course.createCriteria().list {
                coursecategory{
                    eq ('id', cc.id)
                }
                order('rating', 'desc')
                maxResults topcatcrs
            }
            catCrs.add(cc.id)
            catCrs.add(cat.id)
            showTopCat++
        }
        def catname1=catCrs[0]
        cat1=catCrs[1]
        def catname2=catCrs[2]
        cat2=catCrs[3]
        def catname3=catCrs[4]
        cat3=catCrs[5]
        def catname4=catCrs[6]
        cat4=catCrs[7]
        println("cat:"+cat1+"\n"+cat2+"\n"+cat3+"\n"+cat4)
        println("catCrs:"+catCrs)
        topCrs as JSON
        cat1 as JSON
        cat2 as JSON
        cat3 as JSON
        cat4 as JSON
        ArrayList<CourseCategory> mainCat = CourseCategory.findAllByCoursecategoryIsNull()
        ArrayList<CourseCategory> mainmenu = new ArrayList()
        for(CourseCategory mc:mainCat){
            ArrayList<CourseCategory> mm = new ArrayList()
            ArrayList<CourseCategory> sm = CourseCategory.findAllByCoursecategory(mc)
            ArrayList<CourseCategory> ssm = new ArrayList()
            boolean f = false
            println("CC:"+mc.name)
            for(CourseCategory cco : sm){
                def crs = Course.findAllByCoursecategory(cco)
                if(crs.size()>0){
                    f=true
                    //break
                    ssm.add(cco)
                }
            }
            def crs = Course.findAllByCoursecategory(mc)
            println("crs:"+crs.course_name)
            if(crs.size()>0){
                f=true
                //break
            }
            if(f) {
                mm.add(mc)
            }
            else
                continue
            mm.add(ssm)
            mainmenu.add(mm)
            println("CC added:"+mc.name)

        }

        println("Categories MM SM:"+mainmenu.name)
        // session.catCrs = catCrs
        [crs:topCrs,cat1:cat1,cat2:cat2,cat3:cat3,cat4:cat4,cats1:cat1.size(),cats2:cat2.size(),cats3:cat3.size(),cats4:cat4.size(),catname1:catname1,catname2:catname2,catname3:catname3,catname4:catname4,menus:mainmenu]
    }
}
