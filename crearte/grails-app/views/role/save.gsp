<!-- save.gsp -->
<!DOCTYPE html>
<html>
<g:render template="/templates/head"  />
<body>
  <div class="container">
    <h1>Rol creado</h1>
    <g:if test="${params.hasLimitedSpots}">
        <div class="form-container">
            <div class="alert alert-success" role="alert">El rol <strong>${params.name}</strong> se ha creado correctamente con un límite de <strong>${params.totalSpots}</strong> cupos! </div>
        </div>
    </g:if>
    <g:else>
        <div class="form-container">
            <div class="alert alert-success" role="alert">El rol <strong>${params.name}</strong> se ha creado correctamente!</div>
        </div>
    </g:else>
    <g:link controller="project" action="show" params="[name: params.projectName]">
      Ir al proyecto
    </g:link>
  </div>
</body>
</html>