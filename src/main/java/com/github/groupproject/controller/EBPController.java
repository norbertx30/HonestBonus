package com.github.groupproject.controller;

import com.github.groupproject.dto.ClientDto;
import com.github.groupproject.dto.EBPDto;
import com.github.groupproject.service.EBPService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/ebps")
@Api(value = "Employee bonus promise", description = "Get list of employee bonus promises, add EBP")
public class EBPController {

    private EBPService ebpService;

    @Autowired
    public EBPController(EBPService ebpService) {
        this.ebpService = ebpService;
    }

    @PostMapping
    @ApiOperation("Create new bonus")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful EBP created"),
                           @ApiResponse(code = 404, message = "Invalid API key")})
    public void create(@ApiParam(value = "Add API bonus key", required = true , defaultValue = "uuid2")
                           @RequestParam(name = "API bonus key") String bonusUuid,
                       @ApiParam(value = "Add API employee key", required = true, defaultValue = "uuid3")
                       @RequestParam(name = "API employee key") String emloyeeUuid,
                       @ApiParam(value = "Add API client key", required = true, defaultValue = "uuid1")
                           @RequestParam(name = "API client key") String clientUuid){
        ebpService.create(bonusUuid,emloyeeUuid,clientUuid);
    }

    @GetMapping
    @ApiOperation("Get list of all employee bonus promises")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - employee bonus promises found"),
                           @ApiResponse(code = 404, message = "employee bonus promises not found !")})
    public Set<EBPDto> findAll() {
        return ebpService.findAll();
    }

    @GetMapping("byUser_/{userUuid}")
    @ApiOperation("Get list of employee bonus promises by API user key")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - employee bonus promises found"),
                           @ApiResponse(code = 404, message = "invalid API user key")})
    public Set<EBPDto> findAllByClientUserUuid(@ApiParam(value = "API user key", required = true,
                                             defaultValue = "uuid") @PathVariable String userUuid){
        return ebpService.findAllByClientUserUuid(userUuid);
    }

    @GetMapping("byEmployee_/{employeeUuid}")
    @ApiOperation("Get list of employee bonus promises by API employee key")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - employee bonus promises found"),
                           @ApiResponse(code = 404, message = "invalid API employee key")})
    public Set<EBPDto> findAllByEmployeeUuid(@ApiParam(value = "API employee key",
                                            required = true, defaultValue = "uuid3") @PathVariable String employeeUuid){
        return ebpService.findAllByEmployeeUuid(employeeUuid);
    }
}
