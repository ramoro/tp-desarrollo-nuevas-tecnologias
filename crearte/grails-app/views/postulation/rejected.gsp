<!-- show.gsp -->
<!DOCTYPE html>
<g:render template="/templates/head" />
<html>

<body>
    <div class="container">
        <h1 style="margin:10px">Hola ${currentUserName}!</h1>
        Lo sentimos. Ya no hay mas cupos para este rol<br>
        <g:link controller="user" action="showProfile" params="[dni: dni]">
            Volver al Perfil
        </g:link>
    </div>
</body>

</html>