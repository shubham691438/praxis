package dev.praxis.foundations.day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Week 1 — Values, references & identity.
 *
 * Run with:  mvn -q test
 * Or in IntelliJ: click the green arrow next to the class name.
 *
 * Every test fails until you implement the corresponding method.
 * Work top to bottom. Do not skip ahead.
 */
class Day01Test {

    @Nested
    @DisplayName("1. Temperature — immutability")
    class TemperatureTest {

        @Test
        @DisplayName("stores and returns celsius")
        void storesCelsius() {
            assertEquals(20.0, new Temperature(20.0).celsius(), 0.0001);
        }

        @Test
        @DisplayName("converts to fahrenheit (watch out for integer division)")
        void convertsToFahrenheit() {
            assertEquals(32.0,  new Temperature(0.0).fahrenheit(),   0.0001);
            assertEquals(212.0, new Temperature(100.0).fahrenheit(), 0.0001);
            assertEquals(98.6,  new Temperature(37.0).fahrenheit(),  0.0001);
        }

        @Test
        @DisplayName("warmer() returns a NEW object and leaves the original untouched")
        void warmerDoesNotMutate() {
            Temperature original = new Temperature(20.0);
            Temperature warmed = original.warmer(5.0);

            assertEquals(25.0, warmed.celsius(), 0.0001, "warmer() should add the degrees");
            assertEquals(20.0, original.celsius(), 0.0001,
                    "the ORIGINAL must be unchanged — this is what immutable means");
            assertNotSame(original, warmed, "warmer() must return a different object");
        }

        @Test
        @DisplayName("cooler() returns a NEW object and leaves the original untouched")
        void coolerDoesNotMutate() {
            Temperature original = new Temperature(20.0);
            Temperature cooled = original.cooler(5.0);

            assertEquals(15.0, cooled.celsius(), 0.0001);
            assertEquals(20.0, original.celsius(), 0.0001, "the ORIGINAL must be unchanged");
            assertNotSame(original, cooled);
        }

        @Test
        @DisplayName("chaining leaves every intermediate value intact")
        void chainingIsSafe() {
            Temperature a = new Temperature(0.0);
            Temperature b = a.warmer(10.0);
            Temperature c = b.warmer(10.0);

            assertEquals(0.0,  a.celsius(), 0.0001);
            assertEquals(10.0, b.celsius(), 0.0001);
            assertEquals(20.0, c.celsius(), 0.0001);
        }
    }

    @Nested
    @DisplayName("2. StringFacts — immutability and the pool")
    class StringFactsTest {

        @Test
        @DisplayName("shout() uppercases without touching the original")
        void shoutDoesNotMutate() {
            String original = "hello";
            assertEquals("HELLO", StringFacts.shout(original));
            assertEquals("hello", original,
                    "Strings are immutable — the original cannot change");
        }

        @Test
        @DisplayName("two identical literals ARE the same object (the string pool)")
        void literalsArePooled() {
            assertTrue(StringFacts.literalsAreSameObject(),
                    "Identical String literals are interned — they share one object");
        }

        @Test
        @DisplayName("a literal and a new String are NOT the same object")
        void newStringIsNotPooled() {
            assertFalse(StringFacts.literalAndNewAreSameObject(),
                    "new String() forces a separate object on the heap");
        }

        @Test
        @DisplayName("...but they ARE equal by value — which is why you use .equals()")
        void newStringIsStillEqual() {
            assertTrue(StringFacts.literalAndNewAreEqual(),
                    "equals() compares characters, not identity");
        }
    }

    @Nested
    @DisplayName("3. BoxedNumbers — the Integer cache")
    class BoxedNumbersTest {

        @Test
        @DisplayName("small boxed values share one cached object")
        void smallValuesAreCached() {
            assertTrue(BoxedNumbers.sameObjectWhenBoxed(127));
            assertTrue(BoxedNumbers.sameObjectWhenBoxed(0));
            assertTrue(BoxedNumbers.sameObjectWhenBoxed(-128));
        }

