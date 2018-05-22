package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseCategoryServiceSpec extends Specification {

    CourseCategoryService courseCategoryService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseCategory(...).save(flush: true, failOnError: true)
        //new CourseCategory(...).save(flush: true, failOnError: true)
        //CourseCategory courseCategory = new CourseCategory(...).save(flush: true, failOnError: true)
        //new CourseCategory(...).save(flush: true, failOnError: true)
        //new CourseCategory(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseCategory.id
    }

    void "test get"() {
        setupData()

        expect:
        courseCategoryService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseCategory> courseCategoryList = courseCategoryService.list(max: 2, offset: 2)

        then:
        courseCategoryList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseCategoryService.count() == 5
    }

    void "test delete"() {
        Long courseCategoryId = setupData()

        expect:
        courseCategoryService.count() == 5

        when:
        courseCategoryService.delete(courseCategoryId)
        sessionFactory.currentSession.flush()

        then:
        courseCategoryService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseCategory courseCategory = new CourseCategory()
        courseCategoryService.save(courseCategory)

        then:
        courseCategory.id != null
    }
}
