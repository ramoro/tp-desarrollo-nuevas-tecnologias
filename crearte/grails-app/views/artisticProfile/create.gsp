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
          <g:textField name="age" type="number" id="age" required="true" class="form-control" />
          <div id="ageErrorMessage" class="error-message"></div>
        </div>

        <div class="form-group">
          <label for="height">Altura(sin comas):</label>
          <g:textField name="height" id="height" required="true" class="form-control" />
          <div id="heightErrorMessage" class="error-message"></div>
        </div>

        <div class="form-group">
          <label for="weight">Peso:</label>
          <g:textField name="weight" id="weight" required="true" class="form-control" />
          <div id="weightErrorMessage" class="error-message"></div>
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
<script>
    const ageInput = document.getElementById("age");
    const ageErrorMessage = document.getElementById("ageErrorMessage");

    ageInput.addEventListener("input", function() {
        const value = ageInput.value;
        const isValidNumber = /^\d+$/.test(value);
        const age = parseInt(value);

        if (isValidNumber && age >= 16 && age <= 120) {
            ageErrorMessage.textContent = "";
        } else {
            ageErrorMessage.textContent = "La edad debe ser un número válido entre 16 y 120.";
        }
    });

    const heightInput = document.getElementById("height");
    const heightErrorMessage = document.getElementById("heightErrorMessage");

    heightInput.addEventListener("input", function() {
        const value = heightInput.value;
        const isValidNumber = /^\d+$/.test(value);
        const height = parseInt(value);

        if (isValidNumber && height >= 50 && height <= 300) {
            heightErrorMessage.textContent = "";
        } else {
            heightErrorMessage.textContent = "La altura debe ser un número válido entre 50 y 300.";
        }
    });

    const weightInput = document.getElementById("weight");
    const weightErrorMessage = document.getElementById("weightErrorMessage");

    weightInput.addEventListener("input", function() {
        const value = weightInput.value;
        const isValidNumber = /^\d+$/.test(value);
        const weight = parseInt(value);

        if (isValidNumber && weight >= 40 && weight <= 300) {
            weightErrorMessage.textContent = "";
        } else {
            weightErrorMessage.textContent = "El peso debe ser un número válido entre 40 y 300.";
        }
    });
</script>

</html>