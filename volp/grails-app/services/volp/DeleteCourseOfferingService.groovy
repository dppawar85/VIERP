package volp

import grails.gorm.services.Service

@Service(DeleteCourseOffering)
interface DeleteCourseOfferingService {

    DeleteCourseOffering get(Serializable id)

    List<DeleteCourseOffering> list(Map args)

    Long count()

    void delete(Serializable id)

    DeleteCourseOffering save(DeleteCourseOffering deleteCourseOffering)

}