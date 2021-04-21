package ar.com.meli.challenge.mutants.services.impl;

import ar.com.meli.challenge.mutants.dto.StatisticsDto;
import ar.com.meli.challenge.mutants.repository.HumanRepository;
import ar.com.meli.challenge.mutants.services.StatisticsService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
public class StatisticsServiceImpl implements StatisticsService {

    HumanRepository humanRepository;

    @Autowired
    public StatisticsServiceImpl (HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public StatisticsDto getStatistics () {
        return new StatisticsDto (this.humanRepository.countHumansByMutant(Boolean.FALSE), this.humanRepository.countHumansByMutant(Boolean.TRUE));
    }

}
