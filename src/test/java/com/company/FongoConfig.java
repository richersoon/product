package com.company;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by richersoon on 7/12/16.
 */
@Configuration
public class FongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "demo";
    }

    @Bean
    @Override
    public Mongo mongo()  {
        final Fongo fongo = new Fongo(getDatabaseName());
        return fongo.getMongo();
    }

    @Bean
    @Override
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), getDatabaseName());
    }

}