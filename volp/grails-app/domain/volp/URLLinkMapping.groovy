package volp

class URLLinkMapping {

	String url 
	String link_name
	boolean isactive
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address  
	static belongsTo=[usertype:UserType]      
    static constraints = {
    }
}
