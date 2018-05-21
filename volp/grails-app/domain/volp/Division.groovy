package volp

class Division {
	String name
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
	static belongsTo=[year:Year,program:Program]
    static constraints = {
    }
}
