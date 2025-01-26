package com.keschubay.discussions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.keschubay.discussions")
@EntityScan("com.keschubay.discussions.model")
@EnableJpaRepositories(basePackages = "com.keschubay.discussions.repository")
public class DiscussionsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscussionsProjectApplication.class, args);
	}

}
