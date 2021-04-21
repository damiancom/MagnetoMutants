package ar.com.meli.challenge.mutants.analyzer;

import ar.com.meli.challenge.mutants.enums.MutantSequence;
import ar.com.meli.challenge.mutants.analyzer.model.Node;
import ar.com.meli.challenge.mutants.analyzer.model.Touple;
import ar.com.meli.challenge.mutants.analyzer.support.SequenceExtractor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@FieldDefaults(level= AccessLevel.PRIVATE)
public class AnalyzerGTS implements Analyzer {

    int last = 0;
    Tree root = new Tree();
    Tree activeLeaf = root;
    @Value("${analyzer.maxium.amount.human.sequence}")
    private static final int MAXIMUM_AMOUNT_HUMAN_SEQUENCE = 1;
    final SequenceExtractor seqExtractor;

    @Autowired
    public AnalyzerGTS(SequenceExtractor seqExtractor) {
        this.seqExtractor = seqExtractor;
    }

    public boolean analyzeDna(String[] dna) {
        long initTime = System.nanoTime();
        log.info("validating dna... " + Arrays.toString(dna));
        int count = 0;
        this.last = 0;
        root = new Tree();

        List<String> sequences = getSequences(dna);

        for (int i = 0; i < sequences.size(); i++) {
            this.put(sequences.get(i), i);
        }

        for (MutantSequence mutantDnaItem : MutantSequence.values()) {
            count += this.getCount(mutantDnaItem.toString());

            if (count > MAXIMUM_AMOUNT_HUMAN_SEQUENCE) {
                log.info("validating dna finished in " + ((System.nanoTime() - initTime) / 1000000f) + "ms.");
                return Boolean.TRUE;
            }
        }
        log.info("validating dna finished in " + ((System.nanoTime() - initTime) / 1000000f) + "ms.");
        return Boolean.FALSE;
    }

    private List<String> getSequences(String[] dna) {
        List<String> sequences = new ArrayList<>();

        // Add rows of the matrix
        Collections.addAll(sequences, dna);

        sequences.addAll(seqExtractor.getVerticalSecuences(dna));
        sequences.addAll(seqExtractor.getObliqueSecuences(dna));
        sequences.addAll(seqExtractor.getInvertedObliqueSecuences(dna));

        return sequences;
    }

    public int getCount(String word) {
        Tree tmpTree = searchNode(word);
        int count = 0;
        if (tmpTree == null) {
            return 0;
        }

        count += countChildNodes(tmpTree);
        return count;

    }

    public void put(String key, int index) throws IllegalStateException {
        if (index < last) {
            throw new IllegalStateException("The input index must not be less than any of the previously inserted ones. Got " + index + ", expected at least " + last);
        } else {
            last = index;
        }

        activeLeaf = root;

        String remainder = key + "$";
        Tree s = root;


        String text = "";
        for (int i = 0; i < remainder.length(); i++) {
            text = text + remainder.charAt(i);
            text = text.intern();

            Touple<Tree, String> active = update(s, text, remainder.substring(i), index);
            active = canonize(active.getFirstElement(), active.getSecondElement());

            s = active.getFirstElement();
            text = active.getSecondElement();
        }

        if (null == activeLeaf.getSuffix() && activeLeaf != root && activeLeaf != s) {
            activeLeaf.setSuffix(s);
        }
    }

    private Tree searchNode(String word) {
        Tree currentTree = root;
        Node currentNode;

        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);

