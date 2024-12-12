
package com.BookManagementApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.book")
@EnableMongoRepositories(basePackages = "com.book.repository")
public class BookManagementAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookManagementAppApplication.class, args);
    }
}

