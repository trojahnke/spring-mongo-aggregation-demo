package com.example.mongodbaggregationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MongodbAggregationDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongodbAggregationDemoApplication.class, args);
    }
}
