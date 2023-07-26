<!-- show.gsp -->
<!DOCTYPE html>
<g:render template="/templates/head" />
<html>

<body>
    <div class="container">
        <h1 style="margin:10px">Hola ${currentUserName}!</h1>
        Te has postulado para el rol <strong>${postulation.role.name}</strong> en el proyecto <strong>${postulation.projectName}</strong> de <strong>${postulationProjectUserName}</strong><br>
        <g:if test="${postulation.state.toString() == 'REJECTED'}">
            Lo sentimos. Tu postulación fue rechazada.
        </g:if>
        <g:elseif test="${postulation.state.toString() == 'WAITING_LIST'}">
            El tope de cupos ha sido ocupado. Tu postulación fue puesta en <strong>lista de espera</strong>.
        </g:elseif>
        <g:elseif test="${postulation.state.toString() == 'WAITING_PREMIUM'}">
            El tope de cupos ha sido ocupado y no hay lista de espera. Tu postulación fue puesta en espera premium.
        </g:elseif>
        <g:else>
            Felicitaciones. Tu postulación ha sido aceptada.
        </g:else>
        <g:link controller="user" action="showProfile" params="[dni: dni]">
            Volver al Perfil
        </g:link>
    </div>
</body>

</html>