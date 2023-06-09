<!-- logUser.gsp -->
<!DOCTYPE html>
<html>
<g:render template="/templates/head"  />
<body>
  <div class="container" style="background-color:white">
    <h1>Entrar con Usuario</h1>
    <div class="form-container">
      <g:form controller="user" action="logUser">
        <div class="form-group">
          <label for="name">Nombre:</label>
          <g:textField name="name" id="name" required="true" class="form-control"/>
        </div>

        <div class="form-group">
          <label for="lastName">Apellido:</label>
          <g:textField name="lastName" id="lastName" required="true" class="form-control"/>
        </div>

        <div class="form-group">
          <label for="dni">DNI:</label>
          <g:textField name="dni" id="dni" required="true" class="form-control"/>
        </div>

        <div class="form-group">
          <label for="description">Descripción:</label>
          <g:textArea name="description" id="description" required="true" rows="5" cols="40" style="resize:none;" class="form-control"/>
        </div>


        <div class="form-group">
          <g:submitButton name="guardar" value="Guardar" class="btn btn-primary" />
        </div>
      </g:form>
    </div>
  </div>

</body>

</html>

