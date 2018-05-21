package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserTypeController {

    UserTypeService userTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userTypeService.list(params), model:[userTypeCount: userTypeService.count()]
    }

    def show(Long id) {
        respond userTypeService.get(id)
    }

    def create() {
        respond new UserType(params)
    }

    def save(UserType userType) {
        if (userType == null) {
            notFound()
            return
        }

        try {
            userTypeService.save(userType)
        } catch (ValidationException e) {
            respond userType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userType.label', default: 'UserType'), userType.id])
                redirect userType
            }
            '*' { respond userType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userTypeService.get(id)
    }

    def update(UserType userType) {
        if (userType == null) {
            notFound()
            return
        }

        try {
            userTypeService.save(userType)
        } catch (ValidationException e) {
            respond userType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userType.label', default: 'UserType'), userType.id])
                redirect userType
            }
            '*'{ respond userType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userType.label', default: 'UserType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userType.label', default: 'UserType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
