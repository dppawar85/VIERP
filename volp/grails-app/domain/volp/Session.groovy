package volp

class Session {
    int session_number
    Date session_date
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[week:Week,courseoffering:CourseOffering,courseoutline:CourseOutline]
    static hasMany = [coursetopic:CourseTopic]
    static constraints = {
        week nullable:true
        session_date nullable:true
        courseoutline nullable:true
    }
}
