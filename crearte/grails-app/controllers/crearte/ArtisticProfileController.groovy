package crearte

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ArtisticProfileController {

    ArtisticProfileService artisticProfileService

    def create() { 
        String dni = params.dni
        // Puedes realizar cualquier lógica adicional aquí si es necesario

        [dni: dni] // Pasamos el dni a la vista
        render(view: '/artisticProfile/create')
    }

    def save() {

        ArtisticProfile artisticProfile = ArtisticProfile.findByArtisticName(params.name)

        if (artisticProfile) {
            flash.error = "Ya existe un proyecto con el nombre ${params.name}"
            // Pasamos el dni a la vista
            render(view: '/artisticProfile/create', model: [existingName: params.name, dni: params.dni])
        } else {
            int age = params.age as Integer
            float weight = params.weight as Float
            float height = params.height as Float
            artisticProfileService.createArtisticProfile(params.name, params.locality, age, height, weight, params.reel_link, params.dni)

            flash.success = "Proyecto creado exitosamente"
            render(view: 'save', model: [projectName: params.name, dni: params.dni])
        }


    }

}
