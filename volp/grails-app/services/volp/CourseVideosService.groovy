package volp

import grails.gorm.services.Service

@Service(CourseVideos)
interface CourseVideosService {

    CourseVideos get(Serializable id)

    List<CourseVideos> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseVideos save(CourseVideos courseVideos)

}