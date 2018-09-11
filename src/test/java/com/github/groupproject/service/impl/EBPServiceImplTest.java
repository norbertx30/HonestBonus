package com.github.groupproject.service.impl;

import com.github.groupproject.entities.EBP;
import com.github.groupproject.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EBPServiceImplTest {


    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BonusRepository bonusRepository;
    @Autowired
    private EBPRepository ebpRepository;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void whenCreatingEBP_ThenEBPExist() {

        EBPServiceImpl ebpService = new EBPServiceImpl(ebpRepository,bonusRepository,
                                                employeeRepository, clientRepository,
                                                    userRepository);

        String ebpUuid = ebpService.create("uuid2",
                    "uuid3",     "uuid1");
        EBP ebp = ebpRepository.findOneByUuid(ebpUuid);
        Assertions.assertThat(ebp)
                .isNotNull()
                .hasFieldOrPropertyWithValue("employee",employeeRepository.
                                                    findOneByUuid("uuid3"))
                .hasFieldOrPropertyWithValue("client",clientRepository.
                                                    findOneByUuid("uuid1"))
                .hasFieldOrPropertyWithValue("bonus",bonusRepository.
                                                    findOneByUuid("uuid2"));





    }
}
