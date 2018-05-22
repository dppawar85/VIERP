package volp

class AdminController {

    def index() { }
    def manage() {

    }
    def viewFeedback(){
        def feedback=PlatformFeedback.list()
        ArrayList learner=new ArrayList()
        ArrayList instructor=new ArrayList()
        for(PlatformFeedback pf:feedback)
        {
            ArrayList temp=new ArrayList()
         if(pf.feedbackgiventype.type=="Learner")
         {
             temp.add(Learner.findById(pf.userid))
             temp.add(pf.description)
             learner.add(temp)
         }
            if(pf.feedbackgiventype.type=="Instructor")
            {
                temp.add(Instructor.findById(pf.userid))
                temp.add(pf.description)
                instructor.add(temp)

            }
        }
        println("Learner"+learner)
        [learner:learner,instructor:instructor]
    }

    def  deletefeedback()
    {
        println("I am in deletecourseoutcome:"+params)
        CourseOutcomes courseoutcome=CourseOutcomes.findById(params.id)
        courseoutcome.delete(failOnError:true,flush:true)
        String coursecodecoursename=session.coursecodecoursename
        redirect(action: "addcourseoutcomes", params: [coursecodecoursename:coursecodecoursename])
    }
}
