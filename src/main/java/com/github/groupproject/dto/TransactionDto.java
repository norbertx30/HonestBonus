package com.github.groupproject.dto;

import com.github.groupproject.entities.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class TransactionDto {

    @ApiModelProperty(notes = "API transaction key")
    private String uuid;
    @ApiModelProperty(notes = "Client name")
    private String clientName;
    @ApiModelProperty(notes = "Amount of transaction")
    private BigDecimal amountOfTransaction;

    public TransactionDto(String uuid, String clientName, BigDecimal amountOfTransaction) {
        this.uuid = uuid;
        this.clientName = clientName;
        this.amountOfTransaction = amountOfTransaction;
    }

    public TransactionDto(Transaction transaction) {
        this.uuid = transaction.getUuid();
        this.clientName = transaction.getClient().getClientName();
        this.amountOfTransaction = transaction.getAmountOfTransaction();
    }

    public BigDecimal getAmountOfTransaction() {
        return amountOfTransaction;
    }

    public String getUuid() {
        return uuid;
    }

    public String getClientName() {
        return clientName;
    }
}
