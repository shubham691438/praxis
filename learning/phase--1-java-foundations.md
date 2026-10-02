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

## Setup — do this first (1 hr)

Two editors for ten weeks, deliberately. **VS Code runs the MOOC exercises** (its TMC extension
is the only maintained auto-grader — the IntelliJ one died in 2018). **IntelliJ is your real
IDE** for the labs here and everything from Phase 0 on.

### VS Code — for MOOC exercises only
- [x] VS Code installed — already in `/Applications`
- [ ] Add `code` to your PATH: open VS Code → `Cmd+Shift+P` → "Shell Command: Install 'code' command in PATH"
- [ ] **Extensions → install `TestMyCode`** (first launch downloads components; give it a few minutes)
- [ ] Extensions → install **Extension Pack for Java** (Microsoft)
- [ ] Install Maven — you need it for Phase 0 anyway: `brew install maven`
- [ ] 🚨 **Extensions → disable GitHub Copilot and Copilot Chat.** This is where Copilot lives by default. Disabling it in IntelliJ alone does nothing.
- [ ] Create a [MOOC.fi account](https://www.mooc.fi/), then in VS Code: TMC → log in → organization **MOOC** → course **Java Programming I**
- [ ] Complete the first sandbox exercise to confirm the submit loop works

📄 Full macOS instructions: [mooc.fi/en/installation/vscode](https://www.mooc.fi/en/installation/vscode/)

### IntelliJ — your actual IDE
- [x] Install **IntelliJ IDEA Community** ([free](https://www.jetbrains.com/idea/download/)) · *2026-10-02*
- [x] **Disable all AI completion** — Copilot, JetBrains AI, Tabnine, Codeium · *2026-10-02*
- [x] Learn the debugger: breakpoints, step over, step into, inspect variables · *2026-10-02*
- [ ] Java 21 — already installed ✓

> ⚠️ **The MOOC's embedded videos are in Finnish.** The written material is entirely in English
> and is ~95% of the course; the videos are NetBeans-era tool walkthroughs you don't need.
> **Skip every video. Read the text.** (The site's HTML `lang` is `fi`, so the occasional stray
> Finnish page — the 404, for instance — is normal and not something you've done wrong.)

---

## The primary resource

⭐ **[Java Programming MOOC — University of Helsinki](https://java-programming.mooc.fi/)** · 🆓

Free, open, and built on roughly 200 auto-graded programming exercises. It is exercise-first by
design — you cannot progress by reading. For your exact situation there is nothing better at
any price, and it is more useful to you right now than *Effective Java* or any O'Reilly book.

Work through **Parts 1–7** across this phase. Do **every** exercise, including the ones that look
too easy. The easy ones are where the runtime semantics live.

**Read the material in your browser; write the exercises in VS Code with TMC.** Skip the videos —
they're Finnish, and they only cover tooling you aren't using.

**Supporting:**
- 🧪🆓 [Exercism — Java track](https://exercism.org/tracks/java) — small problems with human mentorship; use when the MOOC feels repetitive
- 🎥🆓 [Coding with John](https://www.youtube.com/@CodingWithJohn) — short, sharp videos on exactly the gotchas you missed. Watch his videos on `equals`/`hashCode`, string immutability, and try-with-resources.
- 📄🆓 [dev.java/learn](https://dev.java/learn/) — Oracle's modern tutorials; reference, not curriculum
- 📕💰 *Head First Java, 3rd ed* — only if you want a book. Covers Java 17, genuinely designed for building intuition. Optional.

**Not yet:** *Effective Java* moves to Phase 0. It assumes fluency you're building here, and
reading it now would be wasted.

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
