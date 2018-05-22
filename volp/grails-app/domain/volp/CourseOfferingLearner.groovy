package volp

class CourseOfferingLearner {
    int rollnumber
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[courseoffering:CourseOffering,learner:Learner,program:Program,year:Year,division:Division]
    static constraints = {
        program nullable:true
        year nullable:true
        division nullable:true
    }
}
