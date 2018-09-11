package com.github.groupproject.service.impl;

import com.github.groupproject.dto.PaymentDto;
import com.github.groupproject.entities.*;
import com.github.groupproject.exceptions.BadRequestException;
import com.github.groupproject.repository.*;
import com.github.groupproject.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class PaymentServiceImpl implements PaymentService {

    private Logger LOG = LoggerFactory.getLogger(ClientServiceImpl.class);

    private PaymentRepository paymentRepository;
    private TransactionRepository transactionRepository;
    private EBPRepository ebpRepository;
    private UserRepository userRepository;
    private ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;
    private BonusRepository bonusRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, TransactionRepository transactionRepository,
                              EBPRepository ebpRepository, UserRepository userRepository,
                              ClientRepository clientRepository, EmployeeRepository employeeRepository,
                              BonusRepository bonusRepository) {
        this.paymentRepository = paymentRepository;
        this.transactionRepository = transactionRepository;
        this.ebpRepository = ebpRepository;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.bonusRepository = bonusRepository;
    }

    @Override
    public String create(String transactionUuid, String ebpUuid) {
        Transaction transaction = transactionRepository.findOneByUuid(transactionUuid);
        EBP ebp = ebpRepository.findOneByUuid(ebpUuid);
        if (transaction == null || ebp == null) {
            LOG.error("ERROR: [Request transactionUuid]: " + transactionUuid +
                    " [Request ebpUuid]: " + ebpUuid + " [cause]: Bad Request" );

            throw new BadRequestException("Given UUID of transaction or UUID of employee bonus promise" +
                            "does not exist");
        }
        LOG.info("Created Payment: [transactionUuid]: " + transactionUuid + " [ebpUuid]: " + ebpUuid);
        Payment payment = new Payment();
        payment.setTransaction(transaction);
        payment.setEbp(ebp);
        payment.setPaycheck(countPaycheck(transaction, ebp));
        payment = paymentRepository.save(payment);
        return payment.getUuid();
    }

    @Override
    public Set<PaymentDto> findAll() {
        return paymentRepository.findAllBy().stream()
                .map(PaymentDto::new)
                .collect(toSet());
    }

    @Override
    public Set<PaymentDto> findAllByEBPClientUserUuid(String userUuid) {
        User user = userRepository.findOneByUuid(userUuid);
        if(user == null) {
            LOG.error("ERROR: [Request userUuid]: " + userUuid +
                    " [cause]: Bad Request ");
            throw new BadRequestException("Given UUID of user does not exist");
        }
        return paymentRepository.findAllByEbpClientUserUuid(userUuid).stream()
                .map(PaymentDto::new)
                .collect(toSet());
    }

    @Override
    public Set<PaymentDto> findAllByEbpClientUuid(String clientUuid) {
        Client client = clientRepository.findOneByUuid(clientUuid);
        if(client == null){
            LOG.error("ERROR: [Request clientUuid]: " + clientUuid +
                      " [cause]: Bad Request");
            throw new BadRequestException("Given UUID of client does not exist");
        }
        return paymentRepository.findAllByEbpClientUuid(clientUuid).stream()
                .map(PaymentDto::new)
                .collect(toSet());
    }

    @Override
    public Set<PaymentDto> findAllByEbpEmployeeUuid(String employeeUuid) {
        Employee employee = employeeRepository.findOneByUuid(employeeUuid);
        if(employee == null){
            LOG.error("ERROR: [Request clientUuid]: " + employeeUuid +
                    " [cause]: Bad Request");
            throw new BadRequestException("Given UUID of employee does not exist");
        }
        return paymentRepository.findAllByEbpEmployeeUuid(employeeUuid).stream()
                .map(PaymentDto::new)
                .collect(toSet());
    }

    @Override
    public Set<PaymentDto> findAllByEbpBonusUuid(String bonusUuid) {
        Bonus bonus = bonusRepository.findOneByUuid(bonusUuid);
        if(bonus == null){
            LOG.error("ERROR: [Request clientUuid]: " + bonusUuid +
                    " [cause]: Bad Request");
            throw new BadRequestException("Given UUID of bonus does not exist");
        }
        return paymentRepository.findAllByEbpBonusUuid(bonusUuid).stream()
                .map(PaymentDto::new)
                .collect(toSet());
    }

    private BigDecimal countPaycheck(Transaction transaction, EBP ebp) {
        return transaction.getAmountOfTransaction()
                .multiply(new BigDecimal(ebp.getBonus().getShareOfTransaction()));
    }
}
