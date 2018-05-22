package volp

import grails.gorm.services.Service

@Service(CourseFees)
interface CourseFeesService {

    CourseFees get(Serializable id)

    List<CourseFees> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseFees save(CourseFees courseFees)

}