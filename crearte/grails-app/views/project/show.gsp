<!-- show.gsp -->
<!DOCTYPE html>
<g:render template="/templates/head" />
<html>

<body>
    <div class="container">
        <h1 style="margin:10px">Mi proyecto <strong> ${project.name} </strong></h1>
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
                                    ${role.name}
                                    <g:if test="${role.hasLimitedSpots == true}">
                                        [Cupos Ocupados: ${role.occupiedSpots}/${role.totalSpots}]
                                    </g:if>
                                    <g:if test="${params.dni.toInteger().equals(project.ownerDni.toInteger()) && role.hasWaitingList == true}">
                                        [<g:link controller="postulation" action="deleteWaitingListFromRole" params="[roleName: role.name, projectName: project.name, dni: params.dni]">Eliminar lista de espera</g:link>]
                                    </g:if>
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
        <g:if test="${project.state.toString() == 'DRAFT'}">
            <g:form controller="project" action="publish">
                <h4>Fecha de publicacion</h4>
                <g:hiddenField name="name" value="${params.name}" />
                <g:hiddenField name="dni" value="${params.dni}" />
                <g:datePicker name="publicationDate" value="${new Date()}" precision="day" noSelection="['':'-Choose-']"
                    relativeYears="[-2..7]" />
                <h4>Fecha de expiracion</h4>
                <g:datePicker name="expirationDate" value="${new Date()}" precision="day" noSelection="['':'-Choose-']"
                    relativeYears="[-2..7]" />
                <div class="form-group" style="margin-top:10px">
                    <g:submitButton name="publicar" value="Publicar" class="btn btn-primary" />
                </div>
            </g:form>
        </g:if>
        <g:else>
            <div>Fecha de publicación: ${project.publicationDate}</div>
            <div>Fecha de expiración: ${project.expirationDate}</div>
        </g:else>
        <g:link controller="user" action="showProfile" params="[dni: params.dni]">
            Volver al Perfil
        </g:link>
    </div>
</body>

</html>