package volp

class AssessmentGrade {

	String grade_name
	int grade_lower_value
	int grade_higher_value
	String description
    String username        
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
	static belongsTo=[courseoffering:CourseOffering]
    static constraints = {
    }
}
