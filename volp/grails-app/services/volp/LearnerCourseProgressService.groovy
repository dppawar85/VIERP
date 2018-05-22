package volp

import grails.gorm.services.Service

@Service(LearnerCourseProgress)
interface LearnerCourseProgressService {

    LearnerCourseProgress get(Serializable id)

    List<LearnerCourseProgress> list(Map args)

    Long count()

    void delete(Serializable id)

    LearnerCourseProgress save(LearnerCourseProgress learnerCourseProgress)

}