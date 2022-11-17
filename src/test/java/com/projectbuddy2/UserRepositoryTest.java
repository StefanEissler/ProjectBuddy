package com.projectbuddy2;

import com.projectbuddy2.entities.User;
import com.projectbuddy2.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCheckIfStudentExistsByEmail(){
        String email = "stefaneissler@web.de";
        User user = new User(
                "stefan",
                email,
                "12345678"

        );
        userRepository.save(user);
        boolean expected = userRepository.existsUserByEmail(email);
        assertThat(expected).isTrue();
    }

    @Test
    void shouldCheckIfStudentExistsByID(){
        User user = new User(
                "stefan",
                "stefaneissler@web.de",
                "12345678"

        );
        userRepository.save(user);
        long userid = user.getId();
        boolean expected = userRepository.existsById(userid);
        assertThat(expected).isTrue();
    }
}
