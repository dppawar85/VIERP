package volp

class Person {
    String email
	String firstName
	String middleName
	String lastName
    String highest_qualification
    String skills
    String short_description
	Date date_of_birth
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
	static belongsTo=[gender:Gender]
    static hasMany = [logos : Logo,social:Social]	
    static constraints = {
        firstName nullable: true
        middleName nullable: true
        lastName nullable: true
        date_of_birth nullable: true
        gender nullable: true
        social nullable: true
        highest_qualification nullable: true
        short_description nullable: true
        skills nullable: true
    }
}
