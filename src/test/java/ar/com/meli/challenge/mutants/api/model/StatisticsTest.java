package ar.com.meli.challenge.mutants.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    @Test
    void test_equal_statistics_with_same_input_parameters_ok() {
        int countHumans = 10;
        int countMutants = 4;
        double ratio = 0.4d;

        Statistics statisticsActual = new Statistics(countMutants, countHumans, ratio);
        Statistics statisticsExpected = new Statistics(countMutants, countHumans, ratio);

        Assertions.assertEquals(statisticsExpected, statisticsActual);

    }
}
