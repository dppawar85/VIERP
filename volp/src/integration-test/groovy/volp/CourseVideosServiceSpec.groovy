package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseVideosServiceSpec extends Specification {

    CourseVideosService courseVideosService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseVideos(...).save(flush: true, failOnError: true)
        //new CourseVideos(...).save(flush: true, failOnError: true)
        //CourseVideos courseVideos = new CourseVideos(...).save(flush: true, failOnError: true)
        //new CourseVideos(...).save(flush: true, failOnError: true)
        //new CourseVideos(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseVideos.id
    }

    void "test get"() {
        setupData()

        expect:
        courseVideosService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseVideos> courseVideosList = courseVideosService.list(max: 2, offset: 2)

        then:
        courseVideosList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseVideosService.count() == 5
    }

    void "test delete"() {
        Long courseVideosId = setupData()

        expect:
        courseVideosService.count() == 5

        when:
        courseVideosService.delete(courseVideosId)
        sessionFactory.currentSession.flush()

        then:
        courseVideosService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseVideos courseVideos = new CourseVideos()
        courseVideosService.save(courseVideos)

        then:
        courseVideos.id != null
    }
}
