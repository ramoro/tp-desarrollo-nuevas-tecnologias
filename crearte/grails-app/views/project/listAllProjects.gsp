<!-- listProjects.gsp -->
<!DOCTYPE html>
<g:render template="/templates/head" />
<html>

<body>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1>Proyectos publicados</h1>
                <div class="list-group">
                    <g:each in="${projects}" var="project">
                        <div class="card mb-3 shadow-sm rounded">
                            <div class="card-body">
                                <h5 class="card-title"><a
                                        href="${g.createLink(controller: 'project', action: 'showOtherProject', params: [name: project.name])}">${project.name}</a>
                                </h5>
                            </div>
                        </div>
                    </g:each>
                </div>
            </div>
        </div>
        <g:link controller="user" action="showProfile" params="[dni: params.dni]">
            Volver al Perfil
        </g:link>
    </div>
</body>

</html>