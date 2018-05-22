package volp

import grails.gorm.services.Service

@Service(PlatformFeedback)
interface PlatformFeedbackService {

    PlatformFeedback get(Serializable id)

    List<PlatformFeedback> list(Map args)

    Long count()

    void delete(Serializable id)

    PlatformFeedback save(PlatformFeedback platformFeedback)

}