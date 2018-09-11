package com.github.groupproject.dto;

import com.github.groupproject.entities.User;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;

public class UserDto {

    @ApiModelProperty(notes = "Api user key")
    private String uuid;
    @ApiModelProperty(notes = "Company name")
    private String companyName;
    @ApiModelProperty(notes = "Company email")
    @Email
    private String email;

    public UserDto(String uuid, String companyName, String email) {
        this.uuid = uuid;
        this.companyName = companyName;
        this.email = email;
    }

    public UserDto(User user){
        this.uuid = user.getUuid();
        this.companyName = user.getCompanyName();
        this.email = user.getEmail();
    }

    public String getUuid() {
        return uuid;
    }



    public String getCompanyName() {
        return companyName;
    }

    public String getEmail() {
        return email;
    }
}
