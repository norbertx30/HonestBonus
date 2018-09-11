package com.github.groupproject.controller;


import com.github.groupproject.dto.EBPDto;
import com.github.groupproject.dto.PaymentDto;
import com.github.groupproject.service.PaymentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/payments")
@Api(value = "Payments", description = "Get list of payments, create new payments")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) { this.paymentService = paymentService; }

    @PostMapping
    @ApiOperation("Create new pament")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "PAyment created"),
                           @ApiResponse(code = 404, message = "Invalid API transaction or employee bonus promise key")})
    public void create (@ApiParam(value = "Add API transaction key", required = true, defaultValue = "uuid4")
                            @RequestParam(name = "API transaction key") String transactionUuid,
                       @ApiParam(value = "Add API employee bonus promise key", required = true, defaultValue = "uuid5")
                       @RequestParam(name = "API EBP key") String ebpUuid){
        paymentService.create(transactionUuid, ebpUuid);
    }

    @GetMapping
    @ApiOperation("Get list of all payments")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - payment list returned"),
                           @ApiResponse(code = 404, message = "Payments not found !")})
    Set<PaymentDto> findAll (){
         return paymentService.findAll();
    }

    @GetMapping("byUser_/{userUuid}")
    @ApiOperation("Get list of payments by API user key")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - payment list returned"),
                           @ApiResponse(code = 404, message = "Invalid API user key")})
    public Set<PaymentDto> findAllByEBPClientUserUuid(@ApiParam(value = "Add Api user key", required = true,
            defaultValue = "uuid")@PathVariable String userUuid){
        return paymentService.findAllByEBPClientUserUuid(userUuid);
    }

    @GetMapping("byClient_/{clientUuid}")
    @ApiOperation("Get list of payments by API client key")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - payment list returned"),
                           @ApiResponse(code = 404, message = "Invalid API client key")})
    public Set<PaymentDto> findAllByEbpClientUuid(@ApiParam(value = "Add API client key", required = true,
            defaultValue = "uuid1")@PathVariable String clientUuid){
        return paymentService.findAllByEbpClientUuid(clientUuid);
    }

    @GetMapping("byEmployee_/{employeeUuid}")
    @ApiOperation("Get list of payments by API employee key")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - payment list returned"),
                           @ApiResponse(code = 404, message = "Invalid API employee key")})
    public Set<PaymentDto> findAllByEbpEmployeeUuid(@ApiParam(value = "Add API employee key", required = true,
            defaultValue = "uuid3")@PathVariable String employeeUuid){
        return paymentService.findAllByEbpEmployeeUuid(employeeUuid);
    }

    @GetMapping("byBonus_/{bonusUuid}")
    @ApiOperation("Get list of payments by API bonus key")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - payment list returned"),
                           @ApiResponse(code = 404, message = "Invalid API bonus key")})
    public Set<PaymentDto> findAllByEbpBonusUuid(@ApiParam(value = "Add API bonus key", required = true,
            defaultValue = "uuid2")@PathVariable String bonusUuid){
        return paymentService.findAllByEbpBonusUuid(bonusUuid);
    }
}
