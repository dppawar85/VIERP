package volp

class PlatformFeedback {
    int userid // this is same as Learner.id or Instructor.id
    String description
    static belongsTo = [feedbackgiventype: FeedbackGivenType]
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static constraints = {
    }
}
