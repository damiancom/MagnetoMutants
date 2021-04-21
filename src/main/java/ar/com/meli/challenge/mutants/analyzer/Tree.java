package ar.com.meli.challenge.mutants.analyzer;

import ar.com.meli.challenge.mutants.analyzer.model.Node;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@FieldDefaults(level= AccessLevel.PRIVATE)
public class Tree {

    int[] data;

    int lastIdx = 0;

    static final int START_SIZE = 0;

    static final int INCREMENT = 1;

    final Map<Character, Node> edges;

    Tree suffix;

    Tree() {
        edges = new NodeBag();
        suffix = null;
        data = new int[START_SIZE];
    }

    Collection<Integer> getData() {
        return getData(-1);
    }

    Collection<Integer> getData(int numElements) {
        Set<Integer> ret = new HashSet<>();
        for (int num : data) {
            ret.add(num);
            if (ret.size() == numElements) {
                return ret;
            }
        }

        for (Node e : edges.values()) {
            if (-1 == numElements || ret.size() < numElements) {
                for (int num : e.getDest().getData()) {
                    ret.add(num);
                    if (ret.size() == numElements) {
                        return ret;
                    }
                }
            }
        }
        return ret;
    }

    void addRef(int index) {
        if (contains(index)) {
            return;
        }

        addIndex(index);

        Tree iter = this.suffix;
        while (iter != null) {
            if (iter.contains(index)) {
                break;
            }
            iter.addRef(index);
            iter = iter.suffix;
        }

    }

    private boolean contains(int index) {
        int low = 0;
        int high = lastIdx - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = data[mid];

            if (midVal < index)
                low = mid + 1;
            else if (midVal > index)
                high = mid - 1;
            else
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    void addEdge(char ch, Node e) {
        edges.put(ch, e);
    }

    Node getEdge(char ch) {
        return edges.get(ch);
    }

    Map<Character, Node> getEdges() {
        return edges;
    }

    Tree getSuffix() {
        return suffix;
    }

    void setSuffix(Tree suffix) {
        this.suffix = suffix;
    }

    private void addIndex(int index) {
        if (lastIdx == data.length) {
            int[] copy = new int[data.length + INCREMENT];
            System.arraycopy(data, 0, copy, 0, data.length);
            data = copy;
        }
        data[lastIdx++] = index;
    }
}
