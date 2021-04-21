package ar.com.meli.challenge.mutants.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MutantServiceImplTest {

    @Autowired
    private MutantService mutantService;

    @Test
    void isMutantTrue() {

        String[] evalMutant = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

        assertTrue(mutantService.isMutant(evalMutant));

    }
}
