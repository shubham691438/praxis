package dev.praxis.foundations.week01;

import java.util.ArrayList;
import java.util.List;

/**
 * EXERCISE 4 — Mutate vs reassign
 *
 * This is diagnostic Q4. Java passes references BY VALUE.
 *
 *   - mutating the object a parameter points at  -> caller sees it
 *   - reassigning the parameter itself           -> caller sees nothing
 *
 * That one distinction explains most "why didn't my change stick" bugs.
 */
public final class References {

    /** Add `item` to the caller's list. The caller MUST see this. */
    public static void addTo(List<String> list, String item) {
        list.add(item);
    }

    /**
     * Point the local parameter at a brand new list containing only `item`.
     * The caller must NOT see any change.
     *
     * Write it the obvious way. The test asserts the caller is unaffected.
     */
    public static void replaceWith(List<String> list, String item) {
        List<String> lists = new ArrayList<>();
        lists.add(item);
    }

    /**
     * Try to swap the contents of two int variables via a method.
     * Return true if you managed it, false if Java makes it impossible.
     *
     * Think before you answer. Then write a scratch main() and prove it.
     */
    public static boolean canSwapPrimitivesViaMethod() {
        return false;
    }
}
