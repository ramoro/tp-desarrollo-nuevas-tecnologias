package crearte

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }

    void "new user without artisctic profiles has no active artistic profile"() {
        expect:"fix me"
            true == false
    }

    void "user with invalid dni"() {
        when:
            user.dni = 123
        then:
            domain.validate(['dni'])
    }
}
