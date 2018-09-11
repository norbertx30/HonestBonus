package com.github.groupproject.repository;

import com.github.groupproject.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long>{
    Employee findOneByUuid(String employeeUuid);

    Employee findOneByEmail(String email);

    Set<Employee> findAllBy ();

    Set<Employee> findAllByUserUuid(String userUuid);

}


