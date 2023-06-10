package crearte

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        
        "/"(view:"/index")
        "/login-user"(view:"/user/logUser")
        "/create-role"(view:"/role/create")
        "/create-project"(view:"/project/create")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }

}
