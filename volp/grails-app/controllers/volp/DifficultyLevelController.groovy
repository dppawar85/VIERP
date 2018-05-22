package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DifficultyLevelController {

    DifficultyLevelService difficultyLevelService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond difficultyLevelService.list(params), model:[difficultyLevelCount: difficultyLevelService.count()]
    }

    def show(Long id) {
        respond difficultyLevelService.get(id)
    }

    def create() {
        respond new DifficultyLevel(params)
    }

    def save(DifficultyLevel difficultyLevel) {
        if (difficultyLevel == null) {
            notFound()
            return
        }

        try {
            difficultyLevelService.save(difficultyLevel)
        } catch (ValidationException e) {
            respond difficultyLevel.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'difficultyLevel.label', default: 'DifficultyLevel'), difficultyLevel.id])
                redirect difficultyLevel
            }
            '*' { respond difficultyLevel, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond difficultyLevelService.get(id)
    }

    def update(DifficultyLevel difficultyLevel) {
        if (difficultyLevel == null) {
            notFound()
            return
        }

        try {
            difficultyLevelService.save(difficultyLevel)
        } catch (ValidationException e) {
            respond difficultyLevel.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'difficultyLevel.label', default: 'DifficultyLevel'), difficultyLevel.id])
                redirect difficultyLevel
            }
            '*'{ respond difficultyLevel, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        difficultyLevelService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'difficultyLevel.label', default: 'DifficultyLevel'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'difficultyLevel.label', default: 'DifficultyLevel'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
