package volp

class  AssignmentSubmission {

    String student_answer_file_path
    String student_answer_file_name
    Date submission_date
    double marks
    String teacher_remark
    String student_answer_text
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[learner:Learner, assignmentoffering:AssignmentOffering, assessmentgrade:AssessmentGrade, softwareremark:SoftwareRemark,mcqoptions:MCQOptions]
    static constraints = {
        assessmentgrade nullable:true
        mcqoptions nullable:true
        student_answer_file_path nullable:true
        student_answer_file_name nullable:true
    }
}
