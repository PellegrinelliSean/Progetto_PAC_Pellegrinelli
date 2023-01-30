package com.ourbooks.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * La Classe WebServerApplication. Avvia il web server.
 */
@SpringBootApplication
@EnableMongoRepositories
public class WebServerApplication {

	/**
	 * Il metodo main.
	 *
	 * @param args gli arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebServerApplication.class, args);
	}

}
