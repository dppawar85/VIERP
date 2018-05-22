package volp

import grails.gorm.services.Service

@Service(FeedbackGivenType)
interface FeedbackGivenTypeService {

    FeedbackGivenType get(Serializable id)

    List<FeedbackGivenType> list(Map args)

    Long count()

    void delete(Serializable id)

    FeedbackGivenType save(FeedbackGivenType feedbackGivenType)

}