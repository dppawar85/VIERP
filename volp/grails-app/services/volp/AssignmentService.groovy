package volp

import grails.gorm.services.Service

@Service(Assignment)
interface AssignmentService {

    Assignment get(Serializable id)

    List<Assignment> list(Map args)

    Long count()

    void delete(Serializable id)

    Assignment save(Assignment assignment)

}