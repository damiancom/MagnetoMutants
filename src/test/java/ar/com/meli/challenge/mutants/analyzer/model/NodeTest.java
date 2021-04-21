package ar.com.meli.challenge.mutants.analyzer.model;

import ar.com.meli.challenge.mutants.analyzer.Tree;
import ar.com.meli.challenge.mutants.api.model.Human;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class NodeTest {

    @Test
    void test_node_ok() {
        String label = "AAGGTT";
        Tree tree = mock(Tree.class);
        Node nodeActual = new Node(label, tree);

        assertEquals(label, nodeActual.getLabel());
        assertEquals(tree, nodeActual.getDest());
    }
}
