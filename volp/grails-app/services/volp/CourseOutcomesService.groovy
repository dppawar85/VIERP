package volp

import grails.gorm.services.Service

@Service(CourseOutcomes)
interface CourseOutcomesService {

    CourseOutcomes get(Serializable id)

    List<CourseOutcomes> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseOutcomes save(CourseOutcomes courseOutcomes)

}