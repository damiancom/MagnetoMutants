package ar.com.meli.challenge.mutants.controllers;

import ar.com.meli.challenge.mutants.dto.StatisticsDto;
import ar.com.meli.challenge.mutants.services.StatisticsService;
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
class StatsControllerTest {

    @Mock
    StatisticsService statisticsService;

    @InjectMocks
    StatsController statsController;

    @Test
    void when_receive_statistics_request_return_statistical_data_ok() {

        long humanCountActual = 10;
        long mutantCountActual = 4;

        long humanCountExpected = 10;
        long mutantCountExpected = 4;
        double ratioExpected = 0.4d;

        StatisticsDto statisticsDtoActual = new StatisticsDto(humanCountActual, mutantCountActual);

        when(statisticsService.getStatistics()).thenReturn(statisticsDtoActual);

        ResponseEntity<StatisticsDto> responseEntity = statsController.retriveStatistics();

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(humanCountExpected, responseEntity.getBody().getCountHumanDna());
        Assertions.assertEquals(mutantCountExpected, responseEntity.getBody().getCountMutantDna());
        Assertions.assertEquals(ratioExpected, responseEntity.getBody().getRatio());
    }
}
