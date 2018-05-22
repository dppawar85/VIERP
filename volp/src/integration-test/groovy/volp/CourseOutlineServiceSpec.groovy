package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseOutlineServiceSpec extends Specification {

    CourseOutlineService courseOutlineService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseOutline(...).save(flush: true, failOnError: true)
        //new CourseOutline(...).save(flush: true, failOnError: true)
        //CourseOutline courseOutline = new CourseOutline(...).save(flush: true, failOnError: true)
        //new CourseOutline(...).save(flush: true, failOnError: true)
        //new CourseOutline(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseOutline.id
    }

    void "test get"() {
        setupData()

        expect:
        courseOutlineService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseOutline> courseOutlineList = courseOutlineService.list(max: 2, offset: 2)

        then:
        courseOutlineList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseOutlineService.count() == 5
    }

    void "test delete"() {
        Long courseOutlineId = setupData()

        expect:
        courseOutlineService.count() == 5

        when:
        courseOutlineService.delete(courseOutlineId)
        sessionFactory.currentSession.flush()

        then:
        courseOutlineService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseOutline courseOutline = new CourseOutline()
        courseOutlineService.save(courseOutline)

        then:
        courseOutline.id != null
    }
}
