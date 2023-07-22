package crearte

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    def changeUserProfile(String artisticNameProfile, String dni) {
        User user = User.findByDni(dni)
        def profile = ArtisticProfile.findByArtisticName(artisticNameProfile)

        user.setActiveProfile(profile)

        profile.save(flush:true)
    }

    def deletePostulationFromUser(User user, Postulation postulation) {
        user.postulations.removeElement(postulation)
        user.save(flush: true)
    }
    
}

