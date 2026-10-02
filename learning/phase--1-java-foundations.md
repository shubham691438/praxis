# Phase −1 — Java, Actually Written

**10 weeks · ~120 hrs** · The phase that was missing. Added 2026-10-02 after the baseline
diagnostic came back 7/20.

> **Build at the end:** A real command-line application, written entirely by you, with no AI
> and no tutorial to copy from. Plus a retake of the diagnostic at **16/20 or better**.

---

## Why this phase exists

The diagnostic found a clean pattern. Every question you answered correctly was **conceptual** —
encapsulation, 401 vs 403, primary key vs unique, stateless REST. Every question you got wrong
was **"what does this code do when it runs"** — string immutability, constructor ordering, null
unboxing, which line throws the NPE, resource leaks.

That is the exact signature of reading about Java without writing it.

So the governing rule of this phase is not a reading list:

> ## 🚫 No AI writes your code. None. For ten weeks.
>
> Not autocomplete beyond a single token. Not "just scaffold this bit." Not "I'll write it then
> check with AI." **You type every character.**
>
> AI is allowed for exactly two things: explaining a concept you don't understand, and helping
> you read an error message you're stuck on after 15 minutes of your own effort. Never for
> producing code.
>
> **Turn off Copilot and any AI completion in your IDE now, before you start.** Leaving it on
> and relying on willpower does not work — the suggestion appears before you've had the thought.

This is the whole phase. The curriculum below is secondary to that rule.

---

## Setup — do this first (30 min)

**One editor. IntelliJ. Nothing else.** No VS Code, no TMC, no plugins to hunt down.

