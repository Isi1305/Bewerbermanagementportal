package com.example.bewerbermanagementportal.controller;

import com.example.bewerbermanagementportal.service.NutzerService;
import org.springframework.stereotype.Controller;

@Controller
public class NutzerController {
    private final NutzerService nutzerService;

    public NutzerController(NutzerService nutzerService) {
        this.nutzerService = nutzerService;
    }
}
