package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseOfferingLearnerServiceSpec extends Specification {

    CourseOfferingLearnerService courseOfferingLearnerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseOfferingLearner(...).save(flush: true, failOnError: true)
        //new CourseOfferingLearner(...).save(flush: true, failOnError: true)
        //CourseOfferingLearner courseOfferingLearner = new CourseOfferingLearner(...).save(flush: true, failOnError: true)
        //new CourseOfferingLearner(...).save(flush: true, failOnError: true)
        //new CourseOfferingLearner(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseOfferingLearner.id
    }

    void "test get"() {
        setupData()

        expect:
        courseOfferingLearnerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseOfferingLearner> courseOfferingLearnerList = courseOfferingLearnerService.list(max: 2, offset: 2)

        then:
        courseOfferingLearnerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseOfferingLearnerService.count() == 5
    }

    void "test delete"() {
        Long courseOfferingLearnerId = setupData()

        expect:
        courseOfferingLearnerService.count() == 5

        when:
        courseOfferingLearnerService.delete(courseOfferingLearnerId)
        sessionFactory.currentSession.flush()

        then:
        courseOfferingLearnerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseOfferingLearner courseOfferingLearner = new CourseOfferingLearner()
        courseOfferingLearnerService.save(courseOfferingLearner)

        then:
        courseOfferingLearner.id != null
    }
}
