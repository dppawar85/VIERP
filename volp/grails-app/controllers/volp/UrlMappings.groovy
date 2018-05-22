package volp

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

      //  "/"(view:"/index")
      //  "/"(view:"/login/home1")

        "/" (controller: "login", action: "home1")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
