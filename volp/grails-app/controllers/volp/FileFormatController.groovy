package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FileFormatController {

    FileFormatService fileFormatService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond fileFormatService.list(params), model:[fileFormatCount: fileFormatService.count()]
    }

    def show(Long id) {
        respond fileFormatService.get(id)
    }

    def create() {
        respond new FileFormat(params)
    }

    def save(FileFormat fileFormat) {
        if (fileFormat == null) {
            notFound()
            return
        }

        try {
            fileFormatService.save(fileFormat)
        } catch (ValidationException e) {
            respond fileFormat.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fileFormat.label', default: 'FileFormat'), fileFormat.id])
                redirect fileFormat
            }
            '*' { respond fileFormat, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond fileFormatService.get(id)
    }

    def update(FileFormat fileFormat) {
        if (fileFormat == null) {
            notFound()
            return
        }

        try {
            fileFormatService.save(fileFormat)
        } catch (ValidationException e) {
            respond fileFormat.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'fileFormat.label', default: 'FileFormat'), fileFormat.id])
                redirect fileFormat
            }
            '*'{ respond fileFormat, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        fileFormatService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'fileFormat.label', default: 'FileFormat'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fileFormat.label', default: 'FileFormat'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
