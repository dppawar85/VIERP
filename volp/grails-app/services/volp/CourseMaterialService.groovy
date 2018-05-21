package volp

import grails.gorm.services.Service

@Service(CourseMaterial)
interface CourseMaterialService {

    CourseMaterial get(Serializable id)

    List<CourseMaterial> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseMaterial save(CourseMaterial courseMaterial)

}