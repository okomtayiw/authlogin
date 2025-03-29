package com.auth.authlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.auth")
@EnableJpaRepositories(basePackages = "com.auth.repository")
@EntityScan(basePackages = "com.auth.entity")
public class AuthLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthLoginApplication.class, args);
	}

}
