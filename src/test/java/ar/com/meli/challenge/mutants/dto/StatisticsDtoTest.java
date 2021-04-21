package ar.com.meli.challenge.mutants.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StatisticsDtoTest {

    @Test
    void when_create_StaticsDto_with_parameters_caculate_ratio_ok() {
        int actualCountHuman = 10;
        int actualCountMutant = 4;
        int expectHuman = 10;
        int expectMutant = 4;
        double expectRatio = 0.4d;

        StatisticsDto statisticsDto = new StatisticsDto(actualCountHuman, actualCountMutant);

        assertNotNull(statisticsDto);
        assertEquals(expectRatio, statisticsDto.getRatio());
        assertEquals(expectMutant, statisticsDto.getCountMutantDna());
        assertEquals(expectHuman, statisticsDto.getCountHumanDna());

    }

    @Test
    void when_create_StaticsDto_with_not_human_count_caculate_ratio_ok() {
        int actualCountHuman = 0;
        int actualCountMutant = 4;
        int expectHuman = 0;
        int expectMutant = 4;
        double expectRatio = 4.0d;

        StatisticsDto statisticsDto = new StatisticsDto(actualCountHuman, actualCountMutant);

        assertNotNull(statisticsDto);
        assertEquals(expectRatio, statisticsDto.getRatio());
        assertEquals(expectMutant, statisticsDto.getCountMutantDna());
        assertEquals(expectHuman, statisticsDto.getCountHumanDna());

    }

}
