package com.cocktail_dakk;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		servers = {
				@Server(url="https://www.cocktaildakk.shop", description = "CocktailDakk Server URL"),
				@Server(url="http://localhost:8080", description = "CocktailDakk Local URL")
		}
)
@SpringBootApplication
public class CocktailDakkApplication {
	public static void main(String[] args) {
		SpringApplication.run(CocktailDakkApplication.class, args);
	}
}
