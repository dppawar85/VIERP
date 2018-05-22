package volp

import grails.gorm.services.Service

@Service(CourseCategory)
interface CourseCategoryService {

    CourseCategory get(Serializable id)

    List<CourseCategory> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseCategory save(CourseCategory courseCategory)

}