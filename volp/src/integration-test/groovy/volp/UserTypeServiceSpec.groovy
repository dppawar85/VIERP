package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserTypeServiceSpec extends Specification {

    UserTypeService userTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UserType(...).save(flush: true, failOnError: true)
        //new UserType(...).save(flush: true, failOnError: true)
        //UserType userType = new UserType(...).save(flush: true, failOnError: true)
        //new UserType(...).save(flush: true, failOnError: true)
        //new UserType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //userType.id
    }

    void "test get"() {
        setupData()

        expect:
        userTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UserType> userTypeList = userTypeService.list(max: 2, offset: 2)

        then:
        userTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        userTypeService.count() == 5
    }

    void "test delete"() {
        Long userTypeId = setupData()

        expect:
        userTypeService.count() == 5

        when:
        userTypeService.delete(userTypeId)
        sessionFactory.currentSession.flush()

        then:
        userTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UserType userType = new UserType()
        userTypeService.save(userType)

        then:
        userType.id != null
    }
}
