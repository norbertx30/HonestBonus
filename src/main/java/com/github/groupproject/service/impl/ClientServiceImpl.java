package com.github.groupproject.service.impl;

import com.github.groupproject.dto.ClientDto;
import com.github.groupproject.entities.Client;
import com.github.groupproject.entities.User;
import com.github.groupproject.exceptions.BadRequestException;
import com.github.groupproject.repository.ClientRepository;
import com.github.groupproject.repository.UserRepository;
import com.github.groupproject.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    Logger LOG = LoggerFactory.getLogger(ClientServiceImpl.class);


    private ClientRepository clientRepository;
    private UserRepository userRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String create(String clientName, String userUuid) {
        User user = userRepository.findOneByUuid(userUuid);
        if (user == null){
            LOG.error("ERROR: [Request userUuid]: " + userUuid +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Given UUID of user does not exist");
        }
        LOG.info("Created Client: [userUuid]: " + userUuid);
        Client client = new Client();
        client.setClientName(clientName);
        client.setUser(user);
        clientRepository.save(client);
        return client.getUuid();
    }

    @Override
    public Set<ClientDto> findAll() {
        return clientRepository.findAllBy().stream()
                .map(ClientDto::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ClientDto> findAllByUserUuid(String userUuid) {
        User user = userRepository.findOneByUuid(userUuid);
        if (user == null){
            LOG.error("ERROR: [Request userUuid]: " + userUuid +
                    " [cause]: Bad Request" );
            throw new BadRequestException("Given UUID of user does not exist");
        }
        return clientRepository.findAllByUserUuid(userUuid).stream()
                .map(ClientDto::new)
                .collect(Collectors.toSet());
    }


}
