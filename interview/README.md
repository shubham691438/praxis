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
