package dev.praxis.foundations.day02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/** Day 2 — Equality, Hashing & the Object Contract. */
class Day02Test {

    @Nested
    @DisplayName("1. Person — equals and hashCode with two fields")
    class PersonTest {

        @Test
        @DisplayName("same name and age are equal")
        void sameFieldsAreEqual() {
            assertEquals(new Person("Ada", 36), new Person("Ada", 36));
        }

        @Test
        @DisplayName("different name is not equal")
        void differentNameNotEqual() {
            assertNotEquals(new Person("Ada", 36), new Person("Grace", 36));
        }

        @Test
        @DisplayName("different age is not equal")
        void differentAgeNotEqual() {
            assertNotEquals(new Person("Ada", 36), new Person("Ada", 37));
        }

        @Test
        @DisplayName("never equal to null, and never throws")
        void notEqualToNull() {
            assertNotEquals(null, new Person("Ada", 36));
            assertFalse(new Person("Ada", 36).equals(null));
        }

        @Test
        @DisplayName("not equal to a different type")
        void notEqualToOtherType() {
            assertNotEquals("Ada", new Person("Ada", 36));
        }

        @Test
        @DisplayName("reflexive — equal to itself")
        void reflexive() {
            Person p = new Person("Ada", 36);
            assertEquals(p, p);
        }

        @Test
        @DisplayName("symmetric — a.equals(b) implies b.equals(a)")
        void symmetric() {
            Person a = new Person("Ada", 36);
            Person b = new Person("Ada", 36);
            assertTrue(a.equals(b) && b.equals(a));
        }

        @Test
        @DisplayName("a null name does not blow up")
        void nullNameIsSafe() {
            assertEquals(new Person(null, 36), new Person(null, 36),
                    "Objects.equals handles null on both sides — this is why you use it");
            assertNotEquals(new Person(null, 36), new Person("Ada", 36));
        }

        @Test
        @DisplayName("equal Persons have equal hash codes")
        void equalMeansSameHash() {
            assertEquals(new Person("Ada", 36).hashCode(), new Person("Ada", 36).hashCode());
        }

        @Test
        @DisplayName("hashCode uses BOTH fields, not just one")
        void hashUsesBothFields() {
            assertNotEquals(new Person("Ada", 36).hashCode(), new Person("Ada", 37).hashCode(),
                    "Same name, different age should normally hash differently");
            assertNotEquals(new Person("Ada", 36).hashCode(), new Person("Grace", 36).hashCode(),
                    "Different name, same age should normally hash differently");
        }

        @Test
        @DisplayName("a null name hashes without throwing")
        void nullNameHashes() {
            assertDoesNotThrow(() -> new Person(null, 36).hashCode());
        }

        @Test
        @DisplayName("works in a HashSet — equal Persons deduplicate")
        void worksInHashSet() {
            Set<Person> set = new HashSet<>();
            set.add(new Person("Ada", 36));
            set.add(new Person("Ada", 36));
            assertEquals(1, set.size(), "equals + hashCode agreeing is what makes this work");
            assertTrue(set.contains(new Person("Ada", 36)));
        }

        @Test
        @DisplayName("works as a HashMap key")
        void worksAsMapKey() {
            Map<Person, String> map = new HashMap<>();
            map.put(new Person("Ada", 36), "mathematician");
            assertEquals("mathematician", map.get(new Person("Ada", 36)));
        }

        @Test
        @DisplayName("toString formats as \"Ada (36)\", null name as \"unknown\"")
        void formatsNicely() {
            assertEquals("Ada (36)", new Person("Ada", 36).toString());
            assertEquals("unknown (36)", new Person(null, 36).toString());
        }
    }

    @Nested
    @DisplayName("2. BrokenHash — what breaks without hashCode")
    class BrokenHashTest {

        @Test
        @DisplayName("HashSet CANNOT find an equal Id — the bug")
        void hashSetFails() {
            assertFalse(BrokenHash.hashSetFindsEqualId(),
                    "Equal objects hashing differently land in different buckets, "
                  + "so contains() never even calls equals()");
        }

        @Test
        @DisplayName("ArrayList CAN find it — because a List only uses equals")
        void arrayListWorks() {
            assertTrue(BrokenHash.arrayListFindsEqualId(),
                    "A List scans every element calling equals() — no hashing involved");
        }

        @Test
        @DisplayName("the Set holds duplicates, which a Set is not supposed to do")
        void setHoldsDuplicates() {
            assertEquals(2, BrokenHash.hashSetSizeAfterAddingTwoEqualIds(),
                    "Two equal Ids both got stored. The Set contract is broken.");
        }
    }

    @Nested
    @DisplayName("3. MutableKey — why mutable keys are a landmine")
    class MutableKeyTest {

        @Test
        @DisplayName("lookup works when the key is left alone")
        void normalLookup() {
            assertEquals("value", MutableKey.lookupWithoutMutation());
        }

        @Test
        @DisplayName("lookup FAILS after mutating the key — same object, still lost")
        void lookupBreaksAfterMutation() {
            assertNull(MutableKey.lookupAfterMutatingKey(),
                    "The entry was filed under the OLD hash. The key now hashes "
                  + "somewhere else, so get() looks in the wrong bucket.");
        }

        @Test
        @DisplayName("the entry is still there — unreachable, not removed")
        void entryIsStranded() {
            assertEquals(1, MutableKey.mapSizeAfterMutatingKey(),
                    "size() is 1, but you can never get() it again. A memory leak.");
        }
    }

    @Nested
    @DisplayName("4. Counter — static vs instance")
    class CounterTest {

        @BeforeEach
        void resetCounter() {
            Counter.reset();
        }

        @Test
        @DisplayName("the total is shared across all instances")
        void totalIsShared() {
            new Counter();
            new Counter();
            new Counter();
            assertEquals(3, Counter.totalCreated(),
                    "One static field, incremented by every constructor call");
        }

        @Test
        @DisplayName("each instance has its own serial number")
        void serialsAreIndividual() {
            Counter first = new Counter();
            Counter second = new Counter();
            Counter third = new Counter();

            assertEquals(1, first.serialNumber());
            assertEquals(2, second.serialNumber());
            assertEquals(3, third.serialNumber());
        }

        @Test
        @DisplayName("an instance's serial does not change when more are created")
        void serialIsStable() {
            Counter first = new Counter();
            new Counter();
            new Counter();
            assertEquals(1, first.serialNumber(),
                    "The instance field is its own copy — later objects cannot touch it");
        }

        @Test
        @DisplayName("totalCreated() is callable without any instance")
        void staticNeedsNoInstance() {
            assertEquals(0, Counter.totalCreated());
            new Counter();
            assertEquals(1, Counter.totalCreated());
        }
    }
}
