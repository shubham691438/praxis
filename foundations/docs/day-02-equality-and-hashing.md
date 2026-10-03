# Day 2 — Equality, Hashing & the Object Contract

**~4 hrs** · Closes diagnostic **Q6, Q10**

**Files** — [`Person`](../src/main/java/dev/praxis/foundations/day02/Person.java) ·
[`BrokenHash`](../src/main/java/dev/praxis/foundations/day02/BrokenHash.java) ·
[`MutableKey`](../src/main/java/dev/praxis/foundations/day02/MutableKey.java) ·
[`Counter`](../src/main/java/dev/praxis/foundations/day02/Counter.java) ·
[tests](../src/test/java/dev/praxis/foundations/day02/Day02Test.java)

`./check 02` from `foundations/` at any point.

---

## What Day 1 showed

You moved fast on concepts and slowly on two specific things. Worth naming them, because today
is built to drill both.

**1. Type tracking — your dominant error.** Six times you wrote something whose type didn't fit
where it was going:

```java
new Temperature(warmedTemperature)   // Temperature where a double was wanted
this.amount + other                  // Money where a BigDecimal was wanted
return this.amount.stripTrailingZeros();   // BigDecimal where an int was wanted
```

Every one was the same miss. Before writing a line, ask of each piece: **what type is this, and
what type does the slot want?** Today's exercises are full of places where that matters.

**2. Punctuation.** Four missing semicolons, one misplaced bracket, one typo. These aren't
conceptual — they're a workflow problem. **Look at IntelliJ's right-hand gutter before you run
anything.** Red marks mean it doesn't compile. `./check` is for logic; the IDE catches spelling.

What you did well: you solved `BoxedNumbers` and `References.addTo` with no help, your swap
arithmetic was genuinely clever, and your questions — *"does interning happen with other
objects?"*, *"why cast if instanceof proved it?"* — were the questions of someone building a
model rather than copying syntax. That's the part that matters.

---

## 1. The `equals` contract

You wrote `equals` yesterday. Java requires it to obey five rules, and the collections framework
assumes all five without checking.

| Rule | Meaning |
|---|---|
| **Reflexive** | `x.equals(x)` is always true |
| **Symmetric** | if `x.equals(y)` then `y.equals(x)` |
| **Transitive** | if `x.equals(y)` and `y.equals(z)` then `x.equals(z)` |
| **Consistent** | repeated calls give the same answer, if nothing changed |
| **Null-false** | `x.equals(null)` is always false — and never throws |

The standard template satisfies all five:

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;                    // reflexive, and a fast path
    if (!(o instanceof Person other)) return false; // null-false AND type-safe
    return /* field comparison */;
}
```

`instanceof` returns `false` for `null`, so the null rule is handled for free. That's why the
template looks the way it does.

> **Why symmetry can break:** if `Person.equals` accepted any `Object` with a matching name,
> then `person.equals(string)` could be true while `string.equals(person)` is false. Collections
> would then behave differently depending on which argument came first. Always compare like with
> like.

---

## 2. `hashCode` must agree with `equals`

> **If two objects are equal, they must return the same `hashCode`.**

The reverse is **not** required — unequal objects may share a hash code. That's called a
*collision* and it's normal and fine.

You already hit this in `Money`: `BigDecimal.hashCode()` includes scale, so `10.5` and `10.50`
hashed differently despite being `equals`. `stripTrailingZeros()` fixed it.

---

## 3. How `HashMap` actually works

This is the mechanism behind every bug today.

A `HashMap` is an array of **buckets**. To store a key:

1. Call `key.hashCode()` → an `int`
2. Reduce it to a bucket index, roughly `hash % numberOfBuckets`
3. Go to that bucket, and use `equals()` to check for an existing match

```
  hashCode()        bucket           equals()
  ──────────▶  ┌──┬──┬──┬──┬──┐  ──────────▶  find the exact entry
   "which       │0 │1 │2 │3 │4 │               within that bucket
    drawer?"    └──┴──┴──┴──┴──┘
