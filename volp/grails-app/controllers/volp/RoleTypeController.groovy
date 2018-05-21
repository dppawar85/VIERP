package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class RoleTypeController {

    RoleTypeService roleTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond roleTypeService.list(params), model:[roleTypeCount: roleTypeService.count()]
    }

    def show(Long id) {
        respond roleTypeService.get(id)
    }

    def create() {
        respond new RoleType(params)
    }

    def save(RoleType roleType) {
        if (roleType == null) {
            notFound()
            return
        }

        try {
            roleTypeService.save(roleType)
        } catch (ValidationException e) {
            respond roleType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'roleType.label', default: 'RoleType'), roleType.id])
                redirect roleType
            }
            '*' { respond roleType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond roleTypeService.get(id)
    }

    def update(RoleType roleType) {
        if (roleType == null) {
            notFound()
            return
        }

        try {
            roleTypeService.save(roleType)
        } catch (ValidationException e) {
            respond roleType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'roleType.label', default: 'RoleType'), roleType.id])
                redirect roleType
            }
            '*'{ respond roleType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        roleTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'roleType.label', default: 'RoleType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'roleType.label', default: 'RoleType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
