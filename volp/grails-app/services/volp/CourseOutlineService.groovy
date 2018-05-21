package volp

import grails.gorm.services.Service

@Service(CourseOutline)
interface CourseOutlineService {

    CourseOutline get(Serializable id)

    List<CourseOutline> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseOutline save(CourseOutline courseOutline)

}