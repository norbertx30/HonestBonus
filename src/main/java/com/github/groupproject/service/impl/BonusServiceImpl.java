package com.github.groupproject.service.impl;

import com.github.groupproject.dto.BonusDto;
import com.github.groupproject.entities.Bonus;
import com.github.groupproject.entities.User;
import com.github.groupproject.exceptions.BadRequestException;
import com.github.groupproject.repository.BonusRepository;
import com.github.groupproject.repository.UserRepository;
import com.github.groupproject.service.BonusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Service
public class BonusServiceImpl implements BonusService {

    Logger LOG = LoggerFactory.getLogger(ClientServiceImpl.class);

    private BonusRepository bonusRepository;
    private UserRepository userRepository;

    @Autowired
    public BonusServiceImpl(BonusRepository bonusRepository, UserRepository userRepository) {
        this.bonusRepository = bonusRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String create(String name, Double shareOfTransaction,
                         Integer timeOutInDays, String userUuid) {
        User user = userRepository.findOneByUuid(userUuid);
        if (user == null){
            LOG.error("ERROR: [Request userUuid]: " + userUuid +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Given UUID of user does not exist");
        }
        LOG.info("Created Bonus: [userUuid]: " + userUuid);
        Bonus bonus = new Bonus();
        bonus.setName(name);
        bonus.setShareOfTransaction(shareOfTransaction);
        bonus.setTimeOutInDays(timeOutInDays);
        bonus.setUser(user);
        bonusRepository.save(bonus);
        return bonus.getUuid();
    }

    @Override
    public Set<BonusDto> findAll() {
        return bonusRepository.findAllBy().stream()
                .map(BonusDto::new)
                .collect(toSet());
    }

    @Override
    public Set<BonusDto> findAllByUserUuid(String userUuid) {
        User user = userRepository.findOneByUuid(userUuid);
        if (user == null){
            LOG.error("ERROR: [Request userUuid]: " + userUuid +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Given UUID of user does not exist");
        }
        return bonusRepository.findAllByUserUuid(userUuid).stream()
                .map(BonusDto::new)
                .collect(toSet());
    }

    @Override
    public Set<BonusDto> findAllOfLoggedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findOneByEmail(email);
        if (user == null){
            LOG.error("ERROR: [email]: " + email +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Given UUID of user does not exist");
        }
        return bonusRepository.findAllByUserUuid(user.getUuid()).stream()
                .map(BonusDto::new)
                .collect(toSet());
    }

}
