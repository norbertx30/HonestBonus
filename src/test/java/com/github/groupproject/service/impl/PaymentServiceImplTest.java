package com.github.groupproject.service.impl;

import com.github.groupproject.entities.Payment;
import com.github.groupproject.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentServiceImplTest {


    @Autowired
    private EBPRepository ebpRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BonusRepository bonusRepository;
    @Test
    public void whenCreatingPayment_ThenPaymentExist() {

        PaymentServiceImpl paymentService = new PaymentServiceImpl(paymentRepository,
                                                transactionRepository,ebpRepository,userRepository,
                                                clientRepository, employeeRepository, bonusRepository);

        String paymentUuid = paymentService.create("uuid4","uuid5");
        Payment payment = paymentRepository.findOneByUuid(paymentUuid);
        Assertions.assertThat(payment)
                .isNotNull()
                .hasFieldOrPropertyWithValue("transaction",transactionRepository.
                        findOneByUuid("uuid4"))
                .hasFieldOrPropertyWithValue("ebp",ebpRepository.
                        findOneByUuid("uuid5"));

    }
}
