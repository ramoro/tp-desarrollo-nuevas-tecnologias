<!-- save.gsp -->
<!DOCTYPE html>
<html>
<g:render template="/templates/head"  />
<body>
  <div class="container">
    <h1>Perfil artístico creado</h1>
    <div class="form-container">
        <div class="alert alert-success" role="alert">El perfil artístico ${params.name} se creo correctamente</div>
    </div>


    <g:link controller="user" action="showProfile" params="[dni: params.dni]">
      Volver al Perfil
    </g:link>
  </div>
</body>
</html>