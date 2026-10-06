package dev.praxis.foundations.day03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/** Day 3 — Collections: List, Set, Map. */
class Day03Test {

    @Nested
    @DisplayName("1. ListOps")
    class ListOpsTest {

        @Test
        @DisplayName("longerThan filters without touching the input")
        void filters() {
            List<String> input = new ArrayList<>(List.of("a", "abc", "abcde"));
            assertEquals(List.of("abc", "abcde"), ListOps.longerThan(input, 2));
            assertEquals(List.of("a", "abc", "abcde"), input, "input must be unchanged");
        }

        @Test
        @DisplayName("longerThan on an empty list gives an empty list")
        void filtersEmpty() {
            assertEquals(List.of(), ListOps.longerThan(new ArrayList<>(), 2));
        }

        @Test
        @DisplayName("removeShorterThan mutates in place without a CME")
        void removesInPlace() {
            List<String> list = new ArrayList<>(List.of("a", "abc", "ab", "abcde"));
            ListOps.removeShorterThan(list, 3);
            assertEquals(List.of("abc", "abcde"), list);
        }

        @Test
        @DisplayName("removeShorterThan can empty the list entirely")
        void removesAll() {
            List<String> list = new ArrayList<>(List.of("a", "b"));
            ListOps.removeShorterThan(list, 5);
            assertTrue(list.isEmpty());
        }

        @Test
        @DisplayName("tryAdd succeeds on a mutable list")
        void addSucceeds() {
            assertTrue(ListOps.tryAdd(new ArrayList<>(), "x"));
        }

        @Test
        @DisplayName("tryAdd fails on an immutable List.of — it throws, it does not return false")
        void addFailsOnImmutable() {
            assertFalse(ListOps.tryAdd(List.of("a"), "x"),
                    "List.of returns an immutable list — add() throws UnsupportedOperationException");
        }

        @Test
        @DisplayName("getOrNull returns the element, or null when out of range")
        void safeGet() {
            List<String> list = List.of("a", "b");
            assertEquals("a", ListOps.getOrNull(list, 0));
            assertEquals("b", ListOps.getOrNull(list, 1));
            assertNull(ListOps.getOrNull(list, 2));
            assertNull(ListOps.getOrNull(list, -1));
        }

        @Test
        @DisplayName("reversed returns a new list, input untouched")
        void reverses() {
            List<String> input = new ArrayList<>(List.of("a", "b", "c"));
            assertEquals(List.of("c", "b", "a"), ListOps.reversed(input));
            assertEquals(List.of("a", "b", "c"), input, "input must be unchanged");
        }
    }

    @Nested
    @DisplayName("2. MapOps")
    class MapOpsTest {

        @Test
        @DisplayName("getOrFallback returns the value when present")
        void fallbackPresent() {
            assertEquals(5, MapOps.getOrFallback(Map.of("a", 5), "a", 99));
        }

        @Test
        @DisplayName("getOrFallback returns the fallback when absent")
        void fallbackAbsent() {
            assertEquals(99, MapOps.getOrFallback(Map.of("a", 5), "zzz", 99));
        }

        @Test
        @DisplayName("unboxing a missing key DOES throw — this is diagnostic Q12")
        void unboxingThrows() {
            assertTrue(MapOps.unboxingMissingKeyThrows(Map.of("a", 1), "missing"),
                    "map.get returns null; unboxing null into an int throws NullPointerException");
        }

        @Test
        @DisplayName("unboxing a present key does not throw")
        void unboxingPresentIsFine() {
            assertFalse(MapOps.unboxingMissingKeyThrows(Map.of("a", 1), "a"));
        }

        @Test
        @DisplayName("countWords counts occurrences")
        void counts() {
            Map<String, Integer> result = MapOps.countWords(List.of("a", "b", "a", "c", "a"));
            assertEquals(3, result.get("a"));
            assertEquals(1, result.get("b"));
            assertEquals(1, result.get("c"));
            assertEquals(3, result.size());
        }

        @Test
        @DisplayName("countWords on an empty list gives an empty map")
        void countsEmpty() {
            assertEquals(Map.of(), MapOps.countWords(List.of()));
        }

        @Test
        @DisplayName("groupByFirstLetter buckets words by initial")
        void groups() {
            Map<Character, List<String>> result =
                    MapOps.groupByFirstLetter(List.of("apple", "avocado", "banana"));
            assertEquals(List.of("apple", "avocado"), result.get('a'));
            assertEquals(List.of("banana"), result.get('b'));
            assertEquals(2, result.size());
        }

        @Test
        @DisplayName("sumValues adds everything up, 0 when empty")
        void sums() {
            assertEquals(6, MapOps.sumValues(Map.of("a", 1, "b", 2, "c", 3)));
            assertEquals(0, MapOps.sumValues(Map.of()));
        }

        @Test
        @DisplayName("keyWithHighestValue finds the max, null when empty")
        void findsMax() {
            assertEquals("b", MapOps.keyWithHighestValue(Map.of("a", 1, "b", 9, "c", 3)));
            assertNull(MapOps.keyWithHighestValue(Map.of()));
        }
    }

    @Nested
    @DisplayName("3. SetOps")
    class SetOpsTest {

