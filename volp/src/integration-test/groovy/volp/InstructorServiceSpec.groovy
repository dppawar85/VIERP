package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class InstructorServiceSpec extends Specification {

    InstructorService instructorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Instructor(...).save(flush: true, failOnError: true)
        //new Instructor(...).save(flush: true, failOnError: true)
        //Instructor instructor = new Instructor(...).save(flush: true, failOnError: true)
        //new Instructor(...).save(flush: true, failOnError: true)
        //new Instructor(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //instructor.id
    }

    void "test get"() {
        setupData()

        expect:
        instructorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Instructor> instructorList = instructorService.list(max: 2, offset: 2)

        then:
        instructorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        instructorService.count() == 5
    }

    void "test delete"() {
        Long instructorId = setupData()

        expect:
        instructorService.count() == 5

        when:
        instructorService.delete(instructorId)
        sessionFactory.currentSession.flush()

        then:
        instructorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Instructor instructor = new Instructor()
        instructorService.save(instructor)

        then:
        instructor.id != null
    }
}
