package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PlatformFeedbackServiceSpec extends Specification {

    PlatformFeedbackService platformFeedbackService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PlatformFeedback(...).save(flush: true, failOnError: true)
        //new PlatformFeedback(...).save(flush: true, failOnError: true)
        //PlatformFeedback platformFeedback = new PlatformFeedback(...).save(flush: true, failOnError: true)
        //new PlatformFeedback(...).save(flush: true, failOnError: true)
        //new PlatformFeedback(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //platformFeedback.id
    }

    void "test get"() {
        setupData()

        expect:
        platformFeedbackService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PlatformFeedback> platformFeedbackList = platformFeedbackService.list(max: 2, offset: 2)

        then:
        platformFeedbackList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        platformFeedbackService.count() == 5
    }

    void "test delete"() {
        Long platformFeedbackId = setupData()

        expect:
        platformFeedbackService.count() == 5

        when:
        platformFeedbackService.delete(platformFeedbackId)
        sessionFactory.currentSession.flush()

        then:
        platformFeedbackService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PlatformFeedback platformFeedback = new PlatformFeedback()
        platformFeedbackService.save(platformFeedback)

        then:
        platformFeedback.id != null
    }
}
