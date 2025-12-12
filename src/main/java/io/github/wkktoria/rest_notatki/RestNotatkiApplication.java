package io.github.wkktoria.rest_notatki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestNotatkiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestNotatkiApplication.class, args);
	}

}
