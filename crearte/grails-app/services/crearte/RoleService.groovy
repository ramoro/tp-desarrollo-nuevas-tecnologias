package crearte

import java.time.*
import grails.gorm.transactions.Transactional

@Transactional
class RoleService {

    def createRole(String name, String description, boolean hasLimitedSpots, int totalSpots, String projectName) {
        
        Role role = new Role(
                name: name,
                description: description,
                hasLimitedSpots: hasLimitedSpots,
                totalSpots: totalSpots
            ).save(failOnError: true)

        Project project = Project.findByName(projectName)
        project.roles.add(role)
        project.save(flush:true)
    }
}
