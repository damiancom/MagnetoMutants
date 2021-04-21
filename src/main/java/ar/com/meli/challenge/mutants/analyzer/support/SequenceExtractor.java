package ar.com.meli.challenge.mutants.analyzer.support;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SequenceExtractor {

    public List<String> getVerticalSecuences (String[] dna) {
        List<String> sequences = new ArrayList<>();

        // Vertical dna sequences
        for (int row = 0; row < dna.length; row++) {
            StringBuilder strColumn = new StringBuilder(dna.length);
            for (String s : dna) {
                strColumn.append(s.charAt(row));
            }
            sequences.add(strColumn.toString());
        }
        return sequences;
    }

    public List<String> getObliqueSecuences (String[] dna) {
        List<String> sequences = new ArrayList<>();

        //Assemble oblique dna sequences \
        for (int i = 0; i < dna.length; i++) {
            StringBuilder obliqueDna1 = new StringBuilder(dna.length);
            StringBuilder obliqueDna2 = new StringBuilder(dna.length);

            for (int j = 0; j < dna.length - i; j++) {
                obliqueDna1.append(dna[j].charAt(j + i));

                if (i != 0) {
                    obliqueDna2.append(dna[i + j].charAt(j));
                }
            }

            if (obliqueDna1.length() > 0) {
                sequences.add(obliqueDna1.toString());
            }

            if (obliqueDna2.length() > 0) {
                sequences.add(obliqueDna2.toString());
            }
        }

        return sequences;
    }

    public List<String> getInvertedObliqueSecuences (String[] dna) {
        List<String> sequences = new ArrayList<>();

        //Assemble oblique dna sequences /
        int step = 0;
        for (int i = dna.length - 1; 0 <= i; i--) {
            int x;
            int y;
            StringBuilder obliqueDna1 = new StringBuilder(dna.length);
            StringBuilder obliqueDna2 = new StringBuilder(dna.length);

            for (int j = 0; j <= i; j++) {
                x = i - j;
                y = j;

                obliqueDna1.append(dna[y].charAt(x));

                if (i != dna.length - 1) {
                    x = i - j + step;
                    y = j + step;
                    obliqueDna2.append(dna[y].charAt(x));
                }
            }
            step++;
            if (obliqueDna1.length() > 0) {
                sequences.add(obliqueDna1.toString());
            }
            if (obliqueDna2.length() > 0) {
                sequences.add(obliqueDna2.toString());
            }
        }
        return sequences;
    }

}
