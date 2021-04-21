package ar.com.meli.challenge.mutants.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class Statistics {

    @JsonProperty("count_mutant_dna")
    int countMutantDna;
    @JsonProperty("count_human_dna")
    int countHumanDna;
    double ratio;

}
