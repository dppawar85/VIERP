package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FolderPathServiceSpec extends Specification {

    FolderPathService folderPathService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new FolderPath(...).save(flush: true, failOnError: true)
        //new FolderPath(...).save(flush: true, failOnError: true)
        //FolderPath folderPath = new FolderPath(...).save(flush: true, failOnError: true)
        //new FolderPath(...).save(flush: true, failOnError: true)
        //new FolderPath(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //folderPath.id
    }

    void "test get"() {
        setupData()

        expect:
        folderPathService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<FolderPath> folderPathList = folderPathService.list(max: 2, offset: 2)

        then:
        folderPathList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        folderPathService.count() == 5
    }

    void "test delete"() {
        Long folderPathId = setupData()

        expect:
        folderPathService.count() == 5

        when:
        folderPathService.delete(folderPathId)
        sessionFactory.currentSession.flush()

        then:
        folderPathService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        FolderPath folderPath = new FolderPath()
        folderPathService.save(folderPath)

        then:
        folderPath.id != null
    }
}
