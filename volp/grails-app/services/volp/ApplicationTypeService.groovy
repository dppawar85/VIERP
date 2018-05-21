package volp

import grails.gorm.services.Service

@Service(ApplicationType)
interface ApplicationTypeService {

    ApplicationType get(Serializable id)

    List<ApplicationType> list(Map args)

    Long count()

    void delete(Serializable id)

    ApplicationType save(ApplicationType applicationType)

}