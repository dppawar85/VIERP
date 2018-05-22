package volp

class CourseSchedule {

    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
	static belongsTo=[coursetopic:CourseTopic,session:Session]    
    static constraints = {
    }
}
