package com.cocktail_dakk;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
		servers = {
				@Server(url="https://www.cocktaildakk.shop", description = "CocktailDakk Server URL")
		}
)
@SpringBootApplication
public class CocktailDakkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocktailDakkApplication.class, args);
	}
}
