package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class InitialDataServiceSpec extends Specification {

    InitialDataService initialDataService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new InitialData(...).save(flush: true, failOnError: true)
        //new InitialData(...).save(flush: true, failOnError: true)
        //InitialData initialData = new InitialData(...).save(flush: true, failOnError: true)
        //new InitialData(...).save(flush: true, failOnError: true)
        //new InitialData(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //initialData.id
    }

    void "test get"() {
        setupData()

        expect:
        initialDataService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<InitialData> initialDataList = initialDataService.list(max: 2, offset: 2)

        then:
        initialDataList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        initialDataService.count() == 5
    }

    void "test delete"() {
        Long initialDataId = setupData()

        expect:
        initialDataService.count() == 5

        when:
        initialDataService.delete(initialDataId)
        sessionFactory.currentSession.flush()

        then:
        initialDataService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        InitialData initialData = new InitialData()
        initialDataService.save(initialData)

        then:
        initialData.id != null
    }
}
