package com.github.groupproject.repository;

import com.github.groupproject.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{
    Transaction findOneByUuid(String transactionUuid);
    Set<Transaction> findAllBy();

    Set<Transaction> findAllByClientUuid(String clientUuid);

    Set<Transaction> findAllByClientUserUuid(String userUuid);
}
