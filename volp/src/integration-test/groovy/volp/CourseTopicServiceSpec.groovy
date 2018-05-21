package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseTopicServiceSpec extends Specification {

    CourseTopicService courseTopicService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseTopic(...).save(flush: true, failOnError: true)
        //new CourseTopic(...).save(flush: true, failOnError: true)
        //CourseTopic courseTopic = new CourseTopic(...).save(flush: true, failOnError: true)
        //new CourseTopic(...).save(flush: true, failOnError: true)
        //new CourseTopic(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseTopic.id
    }

    void "test get"() {
        setupData()

        expect:
        courseTopicService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseTopic> courseTopicList = courseTopicService.list(max: 2, offset: 2)

        then:
        courseTopicList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseTopicService.count() == 5
    }

    void "test delete"() {
        Long courseTopicId = setupData()

        expect:
        courseTopicService.count() == 5

        when:
        courseTopicService.delete(courseTopicId)
        sessionFactory.currentSession.flush()

        then:
        courseTopicService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseTopic courseTopic = new CourseTopic()
        courseTopicService.save(courseTopic)

        then:
        courseTopic.id != null
    }
}
