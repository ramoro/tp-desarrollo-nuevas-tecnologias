<!-- profile.gsp -->
<!DOCTYPE html>
<html>
<g:render template="/templates/head" />

<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-custom">
    <a class="navbar-brand" href="${g.createLink(controller: 'user', action: 'showProfile', params: [dni: params.dni])}">Perfil Usuario</a>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <a class="nav-link shadow"
            href="${g.createLink(controller: 'project', action: 'create', params: [dni: params.dni])}"
            style="text-color:blue">Crear Proyecto <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link shadow"
            href="${g.createLink(controller: 'user', action: 'listProjects', params: [dni: params.dni])}">Mis Proyectos
            <span class="sr-only">(current)</span></a>
        </li>
        <li>
          <a class="nav-link shadow"
            href="${g.createLink(controller: 'artisticProfile', action: 'create', params: [dni: params.dni])}">Crear
            Perfil artístico</a>
        </li>    
        <li class="nav-item">
          <a class="nav-link shadow"
            href="${g.createLink(controller: 'project', action: 'listProjects', params: [dni: params.dni])}"
            style="text-color:blue">Ver Proyectos<span class="sr-only">(current)</span></a>
        </li>
      </ul>
    </div>

  </nav>

  <div class="user-presentation">
    <h1>${user.name} ${user.lastName}</h1>
  </div>
  <div class="user-description">
    <p>${user.description}</p>
  </div>


  <div class="user-presentation">
    <h3>Perfiles Artísticos</h3>
    <g:if test="${user.artisticProfiles}">
      <div class="list-group">
        <g:each in="${user.artisticProfiles}" var="profile">
          <div class="card mb-3 custom-card">
            <img src="${createLink(controller: 'static', action: 'showImage', params: [filename: profile.profileImage])}" class="card-img-top" alt="Foto del perfil">
              <h5 class="card-title"><strong>${profile.artisticName}</strong></h5>
              <p class="card-text">
                Link a reel: <a href="${profile.reelLink}">${profile.reelLink}</a><br>
                Estado: <span class="${profile.state == 0 ? 'text-danger' : 'text-success'}">${profile.state == 0 ? 'Inactivo' : 'Activo'}</span><br>
              </p>
              <form action="${createLink(controller: 'user', action: 'changeUserProfile', params: [artisticName: profile.artisticName, dni: params.dni])}" method="POST">
                <button type="submit" class="btn btn-primary">${profile.state == 0 ? 'Activar' : 'Desactivar'}</button>
              </form>
          </div>
        </g:each>
      </div>
    </g:if>
    <g:else>
      <p class="text-muted">Aun no hay Perfiles creados para el usuario.</p>
    </g:else>   
  </div>

</body>

</html>

<style>

.bg-custom {
  background-color: #b4e6b0;
}

.user-presentation {
  font-size: 24px;
  font-weight: bold;
  margin-top: 20px;
  text-align: center;
}

.user-presentation p{
  font-size: 18px;
  margin-top: 20px;
  font-weight: normal;
  text-align: center;
  align-items: center;
  justify-content: center;
}


.user-description {
 border: 1px solid #ccc;
  border-radius: 10px;
  padding: 10px;
  margin-top: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  max-width: 80%;
  margin: 0 auto;
}

.user-description p {
  text-align: center;
  max-width: 100%;
  overflow-wrap: break-word;
  word-wrap: break-word;
  word-break: break-word;
  font-style: italic;
}

.custom-card {
  border: 2px solid #ddd;
  border-radius: 10px;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
  background-color: #f8f8f8;
  text-align: center;
  align: center;
  padding: 10px;
  max-width: 400px;
  margin: 0 auto;
  margin-left: auto; /* Ajusta el margen izquierdo para centrar la tarjeta */
  margin-right: auto;
}

.custom-card img {
  width: 100%;
  height: auto;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

.custom-card .card-body {
  padding: 10px;
}

.custom-card .card-title {
  font-weight: bold;
  font-size: 18px;
}

.custom-card .card-text {
  font-size: 16px;
  margin-top: 10px;
}
</style>