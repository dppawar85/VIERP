package volp

class Otp {

    String email
    String otp
    Date otpgenerationtime  
    static constraints = {
    	email email : true 
    }
}
