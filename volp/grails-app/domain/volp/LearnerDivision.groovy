package volp

class LearnerDivision {

	int rollno
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
	static belongsTo=[learner:Learner,division:Division,year:Year]
    static constraints = {
    }
}
