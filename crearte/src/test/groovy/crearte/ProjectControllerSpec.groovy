package crearte

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class ProjectControllerSpec extends Specification implements ControllerUnitTest<ProjectController> {

    def setup() {
        User user = new User(
            name: "Jorge",
            lastName: "Morales",
            dni: "40314876",
            description: "Actor proactivo en busca de proyectos motivadores"
        ).save(failOnError: true)
    }

    def cleanup() {
    }

    void "test crear un proyecto de forma exitosa"() {
        given: "Dado que un convocante completó el nombre del proyecto con un nombre inexistente y su descripción"
            def controller = new ProjectController()
            def params = [dni: user.dni, name: "Proyecto FUC", description: "Cortometraje para la FUC de drama a rodar a fines de Junio. Un joven se enamora de una mujer casada y con una enfermedad que luego se enterara que es mortal."]
        
        when: "cuando el convocante crea el proyecto"
            controller.params = params
            controller.save()

        then: "el sistema le crea correctamente el proyecto al convocante"
            !controller.flash.error
            controller.flash.success

            Project.findByName("Proyecto FUC") != null
    }
}
