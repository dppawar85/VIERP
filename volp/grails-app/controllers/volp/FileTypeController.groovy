package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FileTypeController {

    FileTypeService fileTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond fileTypeService.list(params), model:[fileTypeCount: fileTypeService.count()]
    }

    def show(Long id) {
        respond fileTypeService.get(id)
    }

    def create() {
        respond new FileType(params)
    }

    def save(FileType fileType) {
        if (fileType == null) {
            notFound()
            return
        }

        try {
            fileTypeService.save(fileType)
        } catch (ValidationException e) {
            respond fileType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fileType.label', default: 'FileType'), fileType.id])
                redirect fileType
            }
            '*' { respond fileType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond fileTypeService.get(id)
    }

    def update(FileType fileType) {
        if (fileType == null) {
            notFound()
            return
        }

        try {
            fileTypeService.save(fileType)
        } catch (ValidationException e) {
            respond fileType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'fileType.label', default: 'FileType'), fileType.id])
                redirect fileType
            }
            '*'{ respond fileType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        fileTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'fileType.label', default: 'FileType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fileType.label', default: 'FileType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
