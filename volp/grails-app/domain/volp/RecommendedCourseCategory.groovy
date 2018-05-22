package volp

class RecommendedCourseCategory {
    String recommended_name
    boolean isMapped
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[course:Course]
    static constraints = {
    }
}
