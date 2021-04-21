package ar.com.meli.challenge.mutants.analyzer;

import ar.com.meli.challenge.mutants.exceptions.DnaSizeException;
import ar.com.meli.challenge.mutants.validator.DnaValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class NodeBagTest {

    @InjectMocks
    NodeBag nodeBag;

    @Test
    void put() {
    }

    @Test
    void get() {
    }

    @Test
    void testGet() {
    }

    @Test
    void values() {
    }

    @Test
    void isEmpty() {
        nodeBag.isEmpty();
    }

    @Test
    void size() {
    }

    @Test
    void when_invoque_entrySet_return_UnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> nodeBag.entrySet());
    }

    @Test
    void when_invoque_keySet_return_UnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> nodeBag.keySet());
    }

    @Test
    void when_invoque_clear_return_UnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> nodeBag.clear());
    }

    @Test
    void when_invoque_putAll_return_UnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> nodeBag.putAll(mock(Map.class)));
    }

    @Test
    void when_invoque_remove_return_UnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> nodeBag.remove(mock(Object.class)));
    }

    @Test
    void when_invoque_containsKey_return_UnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> nodeBag.containsKey(mock(Object.class)));
    }

    @Test
    void when_invoque_containsValue_return_UnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> nodeBag.containsValue(mock(Object.class)));
    }
}
