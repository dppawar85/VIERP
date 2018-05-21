package volp

class CourseType {
	String type
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[organization:Organization]
    static hasMany = [loadtype: LoadType]    
    static constraints = {
    }
}
