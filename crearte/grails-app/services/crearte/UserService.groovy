package crearte

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    User logUser(String name, String lastName, int dni, String description, boolean isPremium) {
        User user = User.findByDni(dni)

        if (!user) {
            user = new User(
                name: name,
                lastName: lastName,
                dni: dni,
                description: description,
                isPremium: isPremium
            ).save(failOnError: true)
        }

        return user
    }

    def changeUserProfile(String artisticNameProfile, String dni) {
        User user = User.findByDni(dni)
        def profile = ArtisticProfile.findByArtisticName(artisticNameProfile)

        user.setActiveProfile(profile)

        profile.save(flush:true)
    }
    
}

