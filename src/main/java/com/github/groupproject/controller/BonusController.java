package com.github.groupproject.controller;

import com.github.groupproject.dto.BonusDto;
import com.github.groupproject.dto.ClientDto;
import com.github.groupproject.service.BonusService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/bonuses")
@Api(value = "Bonus", description = "Get list of bonuses, add new bonus")
public class BonusController {

    private BonusService bonusService;

    @Autowired
    public BonusController(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    @PostMapping
    @ApiOperation("Create new bonus")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Client created"),
                           @ApiResponse(code = 404, message = "Invalid API user key")})
    public void create(@ApiParam(value = "Add bonus", required = true)
                           @RequestParam(name = "Bonus name") String name,
                       @ApiParam(value = "Set transaction share (e.g. 0.06 as 6%)", required = true)
                       @RequestParam(name = "Share of transaction") Double shareOfTransaction,
                       @ApiParam(value = "Set duration of bonus in days", required = true)
                           @RequestParam(name = "Bonus timeout") Integer timeOutInDays,
                       @ApiParam(value = "API user key", required = true, defaultValue = "uuid")
                           @RequestParam(name = "API key") String userUuid) {
        bonusService.create(name, shareOfTransaction, timeOutInDays, userUuid);
    }

    @GetMapping
    @ApiOperation("Get list of all bonuses")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - client list returned"),
                           @ApiResponse(code = 404, message = "Client not found !")})
    public Set<BonusDto> findAll(){
        return bonusService.findAll();
    }

    @GetMapping("/{userUuid}")
    @ApiOperation("Get list of bonuses by API user key")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful - client list returned"),
                           @ApiResponse(code = 404, message = "Client not found !")})
    public Set<BonusDto> findAllByUserUuid(@ApiParam(value = "API user key", required = true, defaultValue = "uuid")
                                               @PathVariable String userUuid){
        return bonusService.findAllByUserUuid(userUuid);
    }
}
