package volp

class MCQOptions {

	String option_name
	String option_value
	boolean isCorrect
	String mcq_option_file_name
	String mcq_option_file_path
    String mcq_option_file_link
    String username        
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[assignment:Assignment]
    static constraints = {
        option_name nullable:true
        mcq_option_file_name nullable:true
        mcq_option_file_path nullable:true
        mcq_option_file_link nullable:true
    }
}
