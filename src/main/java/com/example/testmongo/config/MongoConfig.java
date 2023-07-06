package com.example.testmongo.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import static java.util.Collections.singletonList;

@Configuration
@EnableConfigurationProperties
public class MongoConfig {

    @Bean(name = "mongoProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb")
    @Primary
    public MongoProperties mongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "mongoClient")
    public MongoClient mongoClient(@Qualifier("mongoProperties") MongoProperties mongoProperties) {

//        MongoCredential credential = MongoCredential
//                .createCredential(mongoProperties.getUsername(), mongoProperties.getAuthenticationDatabase(), mongoProperties.getPassword());

        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder
                        .hosts(singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
//                .credential(credential)
                .build());
    }

    @Bean(name = "mongoDBFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(
            @Qualifier("mongoClient") MongoClient mongoClient,
            @Qualifier("mongoProperties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("mongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }
}
