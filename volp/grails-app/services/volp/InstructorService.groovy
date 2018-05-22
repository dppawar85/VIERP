package volp

import grails.gorm.services.Service

@Service(Instructor)
interface InstructorService {

    Instructor get(Serializable id)

    List<Instructor> list(Map args)

    Long count()

    void delete(Serializable id)

    Instructor save(Instructor instructor)

}