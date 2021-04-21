package ar.com.meli.challenge.mutants.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NitrogenBaseTest {

    @Test
    void test_values_not_null_NitrogenBase_ok() {
        assertNotNull(NitrogenBase.A);
        assertNotNull(NitrogenBase.C);
        assertNotNull(NitrogenBase.G);
        assertNotNull(NitrogenBase.T);
    }
}
