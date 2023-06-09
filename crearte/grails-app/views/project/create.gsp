<!-- create.gsp -->
<!DOCTYPE html>
<html>
<g:render template="/templates/head" />

<body>
  <div class="container">
    <h1>Crear Proyecto</h1>
    <div class="form-container">
      <g:form controller="project" action="save">
        <g:if test="${existingName}">
          <div class="alert alert-danger" role="alert">Ya existe un proyecto con el nombre ${existingName}</div>
        </g:if>

        <div class="form-group">
          <label for="name">Nombre:</label>
          <g:textField name="name" id="name" required="true" class="form-control" />
        </div>

        <div class="form-group">
          <label for="description">Descripción:</label>
          <g:textArea name="description" id="description" required="true" rows="5" columns="40" minlength="100"
            maxlength="255" value="${params.description}" style="resize:none;" class="form-control" />
        </div>
        <input type="hidden" id="dni" name="dni" value="${params.dni}">

        <div class="form-group">
          <g:submitButton name="guardar" value="Guardar" class="btn btn-primary" />
        </div>
        <g:link controller="user" action="showProfile" params="[dni: params.dni]">
          Volver al Perfil
        </g:link>
      </g:form>
    </div>
  </div>

</body>

</html>