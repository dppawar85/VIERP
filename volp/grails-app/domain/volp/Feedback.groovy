package volp

class Feedback {

    Date date
    String username        
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address		
	static belongsTo=[feedbacktype:FeedbackType,learner:Learner,courseoffering:CourseOffering,courseofferinginstructor:CourseOfferingInstructor,feedbackquestionare:FeedbackQuestionare,feedbackquestionoptions:FeedbackQuestionOptions]    
    static constraints = {
    }
}
