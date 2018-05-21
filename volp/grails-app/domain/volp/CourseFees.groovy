package volp

class CourseFees {

	double fees  //in dollar
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
	static belongsTo=[courseoffering:CourseOffering,coursecertificatetype:CourseCertificateType,coursepackage:CoursePackage]
    static hasMany =[coursefeestype:CourseFeesType]
    static constraints = {
        coursepackage nullable:true
    }
}
