package redwine

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        // "/"(view:"/index")
        "/"(controller: "proyecto", action: "index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
