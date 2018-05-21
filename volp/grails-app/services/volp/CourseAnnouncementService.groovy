package volp

import grails.gorm.services.Service

@Service(CourseAnnouncement)
interface CourseAnnouncementService {

    CourseAnnouncement get(Serializable id)

    List<CourseAnnouncement> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseAnnouncement save(CourseAnnouncement courseAnnouncement)

}