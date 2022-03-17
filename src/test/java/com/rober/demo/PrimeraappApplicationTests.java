package com.rober.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PrimeraappApplicationTests {
	@Autowired
	private saludoController saludo;
	@Test
	void contextLoads() {
		assertEquals(saludo.verSaludo("Mundo").getContenido(),"Hola, Mundo!");
	}

}
