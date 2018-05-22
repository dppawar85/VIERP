package volp

import grails.gorm.services.Service

@Service(FileType)
interface FileTypeService {

    FileType get(Serializable id)

    List<FileType> list(Map args)

    Long count()

    void delete(Serializable id)

    FileType save(FileType fileType)

}