package volp

import grails.gorm.services.Service

@Service(CourseCertificate)
interface CourseCertificateService {

    CourseCertificate get(Serializable id)

    List<CourseCertificate> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseCertificate save(CourseCertificate courseCertificate)

}