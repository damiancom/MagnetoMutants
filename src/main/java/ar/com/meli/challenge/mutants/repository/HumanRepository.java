package ar.com.meli.challenge.mutants.repository;

import ar.com.meli.challenge.mutants.entity.Human;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends MongoRepository<Human, String> {

    boolean existsHumanByDna(String dna);

    int countHumansByMutant(boolean isMutant);

}
