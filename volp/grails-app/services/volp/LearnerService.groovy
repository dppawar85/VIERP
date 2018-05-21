package volp

import grails.gorm.services.Service

@Service(Learner)
interface LearnerService {

    Learner get(Serializable id)

    List<Learner> list(Map args)

    Long count()

    void delete(Serializable id)

    Learner save(Learner learner)

}