package com.vasl.samp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class VaslProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaslProjectApplication.class, args);
	}

}
