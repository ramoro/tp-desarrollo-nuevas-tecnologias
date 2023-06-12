<!-- save.gsp -->
<!DOCTYPE html>
<html>
<g:render template="/templates/head"  />
<body>
  <div class="container">
    <h1>Proyecto creado</h1>
    <div class="form-container">
        <div class="alert alert-success" role="alert">El proyecto <strong>${params.name}</strong> se creo correctamente en estado borrador! </div>
    </div>


    <g:link controller="user" action="showProfile" params="[dni: params.dni]">
      Volver al Perfil
    </g:link>
  </div>
</body>
</html>