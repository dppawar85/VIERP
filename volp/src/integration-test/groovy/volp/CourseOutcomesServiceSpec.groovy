package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseOutcomesServiceSpec extends Specification {

    CourseOutcomesService courseOutcomesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseOutcomes(...).save(flush: true, failOnError: true)
        //new CourseOutcomes(...).save(flush: true, failOnError: true)
        //CourseOutcomes courseOutcomes = new CourseOutcomes(...).save(flush: true, failOnError: true)
        //new CourseOutcomes(...).save(flush: true, failOnError: true)
        //new CourseOutcomes(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseOutcomes.id
    }

    void "test get"() {
        setupData()

        expect:
        courseOutcomesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseOutcomes> courseOutcomesList = courseOutcomesService.list(max: 2, offset: 2)

        then:
        courseOutcomesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseOutcomesService.count() == 5
    }

    void "test delete"() {
        Long courseOutcomesId = setupData()

        expect:
        courseOutcomesService.count() == 5

        when:
        courseOutcomesService.delete(courseOutcomesId)
        sessionFactory.currentSession.flush()

        then:
        courseOutcomesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseOutcomes courseOutcomes = new CourseOutcomes()
        courseOutcomesService.save(courseOutcomes)

        then:
        courseOutcomes.id != null
    }
}
