package com.github.groupproject.security;


import com.github.groupproject.entities.Employee;
import com.github.groupproject.entities.User;
import com.github.groupproject.repository.EmployeeRepository;
import com.github.groupproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findOneByEmail(email);
        if (user != null) {
            return new UserDetailsImpl(user);
        }
        Employee employee = employeeRepository.findOneByEmail(email);
        if (employee != null) {
            return new UserDetailsImpl(employee);
        }

        throw new UsernameNotFoundException(email);


    }
}
