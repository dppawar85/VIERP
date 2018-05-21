package volp

import grails.gorm.services.Service

@Service(AcademicYear)
interface AcademicYearService {

    AcademicYear get(Serializable id)

    List<AcademicYear> list(Map args)

    Long count()

    void delete(Serializable id)

    AcademicYear save(AcademicYear academicYear)

}