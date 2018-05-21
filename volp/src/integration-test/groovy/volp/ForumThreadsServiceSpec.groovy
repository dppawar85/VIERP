package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ForumThreadsServiceSpec extends Specification {

    ForumThreadsService forumThreadsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ForumThreads(...).save(flush: true, failOnError: true)
        //new ForumThreads(...).save(flush: true, failOnError: true)
        //ForumThreads forumThreads = new ForumThreads(...).save(flush: true, failOnError: true)
        //new ForumThreads(...).save(flush: true, failOnError: true)
        //new ForumThreads(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //forumThreads.id
    }

    void "test get"() {
        setupData()

        expect:
        forumThreadsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ForumThreads> forumThreadsList = forumThreadsService.list(max: 2, offset: 2)

        then:
        forumThreadsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        forumThreadsService.count() == 5
    }

    void "test delete"() {
        Long forumThreadsId = setupData()

        expect:
        forumThreadsService.count() == 5

        when:
        forumThreadsService.delete(forumThreadsId)
        sessionFactory.currentSession.flush()

        then:
        forumThreadsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ForumThreads forumThreads = new ForumThreads()
        forumThreadsService.save(forumThreads)

        then:
        forumThreads.id != null
    }
}
