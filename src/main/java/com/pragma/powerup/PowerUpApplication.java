package com.pragma.powerup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication()
public class PowerUpApplication {
	public static void main(String[] args) {

		SpringApplication.run(PowerUpApplication.class, args);
	}

}
