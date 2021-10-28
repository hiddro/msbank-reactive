package com.bootcamp.transaction.msyanki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsMsyankiTransactionBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsMsyankiTransactionBankApplication.class, args);
	}

}
