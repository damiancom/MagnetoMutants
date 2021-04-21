package ar.com.meli.challenge.mutants.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HumanTest {

    @Test
    void when_create_human_ok_and_convert_dna_string_ok () {
        String[] dna = { "ATGCGA", "CAGGGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        Human humanActual = new Human(dna, Boolean.TRUE);

        String dnaExpected = "ATGCGA,CAGGGC,TTATGT,AGAAGG,CCCCTA,TCACTG";

        Assertions.assertEquals(dnaExpected, humanActual.getDna());
    }
}
