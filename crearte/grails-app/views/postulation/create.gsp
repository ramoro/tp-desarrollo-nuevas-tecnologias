<!-- show.gsp -->
<!DOCTYPE html>
<g:render template="/templates/head" />
<html>

<body>
    <div class="container">
        <h1 style="margin:10px">Hola ${currentUserName}!</h1>
        Te has postulado para el rol <strong>${postulation.roleName}</strong> en el proyecto <strong>${postulation.projectName}</strong> de <strong>${postulationProjectUserName}</strong><br>
        <g:if test="${postulation.state.toString() == 'PENDING'}">
            Tu postulación se encuentra <strong>pendiente</strong>. Te notificaremos cuando ...
        </g:if>
        <g:elseif test="${postulation.state.toString() == 'REJECTED'}">
            Lo sentimos. Tu postulación fue rechazada.
        </g:elseif>
        <g:elseif test="${postulation.state.toString() == 'WAITING_LIST'}">
            El tope de cupos ha sido ocupado. Tu postulación fue puesta en lista de espera.
        </g:elseif>
        <g:elseif test="${postulation.state.toString() == 'WAITING_PREMIUM'}">
            El tope de cupos ha sido ocupado y no hay lista de espera. Tu postulación fue puesta en espera premium.
        </g:elseif>
        <g:else>
            Felicitaciones. Tu postulación ha sido aceptada.
        </g:else>
    </div>
</body>

</html>