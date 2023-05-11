<!-- create.gsp -->
<!DOCTYPE html>
<html>
<g:render template="/templates/head"  />
<body>
  <div class="container">
    <h1>Crear rol</h1>
    <div class="form-container">
      <g:form controller="roles" action="save">
        <div class="form-group">
          <label for="nombre">Nombre:</label>
          <g:textField name="nombre" id="nombre" required="true" class="form-control"/>
        </div>

        <div class="form-group">
          <label for="descripcion">Descripción:</label>
          <g:textArea name="descripcion" id="descripcion" required="true" rows="5" cols="40" style="resize:none;" class="form-control"/>
        </div>

        <div class="form-group">
          <label for="cuposLimitados">Cupos limitados:</label>
          <div class="checkbox-container">
            <g:checkBox name="cuposLimitados" id="cuposLimitados" onchange="mostrarCupos()" class="form-check-input" />
          </div>
        </div>

        <div id="campoCupos" style="display:none" class="form-group">
          <label for="cantidadCupos">Cantidad de cupos:</label>
          <g:textField name="cantidadCupos" id="cantidadCupos" class="form-control"/>
        </div>

        <div class="form-group">
          <g:submitButton name="guardar" value="Guardar" class="btn btn-primary" />
        </div>
      </g:form>
    </div>
  </div>

  <script>
    function mostrarCupos() {
      if (document.getElementById('cuposLimitados').checked) {
        document.getElementById('campoCupos').style.display = 'block';
      } else {
        document.getElementById('campoCupos').style.display = 'none';
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
    }

    .checkbox-container input[type="checkbox"] {
      margin-right: 10px;
    }
  </style>
</body>

</html>

