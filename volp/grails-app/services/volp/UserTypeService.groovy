package volp

import grails.gorm.services.Service

@Service(UserType)
interface UserTypeService {

    UserType get(Serializable id)

    List<UserType> list(Map args)

    Long count()

    void delete(Serializable id)

    UserType save(UserType userType)

}