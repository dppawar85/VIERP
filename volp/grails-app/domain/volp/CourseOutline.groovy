package volp

class CourseOutline {

    int outline_number
    String outline
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    boolean isDeleted
    static belongsTo=[course:Course]

    static constraints = {
    }
    static mapping={
        isDeleted defaultValue:false
    }
}
