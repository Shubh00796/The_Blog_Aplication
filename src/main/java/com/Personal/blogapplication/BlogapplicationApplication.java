package com.Personal.blogapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BlogapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogapplicationApplication.class, args);
	}

}
