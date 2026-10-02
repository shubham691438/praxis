# Day 000 — Baseline diagnostic: 7/20

**Date:** 2026-10-02 · **Phase:** -1 (new) · **Time spent:** ~30 min
**Mode:** 📝 writing

---

## What I did

Took a 20-question Java diagnostic before starting, to calibrate the plan honestly rather than
assume. Scored 6/20 as recorded, 7/20 adjusting for Q19 (knew it, misclicked).

Used the "I don't know" option 5 times rather than guessing, which made the result usable.

## The result

| Area | Correct | Wrong | Didn't know |
|---|---|---|---|
| Errors | 0/2 | 2 | 0 |
| Collections | 0/3 | 1 | 2 |
| Concurrency | 0/1 | 0 | 1 |
| Core | 2/5 | 2 | 1 |
| OOP | 2/4 | 2 | 0 |
| Backend | 3/5 | 1 | 1 |

## What I learned

The score matters less than the pattern in it.

**Everything I got right was conceptual** — encapsulation, 401 vs 403, primary key vs unique,
stateless REST, static fields.

**Everything I got wrong was "what does this code do when it runs"** — string immutability,
constructor ordering, null unboxing, which line throws the NPE, resource leaks on exception,
COUNT with NULLs.

That is the signature of having read about Java without writing and debugging it. Which is
accurate: AI has been writing most of my code.

The implication is uncomfortable but clear — reading more will not fix this, because reading is
what produced this result. Only writing code does.

## What changed in the plan

- **Phase -1 added**: 10 weeks, ~120 hrs, roughly 200 exercises via the Helsinki Java MOOC plus
  a capstone CLI app written alone.
- **Hard rule for Phase -1 through Phase 3: no AI-written code.** Copilot and all AI completion
  off in the IDE. AI only for explaining a concept, or decoding an error after 15 minutes of my
  own effort.
- *Effective Java* moves from "buy now" to Phase 0 — it assumes fluency I don't have yet.
- Alex Xu now runs across weeks 1–10 alongside Phase -1; DDIA starts week 11.
- **Timeline: 34 weeks → 44 weeks.** Target moves from mid-2027 to ~September 2027.

## What I don't understand yet

Based directly on what the diagnostic found:
- Collections — scored 0/3, essentially a blank
- Exceptions and resource handling — 0/2
- Concurrency — 0/1
- Why string immutability behaves the way it does
- Constructor ordering and `super()`
- Where NPEs actually originate

## Interview-worthy

Nothing yet. But the honesty of taking a baseline and adjusting instead of pushing ahead is
itself the kind of thing worth being able to describe. <!-- #interview -->

---

**Next session:** Install IntelliJ, **turn off all AI completion**, spend 30 minutes learning the
debugger, then start Java MOOC Part 1.
