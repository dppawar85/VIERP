package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FolderPathController {

    FolderPathService folderPathService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond folderPathService.list(params), model:[folderPathCount: folderPathService.count()]
    }

    def show(Long id) {
        respond folderPathService.get(id)
    }

    def create() {
        respond new FolderPath(params)
    }

    def save(FolderPath folderPath) {
        if (folderPath == null) {
            notFound()
            return
        }

        try {
            folderPathService.save(folderPath)
        } catch (ValidationException e) {
            respond folderPath.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'folderPath.label', default: 'FolderPath'), folderPath.id])
                redirect folderPath
            }
            '*' { respond folderPath, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond folderPathService.get(id)
    }

    def update(FolderPath folderPath) {
        if (folderPath == null) {
            notFound()
            return
        }

        try {
            folderPathService.save(folderPath)
        } catch (ValidationException e) {
            respond folderPath.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'folderPath.label', default: 'FolderPath'), folderPath.id])
                redirect folderPath
            }
            '*'{ respond folderPath, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        folderPathService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'folderPath.label', default: 'FolderPath'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'folderPath.label', default: 'FolderPath'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
