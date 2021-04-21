package ar.com.meli.challenge.mutants.analyzer.model;

import ar.com.meli.challenge.mutants.analyzer.Tree;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Node {
    String label;
    Tree dest;
}
