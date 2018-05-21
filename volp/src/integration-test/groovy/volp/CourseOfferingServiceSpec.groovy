package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseOfferingServiceSpec extends Specification {

    CourseOfferingService courseOfferingService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseOffering(...).save(flush: true, failOnError: true)
        //new CourseOffering(...).save(flush: true, failOnError: true)
        //CourseOffering courseOffering = new CourseOffering(...).save(flush: true, failOnError: true)
        //new CourseOffering(...).save(flush: true, failOnError: true)
        //new CourseOffering(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseOffering.id
    }

    void "test get"() {
        setupData()

        expect:
        courseOfferingService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseOffering> courseOfferingList = courseOfferingService.list(max: 2, offset: 2)

        then:
        courseOfferingList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseOfferingService.count() == 5
    }

    void "test delete"() {
        Long courseOfferingId = setupData()

        expect:
        courseOfferingService.count() == 5

        when:
        courseOfferingService.delete(courseOfferingId)
        sessionFactory.currentSession.flush()

        then:
        courseOfferingService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseOffering courseOffering = new CourseOffering()
        courseOfferingService.save(courseOffering)

        then:
        courseOffering.id != null
    }
}
