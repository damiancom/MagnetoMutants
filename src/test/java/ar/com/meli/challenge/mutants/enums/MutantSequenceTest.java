package ar.com.meli.challenge.mutants.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MutantSequenceTest {

    @Test
    void valuesIsPresents() {
        assertNotNull(MutantSequence.AAAA);
        assertNotNull(MutantSequence.CCCC);
        assertNotNull(MutantSequence.GGGG);
        assertNotNull(MutantSequence.TTTT);
    }
}
