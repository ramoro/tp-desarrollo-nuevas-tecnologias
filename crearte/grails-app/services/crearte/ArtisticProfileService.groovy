package crearte

import grails.gorm.transactions.Transactional

@Transactional
class ArtisticProfileService {

    def createArtisticProfile(String name, String locality, int age, float height, float weight, String reelLink, String dni, String profileImage) {
        ArtisticProfile artisticProfile = new ArtisticProfile(
                artisticName: name,
                locality: locality,
                age : age,
                height : height,
                weight : weight,
                reelLink: reelLink,
                profileImage: profileImage
            ).save(failOnError: true)
            
        User user = User.findByDni(dni)
        user.artisticProfiles.add(artisticProfile)
        user.save(flush:true)
    }
}
