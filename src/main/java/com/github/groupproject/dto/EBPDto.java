package com.github.groupproject.dto;

import com.github.groupproject.entities.EBP;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;

public class EBPDto {

    @ApiModelProperty(notes = "API employee bonus promise key")
    private String uuid;
    @ApiModelProperty(notes = "Bonus name")
    private String bonusName;
    @ApiModelProperty(notes = "Employee email")
    private String employeeEmail;
    @ApiModelProperty(notes = "Client name")
    private String clientName;

    public EBPDto(String uuid, String bonusName, String employeeEmail, String clientName) {
        this.uuid = uuid;
        this.bonusName = bonusName;
        this.employeeEmail = employeeEmail;
        this.clientName = clientName;
    }

    public EBPDto (EBP ebp) {
        this.uuid = ebp.getUuid();
        this.bonusName = ebp.getBonus().getName();
        this.employeeEmail = ebp.getEmployee().getEmail();
        this.clientName = ebp.getClient().getClientName();
    }

    public String getUuid() {
        return uuid;
    }

    public String getBonusName() {
        return bonusName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public String getClientName() {
        return clientName;
    }
}
