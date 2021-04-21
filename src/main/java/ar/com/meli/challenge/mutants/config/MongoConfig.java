package ar.com.meli.challenge.mutants.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@FieldDefaults(level= AccessLevel.PRIVATE)
@EnableMongoRepositories(basePackages = "ar.com.meli.challenge.mutants.repository")
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    String mongoDbUri;

    @Value("${spring.data.mongodb.database}")
    String mongoDbDatabase;

    @Bean
    public MongoClient mongo() {
        final ConnectionString connectionString = new ConnectionString(mongoDbUri);
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), mongoDbDatabase);
    }
}
