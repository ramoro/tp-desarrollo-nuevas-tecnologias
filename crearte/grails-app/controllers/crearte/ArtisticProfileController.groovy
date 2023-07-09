package crearte

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import java.nio.file.Files
import org.springframework.web.multipart.MultipartFile

class ArtisticProfileController {

    ArtisticProfileService artisticProfileService

    def create() { 
        String dni = params.dni
        // Puedes realizar cualquier lógica adicional aquí si es necesario

        [dni: dni] // Pasamos el dni a la vista
        render(view: '/artisticProfile/create')
    }

    def show() { 
        ArtisticProfile artisticProfile = ArtisticProfile.findByArtisticName(params.name)

        render(view: '/artisticProfile/show', model: [artisticProfile: artisticProfile])
    }

    def save() {

        ArtisticProfile artisticProfile = ArtisticProfile.findByArtisticName(params.name)

        if (artisticProfile) {
            flash.error = "Ya existe un Perfil Artistico con el nombre ${params.name}"
            // Pasamos el dni a la vista
            render(view: '/artisticProfile/create', model: [existingName: params.name, dni: params.dni])
        } else {
            int age = params.age as Integer
            float weight = params.weight as Float
            float height = params.height as Float

            // Obtenemos el archivo de imagen del perfil artístico desde el comando FeaturedImageCommand
            MultipartFile profileImageFile = request.getFile('profileImage')

            String fileName = ""

            // Guardamos la foto del perfil artístico si existe
            if (profileImageFile) {
                fileName = "${UUID.randomUUID()}-${profileImageFile.originalFilename}" // Generamos un nombre de archivo único para evitar conflictos
                String imagesPath = grailsApplication.config.upload.directory
                File targetFolder = new File(imagesPath)

                // Verificamos si la carpeta de destino existe, y si no, la creamos
                if (!targetFolder.exists()) {
                    targetFolder.mkdirs()
                }

                //File targetFile = new File(targetFolder, fileName)
                File targetFile = new File("${imagesPath}/${fileName}")
                profileImageFile.transferTo(targetFile)
            }

            artisticProfileService.createArtisticProfile(params.name, params.locality, age, height, weight, params.reel_link, params.dni, fileName)

            flash.success = "Perfil Artistico creado exitosamente"
            render(view: 'save', model: [projectName: params.name, dni: params.dni])
        }


    }

}
