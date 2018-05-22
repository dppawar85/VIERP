package volp

class SendCommonInvitationToLearner {

    String code
    static belongsTo=[courseoffering:CourseOffering]
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static constraints = {
    }
}
