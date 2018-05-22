package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SendInvitationToLearnerServiceSpec extends Specification {

    SendInvitationToLearnerService sendInvitationToLearnerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SendInvitationToLearner(...).save(flush: true, failOnError: true)
        //new SendInvitationToLearner(...).save(flush: true, failOnError: true)
        //SendInvitationToLearner sendInvitationToLearner = new SendInvitationToLearner(...).save(flush: true, failOnError: true)
        //new SendInvitationToLearner(...).save(flush: true, failOnError: true)
        //new SendInvitationToLearner(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sendInvitationToLearner.id
    }

    void "test get"() {
        setupData()

        expect:
        sendInvitationToLearnerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SendInvitationToLearner> sendInvitationToLearnerList = sendInvitationToLearnerService.list(max: 2, offset: 2)

        then:
        sendInvitationToLearnerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sendInvitationToLearnerService.count() == 5
    }

    void "test delete"() {
        Long sendInvitationToLearnerId = setupData()

        expect:
        sendInvitationToLearnerService.count() == 5

        when:
        sendInvitationToLearnerService.delete(sendInvitationToLearnerId)
        sessionFactory.currentSession.flush()

        then:
        sendInvitationToLearnerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SendInvitationToLearner sendInvitationToLearner = new SendInvitationToLearner()
        sendInvitationToLearnerService.save(sendInvitationToLearner)

        then:
        sendInvitationToLearner.id != null
    }
}
