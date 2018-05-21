package volp

class FileFormat {

	String extension
	static belongsTo=[filetype:FileType]   //text,audio,video
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	     		
    static constraints = {
    }
}
