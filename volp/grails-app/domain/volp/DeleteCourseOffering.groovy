package volp

class DeleteCourseOffering {
    String username
    boolean isDone
    String reason
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[instructor:Instructor,organization:Organization,courseoffering:CourseOffering]
    static constraints = {

    }
}
