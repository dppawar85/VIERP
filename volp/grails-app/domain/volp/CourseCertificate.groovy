package volp

class CourseCertificate {

    Date certificate_date
    String certificate_number
    String period
    String number_of_weeks
    String number_of_hours
    static belongsTo=[courseofferinglearner:CourseOfferingLearner]
    static constraints = {
        period nullable:true
        number_of_weeks nullable:true
        number_of_hours nullable:true
    }
}
