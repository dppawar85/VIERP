package volp

class Course {

	String course_code
	String course_name
	int credit
    String username
    String imgpath
    String imgname
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
    double rating
	static belongsTo=[coursecategory:CourseCategory,department:Department,program:Program,coursevisibility:CourseVisibility,courseowner:Instructor]    
    static hasMany = [courseinstructor:Instructor,features:Features,softwareurl:SoftwareURL,prerequisite:Prerequisite,description:Description]
    static constraints = {
        course_code nullable: true
        credit nulable: true
        rating nullable: true
        department nullable:true
        program nullable:true
        imgpath nullable : true
        imgname nullable : true
    }
    // has Many :: prerequisite:Prerequisite,description:Pescription
}
