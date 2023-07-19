package crearte

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PostulationSpec extends Specification implements DomainUnitTest<Postulation> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == true
    }

    void "postulation is equal to another postulation"() {
        User u
        Role r
        Project p
        Postulation p1,p2
        given:
            u = new User(dni: 12345678)
            r = new Role(name: "asd", description: "asd")
            p = new Project(description: "asd", state: Project.ProjectState.DRAFT, ownerDni: 12345678)

        when:
            p1 = new Postulation(user:u,role:r,project:p)
            p2 = new Postulation(user:u,role:r,project:p)

        then:
            p1.isEqual(p2) == true
            pw.isEqual(p1) == true

    }
}
