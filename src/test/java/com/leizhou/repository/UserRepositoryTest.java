package com.leizhou.repository;

import com.leizhou.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void selectUser() {
        // given
        User user = new User(10L, "abc", "", 0, new Date());
        userRepository.save(user);

        // when
        User user1 = userRepository.selectUser(user.getName());

        // expect
        assertThat(user1).hasFieldOrPropertyWithValue("name", "abc");

    }

    @Test
    void shouldNoUser() {
        // given
        User user = new User(10L, "abc", "", 0, new Date());
        //userRepository.save(user);

        // when
        User user1 = userRepository.selectUser(user.getName());

        // expect
        assertThat(user1).hasAllNullFieldsOrProperties();

    }
}