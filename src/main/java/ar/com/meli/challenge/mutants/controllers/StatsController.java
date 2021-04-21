package ar.com.meli.challenge.mutants.controllers;

import ar.com.meli.challenge.mutants.dto.StatisticsDto;
import ar.com.meli.challenge.mutants.services.StatisticsService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
@RestController
@RequestMapping("/v1/api")
public class StatsController {

    StatisticsService statisticsService;

    @Autowired
    public StatsController (StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/stats")
    public ResponseEntity<StatisticsDto> retriveStatistics() {
        log.info("Retrive statistics of mutants");
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

}
