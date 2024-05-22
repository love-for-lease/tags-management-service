package com.matchmate.tagsmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;



@SpringBootApplication
public class TagsManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagsManagementServiceApplication.class, args);
	}

}
