package dev.praxis.foundations.day03;

import java.util.List;

/**
 * EXERCISE 1 — Lists: cost, mutation, and iteration
 *
 * Diagnostic Q11 — you didn't know ArrayList vs LinkedList.
 *
 * Fewer hints today. Read the tests; they state the requirement precisely.
 */
public final class ListOps {

    /**
     * Return a NEW list containing only the strings longer than `minLength`.
     * Must not modify `input`.
     */
    public static List<String> longerThan(List<String> input, int minLength) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Remove every string shorter than `minLength` FROM the given list, in place.
     *
     * The obvious approach — a for-each loop calling list.remove() — throws
     * ConcurrentModificationException. Find the method on List that does this
     * safely in one call.
     */
    public static void removeShorterThan(List<String> list, int minLength) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Try to add an element to the given list and report whether it worked.
     * Return true if the add succeeded, false if the list refused it.
     *
     * The test passes a List.of(...) list. Find out what happens. Catch the
     * specific exception type — not Exception.
     */
    public static boolean tryAdd(List<String> list, String item) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Return the element at `index` without throwing if the index is out of
     * range — return null instead.
     */
    public static String getOrNull(List<String> list, int index) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Return a NEW list with the elements in reverse order.
     * Must not modify `input`. Several ways to do this; any is fine.
     */
    public static List<String> reversed(List<String> input) {
        throw new UnsupportedOperationException("TODO");
    }
}
