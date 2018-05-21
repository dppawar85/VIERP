package volp

import grails.gorm.services.Service

@Service(CourseOffering)
interface CourseOfferingService {

    CourseOffering get(Serializable id)

    List<CourseOffering> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseOffering save(CourseOffering courseOffering)

}