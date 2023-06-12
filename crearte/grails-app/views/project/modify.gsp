<!-- publish.gsp -->
<!DOCTYPE html>
<html>
<g:render template="/templates/head"  />
<body>
  <div class="container">
    <h1>Tu Proyecto: ${params.name}</h1>
    <div class="form-container">
      <g:form controller="project" action="save">
        <g:if test="${existingName}">
          <div class="alert alert-danger" role="alert">Ya existe un proyecto con el nombre ${existingName}</div>
        </g:if>

        <div class="form-group">
          <label for="name">Nombre:</label>
          <g:textField name="name" id="name" required="true" class="form-control"/>
        </div>

        <div class="form-group">
          <label for="description">Descripción:</label>
            <p> ${params.description} </p>
        </div>

        <div class="form-group">
          <label for="roles">Roles:</label>
            <ul>
                <g:each in="${roles}" var="role">
                    <li>${role.name}</li>
                </g:each>
            </ul>
        </div>

        <g:link controller="rol" action="create" params="[projectName: params.name]">
            + Agregar rol
        </g:link>

        <input type="hidden" id="dni" name="dni" value="${params.dni}">


      </g:form>
    </div>
  </div>

  <style>
    .container {
      max-width: 600px;
      margin-top: 30px;
      padding: 20px;
      border: 1px solid #ccc;
      box-shadow: 0 0 5px #ccc;
    }

    .form-container {
      margin-top: 20px;
    }

    .form-group {
      margin-bottom: 20px;
    }

    label {
      display: block;
      margin-bottom: 5px;
    }
  </style>
</body>

</html>

