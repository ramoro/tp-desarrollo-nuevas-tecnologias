package crearte

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/create-role"(view:"/role/create")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
