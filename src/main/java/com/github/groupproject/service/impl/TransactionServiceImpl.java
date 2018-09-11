package com.github.groupproject.service.impl;

import com.github.groupproject.dto.TransactionDto;
import com.github.groupproject.entities.Client;
import com.github.groupproject.entities.Transaction;
import com.github.groupproject.entities.User;
import com.github.groupproject.exceptions.BadRequestException;
import com.github.groupproject.repository.ClientRepository;
import com.github.groupproject.repository.TransactionRepository;
import com.github.groupproject.repository.UserRepository;
import com.github.groupproject.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class TransactionServiceImpl implements TransactionService {

    private Logger LOG = LoggerFactory.getLogger(ClientServiceImpl.class);

    private TransactionRepository transactionRepository;
    private ClientRepository clientRepository;
    private UserRepository userRepository;


    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  ClientRepository clientRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String create(String clientUuid, BigDecimal amountOfTransaction) {
        Client client = clientRepository.findOneByUuid(clientUuid);
        if (client == null || amountOfTransaction.compareTo(BigDecimal.ZERO) < 0) {
            LOG.error("ERROR: [Request clientUuid]: " + clientUuid +
                    " [Request amountOfTransaction]: " + amountOfTransaction +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Given UUID of client does not exist " +
                    "or amount of transaction is wrong");
        }
        LOG.info("Created Transaction: [clientUuid]: " + clientUuid
                + " [amount]: " + amountOfTransaction);
        Transaction transaction = new Transaction();
        transaction.setClient(client);
        transaction.setAmountOfTransaction(amountOfTransaction);
        transaction = transactionRepository.save(transaction);

        return transaction.getUuid();
    }

    @Override
    public Set<TransactionDto> findAll() {
        return transactionRepository.findAllBy().stream()
                .map(TransactionDto::new)
                .collect(toSet());
    }

    @Override
    public Set<TransactionDto> findAllByClientUuid(String clientUuid) {
        Client client = clientRepository.findOneByUuid(clientUuid);
        if (client == null){
            LOG.error("ERROR: [Request clientUuid]: " + clientUuid +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Given UUID of client does not exist");
        }
        return transactionRepository.findAllByClientUuid(clientUuid).stream()
                .map(TransactionDto::new)
                .collect(toSet());
    }

    @Override
    public Set<TransactionDto> findAllByClientUserUuid(String userUuid) {
        User user = userRepository.findOneByUuid(userUuid);
        if (user == null){
            LOG.error("ERROR: [Request userUuid]: " + userUuid +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Given UUID of user does not exist");
        }
        return transactionRepository.findAllByClientUserUuid(userUuid).stream()
                .map(TransactionDto::new)
                .collect(toSet());
    }
}
