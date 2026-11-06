package com.ejemplo.clima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String inicio() { return "index"; }

    @GetMapping("/pronostico")
    public String pronostico() { return "pronostico"; }

    @GetMapping("/contacto")
    public String contacto() { return "contacto"; }
}
