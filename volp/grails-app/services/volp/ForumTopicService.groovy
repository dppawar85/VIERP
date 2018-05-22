package volp

import grails.gorm.services.Service

@Service(ForumTopic)
interface ForumTopicService {

    ForumTopic get(Serializable id)

    List<ForumTopic> list(Map args)

    Long count()

    void delete(Serializable id)

    ForumTopic save(ForumTopic forumTopic)

}