package com.github.groupproject.service.impl;

import com.github.groupproject.dto.EmployeeDto;
import com.github.groupproject.email.EmailService;
import com.github.groupproject.entities.Employee;
import com.github.groupproject.entities.User;
import com.github.groupproject.exceptions.BadRequestException;
import com.github.groupproject.repository.EmployeeRepository;
import com.github.groupproject.repository.UserRepository;
import com.github.groupproject.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Logger LOG = LoggerFactory.getLogger(ClientServiceImpl.class);


    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private EmailService emailService;

    @Autowired
    public EmployeeServiceImpl(UserRepository userRepository, EmployeeRepository employeeRepository,
                               EmailService emailService) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
    }

    @Override
    public String create(String firstName, String lastName, String email, String userUuid) {
        User user = userRepository.findOneByUuid(userUuid);
        if (user == null){
            LOG.error("ERROR: [Request userUuid]: " + userUuid +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Given UUID of user does not exist");
        }
        LOG.info("Created Employee: [userUuid]: " + userUuid);
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setUser(user);
        employeeRepository.save(employee);
        emailService.sendMessage(employee.getEmail(),"Welcome to Honest Bonus",employee.getUuid());
        return employee.getUuid();
    }

    @Override
    public Set<EmployeeDto> findAll() {
        return employeeRepository.findAllBy().stream()
                .map(EmployeeDto::new)
                .collect(Collectors.toSet());

    }

    @Override
    public Set<EmployeeDto> findByUserUuid(String userUuid) {
        User user = userRepository.findOneByUuid(userUuid);
        if (user == null){
            LOG.error("ERROR: [Request userUuid]: " + userUuid +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Given UUID of user does not exist");
        }
        return employeeRepository.findAllByUserUuid(userUuid).stream()
                .map(EmployeeDto::new)
                .collect(Collectors.toSet());
    }
}
