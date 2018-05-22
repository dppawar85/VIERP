package volp

class MCQSubmission {

    Date submission_date
    double marks
    String teacher_remark
    String username        
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
	static belongsTo=[learner:Learner,mcqoffering:MCQOffering,mcqoptions:MCQOptions,softwareremark:SoftwareRemark]
    static constraints = {
    }
}
