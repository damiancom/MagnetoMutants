package ar.com.meli.challenge.mutants.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class StatisticsDto {

    @JsonProperty("count_mutant_dna")
    long countMutantDna;
    @JsonProperty("count_human_dna")
    long countHumanDna;
    double ratio;

    public StatisticsDto (long countHumanDna, long countMutantDna) {
        this.countHumanDna = countHumanDna;
        this.countMutantDna = countMutantDna;
        this.ratio = calculateRatio(countMutantDna, countHumanDna);
    }

    private double calculateRatio (Long countMutant, Long countHuman) {
        if (countHuman == 0)
            return countMutant;
        else
            return countMutant.doubleValue() / countHuman.doubleValue();
    }
}
