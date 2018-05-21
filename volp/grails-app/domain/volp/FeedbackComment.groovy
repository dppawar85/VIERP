package volp

class FeedbackComment {

    Date date
    String comment
    String username        
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address		
	static belongsTo=[feedbacktype:FeedbackType,courseoffering:CourseOffering,learner:Learner,courseofferinginstructor:CourseOfferingInstructor]
    static constraints = {
    }
}
