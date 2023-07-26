package crearte

class ArtisticProfile {

    String artisticName
    String locality
    int age
    float height
    float weight
    String reelLink
    String profileImage
    int state //0 es inactivo, 1 es activo

    static constraints = {
        artisticName blank: false, nullable: false
        age range: 16..120
        height range: 50..300
        weight range: 40..300
    }

    ArtisticProfile(String artisticName, String locality, int age, float height, float weight,
    String reelLink, String profileImage) {
        assert artisticName != null
        assert age >= 16
        assert age <= 120
        assert height >= 50
        assert height <= 300 
        assert weight >= 40
        assert weight <= 300 
        this.artisticName = artisticName
        this.locality = locality
        this.age = age
        this.height = height
        this.weight = weight
        this.reelLink = reelLink
        this.profileImage = profileImage
        this.state = 0
    }

}
