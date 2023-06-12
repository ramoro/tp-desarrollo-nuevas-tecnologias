package crearte

import grails.gorm.transactions.Transactional

@Transactional
class ArtisticProfileService {

    def createArtisticProfile(String dni, ArtisticProfile artisticProfile) {
        /*
        ArtisticProfile artisticProfile = new ArtisticProfile(
                artisticName: name,
                locality: locality,
                age : age,
                height : height,
                weight : weight,
                reelLink: reel_link
            ).save(failOnError: true)*/
            
        User user = User.findByDni(dni)
        user.artisticProfiles.add(artisticProfile)
        user.save(flush:true)
    }
}
