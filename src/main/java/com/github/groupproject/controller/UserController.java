package com.github.groupproject.controller;

import com.github.groupproject.dto.UserDto;
import com.github.groupproject.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/users")
@Api(value = "User HB panel" , description = "Get list of users, add new user")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userservice) {
        this.userService = userservice;
    }


    @PostMapping
    @ApiOperation(value = "Add User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User added")})
    public void create(@ApiParam(value = "Add company name", required = true)
                           @RequestParam(name = "Company Name") String companyName,
                       @ApiParam(value = "Add company email", required = true)
                       @RequestParam(name = "Company email") String email){
    userService.create(companyName, email);
    }

    @GetMapping
    @ApiOperation(value = "Get list of all users")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User found"),
                           @ApiResponse(code = 404, message = "Unsuccessful - user not found")})
    public Set<UserDto> findAll(){
        return userService.findAll();
    }



}
