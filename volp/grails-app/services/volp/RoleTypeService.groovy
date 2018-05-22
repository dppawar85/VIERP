package volp

import grails.gorm.services.Service

@Service(RoleType)
interface RoleTypeService {

    RoleType get(Serializable id)

    List<RoleType> list(Map args)

    Long count()

    void delete(Serializable id)

    RoleType save(RoleType roleType)

}