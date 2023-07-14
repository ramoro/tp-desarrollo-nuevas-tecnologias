<!-- listNotifications.gsp -->
<!DOCTYPE html>
<g:render template="/templates/head" />
<html>

<body>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1>Notificaciones</h1>
                <div class="list-group">
                    <g:each in="${notifications}" var="notification">
                        <div class="alert alert-info" role="alert">
                        ${notification}
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