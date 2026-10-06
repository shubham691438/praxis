# Day 3 — Collections: List, Set, Map

**~5 hrs** · Closes diagnostic **Q11, Q12**

**Files** — [`ListOps`](../src/main/java/dev/praxis/foundations/day03/ListOps.java) ·
[`MapOps`](../src/main/java/dev/praxis/foundations/day03/MapOps.java) ·
[`SetOps`](../src/main/java/dev/praxis/foundations/day03/SetOps.java) ·
[`Inventory`](../src/main/java/dev/praxis/foundations/day03/Inventory.java) ·
[tests](../src/test/java/dev/praxis/foundations/day03/Day03Test.java)

---

## What changes today

**The stubs have fewer hints.** Day 1 and 2 told you which method to call. Today most say
*"Map has a method for this — find it."* Looking up an API from a description is the actual
daily skill, and you're ready for it.

**Exercise 4 has no structure at all.** `Inventory` gives you behaviour and tests; you choose
the fields and the collection types. That's your first design decision.

Day 2's errors were a semicolon, a capital letter and `+=` vs `++`. Day 1's were conceptual.
That's a real shift, so today assumes more.

---

## 1. The three shapes

| | Holds | Duplicates | Order | Lookup |
|---|---|---|---|---|
| **`List`** | a sequence | yes | by index | by position, O(1) in ArrayList |
| **`Set`** | unique items | no | depends on impl | by value, O(1) in HashSet |
| **`Map`** | key → value pairs | unique keys | depends on impl | by key, O(1) in HashMap |

Choosing wrongly is the most common cause of accidentally quadratic code.

```java
// O(n) per check — O(n²) over a loop
if (bigList.contains(item))

// O(1) per check
if (bigSet.contains(item))
```

A `List.contains` walks every element calling `equals`. A `HashSet.contains` hashes once and
looks in one bucket. For 10,000 items checked 10,000 times, that's 100 million comparisons
versus 10,000.

---

## 2. `ArrayList` vs `LinkedList` — diagnostic Q11

| | `ArrayList` | `LinkedList` |
|---|---|---|
| Backed by | an array | doubly-linked nodes |
| `get(i)` | **O(1)** — one offset calculation | **O(n)** — walk from an end |
| `add` at end | O(1) amortised | O(1) |
| `add`/`remove` at front | O(n) — shifts everything | O(1) |
| Memory | compact | an object header per element |

**Use `ArrayList`.** Practically always. Even where `LinkedList` wins on paper, `ArrayList`
often wins in practice because contiguous memory is cache-friendly and pointer-chasing is not.

Reach for `LinkedList` only when you're constantly inserting and removing at the ends — and at
that point you probably want `ArrayDeque`, which beats it at that too.

> **Interview answer:** "ArrayList for nearly everything. LinkedList's O(1) insertion only pays
> off if you already hold the node, and index access being O(n) usually costs more than it saves."

---

## 3. Iteration and `ConcurrentModificationException`

```java
for (String s : list) {
    if (s.isEmpty()) list.remove(s);   // ❌ ConcurrentModificationException
}
```

The iterator keeps a modification count. Changing the list behind its back invalidates it, and
it fails fast rather than silently skipping elements.

Three ways out:

```java
list.removeIf(String::isEmpty);                    // ✅ best — one call

Iterator<String> it = list.iterator();             // ✅ the iterator's own remove
while (it.hasNext()) { if (...) it.remove(); }

List<String> kept = new ArrayList<>();             // ✅ build a new list
for (String s : list) if (!...) kept.add(s);
```

---

## 4. Immutable collections

```java
List<String> a = List.of("x", "y");      // immutable, Java 9+
a.add("z");                              // UnsupportedOperationException
```

`List.of`, `Set.of`, `Map.of` return **immutable** collections. They also reject `null`
entirely. Great for constants, surprising if you expected something mutable — so to get a
mutable copy: `new ArrayList<>(List.of(...))`.

Note this throws rather than returning `false`. `tryAdd` asks you to observe that.

---

## 5. Map methods that replace four lines with one

This is where most Java gets tidier. The naive count:

```java
Integer current = map.get(word);          // might be null
if (current == null) map.put(word, 1);
else map.put(word, current + 1);
```

versus:

```java
map.merge(word, 1, Integer::sum);
```

| Method | Does |
|---|---|
| `getOrDefault(k, d)` | the value, or `d` if absent — **no null** |
| `putIfAbsent(k, v)` | only writes when the key is missing |
| `computeIfAbsent(k, fn)` | inserts `fn(k)` when missing, then **returns the value** — ideal for map-of-lists |
| `merge(k, v, fn)` | `v` if absent, else `fn(old, v)` — ideal for counting |
| `entrySet()` | key and value together in one pass |

**`computeIfAbsent` for grouping, `merge` for counting.** Learn those two and you'll reach for
them constantly.

---

## 6. The null trap — diagnostic Q12

```java
Map<String, Integer> m = new HashMap<>();
int v = m.get("missing");     // NullPointerException
```

`get` returns `null` for a missing key. Unboxing `null` into an `int` throws. You answered
"v is 0" — the exercise makes you watch it throw.

```java
int v = m.getOrDefault("missing", 0);   // ✅ 0
Integer v = m.get("missing");           // ✅ null, and you must handle it
```

**A `HashMap` permits one null key and any number of null values**, which means `get`
returning null is ambiguous — absent, or present-but-null? `containsKey` is the only way to
tell them apart.

---

## 7. Picking a `Set` or `Map` implementation

| Need | Set | Map |
|---|---|---|
| Fastest, order irrelevant | `HashSet` | `HashMap` |
| Insertion order preserved | `LinkedHashSet` | `LinkedHashMap` |
| Sorted by key | `TreeSet` | `TreeMap` |

Exercise 3 and 4 both hinge on this: *"preserving order"* and *"sorted"* are telling you which
to construct. Declare the interface, construct the implementation —
`Set<String> s = new LinkedHashSet<>();`

---

## 8. Defensive copies

`Inventory.quantities()` must hand back a view callers can't use to corrupt you.

```java
return map;                                 // ❌ caller can modify your internals
return Map.copyOf(map);                     // ✅ immutable snapshot
return Collections.unmodifiableMap(map);    // ✅ read-only view of the LIVE map
```

The difference matters: `copyOf` is frozen at the moment you called it; `unmodifiableMap` keeps
reflecting later changes while refusing writes. Decide which you want and be able to say why —
that's a real interview question.

---

## ✅ Day 3 done when

- [ ] `./check 03` is green — all 33
- [ ] You can state why `ArrayList` beats `LinkedList` for almost everything
- [ ] You can name three ways to remove while iterating
- [ ] You can explain why `int v = map.get(missing)` throws
- [ ] You can explain when to use `merge` vs `computeIfAbsent`
- [ ] You can justify your `Inventory` field choices out loud
- [ ] You can explain `Map.copyOf` vs `unmodifiableMap`
- [ ] You wrote every line yourself, with AI completion off

Then: `cd ~/Downloads/praxis && ./bin/log "day 3 — collections"`

---

## 💬 Interview questions this closes

- *"ArrayList or LinkedList?"*
- *"What's a ConcurrentModificationException and how do you avoid it?"*
- *"How would you count word frequencies?"*
- *"How do you return internal state without letting callers break your object?"*
- *"When would you use a TreeMap over a HashMap?"*
