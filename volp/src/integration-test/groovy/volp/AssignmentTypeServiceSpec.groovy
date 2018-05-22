package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AssignmentTypeServiceSpec extends Specification {

    AssignmentTypeService assignmentTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new AssignmentType(...).save(flush: true, failOnError: true)
        //new AssignmentType(...).save(flush: true, failOnError: true)
        //AssignmentType assignmentType = new AssignmentType(...).save(flush: true, failOnError: true)
        //new AssignmentType(...).save(flush: true, failOnError: true)
        //new AssignmentType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //assignmentType.id
    }

    void "test get"() {
        setupData()

        expect:
        assignmentTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<AssignmentType> assignmentTypeList = assignmentTypeService.list(max: 2, offset: 2)

        then:
        assignmentTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        assignmentTypeService.count() == 5
    }

    void "test delete"() {
        Long assignmentTypeId = setupData()

        expect:
        assignmentTypeService.count() == 5

        when:
        assignmentTypeService.delete(assignmentTypeId)
        sessionFactory.currentSession.flush()

        then:
        assignmentTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        AssignmentType assignmentType = new AssignmentType()
        assignmentTypeService.save(assignmentType)

        then:
        assignmentType.id != null
    }
}
