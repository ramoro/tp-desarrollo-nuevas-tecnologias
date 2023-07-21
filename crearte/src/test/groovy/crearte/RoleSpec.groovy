package crearte

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class RoleSpec extends Specification implements DomainUnitTest<Role> {

    def setup() {
    }

    def cleanup() {
    }


    void "is about to be completed"() {

    }

    void "Test a new Role without limited spots, has available spots"() {
        Role r
        given:
            r = new Role(description:"New Role", hasLimitedSpots:false);
        expect:
            r.hasAvailableSpots() == true
    }

    void "Test a new Role with limited spots but no reservations, has available spots"() {
        Role r 
        given:
            r = new Role(description:"New Role", hasLimitedSpots:true, totalSpots: 5);    
        expect:
            r.hasAvailableSpots() == true
    }

    void "Test a new Role with 5 limited spots and 1 reservations, has available spots"() {
        Role r 
        given:
           r = new Role(description:"New Role", hasLimitedSpots:true, totalSpots: 5);
        when:
            r.occupiedSpots += 1
        then:
            r.hasAvailableSpots() == true
    }

    void "Test a Role with maximum quota filled has no available spots"() {
        Role r 
        given:
            r = new Role(description:"New Role", hasLimitedSpots:true, totalSpots: 1);
        when:
            r.occupiedSpots += 1
        then:
            r.hasAvailableSpots() == false
    }

}
