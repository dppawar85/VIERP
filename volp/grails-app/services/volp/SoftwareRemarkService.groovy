package volp

import grails.gorm.services.Service

@Service(SoftwareRemark)
interface SoftwareRemarkService {

    SoftwareRemark get(Serializable id)

    List<SoftwareRemark> list(Map args)

    Long count()

    void delete(Serializable id)

    SoftwareRemark save(SoftwareRemark softwareRemark)

}