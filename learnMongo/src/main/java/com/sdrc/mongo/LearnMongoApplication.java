package com.sdrc.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sdrc.mongo.resolver.Query;

@SpringBootApplication
public class LearnMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnMongoApplication.class, args);
	}

//	@Bean
//	public Query query() {
//		return new Query();
//	}
}
