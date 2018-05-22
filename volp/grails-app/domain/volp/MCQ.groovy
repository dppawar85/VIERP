package volp

class MCQ {

	int assignment_number
	String title
	String description
	double weightage
	Date due_date
	Date schedule_date
    String username    	
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address		
	static belongsTo=[coursetopic:CourseTopic,difficultylevel:DifficultyLevel]
    static hasMany = [mcqoptions:MCQOptions]       	
    static constraints = {
        assignment_number nullable: true
        title nullable: true
        description nullable: true
    }
}
