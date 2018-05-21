package volp

import grails.gorm.services.Service

@Service(Division)
interface DivisionService {

    Division get(Serializable id)

    List<Division> list(Map args)

    Long count()

    void delete(Serializable id)

    Division save(Division division)

}