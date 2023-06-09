<!-- create.gsp -->
<!DOCTYPE html>
<html>
<g:render template="/templates/head" />

<body>
  <div class="container">
    <h1>Crear Perfil artístico</h1>
    <div class="form-container">
      <g:form controller="artisticProfile" action="save" enctype="multipart/form-data">
        <g:if test="${existingName}">
          <div class="alert alert-danger" role="alert">Ya existe un perfil artístico con el nombre ${existingName}</div>
        </g:if>

        <div class="form-group">
          <label for="name">Nombre artístico:</label>
          <g:textField name="name" id="name" required="true" class="form-control" />
        </div>

        <div class="form-group">
          <label for="locality">Localidad:</label>
          <g:textField name="locality" id="locality" required="true" class="form-control" />
        </div>

        <div class="form-group">
          <label for="age">Edad:</label>
          <g:textField name="age" id="age" required="true" class="form-control" />
        </div>

        <div class="form-group">
          <label for="height">Altura:</label>
          <g:textField name="height" id="height" required="true" class="form-control" />
        </div>

        <div class="form-group">
          <label for="weight">Peso:</label>
          <g:textField name="weight" id="weight" required="true" class="form-control" />
        </div>

        <div class="form-group">
          <label for="reel_link">Enlace:</label>
          <g:textField name="reel_link" id="reel_link" required="false" class="form-control" />
        </div>


        <div class="form-group">
        <label for="profileImage">Foto:</label>
          <input type="file" name="profileImage" accept="image/*" required="true" />
        </div>

        <input type="hidden" id="dni" name="dni" value="${params.dni}">

        <div class="form-group">
          <g:submitButton name="guardar" value="Guardar" class="btn btn-primary" />
        </div>
      </g:form>
      <g:link controller="user" action="showProfile" params="[dni: params.dni]">
        Volver al Perfil
      </g:link>
    </div>
  </div>

</body>

</html>