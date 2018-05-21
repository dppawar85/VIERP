package volp

class Week {

    int week_number
    Date start_date
    Date end_date
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[courseoffering:CourseOffering]
    static constraints = {
    }

}
