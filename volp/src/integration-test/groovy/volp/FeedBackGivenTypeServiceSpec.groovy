package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FeedbackGivenTypeServiceSpec extends Specification {

    FeedbackGivenTypeService feedbackGivenTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new FeedbackGivenType(...).save(flush: true, failOnError: true)
        //new FeedbackGivenType(...).save(flush: true, failOnError: true)
        //FeedbackGivenType feedbackGivenType = new FeedbackGivenType(...).save(flush: true, failOnError: true)
        //new FeedbackGivenType(...).save(flush: true, failOnError: true)
        //new FeedbackGivenType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //feedbackGivenType.id
    }

    void "test get"() {
        setupData()

        expect:
        feedbackGivenTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<FeedbackGivenType> feedbackGivenTypeList = feedbackGivenTypeService.list(max: 2, offset: 2)

        then:
        feedbackGivenTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        feedbackGivenTypeService.count() == 5
    }

    void "test delete"() {
        Long feedbackGivenTypeId = setupData()

        expect:
        feedbackGivenTypeService.count() == 5

        when:
        feedbackGivenTypeService.delete(feedbackGivenTypeId)
        sessionFactory.currentSession.flush()

        then:
        feedbackGivenTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        FeedbackGivenType feedbackGivenType = new FeedbackGivenType()
        feedbackGivenTypeService.save(feedbackGivenType)

        then:
        feedbackGivenType.id != null
    }
}
