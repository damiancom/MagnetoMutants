package ar.com.meli.challenge.mutants.api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Human implements Serializable {

    String[] dna;

}
