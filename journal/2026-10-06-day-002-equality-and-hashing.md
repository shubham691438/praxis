# Day 002 — Equality, hashing & the object contract

**Date:** 2026-10-06 · **Phase:** -1 · **Time spent:** one session
**Mode:** 🔨 building

> Written retroactively from the session record. Sections marked ✍️ are mine to fill in.

---

## What I did

Day 2 complete. 24/24, so 46/46 across both days.

Four classes: `Person` (equals/hashCode over two fields, one nullable), `BrokenHash` (observe
what breaks without hashCode), `MutableKey` (strand a map entry by mutating its key),
`Counter` (static vs instance).

Also read the actual JDK source for `HashSet` and `HashMap` out of `$JAVA_HOME/lib/src.zip`.

## What I learned

**`HashSet` is a `HashMap` in disguise.** Literally:

```java
static final Object PRESENT = new Object();
public boolean add(E e) { return map.put(e, PRESENT) == null; }
```

Your element is the key; the value is one shared dummy object. So everything about HashMap keys
applies to Set elements unchanged.

**How the bucket lookup actually works.** `hashCode()` picks the bucket, `equals()` finds the
item within it. Two stages. If the hash is wrong you never reach stage two — `equals` is never
called at all. The object isn't "compared and rejected", it's never found.

**The hash gets scrambled first:** `(h = key.hashCode()) ^ (h >>> 16)`. The bucket index uses
only the low bits (`(n-1) & hash`), so without mixing the high bits down, hash codes differing
only at the top would always collide.

**Collisions are unavoidable.** `hashCode` returns an `int` — 4.3 billion values — and there are
unlimited possible objects. `"Aa"` and `"BB"` both hash to 2112; I ran it. Collisions cost
*speed*; a broken contract costs *correctness*. Chains over 8 entries become red-black trees.

**A mutable map key strands its entry.** Put it in, mutate it, and `get()` with the identical
object returns null — the entry is filed under the old hash. `size()` still counts it. It's a
memory leak that no profiler flags.

**`private` is per class, not per object** — `other.amount` inside `Money.plus()` is legal.
Every `equals()` in the JDK relies on this.

**Objects inherit equals/hashCode/toString**, but identity-based. A `record` generates
value-based ones instead — though for `Money` the generated `equals` would be *wrong*, because
`BigDecimal.equals` compares scale.

## What broke

Far less than Day 1. Three errors total:

- Missing semicolon on an `import`. (Fifth one. The gutter habit still isn't automatic.)
- `"Unknown"` instead of `"unknown"` — the test printed expected vs actual side by side and the
  difference was right there. Reading the failure message properly would have saved the round trip.
- `count += count` instead of `count++` in the `Counter` constructor. `+=` adds the right side
  to the left; `count += count` doubles it, so starting from 0 it stayed 0 forever.

**No type-tracking errors at all**, which was the dominant failure mode yesterday.

The most useful moment was realising I'd had the fault backwards: I thought `Id`'s `equals` was
broken. It isn't — it's textbook correct. The defect is the *missing* `hashCode`, which falls
back to Object's identity-based one and contradicts `equals`. The proof is that `ArrayList`
finds the element fine, because a List only ever calls `equals`.

## What I don't understand yet

✍️ _(mine to fill)_

## Interview-worthy

- *"What happens if you override equals but not hashCode?"* — I can now describe the bucket
  mechanism, say that `equals` is never called, and contrast `HashSet` failing with `ArrayList`
  succeeding on the identical objects. <!-- #interview #topic:collections -->
- *"Why shouldn't a mutable object be a map key?"* — the stranded entry, reachable by iteration
  but never by `get()`. <!-- #interview #topic:collections -->
- *"How does HashMap work internally?"* — buckets, hash spreading, load factor 0.75, treeify at
  8. From the source, not a blog post. <!-- #interview #topic:collections -->

---

**Next session:** Day 3 — collections. List, Set, Map and choosing between them.
