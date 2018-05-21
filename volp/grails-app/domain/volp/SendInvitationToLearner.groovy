package volp

class SendInvitationToLearner {

    String email
    String code
    boolean isverified   //true:verified  and false: not verified
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[courseoffering:CourseOffering]
    static constraints = {
        email email : true
    }
}
