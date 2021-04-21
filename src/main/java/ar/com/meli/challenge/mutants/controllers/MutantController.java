package ar.com.meli.challenge.mutants.controllers;

import ar.com.meli.challenge.mutants.api.model.Human;
import ar.com.meli.challenge.mutants.services.MutantService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
@RestController
@RequestMapping("/v1/api")
public class MutantController {

    MutantService mutantService;

    @Autowired
    public MutantController (MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping("/mutant")
    public ResponseEntity<HttpStatus> isMutant(@RequestBody Human human) {
        log.info("Evaluate if this isMutation the dna + " + Arrays.toString(human.getDna()));
        boolean mutant = mutantService.isMutant(human.getDna());
        HttpStatus status = mutant ? HttpStatus.OK : HttpStatus.FORBIDDEN;
        log.info("This dna evaluate isMutant " + mutant);
        return ResponseEntity.status(status).build();
    }

}
