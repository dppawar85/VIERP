package volp

class FeedbackQuestionOptions {

	String option_name
	String option_statement
	double option_weightage
    String username    	
    Date creation_date    
    Date updation_date
    String creation_ip_address
    String updation_ip_address		   
    static belongsTo=[feedbackquestionare:FeedbackQuestionare]
    static constraints = {
    }
}
