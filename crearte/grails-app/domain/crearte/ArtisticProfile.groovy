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
        age range: 16..150
        height range: 0.5..3
        weight range: 40..300
    }

    
}
