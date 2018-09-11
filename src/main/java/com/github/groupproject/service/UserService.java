package com.github.groupproject.service;

import com.github.groupproject.dto.UserDto;

import java.util.Set;


public interface UserService {

    String create(String companyName, String email);

    Set<UserDto> findAll();

}