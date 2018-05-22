package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class LearnerCourseProgressServiceSpec extends Specification {

    LearnerCourseProgressService learnerCourseProgressService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new LearnerCourseProgress(...).save(flush: true, failOnError: true)
        //new LearnerCourseProgress(...).save(flush: true, failOnError: true)
        //LearnerCourseProgress learnerCourseProgress = new LearnerCourseProgress(...).save(flush: true, failOnError: true)
        //new LearnerCourseProgress(...).save(flush: true, failOnError: true)
        //new LearnerCourseProgress(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //learnerCourseProgress.id
    }

    void "test get"() {
        setupData()

        expect:
        learnerCourseProgressService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<LearnerCourseProgress> learnerCourseProgressList = learnerCourseProgressService.list(max: 2, offset: 2)

        then:
        learnerCourseProgressList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        learnerCourseProgressService.count() == 5
    }

    void "test delete"() {
        Long learnerCourseProgressId = setupData()

        expect:
        learnerCourseProgressService.count() == 5

        when:
        learnerCourseProgressService.delete(learnerCourseProgressId)
        sessionFactory.currentSession.flush()

        then:
        learnerCourseProgressService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        LearnerCourseProgress learnerCourseProgress = new LearnerCourseProgress()
        learnerCourseProgressService.save(learnerCourseProgress)

        then:
        learnerCourseProgress.id != null
    }
}
