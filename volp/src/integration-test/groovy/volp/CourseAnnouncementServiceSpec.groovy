package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseAnnouncementServiceSpec extends Specification {

    CourseAnnouncementService courseAnnouncementService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseAnnouncement(...).save(flush: true, failOnError: true)
        //new CourseAnnouncement(...).save(flush: true, failOnError: true)
        //CourseAnnouncement courseAnnouncement = new CourseAnnouncement(...).save(flush: true, failOnError: true)
        //new CourseAnnouncement(...).save(flush: true, failOnError: true)
        //new CourseAnnouncement(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseAnnouncement.id
    }

    void "test get"() {
        setupData()

        expect:
        courseAnnouncementService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseAnnouncement> courseAnnouncementList = courseAnnouncementService.list(max: 2, offset: 2)

        then:
        courseAnnouncementList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseAnnouncementService.count() == 5
    }

    void "test delete"() {
        Long courseAnnouncementId = setupData()

        expect:
        courseAnnouncementService.count() == 5

        when:
        courseAnnouncementService.delete(courseAnnouncementId)
        sessionFactory.currentSession.flush()

        then:
        courseAnnouncementService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseAnnouncement courseAnnouncement = new CourseAnnouncement()
        courseAnnouncementService.save(courseAnnouncement)

        then:
        courseAnnouncement.id != null
    }
}
