package volp

class CourseOfferingInstructor {

    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
	static belongsTo=[courseoffering:CourseOffering,instructor:Instructor,division:Division]    
    static constraints = {
    }
}
