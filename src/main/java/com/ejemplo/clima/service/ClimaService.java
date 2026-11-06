package com.ejemplo.clima.service;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.ejemplo.clima.model.Clima;


@Service
public class ClimaService {

    @Value("${openweather.api.key}")
    private String apiKey;

    private final RestClient restClient = RestClient.create();

    public Clima obtenerClima(String ciudad) {

        // Construir URL de OpenWeather
        URI uri = UriComponentsBuilder
                .fromUriString(
                        "https://api.openweathermap.org/data/2.5/weather"
                )
                //parametros de consulta para de url del navegador 
                .queryParam("q", ciudad)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .queryParam("lang", "es")
                .build()
                .encode()
                .toUri();

        // Realizar petición GET
        Map<String, Object> respuesta = restClient.get()
                .uri(uri)
                .retrieve()
                .body(Map.class);

        // Verificar respuesta
        if (respuesta == null) {
            throw new RuntimeException(
                    "OpenWeather no devolvió información."
            );
        }

        // Crear objeto Clima
        Clima clima = new Clima();

        // =========================
        // CIUDAD
        // =========================
        clima.setCiudad(
                String.valueOf(
                        respuesta.getOrDefault("name", "")
                )
        );

        // =========================
        // PAÍS
        // =========================
        Map<String, Object> sys = mapa(
                respuesta.get("sys")
        );

        clima.setPais(
                String.valueOf(
                        sys.getOrDefault("country", "")
                )
        );

        // =========================
        // TEMPERATURAS Y HUMEDAD
        // =========================
        Map<String, Object> main = mapa(
                respuesta.get("main")
        );

        clima.setTemperatura(
                numero(main.get("temp"))
        );

        clima.setSensacion(
                numero(main.get("feels_like"))
        );

        clima.setTemperaturaMin(
                numero(main.get("temp_min"))
        );

        clima.setTemperaturaMax(
                numero(main.get("temp_max"))
        );

        clima.setHumedad(
                entero(main.get("humidity"))
        );

        clima.setPresion(
                entero(main.get("pressure"))
        );

        // =========================
        // VIENTO
        // =========================
        Map<String, Object> wind = mapa(
                respuesta.get("wind")
        );

        clima.setViento(
                numero(wind.get("speed"))
        );

        // =========================
        // NUBOSIDAD
        // =========================
        Map<String, Object> clouds = mapa(
                respuesta.get("clouds")
        );

        clima.setNubosidad(
                entero(clouds.get("all"))
        );

        // =========================
        // DESCRIPCIÓN E ICONO
        // =========================
        Object weatherObject = respuesta.get("weather");

        if (weatherObject instanceof List<?> lista
                && !lista.isEmpty()
                && lista.get(0) instanceof Map<?, ?>) {

            Map<?, ?> actual = (Map<?, ?>) lista.get(0);

            Object descripcion = actual.get("description");
            Object icono = actual.get("icon");

            clima.setDescripcion(
                    descripcion != null
                            ? descripcion.toString()
                            : ""
            );

            clima.setIcono(
                    icono != null
                            ? icono.toString()
                            : ""
            );
        }

        return clima;
    }

    // =========================
    // MÉTODO PARA CONVERTIR A MAP
    // =========================
    @SuppressWarnings("unchecked")
    private Map<String, Object> mapa(Object valor) {

        if (valor instanceof Map<?, ?>) {
            return (Map<String, Object>) valor;
        }

        return Map.of();
    }

    // =========================
    // CONVERTIR A DOUBLE
    // =========================
    private double numero(Object valor) {

        if (valor instanceof Number) {
            return ((Number) valor).doubleValue();
        }

        return 0.0;
    }

    // =========================
    // CONVERTIR A INT
    // =========================
    private int entero(Object valor) {

        if (valor instanceof Number) {
            return ((Number) valor).intValue();
        }

        return 0;
    }
}

