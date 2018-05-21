package volp

class Learner {
    String uid     //This should be simmilar to login.username
	String registration_number
	String graduation_status
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
    static belongsTo=[person:Person,organization:Organization,current_module:Module,current_year:Year,program:Program]
    static constraints = {
        registration_number nullable: true
        graduation_status nullable: true
        organization nullable: true
        current_module nullable: true
        program nullable: true
        current_year nullable: true
    }
}
