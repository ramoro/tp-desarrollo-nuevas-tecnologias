<!-- show.gsp -->
<!DOCTYPE html>
<g:render template="/templates/head" />
<html>

<body>
    <div class="container">
        <h1 style="margin:10px">Hola ${postulation.user.name}!</h1>
        Te has postulado para el rol <strong>${postulation.role.name}</strong> en el proyecto <strong>${postulation.project.name}</strong> de <strong>${postulationProjectUser.name}</strong><br>
        <g:if test="${postulation.state == 'PENDING'}">
            Tu postulación se encuentra <strong>pendiente</strong>. Te notificaremos cuando ...
        </g:if>
        <g:elseif test="${postulation.state == 'REJECTED'}">
            Lo sentimos. Tu postulación fue rechazada.
        </g:elseif>
        <g:else>
            Felicitaciones. Tu postulación ha sido aceptada.
        </g:else>
    </div>
</body>

</html>