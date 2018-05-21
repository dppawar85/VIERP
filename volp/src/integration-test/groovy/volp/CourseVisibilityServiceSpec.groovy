package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseVisibilityServiceSpec extends Specification {

    CourseVisibilityService courseVisibilityService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseVisibility(...).save(flush: true, failOnError: true)
        //new CourseVisibility(...).save(flush: true, failOnError: true)
        //CourseVisibility courseVisibility = new CourseVisibility(...).save(flush: true, failOnError: true)
        //new CourseVisibility(...).save(flush: true, failOnError: true)
        //new CourseVisibility(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseVisibility.id
    }

    void "test get"() {
        setupData()

        expect:
        courseVisibilityService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseVisibility> courseVisibilityList = courseVisibilityService.list(max: 2, offset: 2)

        then:
        courseVisibilityList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseVisibilityService.count() == 5
    }

    void "test delete"() {
        Long courseVisibilityId = setupData()

        expect:
        courseVisibilityService.count() == 5

        when:
        courseVisibilityService.delete(courseVisibilityId)
        sessionFactory.currentSession.flush()

        then:
        courseVisibilityService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseVisibility courseVisibility = new CourseVisibility()
        courseVisibilityService.save(courseVisibility)

        then:
        courseVisibility.id != null
    }
}
