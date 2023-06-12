<!-- profile.gsp -->
<!DOCTYPE html>
<html>
<g:render template="/templates/head"  />
<body>
  <div class="container">
    <h1>Perfil Usuario</h1>
      <div>
          <a href="${g.createLink(controller: 'project', action: 'create', params: [dni: params.dni])}" class="btn btn-primary" class="btn btn-primary">Crear Proyecto</a>
      </div>

      <div>
          <a href="${g.createLink(controller: 'user', action: 'listProjects', params: [dni: params.dni])}" class="btn btn-primary" class="btn btn-primary">Ver proyectos</a>
      </div>

  </div>
</body>
</html>