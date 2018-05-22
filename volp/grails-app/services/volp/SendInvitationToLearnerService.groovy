package volp

import grails.gorm.services.Service

@Service(SendInvitationToLearner)
interface SendInvitationToLearnerService {

    SendInvitationToLearner get(Serializable id)

    List<SendInvitationToLearner> list(Map args)

    Long count()

    void delete(Serializable id)

    SendInvitationToLearner save(SendInvitationToLearner sendInvitationToLearner)

}