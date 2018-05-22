package volp

import grails.gorm.services.Service

@Service(DifficultyLevel)
interface DifficultyLevelService {

    DifficultyLevel get(Serializable id)

    List<DifficultyLevel> list(Map args)

    Long count()

    void delete(Serializable id)

    DifficultyLevel save(DifficultyLevel difficultyLevel)

}