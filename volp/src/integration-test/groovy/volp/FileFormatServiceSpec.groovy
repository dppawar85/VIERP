package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FileFormatServiceSpec extends Specification {

    FileFormatService fileFormatService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new FileFormat(...).save(flush: true, failOnError: true)
        //new FileFormat(...).save(flush: true, failOnError: true)
        //FileFormat fileFormat = new FileFormat(...).save(flush: true, failOnError: true)
        //new FileFormat(...).save(flush: true, failOnError: true)
        //new FileFormat(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //fileFormat.id
    }

    void "test get"() {
        setupData()

        expect:
        fileFormatService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<FileFormat> fileFormatList = fileFormatService.list(max: 2, offset: 2)

        then:
        fileFormatList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        fileFormatService.count() == 5
    }

    void "test delete"() {
        Long fileFormatId = setupData()

        expect:
        fileFormatService.count() == 5

        when:
        fileFormatService.delete(fileFormatId)
        sessionFactory.currentSession.flush()

        then:
        fileFormatService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        FileFormat fileFormat = new FileFormat()
        fileFormatService.save(fileFormat)

        then:
        fileFormat.id != null
    }
}
