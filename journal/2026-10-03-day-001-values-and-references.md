# Day 001 — Values, references & identity

**Date:** 2026-10-03 · **Phase:** -1 · **Time spent:** one long session
**Mode:** 🔨 building

> Written retroactively on 2026-10-06 from the session record. The factual parts are accurate.
> The two sections marked ✍️ are mine to fill in.

---

## What I did

Finished Day 1 of the custom foundations course. 22/22.

Five classes written from stubs: `Temperature` (immutability), `StringFacts` (string pool),
`BoxedNumbers` (Integer cache), `References` (mutate vs reassign), `Money` (BigDecimal,
equals/hashCode).

Before that: abandoned three learning platforms in one evening — MOOC.fi (NetBeans-era tooling,
Finnish videos, dead IntelliJ plugin), Hyperskill (paid), Exercism (no coherent flow). Ended up
with a custom Maven project in my own repo instead. Zero friction after that.

## What I learned

The one idea underneath the whole day: **a variable holds either a value (primitive) or an
address (everything else).** Four things I got wrong in the diagnostic all collapsed into that:

- `s.toUpperCase()` can't change `s` — it builds a new String and returns it
- `Integer 127 == 127` is true, `128 == 128` is false — the cache only covers −128..127
- A method can change what an object *contains*, never which object the caller's variable
  *points at*
- `0.1 + 0.2 != 0.3` — binary floating point, hence `BigDecimal` for money

Also picked up along the way, none of which I knew that morning: what `new` actually does,
that instance methods carry an implicit `this`, that `private` is per-class not per-object,
and that Java has no operator overloading (`+` on `BigDecimal` is a compile error).

## What broke

**Symptom:** `Money.equals("10.5")` vs `Money.equals("10.50")` — the hashCode test failed even
though equals passed.

**Diagnosis path:** the test message said equal objects must have equal hash codes. Traced it to
`BigDecimal.hashCode()`.

**Root cause:** `BigDecimal.hashCode()` includes **scale**, so `10.5` and `10.50` hash
differently. But my `equals` used `compareTo() == 0`, which ignores scale. So two objects were
`equals` with different hash codes — a broken contract. In a `HashMap` they'd land in different
buckets and the key would be unfindable.

**Fix:** `stripTrailingZeros()` before hashing, so both normalise to the same value.

---

Smaller ones, all mine:

- Four missing semicolons. Not reading IntelliJ's gutter before running.
- `new Temperature(warmedTemperature)` — wrapped a `Temperature` in a `Temperature`. The
  constructor wanted a `double`. I'd built the right object then tried to build another from it.
- `compareTo(other)` instead of `compareTo(other.amount)` — passed the whole `Money` where a
  `BigDecimal` was wanted.
- `return this.amount.stripTrailingZeros();` from a method declared `int`.
- Answered "true" for `canSwapPrimitivesViaMethod` after swapping two **locals** inside the
  method. The question was whether a method can swap the **caller's** variables. It can't.
- Dropped `final` from `Temperature`'s field while adding `private`. Tests still passed —
  which is the lesson. Green tests don't prove a design property is *enforced*.

**The pattern across all of them: not tracking what type each expression actually has.**

## What I don't understand yet

✍️ _(mine to fill — what still feels shaky?)_

## Interview-worthy

The `equals`/`hashCode` scale bug. It's a real production failure mode: a `HashMap` key you can
never retrieve, with no exception and no log line. I hit it first-hand rather than reading about
it. <!-- #interview #topic:java #topic:collections -->

Also: "I started a plan assuming I was a working Java dev, took a baseline diagnostic, scored
7/20, and rebuilt the plan around the result instead of pushing on." <!-- #interview #topic:judgment -->

---

**Next session:** Day 2 — equals and hashCode properly.
