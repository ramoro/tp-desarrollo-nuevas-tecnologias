<!-- show.gsp -->
<!DOCTYPE html>
<g:render template="/templates/head" />
<html>

<body>
    <div class="container">
        <h1 style="margin:10px">Proyecto <strong> ${project.name} </strong></h1>
        <div class="row">
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-header bg-custom">
                        <h2>Descripción</h2>
                    </div>
                    <div class="card-body">
                        <p>${project.description}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-header bg-custom">
                        <h2>Roles</h2>
                    </div>
                    <div class="card-body">
                        <g:if test="${project.roles}">
                            <ul>
                                <g:each in="${project.roles}" var="role">


                                    <li>
                                        <g:form controller="postulation" action="create">
                                            ${role.name}
                                            
                                            <g:hiddenField name="projectName" value="${params.name}" />
                                            <g:hiddenField name="ownerDni" value="${params.dni}" />
                                            <g:hiddenField name="roleName" value="${role.name}" />
                                            <div class="form-group">
                                                <g:submitButton name="Postularse" value="Postularse"
                                                    class="btn btn-primary" />
                                            </div>
                                        </g:form>
                                    </li>

                                </g:each>
                            </ul>
                        </g:if>
                        <g:else>
                            <p class="text-muted">No existen roles para este proyecto.</p>
                        </g:else>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>