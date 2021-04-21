package ar.com.meli.challenge.mutants.analyzer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ToupleTest {

    @Test
    void test_tuple_ok() {
        Boolean objBoolean = Boolean.TRUE;
        Node objNode = mock(Node.class);

        Touple<Boolean, Node> touple = new Touple<>(objBoolean, objNode);

        assertTrue(touple.getFirstElement());
        assertEquals(objNode, touple.getSecondElement());
    }

}
