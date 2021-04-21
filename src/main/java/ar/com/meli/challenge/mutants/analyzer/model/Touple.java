package ar.com.meli.challenge.mutants.analyzer.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class Touple<A, B> {
    A firstElement;
    B secondElement;
}
