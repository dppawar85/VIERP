package volp

class Rating {

	double rating
    String username    	
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
	static belongsTo=[feedbacktype:FeedbackType,courseoffering:CourseOffering,courseofferinginstructor:CourseOfferingInstructor]       
    static constraints = {
    }
}
