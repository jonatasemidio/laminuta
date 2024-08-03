package com.laminuta.laminuta;

import org.springframework.boot.SpringApplication;

public class TestLaminutaApplication {

	public static void main(String[] args) {
		SpringApplication.from(LaminutaApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
