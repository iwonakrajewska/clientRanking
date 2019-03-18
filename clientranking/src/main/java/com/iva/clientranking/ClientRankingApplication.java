package com.iva.clientranking;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ClientRankingApplication {

	public static void main(String[] args) {
		log.info("START ClientRankingApplication");

		SpringApplication app = new SpringApplication(ClientRankingApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.setBannerMode(Mode.OFF);
		app.run(args);

		log.info("FINISH ClientRankingApplication");
	}

}
