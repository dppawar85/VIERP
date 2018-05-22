package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DifficultyLevelServiceSpec extends Specification {

    DifficultyLevelService difficultyLevelService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DifficultyLevel(...).save(flush: true, failOnError: true)
        //new DifficultyLevel(...).save(flush: true, failOnError: true)
        //DifficultyLevel difficultyLevel = new DifficultyLevel(...).save(flush: true, failOnError: true)
        //new DifficultyLevel(...).save(flush: true, failOnError: true)
        //new DifficultyLevel(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //difficultyLevel.id
    }

    void "test get"() {
        setupData()

        expect:
        difficultyLevelService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DifficultyLevel> difficultyLevelList = difficultyLevelService.list(max: 2, offset: 2)

        then:
        difficultyLevelList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        difficultyLevelService.count() == 5
    }

    void "test delete"() {
        Long difficultyLevelId = setupData()

        expect:
        difficultyLevelService.count() == 5

        when:
        difficultyLevelService.delete(difficultyLevelId)
        sessionFactory.currentSession.flush()

        then:
        difficultyLevelService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DifficultyLevel difficultyLevel = new DifficultyLevel()
        difficultyLevelService.save(difficultyLevel)

        then:
        difficultyLevel.id != null
    }
}
