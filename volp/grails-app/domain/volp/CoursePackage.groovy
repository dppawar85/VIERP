package volp

class CoursePackage {

    String package_name
    double fees
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[coursecategory:CourseCategory]
    static constraints = {
    }
}
