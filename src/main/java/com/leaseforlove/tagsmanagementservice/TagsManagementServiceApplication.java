package com.leaseforlove.tagsmanagementservice;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableMongock
public class TagsManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagsManagementServiceApplication.class, args);
	}

}
