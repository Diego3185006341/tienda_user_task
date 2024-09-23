package com.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
@SpringBootApplication
public class DB_store_testApplication {

	public static void main(String[] args) {
		SpringApplication.run(DB_store_testApplication.class, args);
	}

}
