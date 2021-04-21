package ar.com.meli.challenge.mutants.services;

import ar.com.meli.challenge.mutants.analyzer.Analyzer;
import ar.com.meli.challenge.mutants.repository.HumanRepository;
import ar.com.meli.challenge.mutants.services.impl.MutantServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@FieldDefaults(level= AccessLevel.PRIVATE)
@ExtendWith(MockitoExtension.class)
class MutantServiceImplTest {

    @Mock
    Analyzer analyzer;

    @Mock
    HumanRepository humanRepository;

    @InjectMocks
    MutantServiceImpl mutantService;

    @Test
    void when_receive_mutant_dna_and_not_in_ddbb_then_return_true() {
        String[] evalMutantActual = {"ATGCGA", "CAGGGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        String dnaActual = "ATGCGA,CAGGGC,TTATGT,AGAAGG,CCCCTA,TCACTG";

        when(analyzer.analyzeDna(evalMutantActual)).thenReturn(Boolean.TRUE);
        when(humanRepository.existsHumanByDna(dnaActual)).thenReturn(Boolean.FALSE);

        boolean mutantActual = mutantService.isMutant(evalMutantActual);
        Assertions.assertTrue(mutantActual);
    }

    @Test
    void when_receive_mutant_dna_and_in_ddbb_then_return_true() {
        String[] evalMutantActual = {"ATGCGA", "CAGGGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        String dnaActual = "ATGCGA,CAGGGC,TTATGT,AGAAGG,CCCCTA,TCACTG";

        when(analyzer.analyzeDna(evalMutantActual)).thenReturn(Boolean.TRUE);
        when(humanRepository.existsHumanByDna(dnaActual)).thenReturn(Boolean.TRUE);

        boolean mutantActual = mutantService.isMutant(evalMutantActual);
        Assertions.assertTrue(mutantActual);
    }

    @Test
    void when_receive_human_dna_and_not_in_ddbb_then_return_false() {
        String[] evalMutantActual = {"AATACT", "CCCAGA", "GGGATT", "AATTCC", "GGATCG", "TCACTG"};
        String dnaActual = "AATACT,CCCAGA,GGGATT,AATTCC,GGATCG,TCACTG";

        when(analyzer.analyzeDna(evalMutantActual)).thenReturn(Boolean.FALSE);
        when(humanRepository.existsHumanByDna(dnaActual)).thenReturn(Boolean.FALSE);

        boolean mutantActual = mutantService.isMutant(evalMutantActual);
        Assertions.assertFalse(mutantActual);

    }

    @Test
    void when_receive_human_dna_and_in_ddbb_then_return_false() {
        String[] evalMutantActual = {"AATACT", "CCCAGA", "GGGATT", "AATTCC", "GGATCG", "TCACTG"};
        String dnaActual = "AATACT,CCCAGA,GGGATT,AATTCC,GGATCG,TCACTG";

        when(analyzer.analyzeDna(evalMutantActual)).thenReturn(Boolean.FALSE);
        when(humanRepository.existsHumanByDna(dnaActual)).thenReturn(Boolean.TRUE);

        boolean mutantActual = mutantService.isMutant(evalMutantActual);
        Assertions.assertFalse(mutantActual);
    }
}
