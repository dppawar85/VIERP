package volp

class MCQOffering {

	Date schedule_date
	Date due_date
    String username    	
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
	static belongsTo=[mcq:MCQ,courseoffering:CourseOffering]    	
    static constraints = {
    }
}
