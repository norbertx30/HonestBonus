package com.github.groupproject.service.impl;

import com.github.groupproject.email.EmailService;
import com.github.groupproject.entities.User;
import com.github.groupproject.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Test
    public void whenCreatingUser_ThenUserExists() {
        UserServiceImpl userService = new UserServiceImpl(userRepository, emailService);

        String uuid = userService.create("Brand24", "pinokio@brand24.pl");

        User user = userRepository.findOneByUuid(uuid);
        Assertions.assertThat(user)
                .isNotNull()
                .hasFieldOrPropertyWithValue("companyName", "Brand24")
                .hasFieldOrPropertyWithValue("email", "pinokio@brand24.pl");
    }

    //TODO custom matcher (harmcrest)


}
