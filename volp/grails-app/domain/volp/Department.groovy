package volp

class Department {

    String name
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address    
	static belongsTo=[organization:Organization]
    static constraints = {
    }
}