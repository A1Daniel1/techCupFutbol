package edu.eci.dosw.tech_cup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "edu.eci.dosw.tech_cup.entities")
@EnableJpaRepositories(basePackages = "edu.eci.dosw.tech_cup.repositories")
public class TechCupApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechCupApplication.class, args);
	}

}
