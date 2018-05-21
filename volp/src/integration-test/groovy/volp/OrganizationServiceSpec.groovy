package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class OrganizationServiceSpec extends Specification {

    OrganizationService organizationService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Organization(...).save(flush: true, failOnError: true)
        //new Organization(...).save(flush: true, failOnError: true)
        //Organization organization = new Organization(...).save(flush: true, failOnError: true)
        //new Organization(...).save(flush: true, failOnError: true)
        //new Organization(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //organization.id
    }

    void "test get"() {
        setupData()

        expect:
        organizationService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Organization> organizationList = organizationService.list(max: 2, offset: 2)

        then:
        organizationList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        organizationService.count() == 5
    }

    void "test delete"() {
        Long organizationId = setupData()

        expect:
        organizationService.count() == 5

        when:
        organizationService.delete(organizationId)
        sessionFactory.currentSession.flush()

        then:
        organizationService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Organization organization = new Organization()
        organizationService.save(organization)

        then:
        organization.id != null
    }
}
