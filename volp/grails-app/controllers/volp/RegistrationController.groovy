package volp

class RegistrationController {
    def RegistrationService
    def index() { }
    def verifyemail()
    {
        println("I am in verify email...")
    }
    def verifyemailprocess()
    {
        println("I am in verifyemailprocess...."+params)
        //Please check whether email already registered....
        String email=params.email
        Login l=Login.findByUsername(email)
        if(l==null)
        {
            //Not Registred..Send OTP
            //Generate OTP
            java.util.Random random=new java.util.Random()
            String otp=""
            int min=0,max=0,n
            for(int i=1;i<=6;i++)
            {
                if(i==1)
                    min=1
                else
                    min=0
                max=9
                n=random.nextInt(max)+min
                otp=otp+n
            }
            Otp otpobj=Otp.findByEmail(email)
            Date date=new java.util.Date()
            if(otpobj==null)
            {
                //insert otp
                otpobj=new Otp()
                otpobj.email=email
                otpobj.otp=otp
                otpobj.otpgenerationtime=date
                otpobj.save(failOnError:true,flush:true)
            }
            else
            {
                //update otp
                otpobj.otp=otp
                otpobj.otpgenerationtime=date
                otpobj.save(failOnError:true,flush:true)
            }
            println("Now sending mail of otp...."+otp)
            sendMail {
                to email
                subject "Verify Your Email for Vishwakosh Platform"
                text "Your OTP for registration is "+otp+"\n"+"Thanks, Vishwakosh Team"
            }
            session.email=email
            redirect action:"confirmemail"
        }
        else
        {
           flash.message("Already Registered...")
        }
    }
    def confirmemail()
    {
        println("I am in confirm email...")
        println("email:"+session.email)
        String email=session.email
        [email:email]
    }
    def verifyotp()
    {
        println("I am in verifyotp:"+params)
        String email=params.email
        String otp=params.otp
        println("Email:"+email+" and otp:"+otp)
        Otp otpobj=Otp.findByEmailAndOtp(email,otp)
        if(otpobj!=null)
        {
            Date otpgenerationtime=otpobj.otpgenerationtime
            Date currenttime=new java.util.Date()
            println("OTP match:otpgenerationtime:"+otpgenerationtime+","+"currenttime:"+currenttime)
            long diff = currenttime.getTime() - otpgenerationtime.getTime() ;
            long diffInMinutes = java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(diff);
            if(diffInMinutes<=30)
            {
                println("OTP match and it is within time limit..")
                session.email=email
                redirect action:"registration"
            }
            else
            {
                println("OTP is expired..please generate OTP again..")
                redirect action:"verifyemail"
            }
        }
        else
        {
            println("OTP do not match, Please Enter OTP again...")
            flash.message="OTP do not match, Please Enter OTP again..."
            redirect action:"verifyotp"
        }
    }
    def registration()
    {
        println("I am in registration...")
        String email=session.email
        ApplicationType at=ApplicationType.findByApplication_type("VOLP")
        def ut=UserType.findAllByApplication_type(at)
        ArrayList<String> usertypelist=new ArrayList<String>();
        for(UserType u:ut)
        {
            usertypelist.add(u.type)
        }
        [email:email,usertypelist:usertypelist]
    }
    def saveregistration()
    {
        println("I am in save registration....")
        String email=params.email
        String password=params.password
        String firstname=params.firstname
        String lastname=params.lastname
        String usertype=params.usertype
        println(email+" "+password+"  "+firstname+"  "+lastname+" "+usertype)
        String ip=request.getRemoteAddr()
        int ret=RegistrationService.registeruser(email,password,firstname,lastname,usertype,ip)
        println("ret:"+ret)
        if(ret==1)
            redirect (action:"login", controller:"Login")
        else
            println("Already Registered.  Thank You...")
    }
}
