<!-- create.gsp -->
<!DOCTYPE html>
<html>
<g:render template="/templates/head"  />
<body>
  <div class="container">
    <h2>Crear Rol para Proyecto ${params.projectName}</h2>
    <div class="form-container">
      <g:form controller="role" action="save">
        <div class="form-group">
          <label for="name">Nombre:</label>
          <g:textField name="name" id="name" required="true" class="form-control"/>
        </div>

        <div class="form-group">
          <label for="description">Descripción:</label>
          <g:textArea name="description" id="description" required="true" rows="5" cols="40" style="resize:none;" class="form-control"/>
        </div>

        <div class="form-group">
          <label for="hasLimitedSpots">Cupos limitados:</label>
          <div class="checkbox-container">
            <div class="checkbox-label">
              <g:checkBox name="hasLimitedSpots" id="hasLimitedSpots" onchange="showSpots()" class="form-check-input"/>
              <span class="checkbox-text">Sí</span>
            </div>
          </div>
        </div>

        <div id="spots" style="display:none" class="form-group">
          <label for="totalSpots">Cantidad de cupos:</label>
          <g:textField name="totalSpots" id="totalSpots" class="form-control" pattern="[0-9]+" title="Ingrese solo números"/>
        </div>

        <input type="hidden" id="projectName" name="projectName" value="${params.projectName}">

        <div class="form-group">
          <g:submitButton name="guardar" value="Guardar" class="btn btn-primary" />
        </div>
      </g:form>
    </div>
  </div>

  <script>
    function showSpots() {
      if (document.getElementById('hasLimitedSpots').checked) {
        document.getElementById('spots').style.display = 'block';
      } else {
        document.getElementById('spots').style.display = 'none';
      }
    }
  </script>

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

    .checkbox-container {
      display: flex;
      align-items: center;
      margin-left: 50px;
    }

    .checkbox-container input[type="checkbox"] {
      margin-right: 10px;
    }
  </style>
</body>

</html>

