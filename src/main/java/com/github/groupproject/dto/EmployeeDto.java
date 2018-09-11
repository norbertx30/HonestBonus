package com.github.groupproject.dto;

import com.github.groupproject.entities.Employee;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;

public class EmployeeDto {

    @ApiModelProperty(notes = "API employee key")
    private String uuid;
    @ApiModelProperty(notes = "Employee first name")
    private String firstName;
    @ApiModelProperty(notes = "Employee last name")
    private String lastname;
    @ApiModelProperty(notes = "Employee email")
    @Email
    private String email;
    @ApiModelProperty(notes = "Company name")
    private String companyName;

    public EmployeeDto(Employee employee){
        this.uuid = employee.getUuid();
        this.firstName = employee.getFirstName();
        this.lastname = employee.getLastName();
        this.email = employee.getEmail();
        this.companyName = employee.getUser().getCompanyName();
    }

    public EmployeeDto(String uuid, String firstName, String lastname, String email, String companyName) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.companyName = companyName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }
}
