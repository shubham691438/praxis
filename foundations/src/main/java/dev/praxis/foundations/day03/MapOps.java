package dev.praxis.foundations.day03;

import java.util.List;
import java.util.Map;

/**
 * EXERCISE 2 — Maps: the null traps
 *
 * Diagnostic Q12 — you answered "v is 0" for an int read from a missing key.
 * It actually throws. This exercise is built around that.
 */
public final class MapOps {

    /**
     * Return the value for `key`, or `fallback` when the key is absent.
     * Do NOT write an if-statement — Map has a method for exactly this.
     */
    public static int getOrFallback(Map<String, Integer> map, String key, int fallback) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Demonstrate the Q12 bug. Do this literally:
     *
     *     int value = map.get(key);
     *
     * on a map that does NOT contain the key, and report what happens:
     * return true if it threw, false if it completed.
     *
     * Catch the specific exception. Predict the answer before you run it.
     */
    public static boolean unboxingMissingKeyThrows(Map<String, Integer> map, String key) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Count how many times each word appears in `words`.
     *
     * The naive version — get, null-check, put — works but is four lines.
     * Map has a method that does the whole thing in one. Two candidates:
     * merge() and computeIfAbsent(). Either is fine; look both up.
     */
    public static Map<String, Integer> countWords(List<String> words) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Group the words by their first letter.
     * "apple", "avocado", "banana" -> {a=[apple, avocado], b=[banana]}
     *
     * computeIfAbsent is made for this: it inserts a new empty list when the
     * key is missing, then returns the list so you can add to it.
     */
    public static Map<Character, List<String>> groupByFirstLetter(List<String> words) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Sum every value in the map. Return 0 for an empty map.
     * Iterate over values() rather than keys — you don't need the keys.
     */
    public static int sumValues(Map<String, Integer> map) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Return the key with the highest value. If the map is empty, return null.
     * On a tie, either key is acceptable.
     *
     * Iterate entrySet() — one pass gives you key and value together, which
     * is cheaper than looking each key up again.
     */
    public static String keyWithHighestValue(Map<String, Integer> map) {
        throw new UnsupportedOperationException("TODO");
    }
}
