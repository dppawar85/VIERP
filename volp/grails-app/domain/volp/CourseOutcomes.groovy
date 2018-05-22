package volp

class CourseOutcomes {
	int co_code
	String co_statement
    boolean isCurrent
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    boolean isDeleted
	static belongsTo=[course:Course,revisionyear:AcademicYear]	
    static constraints = {
    }
    static mapping={
        isDeleted defaultValue:false
    }
}
