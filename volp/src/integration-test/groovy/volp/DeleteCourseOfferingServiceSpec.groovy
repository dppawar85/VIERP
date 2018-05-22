package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DeleteCourseOfferingServiceSpec extends Specification {

    DeleteCourseOfferingService deleteCourseOfferingService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DeleteCourseOffering(...).save(flush: true, failOnError: true)
        //new DeleteCourseOffering(...).save(flush: true, failOnError: true)
        //DeleteCourseOffering deleteCourseOffering = new DeleteCourseOffering(...).save(flush: true, failOnError: true)
        //new DeleteCourseOffering(...).save(flush: true, failOnError: true)
        //new DeleteCourseOffering(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //deleteCourseOffering.id
    }

    void "test get"() {
        setupData()

        expect:
        deleteCourseOfferingService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DeleteCourseOffering> deleteCourseOfferingList = deleteCourseOfferingService.list(max: 2, offset: 2)

        then:
        deleteCourseOfferingList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        deleteCourseOfferingService.count() == 5
    }

    void "test delete"() {
        Long deleteCourseOfferingId = setupData()

        expect:
        deleteCourseOfferingService.count() == 5

        when:
        deleteCourseOfferingService.delete(deleteCourseOfferingId)
        sessionFactory.currentSession.flush()

        then:
        deleteCourseOfferingService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DeleteCourseOffering deleteCourseOffering = new DeleteCourseOffering()
        deleteCourseOfferingService.save(deleteCourseOffering)

        then:
        deleteCourseOffering.id != null
    }
}
