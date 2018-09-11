package com.github.groupproject.service;

import com.github.groupproject.dto.PaymentDto;

import java.util.Set;

public interface PaymentService {
    String create(String transactionUuid, String ebpUuid);

    Set<PaymentDto> findAll();

    Set<PaymentDto> findAllByEBPClientUserUuid(String userUuid);

    Set<PaymentDto> findAllByEbpClientUuid(String clientUuid);

    Set<PaymentDto> findAllByEbpEmployeeUuid(String employeeUuid);

    Set<PaymentDto> findAllByEbpBonusUuid(String bonusUuid);
}
