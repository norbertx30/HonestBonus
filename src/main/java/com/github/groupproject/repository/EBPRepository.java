package com.github.groupproject.repository;

import com.github.groupproject.entities.EBP;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Set;

@Repository
public interface EBPRepository extends CrudRepository<EBP,Long> {
    EBP findOneByUuid(String ebpUuid);
    Set<EBP> findAllBy();

    Set<EBP> findAllByClientUserUuid(String userUuid);

    Set<EBP> findAllByEmployeeUuid(String employeeUuid);
}
