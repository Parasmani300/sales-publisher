package com.example.salespublisher;

import com.example.salespublisher.config.RootConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({RootConfig.class})
@SpringBootApplication
public class SalesPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesPublisherApplication.class, args);
	}

}
