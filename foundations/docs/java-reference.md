# Java Reference

A living reference. It grows each week with what you've actually met — not a textbook you read
front to back. Use `Cmd+F`.

**Contents**
- [`final`](#final)
- [Primitives vs references](#primitives-vs-references)
- [Numeric types and integer division](#numeric-types-and-integer-division)
- [`==` vs `.equals()`](#-vs-equals)
- [Access modifiers](#access-modifiers)
- [`static`](#static)
- [Method anatomy](#method-anatomy)
- [`new` and constructors](#new-and-constructors)
- [Common compile errors](#common-compile-errors)

---

## `final`

`final` means **"this cannot be reassigned after it's set."** It means that, and only that, in
three places — and the third one surprises people.

### On a field

```java
private final double celsius;   // must be set once, in the constructor, then never again
```

Set it exactly once — at declaration, or in every constructor. After that, `this.celsius = x`
is a compile error.

**What it does NOT mean: it does not make the object immutable.**

```java
private final List<String> items = new ArrayList<>();

items.add("x");              // ✅ fine — mutating the OBJECT
items = new ArrayList<>();   // ❌ compile error — reassigning the ARROW
```

This is the arrow picture again. `final` freezes the arrow, not the thing it points at.

```
  final List<String> items
        │
        └──▶ ┌──────────────┐
  arrow is   │  ArrayList   │  ← contents can still change freely
  frozen ✓   │  ["x"]       │
             └──────────────┘
```

So a class is only genuinely immutable when its fields are `final` **and** each field is itself
immutable (a primitive, a `String`, a `BigDecimal`, another immutable class) or defensively
copied. `Temperature` is immutable because `double` is a primitive — there's nothing to mutate.

### On a method

```java
public final double celsius() { ... }
```

Subclasses cannot override it. Use when overriding would break your class's guarantees.

### On a class

```java
public final class Temperature { ... }
```

Nobody can extend it. This is why `Temperature`, `Money` and `String` are all declared `final` —
a subclass could add mutable state and break the immutability promise.

### On a local variable or parameter

```java
final int x = 5;
x = 6;              // ❌ compile error
```

Works the same way. Mostly used to signal intent, and required for variables captured by
lambdas and anonymous classes.

### Why bother

1. **The compiler enforces your intent.** You can't accidentally reassign.
2. **It documents.** A reader sees `final` and knows the value never changes.
3. **Thread safety.** Final fields are safely published across threads — matters from Week 10.
4. **Immutability is built on it.** No `final`, no immutable class.

> **Rule of thumb: make every field `final` unless you have a reason not to.** You'll be
> surprised how rarely you need the exception.

---

## Primitives vs references

| | Primitives | Everything else |
|---|---|---|
| Types | `int` `double` `boolean` `char` `long` `float` `short` `byte` | `String`, `List`, your classes, arrays |
| Variable holds | the value | the address of an object |
| Lives on | the stack (when local) | the heap |
| Default value | `0`, `0.0`, `false`, `'\u0000'` | `null` |
| Can be `null` | no | yes |
| `==` compares | the value ✅ | the address ⚠️ |

Boxed wrappers (`Integer`, `Double`, `Boolean`) are **objects** that wrap a primitive, so they
can be `null` and `==` compares addresses. Unboxing a `null` throws NullPointerException.

---

## Numeric types and integer division

**Integer division truncates.** Both operands `int` means an `int` result, decimals discarded:

```java
9 / 5        // 1    not 1.8
1 / 2        // 0
7 / 2        // 3
```

If **either** operand is floating point, the whole expression is:

```java
9.0 / 5      // 1.8
9 / 5.0      // 1.8
(double) 9 / 5   // 1.8  — cast goes BEFORE the value
```

Cast syntax: `(type) value`. Never `value(type)`.

**Floating point is not decimal.** `0.1 + 0.2 == 0.30000000000000004`. Use `BigDecimal` for
money, built from a `String` — `new BigDecimal("0.1")`, never `new BigDecimal(0.1)`.

`BigDecimal.equals()` compares scale too, so `10.5` and `10.50` are not equal by it. Use
`compareTo(other) == 0`.

---

## `==` vs `.equals()`

```java
a == b         // same OBJECT? (address)
a.equals(b)    // same CONTENTS?
```

Primitives: `==` is correct. Objects: almost always `.equals()`.

Two traps where `==` appears to work:

**String pool** — identical literals are interned and share one object:
```java
"hello" == "hello"                      // true
"hello" == new String("hello")          // false
```

**Integer cache** — boxed values from −128 to 127 are cached:
```java
Integer a = 127, b = 127;  a == b       // true
Integer c = 128, d = 128;  c == d       // false
```

Both make `==` work in tests and fail in production. **Always `.equals()` for objects.**

---

## Access modifiers

| Modifier | Visible from |
|---|---|
| `private` | this class only |
| *(none)* — "package-private" | this package |
| `protected` | this package + subclasses |
| `public` | everywhere |

Default to `private` for fields, `public` only for what callers genuinely need. A public field
is part of your API forever; behind a getter you can change the representation later.

### `private` is per CLASS, not per OBJECT

This surprises nearly everyone. A method can read the private fields of **any** instance of its
own class, not just `this`:

```java
public final class Money {
    private final BigDecimal amount;

    public Money plus(Money other) {
        return new Money(this.amount.add(other.amount));
        //                              ^^^^^^^^^^^^^ legal — we are inside Money
    }
}
```

`private` means "visible inside this class", and `plus` is inside `Money`. Code *outside*
`Money` still cannot touch `other.amount`.

**Why:** privacy protects a class's invariants from outside code. Inside the class you are the
author — you already know the representation and are responsible for keeping it valid, so there
is nothing to protect you from.

**Where you'll see it:** every `equals()`, `compareTo()` and copy constructor in the JDK.

```java
// Integer.equals
public boolean equals(Object obj) {
    if (obj instanceof Integer) {
        return value == ((Integer)obj).value;   // other's private field
    }
    return false;
}
```

Without this rule you would need a public getter on every field just to compare two objects —
destroying encapsulation in order to serve it. Nested classes get the same access: an inner
class and its outer class can see each other's private members.

### Getters are not automatic

Add a getter when a caller has a real need, not reflexively. `Temperature` has `celsius()`
because callers want the number. `Money` has none — callers want `plus`, `minus`, `equals`,
`toString`. Exposing the raw `BigDecimal` would let someone do unrounded arithmetic on it,
which is the exact thing the class exists to prevent.

**Expose behaviour, not data.**

---

## `static`

Belongs to the **class**, not to any instance.

```java
class Counter {
    static int total = 0;      // ONE variable shared by every Counter
    int mine = 0;              // each Counter gets its own
}
```

A `static` method can be called without an object (`Math.max(1, 2)`) and cannot use `this` or
touch instance fields. Use it for pure functions of the arguments.

---

## Method anatomy

```java
public static double convert(double celsius) throws IOException { ... }
  │      │      │        │          │                  │
  │      │      │        │          │                  └─ checked exceptions it may throw
  │      │      │        │          └─ parameters (type then name)
  │      │      │        └─ method name
  │      │      └─ return type, or `void` for nothing
  │      └─ optional: belongs to the class, not an instance
  └─ visibility
```

`return` exits immediately. Any statement after it in the same block is **unreachable**, which
is a compile error in Java — not a warning.

---

## `new` and constructors

`new` is how you create an object.

```java
new ClassName(arguments)
```

Three steps:
1. Allocate space on the heap for a fresh object
2. **Run that class's constructor**, passing the arguments
3. Return the address of the new object

A **constructor** is the method with no return type whose name matches the class:

```java
public final class Temperature {
    private final double celsius;

    public Temperature(double celsius) {   // ← the constructor
        this.celsius = celsius;            //   runs when someone calls new Temperature(...)
    }
}

Temperature t = new Temperature(25.0);     // runs the above with celsius = 25.0
```

Notice the constructor has **no return type** — not even `void`. That's what distinguishes it
from an ordinary method. It implicitly returns the new object.

`this.celsius = celsius` disambiguates: `this.celsius` is the field, bare `celsius` is the
parameter. When their names differ you can drop `this.`, but using it is clearer.

### The immutable-update pattern

An immutable class never modifies itself — it builds a new instance:

```java
public Temperature warmer(double degrees) {
    return new Temperature(/* some new value */);
}
```

Every immutable type in Java works this way:

| Call | Returns |
|---|---|
| `"abc".toUpperCase()` | a new String |
| `bigDecimal.add(other)` | a new BigDecimal |
| `localDate.plusDays(3)` | a new LocalDate |

In every case the original is untouched, which is why ignoring the return value does nothing.

---

## Common compile errors

| Message | Usually means |
|---|---|
| `';' expected` | missing semicolon, or syntax the parser couldn't make sense of |
| `not a statement` | an expression where a statement belongs, often malformed syntax above it |
| `unreachable statement` | code after `return`/`throw`/`break` in the same block |
| `cannot find symbol` | typo, wrong case, missing import, or variable out of scope |
| `incompatible types: X cannot be converted to Y` | wrong type — needs a cast or a different type |
| `variable x might not have been initialized` | a local read before being assigned |
| `cannot assign a value to final variable x` | reassigning a `final` |
| `missing return statement` | a path through the method returns nothing |

**Always fix the first error first.** One syntax mistake cascades into many downstream errors
that vanish when you fix the real one.
