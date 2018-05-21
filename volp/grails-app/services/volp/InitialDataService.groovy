package volp

import grails.gorm.services.Service

@Service(InitialData)
interface InitialDataService {

    InitialData get(Serializable id)

    List<InitialData> list(Map args)

    Long count()

    void delete(Serializable id)

    InitialData save(InitialData initialData)

}