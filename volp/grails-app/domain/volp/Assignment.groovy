package volp

class Assignment {
    int assignment_number
    String title
    String description
    double weightage
    String model_answer_path
    String model_answer_file_name
    String model_answer_text
    String model_answer_link
    String assignment_path
    String assignment_file_name
    String assignment_link
    String assignment_text  //problem statement
    String username        
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address

	static belongsTo=[coursetopic:CourseTopic,courseoutline:CourseOutline,course:Course,assignmenttype:AssignmentType,difficultylevel:DifficultyLevel]
    static constraints = {
        assignment_number nullable: true
        description nullable: true
        title nullable: true
        model_answer_path nullable: true
        model_answer_file_name nullable: true
        model_answer_text nullable: true
        model_answer_link nullable: true
        assignment_path nullable: true
        assignment_file_name nullable: true
        assignment_link nullable: true
        assignment_text nullable: true
        coursetopic nullable: true
        courseoutline nullable: true
    }

}
