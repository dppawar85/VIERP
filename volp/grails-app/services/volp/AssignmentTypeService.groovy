package volp

import grails.gorm.services.Service

@Service(AssignmentType)
interface AssignmentTypeService {

    AssignmentType get(Serializable id)

    List<AssignmentType> list(Map args)

    Long count()

    void delete(Serializable id)

    AssignmentType save(AssignmentType assignmentType)

}