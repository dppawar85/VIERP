package volp

class CourseAnnouncement {

	String notice	
	Date schedule_date
	String announcement_file_path	
	String announcement_file_name
    String username    	
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address		
	static belongsTo=[courseoffering:CourseOffering]
    static constraints = {
        announcement_file_path nullable : true
        announcement_file_name nullable : true
    }
}
