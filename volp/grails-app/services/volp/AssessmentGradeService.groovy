package volp

import grails.gorm.services.Service

@Service(AssessmentGrade)
interface AssessmentGradeService {

    AssessmentGrade get(Serializable id)

    List<AssessmentGrade> list(Map args)

    Long count()

    void delete(Serializable id)

    AssessmentGrade save(AssessmentGrade assessmentGrade)

}