package com.example.mongodbaggregationdemo;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader {
    private final DataRepository dataRepository;
    private final Faker faker = Faker.instance();
    private final MongoTemplate mongoTemplate;

    @PostConstruct
    public void loadData(){
        int i = 0;
        while(i < 10){
            dataRepository.insert(DataEntity.builder()
                    .data(faker.princessBride().quote())
                    .location("princessBride")
                    .build());
            i++;
        }
        queryTests();
    }

    private void queryTests(){
        List<String> ids = new ArrayList<>();
        dataRepository.findAll().forEach(dataEntity -> ids.add(dataEntity.getId()));
        List<AggregationEntity> results = mongoTemplate.aggregate(
                Aggregation.newAggregation(Aggregation.match(Criteria.where("id").in(ids)), Aggregation.group("data", "location")), DataEntity.class, AggregationEntity.class).getMappedResults();
        Assert.notEmpty(results, "Mapped Results Should Not be Empty.");
        Assert.notNull(results.get(0).getData(), "Data should not be null");
        Assert.notNull(results.get(0).getLocation(), "Location should not be null");
    }
}
