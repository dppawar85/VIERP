package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class LearnerServiceSpec extends Specification {

    LearnerService learnerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Learner(...).save(flush: true, failOnError: true)
        //new Learner(...).save(flush: true, failOnError: true)
        //Learner learner = new Learner(...).save(flush: true, failOnError: true)
        //new Learner(...).save(flush: true, failOnError: true)
        //new Learner(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //learner.id
    }

    void "test get"() {
        setupData()

        expect:
        learnerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Learner> learnerList = learnerService.list(max: 2, offset: 2)

        then:
        learnerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        learnerService.count() == 5
    }

    void "test delete"() {
        Long learnerId = setupData()

        expect:
        learnerService.count() == 5

        when:
        learnerService.delete(learnerId)
        sessionFactory.currentSession.flush()

        then:
        learnerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Learner learner = new Learner()
        learnerService.save(learner)

        then:
        learner.id != null
    }
}
