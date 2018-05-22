package volp

class Organization 
{
    String uid     //This should be simmilar to login.username
	String email
	String organization_name
	String organization_code
	String registration_number
	String display_name
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
	static belongsTo=[organization:Organization,organizationtype:OrganizationType]
    static hasMany = [logos : Logo]		
    static constraints = {
        organization_name nullable: true
        organization_code nullable: true
        registration_number nullable: true
        display_name nullable: true
        organization nullable: true
        organizationtype nullable: true
    }
}
