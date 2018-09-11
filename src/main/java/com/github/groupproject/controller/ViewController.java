package com.github.groupproject.controller;

import com.github.groupproject.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private BonusService bonusService;

    @Autowired
    public ViewController(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    @GetMapping("/views/bonuses")
    public String getBonuses(Model model) {
        model.addAttribute("bonuses", bonusService.findAllOfLoggedUser());
        return "bonus";
    }
}
