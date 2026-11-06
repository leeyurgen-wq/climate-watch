package com.ejemplo.clima.controller;

import com.ejemplo.clima.model.Novedad;
import com.ejemplo.clima.service.NovedadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NovedadController {
    private final NovedadService novedadService;

    public NovedadController(NovedadService novedadService) {
        this.novedadService = novedadService;
    }

    @GetMapping("/novedades")
    public String mostrarNovedades(Model model) {
        model.addAttribute("novedades", novedadService.obtenerNovedades());
        return "novedades";
    }

    @GetMapping("/api/novedades")
    @ResponseBody
    public List<Novedad> apiNovedades() {
        return novedadService.obtenerNovedades();
    }
}
