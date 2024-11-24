package com.example.walletApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.walletApi")
public class RestApiWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiWalletApplication.class, args);
	}

}
