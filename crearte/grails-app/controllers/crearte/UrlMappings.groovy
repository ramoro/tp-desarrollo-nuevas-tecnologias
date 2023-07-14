package crearte

class UrlMappings {

    static mappings = {
        "/notify-projects/$date?"(controller: "Project", action: "notifyIfNecessary")

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        
        "/"(view:"/index")
        "/login-user"(view:"/user/logUser")
        "/create-role"(view:"/role/create")
        "/create-project"(view:"/project/create")
        "/show-project" (view: "/project/show")
        "/list-projects" (view: "/project/listProjects")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }

}
