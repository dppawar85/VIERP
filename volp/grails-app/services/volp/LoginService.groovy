package volp

import grails.gorm.services.Service

@Service(Login)
interface LoginService {

    Login get(Serializable id)

    List<Login> list(Map args)

    Long count()

    void delete(Serializable id)

    Login save(Login login)

}