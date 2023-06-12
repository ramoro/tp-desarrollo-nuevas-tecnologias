<!-- listProjects.gsp -->
<!DOCTYPE html>
<g:render template="/templates/head"  />
<html>
<body>
    <div class="container">
     <div class="row">
        <div class="col-md-6">
            <h1>Tus proyectos</h1>
            <div class="shadow p-3 mb-5 bg-white rounded">
                <ul class="list-group">
                    <g:each in="${sortedProjects}" var="project">
                        <a href="#" class="list-group-item list-group-item-action">${project.name}</a>
                    </g:each>
                </ul>
            </div>
        </div>    
     </div>
    </div>
</body>
</html>