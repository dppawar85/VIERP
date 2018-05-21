package volp

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import spock.lang.*

class InitialDataControllerSpec extends Specification implements ControllerUnitTest<InitialDataController>, DomainUnitTest<InitialData> {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.initialDataService = Mock(InitialDataService) {
            1 * list(_) >> []
            1 * count() >> 0
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.initialDataList
        model.initialDataCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.initialData!= null
    }

    void "Test the save action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/initialData/index'
        flash.message != null
    }

    void "Test the save action correctly persists"() {
        given:
        controller.initialDataService = Mock(InitialDataService) {
            1 * save(_ as InitialData)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        def initialData = new InitialData(params)
        initialData.id = 1

        controller.save(initialData)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/initialData/show/1'
        controller.flash.message != null
    }

    void "Test the save action with an invalid instance"() {
        given:
        controller.initialDataService = Mock(InitialDataService) {
            1 * save(_ as InitialData) >> { InitialData initialData ->
                throw new ValidationException("Invalid instance", initialData.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def initialData = new InitialData()
        controller.save(initialData)

        then:"The create view is rendered again with the correct model"
        model.initialData != null
        view == 'create'
    }

    void "Test the show action with a null id"() {
        given:
        controller.initialDataService = Mock(InitialDataService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.initialDataService = Mock(InitialDataService) {
            1 * get(2) >> new InitialData()
        }

        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"A model is populated containing the domain instance"
        model.initialData instanceof InitialData
    }

    void "Test the edit action with a null id"() {
        given:
        controller.initialDataService = Mock(InitialDataService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.initialDataService = Mock(InitialDataService) {
            1 * get(2) >> new InitialData()
        }

        when:"A domain instance is passed to the show action"
        controller.edit(2)

        then:"A model is populated containing the domain instance"
        model.initialData instanceof InitialData
    }


    void "Test the update action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/initialData/index'
        flash.message != null
    }

    void "Test the update action correctly persists"() {
        given:
        controller.initialDataService = Mock(InitialDataService) {
            1 * save(_ as InitialData)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)
        def initialData = new InitialData(params)
        initialData.id = 1

        controller.update(initialData)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/initialData/show/1'
        controller.flash.message != null
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.initialDataService = Mock(InitialDataService) {
            1 * save(_ as InitialData) >> { InitialData initialData ->
                throw new ValidationException("Invalid instance", initialData.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(new InitialData())

        then:"The edit view is rendered again with the correct model"
        model.initialData != null
        view == 'edit'
    }

    void "Test the delete action with a null instance"() {
        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then:"A 404 is returned"
        response.redirectedUrl == '/initialData/index'
        flash.message != null
    }

    void "Test the delete action with an instance"() {
        given:
        controller.initialDataService = Mock(InitialDataService) {
            1 * delete(2)
        }

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then:"The user is redirected to index"
        response.redirectedUrl == '/initialData/index'
        flash.message != null
    }
}






