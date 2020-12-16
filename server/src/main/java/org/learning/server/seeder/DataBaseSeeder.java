package org.learning.server.seeder;

import org.learning.server.entity.User;
import org.learning.server.model.Department;
import org.learning.server.model.Org;
import org.learning.server.repository.UserRepository;
import org.learning.server.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * To insert the seed data to ease the testing
 */
@Component
@Service
public class DataBaseSeeder {
    private UserRepository userRepository;
    private OrgService orgService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setOrgService(OrgService orgService) {
        this.orgService = orgService;
    }

    public void insertData() {
        insertUsers();
        insertOrg();
    }

    private void insertUsers() {
        for (int i = 0; i < 20; ++i){
            String uid = "_test" + i;
            User user = new User();
            user.setUid(uid);
            user.setName(uid);
            user.setPassword("123456");
            user.setAge(18);
            user.setSex(false);
            user.setEmail("");
            userRepository.save(user);
        }
    }

    private void insertOrg() {
        orgService.saveOrUpdate(
                new Org(1,"_org1", "_org1 generated")
                .addDepartment(new Department(2, "_dep1", "_dep1 generated"))
                .addDepartment(new Department(3, "_dep2", "_dep2 generated"))
        );
        orgService.saveOrUpdate(
                new Org(4, "_org2", "_org2 generated")
                .addDepartment(new Department(5, "_dep1", "_dep1 generated"))
                .addDepartment(new Department(6, "_dep2", "_dep2 generated"))
        );
    }
}
