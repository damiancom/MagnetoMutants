package ar.com.meli.challenge.mutants.validator;

import ar.com.meli.challenge.mutants.exceptions.BadFormatDnaException;
import ar.com.meli.challenge.mutants.exceptions.DnaSizeException;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class DnaValidatorTest {

    @Test
    void when_array_malformed_then_validateDna_throws_BadFormatDnaException() {
        log.info("Test checkArrayMalformed");

        String[] adnMalFormated = {"ATGCGA", "CAGGGC", "TTATGT", "AGAAGG", "CJJCTA", "TCACTG"};

        assertThrows(BadFormatDnaException.class,
                () -> DnaValidator.validateDna(adnMalFormated));
    }

    @Test
    void when_dna_not_correct_size_then_validateDna_throws_DnaSizeException() {
        log.info("Test checkArrayInvalidSize");

        String[] badSize = {"ATGCGA", "CAGGGC", "TTATGT", "AGAAGG", "TCACTG"};

        assertThrows(DnaSizeException.class,
                () -> DnaValidator.validateDna(badSize));
    }

    @Test
    void when_dna_is_valid () {
        log.info("Test validateArrayOk");

        String[] validDna = {"ATGCGA", "CAGGGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

        assertDoesNotThrow(() -> DnaValidator.validateDna(validDna));
    }
}
