package crearte

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ArtisticProfileServiceSpec extends Specification {

    ArtisticProfileService artisticProfileService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ArtisticProfile(...).save(flush: true, failOnError: true)
        //new ArtisticProfile(...).save(flush: true, failOnError: true)
        //ArtisticProfile artisticProfile = new ArtisticProfile(...).save(flush: true, failOnError: true)
        //new ArtisticProfile(...).save(flush: true, failOnError: true)
        //new ArtisticProfile(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //artisticProfile.id
    }

    void "test get"() {
        setupData()

        expect:
        artisticProfileService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ArtisticProfile> artisticProfileList = artisticProfileService.list(max: 2, offset: 2)

        then:
        artisticProfileList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        artisticProfileService.count() == 5
    }

    void "test delete"() {
        Long artisticProfileId = setupData()

        expect:
        artisticProfileService.count() == 5

        when:
        artisticProfileService.delete(artisticProfileId)
        sessionFactory.currentSession.flush()

        then:
        artisticProfileService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ArtisticProfile artisticProfile = new ArtisticProfile()
        artisticProfileService.save(artisticProfile)

        then:
        artisticProfile.id != null
    }
}
