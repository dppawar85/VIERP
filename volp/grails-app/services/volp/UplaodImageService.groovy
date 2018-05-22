package volp

import grails.gorm.transactions.Transactional
import grails.web.context.ServletContextHolder
import org.springframework.web.multipart.*;

@Transactional
class UplaodImageService {

    def serviceMethod() {

    }
    def uploadFile( MultipartFile file, String name, String destinationDirectory ) {

        def serveletContext = ServletContextHolder.servletContext
        def storagePath = serveletContext.getRealPath( destinationDirectory )

        def storagePathDirectory = new File( storagePath )

        if( !storagePathDirectory.exists() ){
            println("creating directory ${storagePath}")
            if(storagePathDirectory.mkdirs()){
                println "SUCCESS"
            }else{
                println "FAILED"
            }
        }

        // Store file

        if(!file.isEmpty()){
            file.transferTo( new File("${storagePath}/${name}") )
            println("Saved File: ${storagePath}/${name}")
            return "${storagePath}/${name}"
        }else{
            println "File: ${file.inspect()} was empty"
            return null
        }
    }
}
