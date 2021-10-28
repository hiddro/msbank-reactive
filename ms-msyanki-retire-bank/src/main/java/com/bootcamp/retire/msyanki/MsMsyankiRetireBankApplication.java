package com.bootcamp.retire.msyanki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsMsyankiRetireBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsMsyankiRetireBankApplication.class, args);
	}

}
