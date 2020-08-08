package com.example.mongodbaggregationdemo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<DataEntity, String> { }
