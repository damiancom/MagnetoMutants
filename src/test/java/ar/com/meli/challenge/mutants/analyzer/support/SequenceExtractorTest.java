package ar.com.meli.challenge.mutants.analyzer.support;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SequenceExtractorTest {

    String[] dna = {
            "AATACT",
            "CCCAGA",
            "GGGATT",
            "AATTCC",
            "GGATCG",
            "TCACTG"};

    @InjectMocks
    SequenceExtractor sequenceExtractor;

    @Test
    void getVerticalSecuences() {
        List<String> resultExpected = new ArrayList<>();
        resultExpected.add("ACGAGT");
        resultExpected.add("ACGAGC");
        resultExpected.add("TCGTAA");
        resultExpected.add("AAATTC");
        resultExpected.add("CGTCCT");
        resultExpected.add("TATCGG");

        List<String> resultActual = sequenceExtractor.getVerticalSecuences(dna);

        Assertions.assertEquals(resultExpected, resultActual);
    }

    @Test
    void getObliqueSecuences() {
        List<String> resultExpected = new ArrayList<>();
        resultExpected.add("ACGTCG");
        resultExpected.add("ACACG");
        resultExpected.add("CGTTT");
        resultExpected.add("TATC");
        resultExpected.add("GAAC");
        resultExpected.add("AGT");
        resultExpected.add("AGA");
        resultExpected.add("CA");
        resultExpected.add("GC");
        resultExpected.add("T");
        resultExpected.add("T");

        List<String> resultActual = sequenceExtractor.getObliqueSecuences(dna);

        Assertions.assertEquals(resultExpected, resultActual);
    }

    @Test
    void getInvertedObliqueSecuences() {
        List<String> resultExpected = new ArrayList<>();
        resultExpected.add("TGATGT");
        resultExpected.add("CAGAG");
        resultExpected.add("ATTAC");
        resultExpected.add("ACGA");
        resultExpected.add("TCTA");
        resultExpected.add("TCG");
        resultExpected.add("CCC");
        resultExpected.add("AC");
        resultExpected.add("GT");
        resultExpected.add("A");
        resultExpected.add("G");

        List<String> resultActual = sequenceExtractor.getInvertedObliqueSecuences(dna);
        Assertions.assertEquals(resultExpected, resultActual);
    }
}
