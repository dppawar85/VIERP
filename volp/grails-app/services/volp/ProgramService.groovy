package volp

import grails.gorm.services.Service

@Service(Program)
interface ProgramService {

    Program get(Serializable id)

    List<Program> list(Map args)

    Long count()

    void delete(Serializable id)

    Program save(Program program)

}