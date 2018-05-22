package volp

class ForumThreads {

    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address

    String comment
    String opid //Learner or Instructor
    String imagepath
    String imagename


    static belongsTo=[forumtopic:ForumTopic,type:UserType] //type->Learner or Instructor
    static constraints = {
        imagepath nullable: true
        imagename nullable: true
    }
}
