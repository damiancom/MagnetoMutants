package ar.com.meli.challenge.mutants.controllers;

import ar.com.meli.challenge.mutants.api.model.Human;
import ar.com.meli.challenge.mutants.services.MutantService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@FieldDefaults(level= AccessLevel.PRIVATE)
class MutantControllerTest {

    @Mock
    MutantService mutantService;

    @InjectMocks
    MutantController mutantController;

    @Test
    void when_dna_is_from_human_then_idMutant_response_403_FORBIDDEN () throws Exception {
        Human human = new Human();
        when(mutantService.isMutant(human.getDna())).thenReturn(Boolean.FALSE);

        ResponseEntity<HttpStatus> responseEntity = mutantController.isMutant(human);

        Assertions.assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void when_dna_is_from_mutant_then_idMutant_response_200_OK () throws Exception {
        Human human = new Human();
        when(mutantService.isMutant(human.getDna())).thenReturn(Boolean.TRUE);

        ResponseEntity<HttpStatus> responseEntity = mutantController.isMutant(human);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
