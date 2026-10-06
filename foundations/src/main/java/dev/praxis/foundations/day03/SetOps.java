package dev.praxis.foundations.day03;

import java.util.List;
import java.util.Set;

/**
 * EXERCISE 3 — Sets: operations and choosing an implementation
 *
 * You asked about the Set implementations. Now pick between them.
 */
public final class SetOps {

    /** Elements in BOTH sets. Must not modify either argument. */
    public static Set<String> intersection(Set<String> a, Set<String> b) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Elements in `a` but not in `b`. Must not modify either argument. */
    public static Set<String> difference(Set<String> a, Set<String> b) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Remove duplicates from `input` while PRESERVING the original order.
     * The implementation you pick decides whether this works.
     */
    public static List<String> deduplicatePreservingOrder(List<String> input) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Return the distinct elements of `input`, sorted alphabetically.
     * Again, the right implementation does the sorting for you.
     */
    public static List<String> distinctSorted(List<String> input) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Return true if the two lists contain the same elements, ignoring order
     * and duplicates. ["a","b","a"] and ["b","a"] -> true
     */
    public static boolean sameElements(List<String> a, List<String> b) {
        throw new UnsupportedOperationException("TODO");
    }
}
