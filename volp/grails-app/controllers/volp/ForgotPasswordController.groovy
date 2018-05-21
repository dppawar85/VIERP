package volp

class ForgotPasswordController {

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
        if(l!=null)
        {
            //Registred..Send OTP for Password Reset
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
            println("Please Register First....")
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
                redirect action:"passwordreset"
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
            redirect action:"verifyotp"
        }
    }
    def passwordreset()
    {
        println("I am in password reset...")
        String email=session.email
        [email:email]
    }
    def savepasswordreset()
    {
        println("I am in savepasswordreset...")
        String email=params.email
        String password=params.password
        println(email+" "+password)
        Login l=Login.findByUsername(email)
        if(l!=null)
        {
            l.username=email
            l.updation_date=new java.util.Date()
            l.updation_ip_address=request.getRemoteAddr();
            l.password=password
            l.save(failOnError:true,flush:true)
            redirect (action:"login", controller:"Login")
        }
        else
        {
            println("You need to register first....")
        }
    }
    def index() { }
}
