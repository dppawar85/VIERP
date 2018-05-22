package volp

class CourseVideos {
	
	String video_path
	String video_name
    String video_link
    String meta_data
	double duration_in_minutes
	String description
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    boolean isDeleted
	static belongsTo=[coursetopic:CourseTopic,course:Course,courseoutline:CourseOutline,fileformat:FileFormat]
    static constraints = {
        courseoutline nullable:true
        coursetopic nullable:true
        video_path nullable:true
        fileformat nullable:true
        meta_data nullable:true
        description nullable:true
        duration_in_minutes nullable:true
    }
    static mapping={
        isDeleted defaultValue:false
    }
}
