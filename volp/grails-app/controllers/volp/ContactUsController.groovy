package volp

class ContactUsController {

    def index() { }
    def saveContactDetails(){
        println("I am in save Contact Details"+params)
        def contact=new ContactUs()
        contact.name=params.name
        contact.email=params.email
        contact.message=params.message
        contact.save(flush: true,failOnError :true)
        [msg:"Thank you! we will get back to you soon...!"]

    }
}
