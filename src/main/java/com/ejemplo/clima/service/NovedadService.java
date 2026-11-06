package com.ejemplo.clima.service;

import com.ejemplo.clima.model.Novedad;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class NovedadService {
    private final ObjectMapper objectMapper;

    public NovedadService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Novedad> obtenerNovedades() {
        try (InputStream inputStream = new ClassPathResource("data/novedades.json").getInputStream()) {
            return objectMapper.readValue(inputStream, new TypeReference<List<Novedad>>() {});
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo leer data/novedades.json", e);
        }
    }
}
