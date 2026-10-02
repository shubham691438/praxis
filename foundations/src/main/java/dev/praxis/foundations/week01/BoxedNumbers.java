package dev.praxis.foundations.week01;

/**
 * EXERCISE 3 — The Integer cache
 *
 * This is diagnostic Q1 exactly. You answered "true true".
 *
 * Java caches boxed Integer objects in a specific range. Inside that range,
 * `==` on two boxed values of the same number is true. Outside it, false.
 *
 * Find the boundary yourself by experiment. Do not look it up.
 */
public final class BoxedNumbers {

    /** Box `value` twice into Integer and return whether `==` says they are the same object. */
    public static boolean sameObjectWhenBoxed(int value) {
        // Integer a = value;
        // Integer b = value;
        // return a == b;
        throw new UnsupportedOperationException("TODO: implement sameObjectWhenBoxed()");
    }

    /**
     * Return the HIGHEST int value for which sameObjectWhenBoxed() returns true.
     * Find it by experiment — write a loop in a scratch main() and print where it flips.
     */
    public static int highestCachedValue() {
        throw new UnsupportedOperationException("TODO: find this by experiment, then return it");
    }

    /**
     * Return the LOWEST int value for which sameObjectWhenBoxed() returns true.
     */
    public static int lowestCachedValue() {
        throw new UnsupportedOperationException("TODO: find this by experiment, then return it");
    }
}
