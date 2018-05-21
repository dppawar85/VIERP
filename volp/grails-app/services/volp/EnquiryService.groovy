package volp

import grails.gorm.services.Service

@Service(Enquiry)
interface EnquiryService {

    Enquiry get(Serializable id)

    List<Enquiry> list(Map args)

    Long count()

    void delete(Serializable id)

    Enquiry save(Enquiry enquiry)

}