package com.github.groupproject.controller;

import com.github.groupproject.dto.EmployeeDto;
import com.github.groupproject.service.EmployeeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/employees")
@Api(value = "Employee", description = "Get list of employees, add new employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ApiOperation("Create new employee")
    @ApiResponses( value = {@ApiResponse(code = 200, message = "Employee created"),
                            @ApiResponse(code = 404, message = "Invalid API user key")})
    public void create(@ApiParam(value = "Employee first name", required = true)
                           @RequestParam(name = "First name") String firstName,
                       @ApiParam(value = "Employee last name", required = true)
                       @RequestParam(name = "Last name") String lastName,
                       @ApiParam(value = "Employee email", required = true)
                           @RequestParam(name = "Email") String email,
                       @ApiParam(value = "API user key", defaultValue = "uuid", required = true)
                           @RequestParam(name = "API key") String userUuid){
        employeeService.create(firstName, lastName, email, userUuid);
    }

    @GetMapping
    @ApiOperation("Get list of all employees")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - employee list generated"),
                           @ApiResponse(code = 404, message = "Employee not found !")})
    public Set<EmployeeDto> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/{userUuid}")
    @ApiOperation("Get list of employees by API user key")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - employee list generated"),
                           @ApiResponse(code = 404, message = "invalid API user key")})
    public Set<EmployeeDto> findAllByUserUuid(@ApiParam(value = "API user key", required = true,
            defaultValue = "uuid")@PathVariable String userUuid){
        return employeeService.findByUserUuid(userUuid);
    }
}
