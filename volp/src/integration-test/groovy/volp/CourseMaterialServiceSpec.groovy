package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseMaterialServiceSpec extends Specification {

    CourseMaterialService courseMaterialService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseMaterial(...).save(flush: true, failOnError: true)
        //new CourseMaterial(...).save(flush: true, failOnError: true)
        //CourseMaterial courseMaterial = new CourseMaterial(...).save(flush: true, failOnError: true)
        //new CourseMaterial(...).save(flush: true, failOnError: true)
        //new CourseMaterial(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseMaterial.id
    }

    void "test get"() {
        setupData()

        expect:
        courseMaterialService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseMaterial> courseMaterialList = courseMaterialService.list(max: 2, offset: 2)

        then:
        courseMaterialList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseMaterialService.count() == 5
    }

    void "test delete"() {
        Long courseMaterialId = setupData()

        expect:
        courseMaterialService.count() == 5

        when:
        courseMaterialService.delete(courseMaterialId)
        sessionFactory.currentSession.flush()

        then:
        courseMaterialService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseMaterial courseMaterial = new CourseMaterial()
        courseMaterialService.save(courseMaterial)

        then:
        courseMaterial.id != null
    }
}