            currentNode = currentTree.getEdge(ch);
            if (null == currentNode) {
                return null;
            } else {
                String label = currentNode.getLabel();
                int lenToMatch = Math.min(word.length() - i, label.length());
                if (!word.regionMatches(i, label, 0, lenToMatch)) {
                    return null;
                }

                if (label.length() >= word.length() - i) {
                    return currentNode.getDest();
                } else {
                    currentTree = currentNode.getDest();
                    i += (lenToMatch - 1);
                }
            }
        }
        return null;
    }

    private int countChildNodes(Tree n) {
        int count = 0;

        Iterator<Node> iteraEdges = n.getEdges().values().iterator();

        // It is a leaf edge
        if (n.getEdges().size() == 0) {
            return n.getData().size();
        }
        while (iteraEdges.hasNext()) {
            Node e = iteraEdges.next();

            if (e.getDest() != null) {
                count += countChildNodes(e.getDest());
            }
        }

        return count;
    }

    private Touple<Tree, String> update(final Tree inputTree, final String stringPart, final String rest, final int value) {
        Tree s = inputTree;
        String tempstr = stringPart;
        char newChar = stringPart.charAt(stringPart.length() - 1);

        Tree oldroot = root;

        Touple<Boolean, Tree> ret = testAndSplit(s, tempstr.substring(0, tempstr.length() - 1), newChar, rest, value);

        Tree r = ret.getSecondElement();
        boolean endpoint = ret.getFirstElement();

        Tree leaf;
        while (!endpoint) {
            Node tempNode = r.getEdge(newChar);
            if (null != tempNode) {
                leaf = tempNode.getDest();
            } else {
                leaf = new Tree();
                leaf.addRef(value);
                Node newedge = new Node(rest, leaf);
                r.addEdge(newChar, newedge);
            }

            if (activeLeaf != root) {
                activeLeaf.setSuffix(leaf);
            }
            activeLeaf = leaf;

            if (oldroot != root) {
                oldroot.setSuffix(r);
            }

            oldroot = r;

            if (null == s.getSuffix()) { // root node
                assert (root == s);
                tempstr = tempstr.substring(1);
            } else {
                Touple<Tree, String> canret = canonize(s.getSuffix(), safeCutLastChar(tempstr));
                s = canret.getFirstElement();
                tempstr = (canret.getSecondElement() + tempstr.charAt(tempstr.length() - 1)).intern();
            }

            ret = testAndSplit(s, safeCutLastChar(tempstr), newChar, rest, value);
            r = ret.getSecondElement();
            endpoint = ret.getFirstElement();

        }

        if (oldroot != root) {
            oldroot.setSuffix(r);
        }

        return new Touple<>(s, tempstr);
    }

    private Touple<Boolean, Tree> testAndSplit(final Tree inputs, final String stringPart, final char t, final String remainder, final int value) {
        Touple<Tree, String> ret = canonize(inputs, stringPart);
        Tree s = ret.getFirstElement();
        String str = ret.getSecondElement();

        if (!"".equals(str)) {
            Node g = s.getEdge(str.charAt(0));

            String label = g.getLabel();
            if (label.length() > str.length() && label.charAt(str.length()) == t) {
                return new Touple<>(true, s);
            } else {
                String newlabel = label.substring(str.length());
                assert (label.startsWith(str));

                Tree r = new Tree();
                Node newedge = new Node(str, r);

                g.setLabel(newlabel);

                r.addEdge(newlabel.charAt(0), g);
                s.addEdge(str.charAt(0), newedge);

                return new Touple<>(false, r);
            }

        } else {
            Node e = s.getEdge(t);
            if (null == e) {
                return new Touple<>(false, s);
            } else {
                if (remainder.equals(e.getLabel())) {
                    e.getDest().addRef(value);
                    return new Touple<>(true, s);
                } else if (remainder.startsWith(e.getLabel())) {
                    return new Touple<>(true, s);
                } else if (e.getLabel().startsWith(remainder)) {
                    Tree newTree = new Tree();
                    newTree.addRef(value);

                    Node newNode = new Node(remainder, newTree);

                    e.setLabel(e.getLabel().substring(remainder.length()));

                    newTree.addEdge(e.getLabel().charAt(0), e);

                    s.addEdge(t, newNode);

                    return new Touple<>(false, s);
                } else {
                    return new Touple<>(true, s);
                }
            }
        }

    }

    private Touple<Tree, String> canonize(final Tree s, final String inputstr) {

        if ("".equals(inputstr)) {
            return new Touple<>(s, inputstr);
        } else {
            Tree currentTree = s;
            String str = inputstr;
            Node g = s.getEdge(str.charAt(0));
            while (g != null && str.startsWith(g.getLabel())) {
                str = str.substring(g.getLabel().length());
                currentTree = g.getDest();
                if (str.length() > 0) {
                    g = currentTree.getEdge(str.charAt(0));
                }
            }

            return new Touple<>(currentTree, str);
        }
    }

    private String safeCutLastChar(String seq) {
        if (seq.length() == 0) {
            return "";
        }
        return seq.substring(0, seq.length() - 1);
    }

}
