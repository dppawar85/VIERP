package volp

import grails.gorm.services.Service

@Service(CourseOfferingLearner)
interface CourseOfferingLearnerService {

    CourseOfferingLearner get(Serializable id)

    List<CourseOfferingLearner> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseOfferingLearner save(CourseOfferingLearner courseOfferingLearner)

}