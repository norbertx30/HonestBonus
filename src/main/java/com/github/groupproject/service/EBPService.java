package com.github.groupproject.service;

import com.github.groupproject.dto.EBPDto;

import java.util.Set;

public interface EBPService {
    String create(String bonusUuid, String employeeUuid, String clientUuid);
    Set<EBPDto> findAll();

    Set<EBPDto> findAllByClientUserUuid(String userUuid);

    Set<EBPDto> findAllByEmployeeUuid(String employeeUuid);
}
