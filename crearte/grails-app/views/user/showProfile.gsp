<!-- profile.gsp -->
<!DOCTYPE html>
<html>
<g:render template="/templates/head"  />
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-custom">
    <a class="navbar-brand">Perfil Usuario</a>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <a class="nav-link shadow" href="${g.createLink(controller: 'project', action: 'create', params: [dni: params.dni])}" style="text-color:blue">Crear Proyecto <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link shadow" href="${g.createLink(controller: 'user', action: 'listProjects', params: [dni: params.dni])}">Mis Proyectos <span class="sr-only">(current)</span></a>
        </li>

        
        <li>
          <a class="nav-link shadow" href="${g.createLink(controller: 'artisticProfile', action: 'create', params: [dni: params.dni])}">Crear Perfil artístico</a>
        </li>
        <li class="nav-item">
          <a class="nav-link shadow" href="${g.createLink(controller: 'user', action: 'listArtisticProfiles', params: [dni: params.dni])}">Mis Perfies Artisticos<span class="sr-only">(current)</span></a>
        </li>
      </ul>

    </div>

  </nav>

  ${params}
  
</body>
</html>

<style>
.bg-custom {
  background-color: #b4e6b0;
}
</style>