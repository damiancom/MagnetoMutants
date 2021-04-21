package ar.com.meli.challenge.mutants.analyzer;

import ar.com.meli.challenge.mutants.analyzer.model.Node;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@FieldDefaults(level= AccessLevel.PRIVATE)
public class NodeBag implements Map<Character, Node> {

    byte[] chars;
    Node[] values;
    static final int BSEARCH_THRESHOLD = 6;

    public Node put(Character character, Node e) {
        char c = character;
        if (c != (char) (byte) c) {
            throw new IllegalArgumentException("Illegal input character " + c + ".");
        }

        if (chars == null) {
            chars = new byte[0];
            values = new Node[0];
        }
        int idx = search(c);
        Node previous = null;

        if (idx < 0) {
            int currsize = chars.length;
            byte[] copy = new byte[currsize + 1];
            System.arraycopy(chars, 0, copy, 0, currsize);
            chars = copy;
            Node[] copy1 = new Node[currsize + 1];
            System.arraycopy(values, 0, copy1, 0, currsize);
            values = copy1;
            chars[currsize] = (byte) c;
            values[currsize] = e;
            currsize++;
            if (currsize > BSEARCH_THRESHOLD) {
                sortArrays();
            }
        } else {
            previous = values[idx];
            values[idx] = e;
        }
        return previous;
    }

    public Node get(Object maybeCharacter) {
        return get(((Character) maybeCharacter).charValue());
    }

    public Node get(char c) {
        if (c != (char) (byte) c) {
            throw new IllegalArgumentException("Illegal input character " + c + ".");
        }

        int idx = search(c);
        if (idx < 0) {
            return null;
        }
        return values[idx];
    }

    private int search(char c) {
        if (chars == null)
            return -1;

        if (chars.length > BSEARCH_THRESHOLD) {
            return Arrays.binarySearch(chars, (byte) c);
        }

        for (int i = 0; i < chars.length; i++) {
            if (c == chars[i]) {
                return i;
            }
        }
        return -1;
    }

    public Collection<Node> values() {
        return Arrays.asList(values == null ? new Node[0] : values);
    }

    private void sortArrays() {
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j > 0; j--) {
                if (chars[j-1] > chars[j]) {
                    byte swap = chars[j];
                    chars[j] = chars[j-1];
                    chars[j-1] = swap;

                    Node swapNode = values[j];
                    values[j] = values[j-1];
                    values[j-1] = swapNode;
                }
            }
        }
    }

    public boolean isEmpty() {
        return chars == null || chars.length == 0;
    }

    public int size() {
        return chars == null ? 0 : chars.length;
    }

    public Set<Entry<Character, Node>> entrySet() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Set<Character> keySet() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void clear() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void putAll(Map<? extends Character, ? extends Node> m) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Node remove(Object key) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean containsValue(Object key) {
        throw new UnsupportedOperationException("Not implemented");
    }

}
