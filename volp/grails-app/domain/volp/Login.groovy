package volp

class Login {

    String username
    String password
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    boolean isloginblocked       
    static belongsTo=[usertype:UserType,socialaccounttype:SocialAccountType]
    static hasMany = [roles: Role]    
    static constraints = {
    	username unique : true, blank: false
        socialaccounttype nullable:true
    }
}
