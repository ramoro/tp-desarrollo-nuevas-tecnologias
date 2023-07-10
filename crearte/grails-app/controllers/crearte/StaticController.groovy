class StaticController {

    def showImage() {
        String filename = params.filename

        // Construye la ruta completa de la imagen en tu servidor
        String imagePath = "${grailsApplication.config.upload.directory}/${filename}"

        // Devuelve la imagen como respuesta
        response.contentType = 'image/jpeg'
        response.outputStream << new File(imagePath).bytes
    }
}
