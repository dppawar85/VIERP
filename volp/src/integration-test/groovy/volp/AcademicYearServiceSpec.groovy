package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AcademicYearServiceSpec extends Specification {

    AcademicYearService academicYearService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new AcademicYear(...).save(flush: true, failOnError: true)
        //new AcademicYear(...).save(flush: true, failOnError: true)
        //AcademicYear academicYear = new AcademicYear(...).save(flush: true, failOnError: true)
        //new AcademicYear(...).save(flush: true, failOnError: true)
        //new AcademicYear(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //academicYear.id
    }

    void "test get"() {
        setupData()

        expect:
        academicYearService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<AcademicYear> academicYearList = academicYearService.list(max: 2, offset: 2)

        then:
        academicYearList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        academicYearService.count() == 5
    }

    void "test delete"() {
        Long academicYearId = setupData()

        expect:
        academicYearService.count() == 5

        when:
        academicYearService.delete(academicYearId)
        sessionFactory.currentSession.flush()

        then:
        academicYearService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        AcademicYear academicYear = new AcademicYear()
        academicYearService.save(academicYear)

        then:
        academicYear.id != null
    }
}
