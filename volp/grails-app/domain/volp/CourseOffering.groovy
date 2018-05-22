package volp

class CourseOffering {

    Date start_date
    Date end_date
    int threshold
    boolean isActive    //Active / Archieved
    boolean isDeleted
    boolean isDeleteRequestDone
    String batch
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[course:Course,year:Year,academicyear:AcademicYear,semester:Semester,coursetype:CourseType,courseofferingtype:CourseOfferingType]
    static constraints = {
        year nullable:true
        academicyear nullable:true
        semester nullable:true
        coursetype nullable:true
        start_date nullable:true
        end_date nullable:true
    }
}
