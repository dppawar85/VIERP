package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SendCommonInvitationToLearnerServiceSpec extends Specification {

    SendCommonInvitationToLearnerService sendCommonInvitationToLearnerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SendCommonInvitationToLearner(...).save(flush: true, failOnError: true)
        //new SendCommonInvitationToLearner(...).save(flush: true, failOnError: true)
        //SendCommonInvitationToLearner sendCommonInvitationToLearner = new SendCommonInvitationToLearner(...).save(flush: true, failOnError: true)
        //new SendCommonInvitationToLearner(...).save(flush: true, failOnError: true)
        //new SendCommonInvitationToLearner(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sendCommonInvitationToLearner.id
    }

    void "test get"() {
        setupData()

        expect:
        sendCommonInvitationToLearnerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SendCommonInvitationToLearner> sendCommonInvitationToLearnerList = sendCommonInvitationToLearnerService.list(max: 2, offset: 2)

        then:
        sendCommonInvitationToLearnerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sendCommonInvitationToLearnerService.count() == 5
    }

    void "test delete"() {
        Long sendCommonInvitationToLearnerId = setupData()

        expect:
        sendCommonInvitationToLearnerService.count() == 5

        when:
        sendCommonInvitationToLearnerService.delete(sendCommonInvitationToLearnerId)
        sessionFactory.currentSession.flush()

        then:
        sendCommonInvitationToLearnerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SendCommonInvitationToLearner sendCommonInvitationToLearner = new SendCommonInvitationToLearner()
        sendCommonInvitationToLearnerService.save(sendCommonInvitationToLearner)

        then:
        sendCommonInvitationToLearner.id != null
    }
}
