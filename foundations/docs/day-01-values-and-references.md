# Day 1 — Values, References & Identity

**~12 hrs** · Closes diagnostic **Q1, Q2, Q3, Q4**

> Read a section, then do its exercise. Don't read the whole thing first.

**Files** — [`Temperature`](../src/main/java/dev/praxis/foundations/day01/Temperature.java) · [`StringFacts`](../src/main/java/dev/praxis/foundations/day01/StringFacts.java) ·
[`BoxedNumbers`](../src/main/java/dev/praxis/foundations/day01/BoxedNumbers.java) · [`References`](../src/main/java/dev/praxis/foundations/day01/References.java) ·
[`Money`](../src/main/java/dev/praxis/foundations/day01/Money.java) · [tests](../src/test/java/dev/praxis/foundations/day01/Day01Test.java)

Run `./check` from `foundations/` at any point to see where you are.

---

## Why this is week 1

Four of your diagnostic misses were the same misunderstanding wearing different clothes:

| Q | You said | Actually |
|---|---|---|
| Q1 | `Integer 127 == 127` and `128 == 128` both true | Only the first |
| Q2 | didn't know | `0.1 + 0.2 != 0.3` |
| Q3 | `s.toUpperCase()` changes `s` | It cannot |
| Q4 | reassigning a parameter affects the caller | It cannot |

All four come down to one question: **what does a variable actually hold?** Get that right and
they all collapse into obvious.

---

## 1. What a variable holds

Java has exactly two kinds of value.

**Primitives** — `int`, `double`, `boolean`, `char`, `long`, `float`, `short`, `byte`.
The variable holds **the value itself**.

```java
int a = 5;
int b = a;   // b gets a COPY of the value
b = 10;
// a is still 5
```

**Everything else is a reference.** The variable holds **the address of an object**, not the object.

```java
List<String> a = new ArrayList<>();
List<String> b = a;   // b gets a COPY of the ADDRESS — both point at the SAME list
b.add("x");
// a.size() is now 1 — there was only ever one list
```

The box-and-arrow picture:

```
   PRIMITIVES                    REFERENCES
   ┌─────────┐                   ┌─────────┐         ┌──────────────┐
 a │    5    │                 a │  ──────────────▶  │  ArrayList   │
   └─────────┘                   └─────────┘    ▲    │   ["x"]      │
   ┌─────────┐                   ┌─────────┐    │    └──────────────┘
 b │   10    │                 b │  ───────────┘
   └─────────┘                   └─────────┘
   two values                    two arrows, ONE object
```

**This is the whole week.** Everything below follows from it.

---

## 2. Immutability

An object is immutable when nothing can change it after construction. `String` is the famous one.

```java
String s = "hello";
s.toUpperCase();          // returns "HELLO" — and you threw it away
System.out.println(s);    // hello

s = s.toUpperCase();      // NOW s points at the new String
System.out.println(s);    // HELLO
```

`toUpperCase()` cannot modify `s`. There is no mechanism by which it could — Strings have no
mutating operations at all. It builds a *new* String and hands it back. If you ignore the return
value, nothing happened.

The same catches people with `trim()`, `replace()`, `substring()`, `concat()`. **Every String
method returns a new String.**

Why bother? Immutable objects are safe to share — across methods, across threads, as Map keys —
because nobody can change them behind your back. You'll build two immutable classes this week.

> 🔨 **Exercise 1: [`Temperature.java`](../src/main/java/dev/praxis/foundations/day01/Temperature.java)** · 🔨 **Exercise 2: [`StringFacts.java`](../src/main/java/dev/praxis/foundations/day01/StringFacts.java)**
>
> Tests: [`Day01Test`](../src/test/java/dev/praxis/foundations/day01/Day01Test.java) → `TemperatureTest`, `StringFactsTest`

---

## 3. Identity vs equality

```java
a == b         // are these the SAME OBJECT? (same address)
a.equals(b)    // do they have the same CONTENTS?
```

For primitives `==` compares values and is correct. For objects it compares **addresses**, which
is almost never what you want.

```java
String a = new String("hello");
String b = new String("hello");
a == b         // false — two separate objects
a.equals(b)    // true  — same characters
```

