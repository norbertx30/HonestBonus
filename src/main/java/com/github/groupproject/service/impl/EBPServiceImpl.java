package com.github.groupproject.service.impl;

import com.github.groupproject.dto.EBPDto;
import com.github.groupproject.entities.*;
import com.github.groupproject.exceptions.BadRequestException;
import com.github.groupproject.repository.*;
import com.github.groupproject.service.EBPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EBPServiceImpl implements EBPService {

    Logger LOG = LoggerFactory.getLogger(ClientServiceImpl.class);

    private EBPRepository ebpRepository;
    private BonusRepository bonusRepository;
    private EmployeeRepository employeeRepository;
    private ClientRepository clientRepository;
    private UserRepository userRepository;

    @Autowired
    public EBPServiceImpl(EBPRepository ebpRepository, BonusRepository bonusRepository,
                          EmployeeRepository employeeRepository, ClientRepository clientRepository, UserRepository userRepository) {
        this.ebpRepository = ebpRepository;
        this.bonusRepository = bonusRepository;
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String create(String bonusUuid, String employeeUuid, String clientUuid) {
        Bonus bonus = bonusRepository.findOneByUuid(bonusUuid);
        Employee employee = employeeRepository.findOneByUuid(employeeUuid);
        Client client = clientRepository.findOneByUuid(clientUuid);
        if (client == null || bonus == null || employee == null) {
            LOG.error("ERROR: [Request bonusUuid]: " + bonusUuid +
                    " [Request employeeUuid]: " + employeeUuid +
                    " [Request clientUuid]: " + clientUuid +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Given UUID of client or" +
                    "given Uuid of bonus or given Uuid of employee does not exist ");
        }
        LOG.info("Created EBP : [employeeUuid]: " + clientUuid + " [bonusUuid]: "
                + bonusUuid + " [clientUuid]: " + clientUuid);
    EBP ebp = new EBP();
        ebp.setBonus(bonusRepository.findOneByUuid(bonusUuid));
        ebp.setEmployee(employeeRepository.findOneByUuid(employeeUuid));
        ebp.setClient(clientRepository.findOneByUuid(clientUuid));
        ebpRepository.save(ebp);
        return ebp.getUuid();
}

    @Override
    public Set<EBPDto> findAll() {
        return ebpRepository.findAllBy().stream()
                .map(EBPDto::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<EBPDto> findAllByClientUserUuid(String userUuid) {
        User user = userRepository.findOneByUuid(userUuid);
        if (user == null){
            LOG.error("ERROR: [Request userUuid]: " + userUuid +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Given UUID of user does not exist");
        }
        return ebpRepository.findAllByClientUserUuid(userUuid).stream()
                .map(EBPDto::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<EBPDto> findAllByEmployeeUuid(String employeeUuid) {
        Employee employee = employeeRepository.findOneByUuid(employeeUuid);
        if (employee == null){
            LOG.error("ERROR: [Request employeeUuid]: " + employeeUuid +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Given UUID of employee does not exist");
        }
        return ebpRepository.findAllByEmployeeUuid(employeeUuid).stream()
                .map(EBPDto::new)
                .collect(Collectors.toSet());
    }
}
