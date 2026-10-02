# Praxis Foundations

Phase -1 exercises. A course built for one person, targeted at one diagnostic.

No account, no platform, no CLI, no second editor. A Maven project and a test suite.

---

## The loop

```
read docs/week-NN-*.md  →  implement the stubs  →  mvn test  →  green  →  journal
```

1. Read the week's lesson, section by section
2. Each section ends with 🔨 — go do that exercise before reading on
3. Replace every `throw new UnsupportedOperationException("TODO: ...")` with real code
4. `mvn -q test` until green
5. Log it: `cd .. && ./bin/log "week N — topic"`

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

It tells you how far you are and which exercise is next, in lesson order. One week only:
`./check 01`. Raw Maven output if you want it: `mvn test`.

In IntelliJ: open the `foundations` folder, let it import the Maven project, then click the
green arrow next to `Week01Test`. The debugger is right there — use it.

Run a single week:

```bash
mvn -q test -Dtest='Week01Test'
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

## Weeks

| Wk | Topic | Closes | Status |
|---|---|---|---|
| [01](docs/week-01-values-and-references.md) | Values, references & identity | Q1, Q2, Q3, Q4 | 📝 ready |
| 02 | `equals` & `hashCode` — the contract | Q6, Q10 | — |
| 03 | Collections I — List, Set | Q10, Q11 | — |
| 04 | Collections II — Map, nulls, iteration | Q12 | — |
| 05 | Exceptions & resources | Q13, Q14 | — |
| 06 | Classes, constructors, init order | Q7 | — |
| 07 | Inheritance, polymorphism, casting | Q8 | — |
| 08 | Interfaces, generics, composition | — | — |
| 09 | Streams, Optional, lambdas | — | — |
| 10 | Concurrency + capstone | Q15 | — |

Each week is written when you reach it, so it can adapt to how the previous one went.

---

## Why this exists

The baseline diagnostic came back 7/20 with a clear pattern: conceptual questions right,
runtime-behaviour questions wrong. That's reading about Java rather than writing it.

Three platforms were tried and discarded — dated tooling, paywalls, no coherent flow. This
replaces them. Every exercise here traces to a specific question that was missed.
