package com.gruposalinas.msdprppreciosvtex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gruposalinas")
public class MsdApplication {

	public static void main(String[] args) {
		System.setProperty("name.app", "msdprppreciosvtex");
		SpringApplication.run(MsdApplication.class, args);
	}

}
