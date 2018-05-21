package volp

import grails.gorm.services.Service

@Service(SendCommonInvitationToLearner)
interface SendCommonInvitationToLearnerService {

    SendCommonInvitationToLearner get(Serializable id)

    List<SendCommonInvitationToLearner> list(Map args)

    Long count()

    void delete(Serializable id)

    SendCommonInvitationToLearner save(SendCommonInvitationToLearner sendCommonInvitationToLearner)

}