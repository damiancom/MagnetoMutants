package ar.com.meli.challenge.mutants.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HumanTest {

    @Test
    void test_equal_humans_with_same_dna_ok () {
        String[] dna = { "ATGCGA", "CAGGGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
        Human humanActual = new Human();
        humanActual.setDna(dna);

        Human humanExpect = new Human();
        humanExpect.setDna(dna);

        Assertions.assertEquals(humanExpect, humanActual);
    }

}
