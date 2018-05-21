package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AssignmentSubmissionServiceSpec extends Specification {

    AssignmentSubmissionService assignmentSubmissionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new AssignmentSubmission(...).save(flush: true, failOnError: true)
        //new AssignmentSubmission(...).save(flush: true, failOnError: true)
        //AssignmentSubmission assignmentSubmission = new AssignmentSubmission(...).save(flush: true, failOnError: true)
        //new AssignmentSubmission(...).save(flush: true, failOnError: true)
        //new AssignmentSubmission(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //assignmentSubmission.id
    }

    void "test get"() {
        setupData()

        expect:
        assignmentSubmissionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<AssignmentSubmission> assignmentSubmissionList = assignmentSubmissionService.list(max: 2, offset: 2)

        then:
        assignmentSubmissionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        assignmentSubmissionService.count() == 5
    }

    void "test delete"() {
        Long assignmentSubmissionId = setupData()

        expect:
        assignmentSubmissionService.count() == 5

        when:
        assignmentSubmissionService.delete(assignmentSubmissionId)
        sessionFactory.currentSession.flush()

        then:
        assignmentSubmissionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        AssignmentSubmission assignmentSubmission = new AssignmentSubmission()
        assignmentSubmissionService.save(assignmentSubmission)

        then:
        assignmentSubmission.id != null
    }
}
