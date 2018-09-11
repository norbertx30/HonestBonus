package com.github.groupproject.service.impl;

import com.github.groupproject.dto.UserDto;
import com.github.groupproject.email.EmailService;
import com.github.groupproject.entities.User;
import com.github.groupproject.repository.UserRepository;
import com.github.groupproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    Logger LOG = LoggerFactory.getLogger(ClientServiceImpl.class);

    private UserRepository userRepository;
    private EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }



    @Override
    public String create(String companyName, String email) {
        LOG.info("Created User: [companyName]: " + companyName + " [email]: " + email);
        User user = new User();
        user.setCompanyName(companyName);
        user.setEmail(email);
        User createdUser = userRepository.save(user);
        try {
            emailService.sendMessage(user.getEmail(), "Welcome to Honest Bonus", user.getUuid());
        }catch (MailException e) {
            LOG.info("error sending:" + e.getMessage());
        }
        return createdUser.getUuid();

    }

    @Override
    public Set<UserDto> findAll() {
        return userRepository.findAllBy().stream()
                .map(UserDto::new)
                .collect(Collectors.toSet());
    }
}
