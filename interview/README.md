# Interview Prep — the accumulating sheet

This grows as you go. It is **generated from the journal and ADRs**, not written separately —
that's the Phase 8 feature. Until then, maintain it by hand after each phase checkpoint.

---

## The 2-minute pitch

Practise until it's effortless. This is the first thing out of your mouth when they say
*"tell me about a project you're proud of."*

> _Draft it after Phase 3 — you won't have the substance before then._

**Structure that works:**
1. **The hook** (15s) — the self-referential thing. It teaches the concepts it's built on, and
   proves it at runtime.
2. **The scale of it** (20s) — 10 services, Kafka, Kubernetes, sandboxed execution, RAG.
3. **One hard problem, in depth** (60s) — pick the sandbox or the saga. Tell it as a story
   with a specific failure in it.
4. **What it cost you** (15s) — a trade-off you accepted and regret slightly. Signals honesty.
5. **Hand them something** (10s) — "I wrote it up, I can send you the architecture doc."

---

## Flagship stories

The three you lead with. Each needs: the problem → what you tried → what broke → what you
learned. **Specific numbers beat adjectives every time.**

| # | Story | Phase | Status |
|---|---|---|---|
| 1 | Running untrusted code safely — the attack/mitigation doc | 4 | _pending_ |
| 2 | The dual-write bug and the outbox that fixed it | 3 | _pending_ |
| 3 | Tracing a request across an async Kafka boundary | 3/6 | _pending_ |
| 4 | Three performance fixes, with before/after p99 | 7 | _pending_ |
| 5 | Measuring prompt quality instead of guessing | 2 | _pending_ |

---

## Question bank

Filled in per phase, from each phase file's "Interview questions this phase answers" section.
Format: **question → 3-line answer → the one gotcha.**

### Phase -1 — Java Foundations

#### Day 1 — Values, references & identity

**Q: What's the difference between `==` and `.equals()`?**
`==` compares addresses — are these the same object? `.equals()` compares contents. For
primitives `==` is correct; for objects it's almost never what you want.
> **Gotcha:** `==` *sometimes* works on Strings, because identical literals are interned into
> one pooled object. That's worse than never working — it passes tests with literals and fails
> in production with values from a database or an HTTP request, which are never pooled.

**Q: `Integer a = 127, b = 127; a == b`?**
`true`. Java caches boxed Integers from −128 to 127, so both reference the same cached object.
At 128 it's `false` — outside the cache, two separate objects.
> **Gotcha:** the same applies to `Short`, `Long`, `Byte` (−128..127) and `Character` (0..127).
> `Float` and `Double` have no cache at all. Every cached type is immutable — that's the
> precondition, since sharing a mutable object would let one holder corrupt another's value.

**Q: Why doesn't `s.toUpperCase()` change `s`?**
Strings are immutable. The method builds a *new* String and returns it; ignoring the return
value discards the result. You need `s = s.toUpperCase()`.
> **Gotcha:** the same pattern covers `BigDecimal.add()`, `LocalDate.plusDays()` and every
> immutable type. If ignoring the return value makes a call pointless, the type is immutable.
> `StringBuilder.append()` is the exception — it mutates *and* returns `this` for chaining.

**Q: Is Java pass-by-value or pass-by-reference?**
Always pass-by-value. What confuses people is that the value of a reference variable is an
address, so a copy of it still points at the same object.
> **Gotcha:** a method can change what an object *contains* (caller sees it) but never which
> object the caller's variable *points at* (caller sees nothing). Which is why you cannot write
> a `swap(int, int)` method in Java.

**Q: Why not use `double` for money?**
`double` is binary floating point; 0.1 has no exact binary representation. `0.1 + 0.2` gives
`0.30000000000000004`, and the error compounds. Use `BigDecimal`, constructed from a `String`.
> **Gotcha:** `new BigDecimal(0.1)` imports the very error you're escaping — pass a String.
> And `BigDecimal.equals()` compares *scale*, so `10.5` and `10.50` are not equal by it.
> Use `compareTo(x) == 0`.

