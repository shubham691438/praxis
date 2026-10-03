package dev.praxis.foundations.week01;

/**
 * EXERCISE 2 — String immutability and the string pool
 *
 * This is diagnostic Q1 and Q3. Both caught you.
 *
 * Do NOT guess these. Write a scratch main() method, print the values,
 * and find out. Then implement. The point is that you SAW it happen.
 */
public final class StringFacts {

    /**
     * Return the result of calling toUpperCase() on the input,
     * WITHOUT modifying the original (you can't — that's the lesson).
     */
    public static String shout(String input) {
        return input.toUpperCase();
    }

    /**
     * Two String LITERALS with the same characters.
     * Return whether `==` considers them the same object.
     *
     * Write it, run it, see the answer. Don't reason it out from memory.
     */
    public static boolean literalsAreSameObject() {
        String a = "hello";
        String b = "hello";
        return a == b;

    }

    /**
     * Same characters, but one built with `new String("hello")`.
     * Return whether `==` considers them the same object.
     */
    public static boolean literalAndNewAreSameObject() {
        String a="Hello";
        String b= new String("Hello");

        return a==b;
    }

    /**
     * Same two values as above, compared with .equals() instead.
     * Return the result.
     */
    public static boolean literalAndNewAreEqual() {
        String a="Hello";
        String b= new String("Hello");
        return a.equals(b);
    }
}
