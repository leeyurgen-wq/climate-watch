package com.ejemplo.clima.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ejemplo.clima.model.Clima;
import com.ejemplo.clima.service.ClimaService;



@Controller
public class ClimaController {

    private final ClimaService climaService;

    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }

    // Mostrar formulario del clima
    @GetMapping("/clima")
    public String mostrarClima() {
        return "clima";
    }

    // Procesar búsqueda de ciudad
    @GetMapping("/clima/buscar")
    public String buscarClima(
            @RequestParam("ciudad") String ciudad,
            Model model) {

        try {

            Clima clima = climaService.obtenerClima(ciudad);

            model.addAttribute("clima", clima);

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    "No se pudo encontrar el clima de: " + ciudad
            );
        }

        return "clima";
    }
}




