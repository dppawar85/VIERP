package volp

class Contact 
{
	String mobile_no
    String ulternate_mobile_no
	String alternate_email
	String telephone_no
    String fax
    String website_url
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[person:Person,organization:Organization]
    static constraints = {
    	mobile_no size : 10 .. 15
        ulternate_mobile_no size : 10 .. 15
    	alternate_email email : true
    	telephone_no size : 5 .. 15
    	fax size : 5 .. 15
    	website_url size : 0 .. 100
        organization nullable:true
    }
}
