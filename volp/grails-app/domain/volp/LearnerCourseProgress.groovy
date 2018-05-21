package volp

class LearnerCourseProgress {

    boolean isViewed   //false:NOT Viewed     //true:Viewed
    static belongsTo=[courseofferinglearner:CourseOfferingLearner,coursevideos:CourseVideos]
    static constraints = {
    }
}
