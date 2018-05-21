package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SoftwareRemarkServiceSpec extends Specification {

    SoftwareRemarkService softwareRemarkService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SoftwareRemark(...).save(flush: true, failOnError: true)
        //new SoftwareRemark(...).save(flush: true, failOnError: true)
        //SoftwareRemark softwareRemark = new SoftwareRemark(...).save(flush: true, failOnError: true)
        //new SoftwareRemark(...).save(flush: true, failOnError: true)
        //new SoftwareRemark(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //softwareRemark.id
    }

    void "test get"() {
        setupData()

        expect:
        softwareRemarkService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SoftwareRemark> softwareRemarkList = softwareRemarkService.list(max: 2, offset: 2)

        then:
        softwareRemarkList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        softwareRemarkService.count() == 5
    }

    void "test delete"() {
        Long softwareRemarkId = setupData()

        expect:
        softwareRemarkService.count() == 5

        when:
        softwareRemarkService.delete(softwareRemarkId)
        sessionFactory.currentSession.flush()

        then:
        softwareRemarkService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SoftwareRemark softwareRemark = new SoftwareRemark()
        softwareRemarkService.save(softwareRemark)

        then:
        softwareRemark.id != null
    }
}
