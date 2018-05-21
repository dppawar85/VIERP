package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RoleTypeServiceSpec extends Specification {

    RoleTypeService roleTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new RoleType(...).save(flush: true, failOnError: true)
        //new RoleType(...).save(flush: true, failOnError: true)
        //RoleType roleType = new RoleType(...).save(flush: true, failOnError: true)
        //new RoleType(...).save(flush: true, failOnError: true)
        //new RoleType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //roleType.id
    }

    void "test get"() {
        setupData()

        expect:
        roleTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<RoleType> roleTypeList = roleTypeService.list(max: 2, offset: 2)

        then:
        roleTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        roleTypeService.count() == 5
    }

    void "test delete"() {
        Long roleTypeId = setupData()

        expect:
        roleTypeService.count() == 5

        when:
        roleTypeService.delete(roleTypeId)
        sessionFactory.currentSession.flush()

        then:
        roleTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        RoleType roleType = new RoleType()
        roleTypeService.save(roleType)

        then:
        roleType.id != null
    }
}
