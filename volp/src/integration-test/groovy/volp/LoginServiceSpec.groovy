package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class LoginServiceSpec extends Specification {

    LoginService loginService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Login(...).save(flush: true, failOnError: true)
        //new Login(...).save(flush: true, failOnError: true)
        //Login login = new Login(...).save(flush: true, failOnError: true)
        //new Login(...).save(flush: true, failOnError: true)
        //new Login(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //login.id
    }

    void "test get"() {
        setupData()

        expect:
        loginService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Login> loginList = loginService.list(max: 2, offset: 2)

        then:
        loginList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        loginService.count() == 5
    }

    void "test delete"() {
        Long loginId = setupData()

        expect:
        loginService.count() == 5

        when:
        loginService.delete(loginId)
        sessionFactory.currentSession.flush()

        then:
        loginService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Login login = new Login()
        loginService.save(login)

        then:
        login.id != null
    }
}
