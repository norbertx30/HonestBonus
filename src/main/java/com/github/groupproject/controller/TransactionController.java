package com.github.groupproject.controller;

import com.github.groupproject.dto.ClientDto;
import com.github.groupproject.dto.TransactionDto;
import com.github.groupproject.service.TransactionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@Api(value = "Transactions", description = "Get list of transaction, create new transaction")
@RequestMapping("/api/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ApiOperation("Create new transaction")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Transaction created"),
                           @ApiResponse(code = 404, message = "invalid API client key")})
    public void create(@ApiParam(value = "Add API client key", required = true, defaultValue = "uuid1")
                           @RequestParam(name = "API client key") String clientUuid,
                       @ApiParam(value = "Declare amount of transaction", required = true)
                       @RequestParam(name = "Amount") BigDecimal amountOfTransaction){
        transactionService.create(clientUuid,amountOfTransaction);
    }

    @GetMapping
    @ApiOperation("Get list of all transactions")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - transaction list returned"),
                           @ApiResponse(code = 404, message = "Transactions not found !")})
    public Set<TransactionDto> findAll(){
        return transactionService.findAll();
    }

    @GetMapping("/byClient_{clientUuid}")
    @ApiOperation("Get list of transactions by API cl key")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - transaction list returned"),
                           @ApiResponse(code = 404, message = "Invalid API client key")})
    public Set<TransactionDto> findAllByClientUuid(@ApiParam(value = "Add API client key",
            required = true, defaultValue = "uuid1")@PathVariable String clientUuid){
        return transactionService.findAllByClientUuid(clientUuid);
    }

    @GetMapping("/byUser_{userUuid}")

    @ApiOperation("Get list transactions by API user key")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - transaction list returned"),
                           @ApiResponse(code = 404, message = "Invalid API user key")})
    public Set<TransactionDto> findAllByClientUserUuid(@ApiParam(value = "Add API user key",
            required = true, defaultValue = "uuid")@PathVariable String userUuid){
        return transactionService.findAllByClientUserUuid(userUuid);
    }
}
