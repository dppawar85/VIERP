package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ApplicationTypeServiceSpec extends Specification {

    ApplicationTypeService applicationTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ApplicationType(...).save(flush: true, failOnError: true)
        //new ApplicationType(...).save(flush: true, failOnError: true)
        //ApplicationType applicationType = new ApplicationType(...).save(flush: true, failOnError: true)
        //new ApplicationType(...).save(flush: true, failOnError: true)
        //new ApplicationType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //applicationType.id
    }

    void "test get"() {
        setupData()

        expect:
        applicationTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ApplicationType> applicationTypeList = applicationTypeService.list(max: 2, offset: 2)

        then:
        applicationTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        applicationTypeService.count() == 5
    }

    void "test delete"() {
        Long applicationTypeId = setupData()

        expect:
        applicationTypeService.count() == 5

        when:
        applicationTypeService.delete(applicationTypeId)
        sessionFactory.currentSession.flush()

        then:
        applicationTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ApplicationType applicationType = new ApplicationType()
        applicationTypeService.save(applicationType)

        then:
        applicationType.id != null
    }
}
