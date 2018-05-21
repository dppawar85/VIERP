package volp

class CourseMaterial {

	String material_path
	String material_name
    String material_link
    String meta_data
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
        material_path nullable:true
        fileformat nullable:true
        meta_data nullable:true
        description nullable:true
    }
    static mapping={
        isDeleted defaultValue:false
    }
}