        @Test
        @DisplayName("large boxed values do not")
        void largeValuesAreNotCached() {
            assertFalse(BoxedNumbers.sameObjectWhenBoxed(128));
            assertFalse(BoxedNumbers.sameObjectWhenBoxed(1000));
            assertFalse(BoxedNumbers.sameObjectWhenBoxed(-129));
        }

        @Test
        @DisplayName("you found the exact boundary by experiment")
        void foundTheBoundary() {
            assertEquals(127,  BoxedNumbers.highestCachedValue());
            assertEquals(-128, BoxedNumbers.lowestCachedValue());
        }
    }

    @Nested
    @DisplayName("4. References — mutate vs reassign")
    class ReferencesTest {

        @Test
        @DisplayName("mutating the object IS visible to the caller")
        void mutationIsVisible() {
            List<String> mine = new ArrayList<>();
            mine.add("a");

            References.addTo(mine, "b");

            assertEquals(List.of("a", "b"), mine,
                    "addTo() mutates the object the caller handed over");
        }

        @Test
        @DisplayName("reassigning the parameter is NOT visible to the caller")
        void reassignmentIsInvisible() {
            List<String> mine = new ArrayList<>();
            mine.add("a");

            References.replaceWith(mine, "b");

            assertEquals(List.of("a"), mine,
                    "replaceWith() only rebound its local copy of the reference — "
                  + "the caller's variable still points at the original list");
        }

        @Test
        @DisplayName("you cannot swap primitives through a method in Java")
        void cannotSwapPrimitives() {
            assertFalse(References.canSwapPrimitivesViaMethod(),
                    "Java is pass-by-value. A method gets copies and cannot "
                  + "rebind the caller's variables.");
        }
    }

    @Nested
    @DisplayName("5. Money — never use double for money")
    class MoneyTest {

        @Test
        @DisplayName("the floating point problem this class exists to avoid")
        void doublesAreWrongForMoney() {
            assertNotEquals(0.3, 0.1 + 0.2,
                    "If this ever passes, binary floating point changed. It did not.");
        }

        @Test
        @DisplayName("adds exactly")
        void addsExactly() {
            Money result = new Money("0.10").plus(new Money("0.20"));
            assertEquals("0.30", result.toString(),
                    "BigDecimal gets this exactly right where double does not");
        }

        @Test
        @DisplayName("subtracts exactly")
        void subtractsExactly() {
            assertEquals("9.50", new Money("10.00").minus(new Money("0.50")).toString());
        }

        @Test
        @DisplayName("always renders 2 decimal places")
        void rendersTwoDecimals() {
            assertEquals("5.00", new Money("5").toString());
            assertEquals("5.50", new Money("5.5").toString());
        }

        @Test
        @DisplayName("plus() returns a NEW Money, leaving both operands untouched")
        void plusDoesNotMutate() {
            Money a = new Money("10.00");
            Money b = new Money("5.00");
            Money sum = a.plus(b);

            assertEquals("15.00", sum.toString());
            assertEquals("10.00", a.toString(), "a must be unchanged");
            assertEquals("5.00",  b.toString(), "b must be unchanged");
        }

        @Test
        @DisplayName("equal by value — and 10.5 equals 10.50, which BigDecimal.equals() gets wrong")
        void equalsByValue() {
            assertEquals(new Money("10.00"), new Money("10.00"));
            assertEquals(new Money("10.5"),  new Money("10.50"),
                    "Use compareTo() == 0, not BigDecimal.equals() — scale must not matter");
            assertNotEquals(new Money("10.00"), new Money("10.01"));
        }

        @Test
        @DisplayName("hashCode agrees with equals")
        void hashCodeAgreesWithEquals() {
            assertEquals(new Money("10.5").hashCode(), new Money("10.50").hashCode(),
                    "Objects that are equal MUST have the same hash code. Week 2 covers why.");
        }
    }
}
