package com.github.groupproject.controller;


import com.github.groupproject.dto.SetPasswordDto;
import com.github.groupproject.service.PasswordService;
import com.github.groupproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class PasswordController {

    private PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @GetMapping("/set_password/{uuid}")
    public String signUp(@PathVariable String uuid, Model model) {

        SetPasswordDto dto = new SetPasswordDto();
        dto.setUuid(uuid);
        model.addAttribute("dto", dto);
        return "rejestracjaform";
    }

    @PutMapping("/set_password")
    public String setPassword(SetPasswordDto dto) {
        passwordService.setPassword(dto);
        return "login";
    }

}
