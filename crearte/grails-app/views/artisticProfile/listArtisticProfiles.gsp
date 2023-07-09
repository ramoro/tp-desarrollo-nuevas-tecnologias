<!-- listArtisticProfiles.gsp -->
<!DOCTYPE html>
<g:render template="/templates/head"  />
<html>

<body>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1>Tus perfiles artísticos</h1>
                <div class="list-group">
                    <g:each in="${artisticProfiles}" var="artisticProfile">
                        <div class="card mb-3 shadow-sm rounded">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <a href="${g.createLink(controller: 'artisticProfile', action: 'show', params: [name: artisticProfile.artisticName])}">${artisticProfile.artisticName}</a>
                                </h5>
                                <!-- <a href="${g.createLink(controller: 'artisticProfile', action: 'create', params: [artisticProfileName: artisticProfile.artisticName])}">+ Crear perfil</a> -->
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
</html>
