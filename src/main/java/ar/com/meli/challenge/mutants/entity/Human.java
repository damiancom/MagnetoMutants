package ar.com.meli.challenge.mutants.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;

@FieldDefaults(level= AccessLevel.PRIVATE)
@QueryEntity
@Document
public class Human {

    @Id
    @Field("_id")
    @JsonIgnore
    String id;

    @Indexed(unique = true)
    @Getter
    String dna;
    boolean mutant;

    public Human (String[] dna, boolean mutant) {
        final String DELIMITER = ",";
        this.dna = String.join(DELIMITER, Arrays.asList(dna));
        this.mutant = mutant;
    }

}
