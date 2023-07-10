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
            href="${g.createLink(controller: 'user', action: 'listArtisticProfiles', params: [dni: params.dni])}">Mis
            Perfiles Artisticos<span class="sr-only">(current)</span></a>
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
</style>