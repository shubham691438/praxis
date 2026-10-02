# Phase 8 — Adaptive Engine

**3 weeks · ~34 hrs** · Close the loop. The app finally produces the thing that started all
this: your interview revision sheet, generated from what you actually learned.

> **Build at the end:** `progress` service with real spaced-repetition scheduling and knowledge
> tracing; `analytics` service with CQRS read models; the interview-sheet generator.

---

## 1. Spaced repetition algorithms — 10 hrs

Real algorithmic substance — rare in a portfolio project, and a nice change of register in an
interview.

**Concepts**
- SM-2 (the Anki classic) — ease factor, interval growth, and its known weaknesses
- **FSRS** — the modern replacement. Difficulty/Stability/Retrievability, the forgetting curve,
  optimising parameters against your own review history
- Desired retention as a tunable; the retention/workload trade-off
- Scheduling as an optimisation problem

**Resources**
- 🆓 **[FSRS algorithm explanation (GitHub wiki)](https://github.com/open-spaced-repetition/fsrs4anki/wiki/The-Algorithm)** — well written, implementable
- 🆓 [SuperMemo — SM-2 description](https://super-memory.com/english/ol/sm2.htm) — the original
- 🆓 [Gwern — Spaced repetition](https://gwern.net/spaced-repetition) — the research review

---

## 2. Knowledge tracing — 8 hrs

**Concepts**
- **Bayesian Knowledge Tracing** — a 4-parameter HMM (prior, learn, guess, slip). Simple,
  interpretable, genuinely works
- Item Response Theory — modelling question difficulty separately from learner ability
- Prerequisite-aware mastery propagation over your curriculum DAG
- Cold start: what to show someone on day one
- Honest evaluation: are the recommendations actually better than random?

**Resources**
- 🆓 [Corbett & Anderson (1994) — Knowledge Tracing](https://link.springer.com/article/10.1007/BF01099821) — the original paper; short
- 🆓 [pyBKT](https://github.com/CAHLR/pyBKT) — reference implementation to port to Java
- 🆓 [Item Response Theory primer](https://en.wikipedia.org/wiki/Item_response_theory) — Wikipedia is adequate here

---

## 3. CQRS read models & analytics — 10 hrs

**Concepts**
- Projections from the event stream into purpose-built read models
- Rebuilding projections from scratch — the superpower of event-driven systems
- Handling eventual consistency in the UI honestly
- TimescaleDB hypertables, continuous aggregates, retention policies
- Cohort and retention analysis over your own learning data
- Materialized views and refresh strategies

**Resources**
- 📄🆓 [TimescaleDB docs](https://docs.timescale.com/) — hypertables + continuous aggregates
- 🆓 [Martin Fowler — CQRS](https://martinfowler.com/bliki/CQRS.html) · [Event Sourcing](https://martinfowler.com/eaaDev/EventSourcing.html)
- 📕 **DDIA Ch. 11 (Stream Processing), 12 (The Future of Data Systems)** — the final chapters land here

---

## 4. The interview-sheet generator — 6 hrs

The feature that closes the loop back to where this project started.

**What it does**
- Reads your mastery model and journal entries
- Generates, per topic: **question → 3-line answer → the one gotcha**
- Weights toward your *weak* areas, not your strong ones
- Exports to Markdown, PDF, and Anki `.apkg`
- Regenerates on demand, always current with what you've covered

**Concepts**
- Prompt design for consistent structured output
- Anki `.apkg` format (it's a zipped SQLite DB — fun to generate)
- PDF generation from Markdown

**Resources**
- 📄🆓 [Anki apkg format notes](https://github.com/ankidroid/Anki-Android/wiki/Database-Structure)
- 📄🆓 [Anthropic — structured output / tool use](https://docs.claude.com/en/docs/agents-and-tools/tool-use/overview)

---

## ✅ Phase 8 Checkpoint

- [ ] Explain FSRS vs SM-2 and why FSRS is better
- [ ] Explain Bayesian Knowledge Tracing's four parameters
- [ ] Explain CQRS and when the complexity is justified
- [ ] Rebuild a read model from the event log, from zero, successfully
- [ ] Generate your own interview sheet and revise from it

**Then build:** the adaptive engine. ADRs: `0022-fsrs-over-sm2.md`, `0023-cqrs-read-models.md`.

---

## 💬 Interview questions this phase answers

- *"Tell me about an algorithm you implemented."*
- *"What's CQRS? When is it overkill?"*
- *"How do you handle eventual consistency in a UI?"*

---

## 🎓 After Phase 8

The project is never "done," but it is **presentable**. Next:

1. **Write the architecture deep-dive** in `docs/architecture/` — the thing you send before an interview
2. **Record a 5-minute demo video** — most interviewers won't clone your repo; they will watch a video
3. **Write 2–3 blog posts** on the hardest parts (the sandbox, the trace propagation, the RAG evals).
   A blog post about your own project is what turns it from "a repo" into "a thing people know you for."
4. **Practice the 2-minute pitch** until it's effortless. See [`interview/README.md`](../interview/README.md).
