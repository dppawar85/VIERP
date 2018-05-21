package volp

class Instructor {
    String uid     //This should be simmilar to login.username
	String employee_code
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
    double rating
	static belongsTo=[person:Person,organization:Organization,designation:Designation,program:Program]
    static constraints = {
        employee_code nullable: true
        rating nullable: true
        organization nullable: true
        designation nullable: true
        program nullable: true
    }
}
