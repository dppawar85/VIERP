package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EnquiryServiceSpec extends Specification {

    EnquiryService enquiryService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Enquiry(...).save(flush: true, failOnError: true)
        //new Enquiry(...).save(flush: true, failOnError: true)
        //Enquiry enquiry = new Enquiry(...).save(flush: true, failOnError: true)
        //new Enquiry(...).save(flush: true, failOnError: true)
        //new Enquiry(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //enquiry.id
    }

    void "test get"() {
        setupData()

        expect:
        enquiryService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Enquiry> enquiryList = enquiryService.list(max: 2, offset: 2)

        then:
        enquiryList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        enquiryService.count() == 5
    }

    void "test delete"() {
        Long enquiryId = setupData()

        expect:
        enquiryService.count() == 5

        when:
        enquiryService.delete(enquiryId)
        sessionFactory.currentSession.flush()

        then:
        enquiryService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Enquiry enquiry = new Enquiry()
        enquiryService.save(enquiry)

        then:
        enquiry.id != null
    }
}
