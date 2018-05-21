package volp

import grails.gorm.services.Service

@Service(Week)
interface WeekService {

    Week get(Serializable id)

    List<Week> list(Map args)

    Long count()

    void delete(Serializable id)

    Week save(Week week)

}