        @Test
        @DisplayName("intersection, inputs untouched")
        void intersects() {
            Set<String> a = new HashSet<>(Set.of("x", "y", "z"));
            Set<String> b = new HashSet<>(Set.of("y", "z", "w"));
            assertEquals(Set.of("y", "z"), SetOps.intersection(a, b));
            assertEquals(Set.of("x", "y", "z"), a, "a must be unchanged");
            assertEquals(Set.of("y", "z", "w"), b, "b must be unchanged");
        }

        @Test
        @DisplayName("difference, inputs untouched")
        void differs() {
            Set<String> a = new HashSet<>(Set.of("x", "y", "z"));
            Set<String> b = new HashSet<>(Set.of("y"));
            assertEquals(Set.of("x", "z"), SetOps.difference(a, b));
            assertEquals(Set.of("x", "y", "z"), a, "a must be unchanged");
        }

        @Test
        @DisplayName("deduplicate keeps first-seen order")
        void dedupInOrder() {
            assertEquals(List.of("c", "a", "b"),
                    SetOps.deduplicatePreservingOrder(List.of("c", "a", "c", "b", "a")),
                    "HashSet would lose the order — pick the implementation that keeps it");
        }

        @Test
        @DisplayName("distinctSorted returns them alphabetically")
        void sortsDistinct() {
            assertEquals(List.of("a", "b", "c"),
                    SetOps.distinctSorted(List.of("c", "a", "c", "b", "a")));
        }

        @Test
        @DisplayName("sameElements ignores order and duplicates")
        void comparesIgnoringOrder() {
            assertTrue(SetOps.sameElements(List.of("a", "b", "a"), List.of("b", "a")));
            assertTrue(SetOps.sameElements(List.of(), List.of()));
            assertFalse(SetOps.sameElements(List.of("a"), List.of("a", "b")));
        }
    }

    @Nested
    @DisplayName("4. Inventory")
    class InventoryTest {

        @Test
        @DisplayName("add then read back")
        void addAndRead() {
            Inventory inv = new Inventory();
            inv.add("bolt", 10);
            assertEquals(10, inv.quantityOf("bolt"));
        }

        @Test
        @DisplayName("adding an existing item accumulates")
        void addAccumulates() {
            Inventory inv = new Inventory();
            inv.add("bolt", 10);
            inv.add("bolt", 5);
            assertEquals(15, inv.quantityOf("bolt"));
        }

        @Test
        @DisplayName("unknown item is 0, not an error")
        void unknownIsZero() {
            assertEquals(0, new Inventory().quantityOf("nothing"));
        }

        @Test
        @DisplayName("a non-positive quantity is rejected")
        void rejectsBadQuantity() {
            Inventory inv = new Inventory();
            assertThrows(IllegalArgumentException.class, () -> inv.add("bolt", 0));
            assertThrows(IllegalArgumentException.class, () -> inv.add("bolt", -1));
        }

        @Test
        @DisplayName("remove subtracts, and may reach exactly zero")
        void removeSubtracts() {
            Inventory inv = new Inventory();
            inv.add("bolt", 10);
            inv.remove("bolt", 4);
            assertEquals(6, inv.quantityOf("bolt"));
            inv.remove("bolt", 6);
            assertEquals(0, inv.quantityOf("bolt"));
        }

        @Test
        @DisplayName("removing too many, or an unknown item, is rejected")
        void removeRejects() {
            Inventory inv = new Inventory();
            inv.add("bolt", 3);
            assertThrows(IllegalArgumentException.class, () -> inv.remove("bolt", 4));
            assertThrows(IllegalArgumentException.class, () -> inv.remove("ghost", 1));
        }

        @Test
        @DisplayName("items are listed in the order first added")
        void ordersByInsertion() {
            Inventory inv = new Inventory();
            inv.add("zinc", 1);
            inv.add("alloy", 1);
            inv.add("bolt", 1);
            inv.add("zinc", 5);
            assertEquals(List.of("zinc", "alloy", "bolt"), inv.itemsInOrderAdded(),
                    "re-adding zinc must not move it to the end");
        }

        @Test
        @DisplayName("inStockSorted excludes zero-quantity items")
        void inStockOnly() {
            Inventory inv = new Inventory();
            inv.add("zinc", 1);
            inv.add("alloy", 2);
            inv.add("bolt", 3);
            inv.remove("alloy", 2);
            assertEquals(List.of("bolt", "zinc"), inv.inStockSorted(),
                    "alloy is at zero, so it is not in stock; the rest are sorted");
        }

        @Test
        @DisplayName("totalUnits adds everything")
        void totals() {
            Inventory inv = new Inventory();
            inv.add("a", 2);
            inv.add("b", 3);
            assertEquals(5, inv.totalUnits());
            assertEquals(0, new Inventory().totalUnits());
        }

        @Test
        @DisplayName("quantities() is a read-only view")
        void quantitiesIsReadOnly() {
            Inventory inv = new Inventory();
            inv.add("bolt", 10);
            Map<String, Integer> view = inv.quantities();

            assertEquals(10, view.get("bolt"));
            assertThrows(UnsupportedOperationException.class, () -> view.put("hack", 999));
        }

        @Test
        @DisplayName("modifying the returned view cannot corrupt the Inventory")
        void viewCannotCorrupt() {
            Inventory inv = new Inventory();
            inv.add("bolt", 10);
            try {
                inv.quantities().put("hack", 999);
            } catch (UnsupportedOperationException expected) {
                // fine
            }
            assertEquals(0, inv.quantityOf("hack"), "the Inventory must be unaffected");
        }
    }
}
