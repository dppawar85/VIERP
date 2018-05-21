package volp

import grails.gorm.services.Service

@Service(CourseTopic)
interface CourseTopicService {

    CourseTopic get(Serializable id)

    List<CourseTopic> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseTopic save(CourseTopic courseTopic)

}