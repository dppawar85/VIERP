package volp

class FeedbackQuestionare {

    int question_number
    String question_statement
	int question_weightage  
    String username    	  
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
	static belongsTo=[feedbacktype:FeedbackType]    	    
    static constraints = {
    }
}
