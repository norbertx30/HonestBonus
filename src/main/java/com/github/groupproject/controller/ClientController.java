package com.github.groupproject.controller;

import com.github.groupproject.dto.ClientDto;
import com.github.groupproject.dto.EmployeeDto;
import com.github.groupproject.service.ClientService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/clients")
@Api(value = "/api/clients", description = "Get list of clients, add new client")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ApiOperation(value = "Create new client")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Client created"),
                           @ApiResponse(code = 404, message = "Invalid API user key")})
    public void create(@ApiParam(value = "Create new client", required = true)
                           @RequestParam(name = "Client Name") String clientName,
                       @ApiParam(value = "API user key", required = true, defaultValue = "uuid")
                       @RequestParam(name = "API key") String userUuid) {
        clientService.create(clientName,userUuid);
    }

    @GetMapping
    @ApiOperation("Get list of all clients")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - client list returned"),
                           @ApiResponse(code = 404, message = "Client not found !")})
    public Set<ClientDto> findAll(){
        return clientService.findAll();
    }

    @GetMapping("/{userUuid}")
    @ApiOperation("Get list of clients by API user key")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - client list returned"),
                           @ApiResponse(code = 404, message = "Client not found !")})
    public Set<ClientDto> findAllByUserUuid(@ApiParam(value = "API user key", required = true)
                                                @PathVariable String userUuid){
        return clientService.findAllByUserUuid(userUuid);
    }
}
