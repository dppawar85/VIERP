package volp

import grails.gorm.services.Service

@Service(FolderPath)
interface FolderPathService {

    FolderPath get(Serializable id)

    List<FolderPath> list(Map args)

    Long count()

    void delete(Serializable id)

    FolderPath save(FolderPath folderPath)

}