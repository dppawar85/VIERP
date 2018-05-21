package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseFeesServiceSpec extends Specification {

    CourseFeesService courseFeesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseFees(...).save(flush: true, failOnError: true)
        //new CourseFees(...).save(flush: true, failOnError: true)
        //CourseFees courseFees = new CourseFees(...).save(flush: true, failOnError: true)
        //new CourseFees(...).save(flush: true, failOnError: true)
        //new CourseFees(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseFees.id
    }

    void "test get"() {
        setupData()

        expect:
        courseFeesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseFees> courseFeesList = courseFeesService.list(max: 2, offset: 2)

        then:
        courseFeesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseFeesService.count() == 5
    }

    void "test delete"() {
        Long courseFeesId = setupData()

        expect:
        courseFeesService.count() == 5

        when:
        courseFeesService.delete(courseFeesId)
        sessionFactory.currentSession.flush()

        then:
        courseFeesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseFees courseFees = new CourseFees()
        courseFeesService.save(courseFees)

        then:
        courseFees.id != null
    }
}
