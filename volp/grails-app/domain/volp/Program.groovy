package volp

class Program {

    String name
	static belongsTo=[department:Department]
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
    static constraints = {
    }
}
