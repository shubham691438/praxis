package dev.praxis.foundations.day03;

import java.util.List;
import java.util.Map;

/**
 * EXERCISE 4 — Putting them together
 *
 * A small stock system. No hints on which collection to use — that choice is
 * the exercise. Read the tests, decide, justify it to yourself.
 *
 * This is the first exercise where you design rather than fill in a blank.
 */
public final class Inventory {

    // TODO: choose your fields. You need:
    //   - a quantity per item name
    //   - the set of item names ever stocked, in the order first added

    public Inventory() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Add `quantity` of `name`. Adding an item that already exists increases
     * its quantity rather than replacing it.
     * A quantity of zero or less is rejected with IllegalArgumentException.
     */
    public void add(String name, int quantity) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Remove `quantity` of `name`.
     * Removing more than is in stock throws IllegalArgumentException.
     * Removing an item that was never stocked throws IllegalArgumentException.
     * Quantity reaching exactly zero is fine — the item stays known, at zero.
     */
    public void remove(String name, int quantity) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Current quantity, or 0 for an item that was never stocked. */
    public int quantityOf(String name) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Every item name ever stocked, in the order they were first added. */
    public List<String> itemsInOrderAdded() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Names of items currently in stock (quantity > 0), sorted alphabetically. */
    public List<String> inStockSorted() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Total units across all items. */
    public int totalUnits() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * A read-only view of the quantities.
     * Callers must NOT be able to modify the Inventory through it —
     * attempting to should throw. Look at Map.copyOf and
     * Collections.unmodifiableMap, and decide which you want and why.
     */
    public Map<String, Integer> quantities() {
        throw new UnsupportedOperationException("TODO");
    }
}
