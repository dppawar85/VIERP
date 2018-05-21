package volp

import grails.gorm.services.Service

@Service(ForumThreads)
interface ForumThreadsService {

    ForumThreads get(Serializable id)

    List<ForumThreads> list(Map args)

    Long count()

    void delete(Serializable id)

    ForumThreads save(ForumThreads forumThreads)

}