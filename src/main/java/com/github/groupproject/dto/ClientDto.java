package com.github.groupproject.dto;

import com.github.groupproject.entities.Client;
import io.swagger.annotations.ApiModelProperty;


public class ClientDto {

    @ApiModelProperty(notes = "API client key")
    private String uuid;
    @ApiModelProperty(notes = "Name of client")
    private String clientName;
    @ApiModelProperty(notes = "Name of company - user name")
    private String companyName;

    public ClientDto(String uuid, String clientName, String companyName) {
        this.uuid = uuid;
        this.clientName = clientName;
        this.companyName = companyName;
    }

    public ClientDto ( Client client) {
        this.uuid = client.getUuid();
        this.clientName = client.getClientName();
        this.companyName = client.getUser().getCompanyName();
    }

    public String getUuid() {
        return uuid;
    }

    public String getClientName() {
        return clientName;
    }

    public String getCompanyName() {
        return companyName;
    }
}
