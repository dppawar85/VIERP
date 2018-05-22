package volp

class AssignmentOffering
{
    int assignment_offering_number
	Date schedule_date
	Date due_date
    String username    	
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    boolean isDeleted
    static belongsTo=[assignment:Assignment,courseoffering:CourseOffering,courseassesmentscheme:CourseAssesmentScheme]
    static constraints = {
        courseassesmentscheme nullable:true
        schedule_date nullable:true
        due_date nullable:true
    }
    static mapping={
        isDeleted defaultValue:false
    }
}
