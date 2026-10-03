# Praxis Foundations

Phase -1 exercises. A course built for one person, targeted at one diagnostic.

No account, no platform, no CLI, no second editor. A Maven project and a test suite.

---

## The loop

```
read docs/day-NN-*.md  →  implement the stubs  →  mvn test  →  green  →  journal
```

1. Read the day's lesson, section by section
2. Each section ends with 🔨 — go do that exercise before reading on
3. Replace every `throw new UnsupportedOperationException("TODO: ...")` with real code
4. `mvn -q test` until green
5. Log it: `cd .. && ./bin/log "day N — topic"`

---

## Running it

```bash
cd ~/Downloads/praxis/foundations
./check
```

```
  Week 01 — Values, References & Identity

     Temperature      ░░░░░  0/5
     StringFacts      ░░░░   0/4
     BoxedNumbers     ░░░    0/3
     References       ░░░    0/3
     Money            █░░░░░░  1/7

   1/22 passing (4%)

   Next: Temperature.storesCelsius
          TODO: implement the constructor
```

It tells you how far you are and which exercise is next, in lesson order. One day only:
`./check 01`. Raw Maven output if you want it: `mvn test`.

In IntelliJ: open the `foundations` folder, let it import the Maven project, then click the
green arrow next to `Day01Test`. The debugger is right there — use it.

Run a single day:

```bash
mvn -q test -Dtest='Day01Test'
```

---

## The rules

> ### 🚫 No AI writes your code
>
> Not completion, not "just this bit", not writing it then checking. **Every character yours.**
>
> Allowed: asking me to explain a *concept*, or to help read an error after you've spent 15
> minutes on it. Never: asking for the code.
>
> You cannot cheat this and still pass the Phase -1 checkpoint, because the checkpoint is a
> retake of the diagnostic. The tests are not the goal — understanding is. The tests just tell
> you whether you got there.

- **Read the test when stuck.** It states the requirement and its failure message explains why.
- **Write scratch `main()` methods and print things.** Several exercises are explicitly "run it and look."
- **Don't skip ahead.** Each week assumes the one before.

---

## Reference

**[docs/java-reference.md](docs/java-reference.md)** — a living reference that grows each week
with what you've actually met. `final`, primitives vs references, integer division, `==` vs
`.equals()`, access modifiers, `static`, and a table of common compile errors.

Look things up there rather than asking me for syntax.

---

## Days

Each day is written when you reach it, so it adapts to how the previous one went — the content, the pacing and the emphasis all shift based on where you actually struggled.

---

## Why this exists

The baseline diagnostic came back 7/20 with a clear pattern: conceptual questions right,
runtime-behaviour questions wrong. That's reading about Java rather than writing it.

Three platforms were tried and discarded — dated tooling, paywalls, no coherent flow. This
replaces them. Every exercise here traces to a specific question that was missed.
