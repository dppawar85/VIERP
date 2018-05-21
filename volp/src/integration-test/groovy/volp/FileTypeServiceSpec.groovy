package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FileTypeServiceSpec extends Specification {

    FileTypeService fileTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new FileType(...).save(flush: true, failOnError: true)
        //new FileType(...).save(flush: true, failOnError: true)
        //FileType fileType = new FileType(...).save(flush: true, failOnError: true)
        //new FileType(...).save(flush: true, failOnError: true)
        //new FileType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //fileType.id
    }

    void "test get"() {
        setupData()

        expect:
        fileTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<FileType> fileTypeList = fileTypeService.list(max: 2, offset: 2)

        then:
        fileTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        fileTypeService.count() == 5
    }

    void "test delete"() {
        Long fileTypeId = setupData()

        expect:
        fileTypeService.count() == 5

        when:
        fileTypeService.delete(fileTypeId)
        sessionFactory.currentSession.flush()

        then:
        fileTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        FileType fileType = new FileType()
        fileTypeService.save(fileType)

        then:
        fileType.id != null
    }
}
