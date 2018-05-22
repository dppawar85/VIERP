package volp

class CourseRating {
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address

    double rating
    static belongsTo=[course:Course,learner:Learner]
    static constraints = {
    }
}
