package volp

class CourseAssesmentScheme {

    double weightage
    String username        
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
	static belongsTo=[course:Course,assesmenttype:AssesmentType]
    static constraints = {
    }
}
