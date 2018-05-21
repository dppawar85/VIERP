package volp

import grails.gorm.services.Service

@Service(AssignmentSubmission)
interface AssignmentSubmissionService {

    AssignmentSubmission get(Serializable id)

    List<AssignmentSubmission> list(Map args)

    Long count()

    void delete(Serializable id)

    AssignmentSubmission save(AssignmentSubmission assignmentSubmission)

}