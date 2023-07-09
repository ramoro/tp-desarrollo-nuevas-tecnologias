<!-- show.gsp -->
<!DOCTYPE html>
<g:render template="/templates/head"  />
<html>
<body>
    <div class="container">
        <h1 style="margin:10px">Mi perfil <strong> ${artisticProfile.artisticName} </strong></h1>
        <div class="row">
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-header bg-custom">
                        <h2>Descripción</h2>
                    </div>
                    <div class="card-body">
                        <p>${artisticProfile.age}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
