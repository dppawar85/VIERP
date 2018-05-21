package volp

import grails.gorm.services.Service

@Service(CourseVisibility)
interface CourseVisibilityService {

    CourseVisibility get(Serializable id)

    List<CourseVisibility> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseVisibility save(CourseVisibility courseVisibility)

}