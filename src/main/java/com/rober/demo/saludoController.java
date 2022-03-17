package com.rober.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class saludoController {

    private static final String plantilla="Hola, %s!";
    private final AtomicLong contador = new AtomicLong();

    @GetMapping("/saludo")
    public saludo verSaludo(@RequestParam(value="nombre", defaultValue = "Mundo") String nombre){
        return new saludo(contador.incrementAndGet(), String.format(plantilla,nombre));
    }

}
