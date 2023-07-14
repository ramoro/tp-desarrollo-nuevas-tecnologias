package crearte

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    def changeUserProfile(String artisticNameProfile, String dni) {
        User user = User.findByDni(dni)
        def profile = ArtisticProfile.findByArtisticName(artisticNameProfile)

        if(profile.state == 0 && user.hasActiveProfile()) {
            throw new User.InvalidProfileChangeException("El usuario ya tiene un perfil activo")
        }

        profile.state = profile.state == 0 ? 1 : 0
        profile.save(flush:true)
    }
    
    def notifyUser(int userDni, String message) {
        User user = User.findByDni(userDni)
        user.notifications.add(message)
        user.save(flush:true)
    }
}

