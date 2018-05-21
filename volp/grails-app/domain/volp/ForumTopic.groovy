package volp

class ForumTopic {
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address

    String title
    String description
    String opid //Learner or Instructor
    boolean isClosed
    String imagepath
    String imagename

    static belongsTo=[courseoffering:CourseOffering,type:UserType] //type->Learner or Instructor
    static hasMany = [forumthreads:ForumThreads]
    static constraints = {
        imagepath nullable: true
        imagename nullable: true
    }
}
