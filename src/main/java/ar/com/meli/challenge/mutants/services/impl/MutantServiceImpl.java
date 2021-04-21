package ar.com.meli.challenge.mutants.services.impl;

import ar.com.meli.challenge.mutants.analyzer.Analyzer;
import ar.com.meli.challenge.mutants.entity.Human;
import ar.com.meli.challenge.mutants.repository.HumanRepository;
import ar.com.meli.challenge.mutants.services.MutantService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class MutantServiceImpl implements MutantService {

    Analyzer analyzer;
    HumanRepository humanRepository;

    @Autowired
    public MutantServiceImpl (Analyzer analyzer, HumanRepository humanRepository) {
        this.analyzer = analyzer;
        this.humanRepository = humanRepository;
    }

    public boolean isMutant (String[] dna) {
        boolean mutant = analyzer.analyzeDna(dna);

        Human human = new Human(dna, mutant);
        if (!humanRepository.existsHumanByDna(human.getDna())) {
            humanRepository.save(human);
        }

        return mutant;
    }
}
