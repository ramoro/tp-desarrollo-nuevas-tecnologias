package crearte

import java.time.*
import grails.gorm.transactions.Transactional

@Transactional
class RoleService {

    def createRole(String name, String description, boolean hasLimitedSpots, int totalSpots, String projectName) {
        Project project = Project.findByName(projectName)
        Role role = project.createRole(name, description, hasLimitedSpots, totalSpots)         
        
        role.save(failOnError: true)
        project.save(flush:true)
    }
}