**Q: What does `final` mean?**
Cannot be reassigned. On a field, set once; on a method, cannot be overridden; on a class,
cannot be extended.
> **Gotcha:** it does **not** make an object immutable. `final List l = new ArrayList<>()`
> still permits `l.add(x)`. It freezes the reference, not the object.

---

#### Day 2 — Equality, hashing & the object contract

**Q: What's the contract between `equals` and `hashCode`?**
If two objects are equal, they must return the same hash code. The reverse isn't required —
unequal objects may share one, which is a collision and is normal.
> **Gotcha:** collisions are mathematically unavoidable — `hashCode` returns an `int` (~4.3
> billion values) and there are unlimited possible objects. `"Aa"` and `"BB"` both hash to 2112.
> Collisions cost speed; a broken contract costs correctness.

**Q: What happens if you override `equals` but not `hashCode`?**
The object becomes invisible to hash-based collections. `HashSet.contains()` hashes to one
bucket, the object is in another, and **`equals` is never called**. A `Set` will hold
duplicates; a `HashMap` will lose keys.
> **Gotcha:** `equals` isn't broken — it's correct. The *inherited* `hashCode` contradicts it.
> The proof: an `ArrayList` finds the same object fine, because a List only ever calls `equals`.
> Nothing enforces the contract — no compiler warning, no exception. It just silently misbehaves.

**Q: How does `HashMap` work internally?**
An array of buckets. `hashCode()` picks the bucket, then `equals()` finds the entry within it —
two-stage lookup. Collisions chain as a linked list, and convert to a red-black tree past 8
entries in one bucket. Load factor 0.75: at 12 of 16 slots it doubles and rehashes everything.
> **Gotcha:** the hash is scrambled first — `h ^ (h >>> 16)`. The bucket index is `(n-1) & hash`,
> which only reads the low bits, so the high bits are mixed down to make them count. And
> `HashSet` is literally a `HashMap` whose values are all one shared dummy object.

**Q: Why shouldn't a mutable object be a `HashMap` key?**
An entry is filed by its hash at insertion time. Mutate the key and its hash changes, but the
entry doesn't move. `get()` with the *identical object* then returns null.
> **Gotcha:** the entry isn't removed — `size()` still counts it and iteration still finds it.
> It's unreachable by `get()` forever. A memory leak no profiler flags. This is why `String`,
> `Integer` and `LocalDate` — the usual key types — are all immutable.

**Q: `static` vs instance?**
A `static` member belongs to the class — one copy shared by everything. An instance member
belongs to each object. A `static` method has no `this`, so it can't touch instance state.
> **Gotcha:** this is why `toString()` takes no parameters (instance method — the object is the
> implicit `this`) while `Objects.equals(a, b)` takes two (static — no object to read from).

**Q: Why is the parameter of `equals` `Object` rather than your own type?**
Because it overrides `Object.equals(Object)`, and an override must match the signature exactly.
> **Gotcha:** writing `equals(Person o)` compiles but creates an *overload*, not an override.
> `@Override` would error, and `HashMap` and `List.contains()` would never call it. Silent bug.

**Q: Why cast after `instanceof` has already proved the type?**
The compiler tracks declared types, not runtime facts. `o` is declared `Object`, so `o.field`
won't compile regardless of what `instanceof` established.
> **Gotcha:** a cast converts nothing — the object was always that type. It only changes what
> the compiler permits. Java 16+ pattern matching (`o instanceof Money m`) binds the variable
> and removes the separate cast.

### Phase 0 — Foundations
_pending_

### Phase 1 — Spring & Data
_pending_

### Phase 2 — Reactive & AI
_pending_

### Phase 3 — Distributed Systems
_pending_

### Phase 4 — Container Security
_pending_

### Phase 5 — Data & Retrieval
_pending_

### Phase 6 — Kubernetes & Observability
_pending_

### Phase 7 — Cloud & Resilience
_pending_

### Phase 8 — Adaptive Engine
_pending_

---

## System design track

Running alongside everything. Log worked problems here as you do them.

| Date | Problem | Where I struggled |
|---|---|---|
| | | |

---

## Things I still can't explain well

Keep this list honest and short. Anything here is tomorrow's study.

- _(start filling this after Phase 0)_
