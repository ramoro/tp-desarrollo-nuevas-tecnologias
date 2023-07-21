package crearte

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    User user
    
    def setup() {
        user = new User(
            name: "Jorge",
            lastName: "Morales",
            dni: 40314876,
            description: "Actor proactivo en busca de proyectos motivadores"
        )
    }

    def cleanup() {
    }

    void "Activate user artistic profile"() {

        given: "an artist that has created an artistic profile"
        ArtisticProfile artisticProfile = new ArtisticProfile( "Jorgem", "CABA", 25, 1.73,
                80, "www.youtube.com/2erqweq", "imagen.png"
            )

        when: "when the artist activates the artistic profile"
        user.artisticProfiles.add(artisticProfile)
        user.setActiveProfile(artisticProfile)

        then:"the artistic profile changes its state to active"
        artisticProfile.state == 1
    }

    void "Activate user artistic profile while having one already active."() {

        given: "an artist that has an active artistic profile and has created another one"
        ArtisticProfile artisticProfile1 = new ArtisticProfile( "Jorgem", "CABA", 25, 1.73,
                80, "www.youtube.com/2erqweq", "imagen.png"
            )
        user.artisticProfiles.add(artisticProfile1)
        user.setActiveProfile(artisticProfile1)

        ArtisticProfile artisticProfile2 = new ArtisticProfile( "Jorgem2", "CABA2", 25, 1.73,
                80, "www.youtube.com/2erqweq", "imagen2.png"
        )
        user.artisticProfiles.add(artisticProfile2)
        
        when: "the artist activates this new profile"
        user.setActiveProfile(artisticProfile2)

        then: "an exception is thrown"
        Exception e = thrown()
        e.message == "El usuario ya tiene un perfil activo"
    }
}
