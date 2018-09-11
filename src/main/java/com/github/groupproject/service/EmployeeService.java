package com.github.groupproject.service;

import com.github.groupproject.dto.EmployeeDto;
import com.github.groupproject.entities.Employee;

import java.util.Set;

public interface EmployeeService {
    String create(String firstName, String lastName, String email, String userUuid);

    Set<EmployeeDto> findAll();

    Set<EmployeeDto> findByUserUuid(String userUuid);
}
