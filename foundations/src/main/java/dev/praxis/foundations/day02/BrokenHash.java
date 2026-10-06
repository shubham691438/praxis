package dev.praxis.foundations.day02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * EXERCISE 2 — What actually breaks when hashCode is missing
 *
 * This is diagnostic Q6, seen from the inside.
 *
 * The class below overrides equals() but NOT hashCode(). That is the single
 * most common bug in Java.
 *
 * Do NOT fix the class. The point is to observe the damage.
 * Write a scratch main(), run it, and watch it happen.
 */
public final class BrokenHash {

    /** Overrides equals but deliberately NOT hashCode. Leave it that way. */
    public static final class Id {
        private final String value;

        public Id(String value) { this.value = value; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Id other)) return false;
            return value.equals(other.value);
        }
        // hashCode() deliberately NOT overridden -> inherits Object's,
        // which is based on identity, so two equal Ids hash differently.
    }

    /**
     * Put one Id into a HashSet, then ask whether an EQUAL but separately
     * constructed Id is in there.
     *
     *     Set<Id> set = new HashSet<>();
     *     set.add(new Id("a"));
     *     return set.contains(new Id("a"));
     *
     * Predict the answer before you run it. Then run it.
     */
    public static boolean hashSetFindsEqualId() {
       Set<Id> set= new HashSet<>();
       set.add(new Id("a"));
       return set.contains(new Id("a"));
    }

    /**
     * Same question, but with an ArrayList instead of a HashSet.
     *
     *     List<Id> list = new ArrayList<>();
     *     list.add(new Id("a"));
     *     return list.contains(new Id("a"));
     *
     * Predict this one too. It does NOT match the answer above.
     * When you see why, you understand what hashCode is for.
     */
    public static boolean arrayListFindsEqualId() {
       List<Id> list = new ArrayList<>();
       list.add(new Id("a"));

       return list.contains(new Id("a"));
    }

    /**
     * Add TWO equal Ids to a HashSet. Return the resulting size().
     *
     * A Set is supposed to hold no duplicates. Does it manage that here?
     */
    public static int hashSetSizeAfterAddingTwoEqualIds() {
       Set<Id> set= new HashSet<>();
       set.add(new Id("a"));
       set.add(new Id("a"));

       return set.size();
    }
}
