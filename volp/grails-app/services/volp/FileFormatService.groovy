package volp

import grails.gorm.services.Service

@Service(FileFormat)
interface FileFormatService {

    FileFormat get(Serializable id)

    List<FileFormat> list(Map args)

    Long count()

    void delete(Serializable id)

    FileFormat save(FileFormat fileFormat)

}