<!-- listProjects.gsp -->
<!DOCTYPE html>
<g:render template="/templates/head"  />
<html>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1>Tus proyectos</h1>
                <div class="list-group">
                    <g:each in="${sortedProjects}" var="project">
                        <div class="card mb-3 shadow-sm rounded">
                            <div class="card-body">
                                <h5 class="card-title"><a href="${g.createLink(controller: 'project', action: 'show', params: [name: project.name])}">${project.name}</a></h5>
                                <a href="${g.createLink(controller: 'role', action: 'create', params: [projectName: project.name])}">+ Crear rol</a>
                            </div>
                        </div>
                    </g:each>
                </div>
            </div>
        </d
</html>

<style>
    .card {
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        margin-bottom: 10px;
    }
</style>