```

**Two-stage lookup: the hash picks the drawer, `equals` finds the item in it.**

Now the failure mode is obvious. If two equal objects produce different hash codes, they go to
**different drawers**. `contains()` looks in one drawer, the object is in another, and
`equals()` is never even called. The object is invisible.

That's exactly diagnostic Q6, and Exercise 2 makes you watch it happen.

> 🔨 [`Person`](../src/main/java/dev/praxis/foundations/day02/Person.java) · [`BrokenHash`](../src/main/java/dev/praxis/foundations/day02/BrokenHash.java)

---

## 4. `java.util.Objects` — the helpers

Writing null-safe `equals` and `hashCode` by hand is tedious. The JDK has utilities:

```java
Objects.equals(a, b)        // true if both null, false if one is, else a.equals(b)
Objects.hash(a, b, c)       // combines any number of values into one int, nulls allowed
Objects.hashCode(a)         // 0 for null, else a.hashCode()
Objects.requireNonNull(x)   // throws immediately if null — fail fast at the boundary
```

`Objects.equals(a, b)` is the one you need today, because `Person.name` can be null and
`this.name.equals(other.name)` would throw a NullPointerException on a nameless person.

Import it with `import java.util.Objects;`.

> **Worth knowing for later:** a `record` generates `equals`, `hashCode` and `toString` for you
> from its fields. You're writing them by hand first so that when you use records you know what
> they generate and when the generated version is wrong — as it would be for `Money`, where
> scale must be ignored.

---

## 5. Never use a mutable object as a key

An entry is filed by its hash **at the moment of insertion**. If the key mutates afterwards, its
hash changes — but the entry doesn't move.

```java
map.put(key, "value");     // filed in bucket 3
key.setLabel("b");         // the key now hashes to bucket 7
map.get(key);              // looks in bucket 7. Finds nothing. Returns null.
```

The entry is still in bucket 3. `size()` still counts it. You can still reach it by iterating.
You can **never** `get()` it again — not even with the identical object you put in.

That's a memory leak that no profiler will flag as one, and it's why `String`, `Integer` and
`LocalDate` — the usual key types — are all immutable. `Person` and `Money` are safe for the
same reason.

> 🔨 [`MutableKey`](../src/main/java/dev/praxis/foundations/day02/MutableKey.java)

---

## 6. `static` vs instance

You asked yesterday why `toString()` takes no input. The answer was the implicit `this`. Here's
the other half.

| | Belongs to | Copies | Has `this`? |
|---|---|---|---|
| `static` field | the class | **one**, shared by all | — |
| instance field | each object | one **per object** | — |
| `static` method | the class | — | no |
| instance method | the object | — | yes |

```java
class Counter {
    static int total;    // ONE int. Every Counter sees the same value.
    int serial;          // Each Counter has its own.
}
```

A `static` method has no `this`, so it cannot read instance fields — there's no object to read
them from. `Counter.totalCreated()` is callable with no `Counter` in existence;
`counter.serialNumber()` needs one.

You've already used both: `StringFacts.shout(s)` was static (hence the parameter — no object to
read from), while `money.toString()` was an instance method (no parameter — the object was
right there).

> 🔨 [`Counter`](../src/main/java/dev/praxis/foundations/day02/Counter.java)

---

## ✅ Day 2 done when

- [ ] `./check 02` is green — all 24
- [ ] You can state all five `equals` rules
- [ ] You can draw the bucket diagram and explain why a missing `hashCode` loses objects
- [ ] You can explain why `HashSet` failed but `ArrayList` worked in Exercise 2
- [ ] You can explain why a mutated key strands its entry
- [ ] You can say when to use `static` and when not to
- [ ] You wrote every line yourself, with AI completion off

Then: `cd ~/Downloads/praxis && ./bin/log "day 2 — equality and hashing"`

Write up Exercise 2 or 3 in **What broke**. Both are genuine production bugs and both make
strong interview answers.

---

## 💬 Interview questions this closes

- *"What's the contract between equals and hashCode?"*
- *"What happens if you override equals but not hashCode?"* ← you'll have seen it
- *"How does HashMap work internally?"*
- *"Why shouldn't you use a mutable object as a map key?"*
- *"When would you use a static method?"*
