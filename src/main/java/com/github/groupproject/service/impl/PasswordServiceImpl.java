package com.github.groupproject.service.impl;

import com.github.groupproject.dto.SetPasswordDto;
import com.github.groupproject.entities.Employee;
import com.github.groupproject.entities.User;
import com.github.groupproject.exceptions.BadRequestException;
import com.github.groupproject.repository.EmployeeRepository;
import com.github.groupproject.repository.UserRepository;
import com.github.groupproject.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {


    Logger LOG = LoggerFactory.getLogger(ClientServiceImpl.class);

    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordServiceImpl(UserRepository userRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void setPassword(SetPasswordDto dto) {

        if (!dto.getPassword().equals(dto.getRepeatPassword())) {
            LOG.error("ERROR: [Request password]: " + dto.getPassword() +
                    " [Request RepeatPassword]: " + dto.getRepeatPassword() +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Passwords do not match");
        }

        User user = userRepository.findOneByUuid(dto.getUuid());
        if (user != null) {
            LOG.info("Setted Password for user: [password]: " + dto.getPassword() +
                    " [userUuid]: " + dto.getUuid());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            userRepository.save(user);
            return;
        }

        Employee employee = employeeRepository.findOneByUuid(dto.getUuid());
        if (employee != null) {
            LOG.info("Setted Password for employee: [password]: " + dto.getPassword() +
                    " [employeeUuid]: " + dto.getUuid());
            employee.setPassword(passwordEncoder.encode(dto.getPassword()));
            employeeRepository.save(employee);
            return;
        }

        throw new BadRequestException("Uuid does not exist");
    }

}
