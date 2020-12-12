package org.software.learning.seeder;

import org.software.learning.model.entity.User;
import org.software.learning.repository.UserRepository;
import org.software.learning.security.UserEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataBaseSeeder {
    private UserRepository userRepository;
    private UserEncryptor userEncryptor;

    public void insertData() {
        if (userRepository.findById("admin").isEmpty()) {
            userRepository.save(
                    userEncryptor.encrypt(
                            new User("admin", User.NORMAL, User.GROUP_NORMAL, "admin", "admin_password", "admin@outlook.com")
                    )
            );
        }

        if (userRepository.findById("test").isEmpty()) {
            userRepository.save(
                    userEncryptor.encrypt(
                            new User("test", User.NORMAL, User.GROUP_NORMAL, "test", "test_password", "test@outlook.com")
                    )
            );
        }
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserEncryptor(UserEncryptor userEncryptor) {
        this.userEncryptor = userEncryptor;
    }
}
