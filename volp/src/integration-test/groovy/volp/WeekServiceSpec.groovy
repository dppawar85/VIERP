package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class WeekServiceSpec extends Specification {

    WeekService weekService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Week(...).save(flush: true, failOnError: true)
        //new Week(...).save(flush: true, failOnError: true)
        //Week week = new Week(...).save(flush: true, failOnError: true)
        //new Week(...).save(flush: true, failOnError: true)
        //new Week(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //week.id
    }

    void "test get"() {
        setupData()

        expect:
        weekService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Week> weekList = weekService.list(max: 2, offset: 2)

        then:
        weekList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        weekService.count() == 5
    }

    void "test delete"() {
        Long weekId = setupData()

        expect:
        weekService.count() == 5

        when:
        weekService.delete(weekId)
        sessionFactory.currentSession.flush()

        then:
        weekService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Week week = new Week()
        weekService.save(week)

        then:
        week.id != null
    }
}
