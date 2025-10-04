package seu.edu.bd.southeast_portal;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SoutheastPortalApplication {

	public static void main(String[] args) {

		SpringApplication.run(SoutheastPortalApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Southeast Portal API")
						.description("Documentation for Southeast Portal API. Documented all the endpoints and working methods")
						.version("v1.0.0")
						.license(new License().name("Copyright © 2024 Tanjim Abubokor All Rights Reserved.").url("http://tanjim-abubokor.github.io/")))
				.externalDocs(new ExternalDocumentation()
						.description("Southeast University")
						.url("https://seu.ac.edu.bd"));
	}

}