- [x] **IntelliJ IDEA Community** installed · *2026-10-02*
- [x] **All AI completion disabled** — Copilot, JetBrains AI, Tabnine, Codeium · *2026-10-02*
- [x] Debugger basics: breakpoints, step over, step into, inspect variables · *2026-10-02*
- [x] Java 21 LTS, `JAVA_HOME` pinned to it · *2026-10-02*
- [x] Maven 3.10 (needed from Phase 0) · *2026-10-02*
- [ ] Create a free [Exercism account](https://exercism.org/tracks/java) and join the Java track
- [ ] Install the CLI: `brew install exercism`
- [ ] Configure it with your token (Exercism shows the exact command after signup)
- [ ] `exercism download --track=java --exercise=hello-world`, open the folder in IntelliJ, run `./gradlew test`

> Each exercise ships with a Gradle wrapper, so tests run with no Gradle install and nothing
> conflicts with the Maven you'll use for Praxis itself.

---

## The primary resource

⭐ **[Exercism — Java Track](https://exercism.org/tracks/java)** · 🆓 **free, permanently**

**158 exercises across 26 concepts**, automated analysis of your code, and **free human mentoring** —
real people who read what you wrote and tell you why it isn't idiomatic. Nothing else free offers that.

Two modes, use both:
- **Learn** — concept exercises in a fixed order. This is your syllabus; follow it top to bottom.
- **Practice** — open-ended problems. Use these to drill whatever the section you're on covers.

**Request mentoring on every fifth exercise minimum.** It is the single most valuable thing on
the platform and the part most people skip. A stranger telling you "this works, but here's what
a Java developer would have written" is worth more than ten more exercises.

Each concept carries a written **About** document before its exercises — the Exceptions one runs
to roughly 7,000 words covering checked vs unchecked and the `Throwable` hierarchy properly. So
Exercism is both the tutorial and the practice. You do not need a separate course.

### How the 26 concepts map onto this phase

| Section | Exercism concepts | Covered? |
|---|---|---|
| 1 · Object semantics | Basics, Strings, Numbers, **Nullability**, Chars | ✅ fully |
| 2 · Collections | **Lists, Maps, Sets**, Arrays, Enums | ✅ fully |
| 3 · Exceptions | **Exceptions**, Nullability | ✅ fully |
| 4 · OOP | Classes, **Constructors, Inheritance, Interfaces**, Method Overloading | ✅ fully |
| 5 · Generics & streams | Generic Types | ⚠️ generics yes, **streams/Optional no** |
| 6 · Concurrency | — | ❌ **not covered at all** |

The bolded concepts are your diagnostic's weak spots. Exercism hits every one of them.

### Filling the two gaps

Exercism has no concurrency track and doesn't teach streams as a concept. For those two sections:

- 📄🆓 **[dev.java — Streams](https://dev.java/learn/api/streams/)** and **[Concurrency](https://dev.java/learn/concurrency/)** — Oracle's own modern tutorials, English, genuinely good
- 🎥🆓 [Coding with John](https://www.youtube.com/@CodingWithJohn) — his threads and streams videos
- 🧪 The §6 lab (two threads, shared counter, fix it three ways) stands on its own — write it in IntelliJ, no platform needed

### Optional backup reading

[Java Programming MOOC — Helsinki](https://java-programming.mooc.fi/) · 🆓 · **browser only**

If an Exercism concept doesn't click, its written material is excellent and free. Read it as a
book in your browser — no account, no TMC, no plugin. **Optional, not required.**

> ⚠️ Skip its videos — they're in Finnish. The written text is fully English.

**Not yet:** *Effective Java* moves to Phase 0 — it assumes fluency you're building here.

---

## Week by week — how to run both sources

Two sources only work with a mapping. **MOOC is what you read; Exercism is what you write.**
Read the MOOC section first, then do the matching Exercism concept — reading gives the
continuity, exercises make it stick.

| Wk | Read — MOOC | Write — Exercism concepts | Fixes |
|---|---|---|---|
| 1 | Part 1 — all | Basics, Numbers, Booleans, If-Else | Q2 floating point |
| 2 | Part 2 — loops, methods | For Loops, For-Each, Ternary, Switch, Chars | — |
| 3 | Part 3 — errors, Lists, Arrays, Strings | Arrays, **Lists**, Strings | **Q3, Q11** |
| 4 | Part 4 — intro OOP, files | Classes, **Constructors** | **Q7** |
| 5 | **Part 5 — primitive vs reference variables, objects and references** | **Nullability** | **Q1, Q4, Q12** ⭐ |
| 6 | — | **Maps, Sets**, Enums | **Q10, Q12** |
| 7 | Part 3 §1 again — discovering errors | **Exceptions** | **Q13, Q14** |
| 8 | Part 6 — UI vs logic, testing | **Inheritance, Interfaces**, Method Overloading, Generic Types | **Q8** |
| 9 | — | *(Exercism has neither)* [dev.java Streams](https://dev.java/learn/api/streams/) + [Concurrency](https://dev.java/learn/concurrency/) | **Q15** |
| 10 | — | **Capstone** — §7 below | — |

**Week 5 is the most important week of this phase.** MOOC Part 5 §3–4 teaches primitive vs
reference variables and object references directly — the exact thing Q1, Q4 and Q12 caught. Do
not rush it.

MOOC Part 7 (paradigms, algorithms) is optional. Skip it unless you want it.

> **Request Exercism mentoring in weeks 3, 5, 7 and 8 at minimum** — the weeks covering your
> actual gaps. It's free, and a human saying "this works, but here's what a Java developer would
> write" is the feedback loop AI-written code has been denying you.

---

## 1. Object semantics & memory — 18 hrs
*Why you missed Q1, Q3, Q4*

- References vs values; what a variable actually holds
- **Immutability** — why `s.toUpperCase()` doesn't change `s`, and which other methods trap you
- `==` vs `.equals()`, the string pool, the Integer cache (−128 to 127)
- `equals`/`hashCode` as a contract, and what breaks when you ignore it
- Stack vs heap, garbage collection at a working level
- Autoboxing and the `null` unboxing NPE
- `double` is not for money

**🧪 Lab:** Write a `Money` class — immutable, correct `equals`/`hashCode`, no floating point.
Write tests that prove two separately constructed `Money(10, "USD")` are equal and land in the
same `HashSet` bucket. Then delete `hashCode` and watch the test fail. **Breaking it on purpose
is the exercise.**

---

## 2. Collections — 20 hrs
*You scored 0/3 here*

- `List`, `Set`, `Map`, `Queue` — what each is actually for
- `ArrayList` vs `LinkedList` and why ArrayList nearly always wins
- `HashMap` vs `TreeMap` vs `LinkedHashMap` — ordering guarantees
- How a `HashMap` really works: buckets, hashing, collisions
- `HashSet` deduplication and its dependence on `equals`/`hashCode`
- Iteration, `ConcurrentModificationException`, `removeIf`
- `getOrDefault`, `computeIfAbsent`, `merge` — the methods that remove most boilerplate
- Sorting: `Comparable` vs `Comparator`

**🧪 Lab:** Build a word-frequency counter over a large text file. Then rebuild it three times —
`HashMap`, `TreeMap`, `LinkedHashMap` — and explain in your journal how the output differs and why.

---

## 3. Exceptions & resources — 14 hrs
*You scored 0/2 here*

- Checked vs unchecked, and when to use which
- **Reading a stack trace properly** — this alone will change your debugging
- Where NPEs actually come from (dereferencing, not passing null)
- `try`/`catch`/`finally` and why returning from `finally` is a bug
- **try-with-resources** and `AutoCloseable` — the resource leak you missed
- Custom exceptions; never swallowing one silently
- Fail fast: validating at the boundary

**🧪 Lab:** Write a file parser that handles a missing file, a malformed line, and an empty file —
three distinct, correct behaviours. Then open a file without try-with-resources, throw mid-read,
and prove the handle leaked.

---

## 4. OOP you can use, not define — 20 hrs
*Q7 and Q8 were wrong; Q9 was right — you know the theory, not the mechanics*

- Classes, constructors, **constructor chaining and `super()` ordering**
- Inheritance vs composition, and why composition usually wins
- Overriding vs overloading — runtime vs compile-time resolution
- Interfaces, default methods, abstract classes
- Polymorphism in practice, not as a definition
- Casting, `instanceof`, `ClassCastException`
- `static` vs instance; when `static` is right
- Records and sealed interfaces (modern Java, genuinely simpler)

**🧪 Lab:** Model a small domain — a library, a parking lot — with 5–6 classes. Then refactor it
from inheritance to composition. Writing it twice is where the understanding comes from.

---

## 5. Generics, streams, Optional — 18 hrs

- Generics: why `List<String>` beats raw `List`; type erasure at a practical level
- Bounded types and wildcards (`? extends`, `? super`) — enough to read library signatures
- Streams: `filter`, `map`, `collect`, `reduce` — and when a plain loop is clearer
- `Optional` done right: return types only, never fields or parameters
- Lambdas and method references
- `var` — where it helps and where it hides things

**🧪 Lab:** Take the word-frequency counter from §2 and rewrite it with streams. Keep both.
Decide honestly which reads better, and write down why.

---

## 6. Concurrency, first contact — 12 hrs
*You scored 0/1 — this is foundational for everything after Phase 2*

- Threads, `Runnable`, starting and joining
- **Race conditions** — write one, see it, then fix it
- `synchronized` and what it actually locks (the instance, not the method)
- `AtomicInteger` and `ConcurrentHashMap`
- `ExecutorService` and thread pools
- Why `ArrayList` is not thread-safe
- A first look at virtual threads (Phase 0 goes deep)

**🧪 Lab:** Two threads, shared counter, 10,000 increments each. Watch the total come out wrong.
Fix it three ways — `synchronized`, `AtomicInteger`, a lock. Measure each. **This lab is the
direct prerequisite for Phase 0's first lab.**

---

## 7. Capstone — build something alone — 20 hrs

A command-line application, **no tutorial, no AI, no copying**. Something like:
a personal expense tracker, a flashcard reviewer (fitting, given the project), a file organiser.

**Requirements:**
- Reads and writes files, handles bad input without crashing
- Uses at least three different collection types, each chosen for a reason
- Has custom exceptions and proper resource handling
- Has unit tests you wrote yourself
- At least 400 lines of your own code

**You will get stuck. That is the phase working.** Being stuck for an hour and solving it
yourself builds something that no amount of reading does. Journal every time it happens — those
entries become the best material in the whole project.

---

## ✅ Phase −1 Checkpoint

The gate to Phase 0. Be strict with yourself here; the whole plan rests on it.

- [ ] **Retake the diagnostic and score 16/20 or better**
- [ ] Explain why `s.toUpperCase()` alone changes nothing
- [ ] Explain what breaks when you override `equals` but not `hashCode`
- [ ] Write a try-with-resources block from memory and say what it guarantees
- [ ] Read a stack trace and name the failing line without running anything
- [ ] Demonstrate a race condition and fix it three ways
- [ ] Your capstone runs, has tests, and **you can explain every line in it**
- [ ] You wrote all of it with AI completion switched off

That last one is not decoration. If you can't honestly tick it, the score doesn't matter.

---

## 💬 What this phase buys you in interviews

Nothing directly — and that's fine. No one asks about `ArrayList` in a senior loop.

What it buys you is the ability to **survive every other phase.** Phase 3 asks you to reason
about idempotent consumers; you cannot do that without reasoning about object state first. This
phase is the floor everything else stands on.

---

## Then what

Phase 0 as originally written, with *Effective Java* now making sense. The project timeline moves
from 34 weeks to **44 weeks** — target shifts from mid-2027 to around **September 2027**.

Ten weeks now, or a project you can't explain in seven months. It isn't a close call.
