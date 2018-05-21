package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ForumTopicServiceSpec extends Specification {

    ForumTopicService forumTopicService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ForumTopic(...).save(flush: true, failOnError: true)
        //new ForumTopic(...).save(flush: true, failOnError: true)
        //ForumTopic forumTopic = new ForumTopic(...).save(flush: true, failOnError: true)
        //new ForumTopic(...).save(flush: true, failOnError: true)
        //new ForumTopic(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //forumTopic.id
    }

    void "test get"() {
        setupData()

        expect:
        forumTopicService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ForumTopic> forumTopicList = forumTopicService.list(max: 2, offset: 2)

        then:
        forumTopicList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        forumTopicService.count() == 5
    }

    void "test delete"() {
        Long forumTopicId = setupData()

        expect:
        forumTopicService.count() == 5

        when:
        forumTopicService.delete(forumTopicId)
        sessionFactory.currentSession.flush()

        then:
        forumTopicService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ForumTopic forumTopic = new ForumTopic()
        forumTopicService.save(forumTopic)

        then:
        forumTopic.id != null
    }
}
