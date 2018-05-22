package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DepartmentServiceSpec extends Specification {

    DepartmentService departmentService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Department(...).save(flush: true, failOnError: true)
        //new Department(...).save(flush: true, failOnError: true)
        //Department department = new Department(...).save(flush: true, failOnError: true)
        //new Department(...).save(flush: true, failOnError: true)
        //new Department(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //department.id
    }

    void "test get"() {
        setupData()

        expect:
        departmentService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Department> departmentList = departmentService.list(max: 2, offset: 2)

        then:
        departmentList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        departmentService.count() == 5
    }

    void "test delete"() {
        Long departmentId = setupData()

        expect:
        departmentService.count() == 5

        when:
        departmentService.delete(departmentId)
        sessionFactory.currentSession.flush()

        then:
        departmentService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Department department = new Department()
        departmentService.save(department)

        then:
        department.id != null
    }
}
