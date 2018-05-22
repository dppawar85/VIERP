package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseCertificateServiceSpec extends Specification {

    CourseCertificateService courseCertificateService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseCertificate(...).save(flush: true, failOnError: true)
        //new CourseCertificate(...).save(flush: true, failOnError: true)
        //CourseCertificate courseCertificate = new CourseCertificate(...).save(flush: true, failOnError: true)
        //new CourseCertificate(...).save(flush: true, failOnError: true)
        //new CourseCertificate(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseCertificate.id
    }

    void "test get"() {
        setupData()

        expect:
        courseCertificateService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseCertificate> courseCertificateList = courseCertificateService.list(max: 2, offset: 2)

        then:
        courseCertificateList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseCertificateService.count() == 5
    }

    void "test delete"() {
        Long courseCertificateId = setupData()

        expect:
        courseCertificateService.count() == 5

        when:
        courseCertificateService.delete(courseCertificateId)
        sessionFactory.currentSession.flush()

        then:
        courseCertificateService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseCertificate courseCertificate = new CourseCertificate()
        courseCertificateService.save(courseCertificate)

        then:
        courseCertificate.id != null
    }
}
