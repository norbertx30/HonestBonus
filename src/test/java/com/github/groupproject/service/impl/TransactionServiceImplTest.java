package com.github.groupproject.service.impl;

import com.github.groupproject.entities.Transaction;
import com.github.groupproject.repository.ClientRepository;
import com.github.groupproject.repository.TransactionRepository;
import com.github.groupproject.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionServiceImplTest {


    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void whenCreatingTransaction_ThenTransactionExist() {

        TransactionServiceImpl transactionService = new TransactionServiceImpl(transactionRepository,
                                                    clientRepository, userRepository);

        String transactionUuid = transactionService.create("uuid1",new BigDecimal("100000"));

        Transaction transaction = transactionRepository.findOneByUuid(transactionUuid);
        Assertions.assertThat(transaction)
                .isNotNull()
                .hasFieldOrPropertyWithValue("client",
                                                clientRepository.findOneByUuid("uuid1"))
                .hasFieldOrPropertyWithValue("amountOfTransaction", new BigDecimal("100000"));

    }
}