### The string pool

Here's where Q1 caught you. Java interns string *literals* — identical literals in your source
share one object:

```java
String a = "hello";
String b = "hello";
a == b         // TRUE — both point at the one pooled object
```

So `==` on strings sometimes works, which is worse than never working, because you can ship code
that passes your tests and breaks on data read from a file or a database.

**Rule: always `.equals()` for objects. No exceptions.**

### The Integer cache

Same trap, different type. Java caches boxed `Integer` objects in a small range around zero:

```java
Integer a = 127, b = 127;
a == b         // true  — both from the cache

Integer c = 128, d = 128;
c == d         // false — outside the cache, two objects
```

Find the exact boundary yourself in Exercise 3. Don't look it up — the point is that you *saw*
it flip.

> 🔨 **Exercise 3: [`BoxedNumbers.java`](../src/main/java/dev/praxis/foundations/day01/BoxedNumbers.java)**
>
> Tests: [`Day01Test`](../src/test/java/dev/praxis/foundations/day01/Day01Test.java) → `BoxedNumbersTest`

---

## 4. Pass-by-value, always

Java is **always** pass-by-value. Always. What trips people up is that *the value of a reference
variable is an address*, so a copy of it still points at the same object.

```java
void mutate(List<String> items) {
    items.add("x");              // mutates the object both arrows point at
}                                // CALLER SEES THIS

void reassign(List<String> items) {
    items = new ArrayList<>();   // rebinds only the local copy of the arrow
    items.add("x");              // mutates the NEW list
}                                // CALLER SEES NOTHING
```

```
 reassign(mine):
   caller's `mine`  ──────────▶ ┌────────────┐
                                │  ["a"]     │   ← untouched
   method's `items` ───╳        └────────────┘
            └──────────────────▶ ┌────────────┐
                                 │  ["x"]     │   ← discarded at return
                                 └────────────┘
```

A method can **change what an object contains**. It can never **change which object the
caller's variable points at.** That one sentence is Q4.

> 🔨 **Exercise 4: [`References.java`](../src/main/java/dev/praxis/foundations/day01/References.java)**
>
> Tests: [`Day01Test`](../src/test/java/dev/praxis/foundations/day01/Day01Test.java) → `ReferencesTest`

---

## 5. Floating point is not decimal

```java
System.out.println(0.1 + 0.2);   // 0.30000000000000004
```

Not a Java bug. `double` is binary floating point (IEEE 754), and 0.1 has no exact binary
representation — same reason 1/3 has no exact decimal one. The error is tiny, and it compounds.

For money that is unacceptable. Use `BigDecimal`, constructed **from a String**:

```java
new BigDecimal("0.1")   // exactly 0.1
new BigDecimal(0.1)     // 0.1000000000000000055511151231257827... — you imported the error
```

One more trap, which catches experienced developers: `BigDecimal.equals()` considers *scale*,
so `10.5` and `10.50` are **not** equal by it. Use `compareTo(other) == 0` for value equality.

> 🔨 **Exercise 5: [`Money.java`](../src/main/java/dev/praxis/foundations/day01/Money.java)**
>
> Tests: [`Day01Test`](../src/test/java/dev/praxis/foundations/day01/Day01Test.java) → `MoneyTest`

---

## ✅ Day 1 done when

- [x] `mvn -q test` is green — all 22 tests
- [x] You can draw the box-and-arrow picture for mutate vs reassign from memory
- [x] You can say why `s.toUpperCase()` alone does nothing, in one sentence
- [x] You found the Integer cache boundary **by experiment**, not by looking it up
- [x] You can explain why `==` on strings sometimes works, and why that's worse than never
- [x] You wrote every line yourself, with AI completion off

Then: `cd ~/Downloads/praxis && ./bin/log "week 1 — values and references"`

---

## If you get stuck

In order:

1. **Read the test.** It says exactly what's expected, and its failure message usually says why.
2. **Write a scratch `main()`** and print things. Half these exercises are "run it and look."
3. **Use the debugger.** Breakpoint, inspect, step. This is what it's for.
4. **Re-read the section above.**
5. Only then ask me — and ask about the *concept*, not for the code.
