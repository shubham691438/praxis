# Praxis — Learning Path

The full curriculum. **Learn first, build second.** Every phase has a checkpoint you must pass
before writing production code for that phase.

---

## The rules

1. **No building before the checkpoint passes.** The checkpoint is "can you do this without
   looking it up." Not "did you watch the video."
2. **One primary resource per concept.** Pick the book *or* the course, not both. Breadth of
   resources is procrastination wearing a disguise.
3. **Type the code.** Never copy-paste from a tutorial. Muscle memory is the point.
4. **Write the ADR before you build**, not after. If you can't justify the choice in writing,
   you don't understand the choice.
5. **Journal every session**, even the bad ones. `./bin/log` — takes 3 minutes.
6. **Timebox learning.** If a phase's learning runs 50% over estimate, start building anyway.
   The gaps will reveal themselves and you'll learn them under pressure, which sticks better.

---

## Resource legend

| | |
|---|---|
| 📕 | Book — the deep source. Read the named chapters, not cover to cover. |
| 🎥 | Video course |
| 📄 | Docs / reference — read when stuck, not front to back |
| 🧪 | Hands-on lab or exercise |
| 💰 | Paid |
| 🆓 | Free |

---

## ⭐ The one resource per section

The failure mode of any curriculum this size is paralysis — five resources per section and no
idea where to start. **Start with the ⭐ one. Everything else in the phase file is for when the
⭐ one leaves you with a question.**

| Phase | Section | ⭐ Start with | Cost |
|---|---|---|---|
| 0 | Modern Java | Do the lab, then [JEP 444](https://openjdk.org/jeps/444) | 🆓 |
| 0 | Maven | [Intro to the Build Lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html) | 🆓 |
| 0 | Docker | *Docker Deep Dive* — Poulton | 💰 ~$15 |
| 0 | Git | [Learn Git Branching](https://learngitbranching.js.org/) | 🆓 |
| 1 | Spring internals | [Laurentiu Spilca — YouTube](https://www.youtube.com/@laurspilca) | 🆓 |
| 1 | JPA / Hibernate | [vladmihalcea.com](https://vladmihalcea.com/) | 🆓 |
| 1 | PostgreSQL | [Use The Index, Luke](https://use-the-index-luke.com/) | 🆓 |
| 1 | Spring Security | [Filter chain reference](https://docs.spring.io/spring-security/reference/servlet/architecture.html) | 🆓 |
| 1 | Testing | [rieckpil.de](https://rieckpil.de/) | 🆓 |
| 2 | Reactor | [tech.io Reactor 3 playground](https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/Intro) | 🆓 |
| 2 | SSE | [MDN — Server-Sent Events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events) | 🆓 |
| 2 | Claude API | [Prompt engineering guide](https://docs.claude.com/en/docs/build-with-claude/prompt-engineering/overview) | 🆓 |
| 2 | React | [react.dev/learn](https://react.dev/learn) | 🆓 |
| 3 | Decomposition | *Building Microservices, 2nd ed* — Newman | 💰 |
| 3 | Kafka | [Confluent Developer courses](https://developer.confluent.io/courses/) | 🆓 |
| 3 | Distributed data | [microservices.io patterns](https://microservices.io/patterns/index.html) + DDIA Ch. 7–9 | 🆓 |
| 3 | Resilience | [AWS Builders' Library](https://aws.amazon.com/builders-library/) | 🆓 |
| 3 | Observability | [OpenTelemetry Java docs](https://opentelemetry.io/docs/languages/java/) | 🆓 |
| 4 | Container internals | [Liz Rice — Containers From Scratch](https://www.youtube.com/watch?v=8fi7uSYlOdc) | 🆓 |
| 4 | Sandboxing | [gVisor docs](https://gvisor.dev/docs/) | 🆓 |
| 4 | K8s Jobs | [kubernetes.io — Jobs](https://kubernetes.io/docs/concepts/workloads/controllers/job/) | 🆓 |
| 4 | AppSec | [OWASP Cheat Sheets](https://cheatsheetseries.owasp.org/) | 🆓 |
| 5 | Vector search | [pgvector README](https://github.com/pgvector/pgvector) | 🆓 |
| 5 | RAG | [Anthropic — Contextual Retrieval](https://www.anthropic.com/news/contextual-retrieval) | 🆓 |
| 5 | Postgres at scale | [PostgreSQL Internals](https://postgrespro.com/community/books/internals) — Rogov | 🆓 |
| 6 | Kubernetes | *The Kubernetes Book* — Poulton | 💰 ~$20 |
| 6 | GitOps | [Argo CD docs](https://argo-cd.readthedocs.io/) | 🆓 |
| 6 | Observability | [Google SRE Book](https://sre.google/sre-book/table-of-contents/) Ch. 4, 6 | 🆓 |
| 7 | AWS | [AWS Builders' Library](https://aws.amazon.com/builders-library/) | 🆓 |
| 7 | Terraform | [HashiCorp tutorials](https://developer.hashicorp.com/terraform/tutorials) | 🆓 |
| 7 | Performance | [Gil Tene — How NOT to Measure Latency](https://www.youtube.com/watch?v=lJ8ydIuPFeU) | 🆓 |
| 7 | Chaos | [principlesofchaos.org](https://principlesofchaos.org/) | 🆓 |
| 8 | Spaced repetition | [FSRS algorithm wiki](https://github.com/open-spaced-repetition/fsrs4anki/wiki/The-Algorithm) | 🆓 |
| 8 | Knowledge tracing | [pyBKT](https://github.com/CAHLR/pyBKT) | 🆓 |
| 8 | CQRS | [Fowler — CQRS](https://martinfowler.com/bliki/CQRS.html) | 🆓 |

**29 of 35 primary resources are free.**

---

## 💰 What to actually buy

The phase files name ~15 paid books. Buying them all is ~$500 and you will read three of them.
**Buy in this order, only when you reach the phase:**

| When | Book | Why this one |
|---|---|---|
| **Now** | **Designing Data-Intensive Applications** — Kleppmann | Runs across the entire project. The highest-leverage book in backend engineering. If you buy one thing, this. |
| **Now** | **Effective Java, 3rd ed** — Bloch | Phase 0, and you'll reread it for years |
| **Phase 1** | **High-Performance Java Persistence** — Mihalcea | JPA is where your bugs will be. The author's free blog may be enough — try that first. |
| **Phase 3** | **Building Microservices, 2nd ed** — Newman | The one book for the most important phase |
| **Phase 6** | **The Kubernetes Book** — Poulton | Cheap, short, updated yearly |

Everything else: wait until the phase, and check whether the free primary resource was enough
first. It usually is.

> An O'Reilly subscription (~$49/mo) covers *Effective Java*, *Building Microservices*,
> *Kubernetes Patterns*, *Container Security*, *Observability Engineering*, *Optimizing Java*
> and *Release It!*. If you're going to read four or more of those, subscribe for two months
> during Phases 3–4 instead of buying.

---

## ⚠️ What this plan does NOT cover

Being honest about scope is better than discovering the gap in an interview loop.

**1. DSA / coding rounds.** This plan makes you a strong backend *engineer*. It does nothing for
the LeetCode-style round that most senior backend loops still include. That is a **separate,
parallel track** — budget 3–4 hrs/week from month 3:
- 🆓 [NeetCode 150](https://neetcode.io/practice) — the right list; don't grind 500 random problems
- 📕💰 *Elements of Programming Interviews in Java* — if you want depth
- Consistency beats volume. 2 problems a day for 6 months >> 40 problems in one panicked week.

**2. Behavioural / leadership rounds.** Your journal is unusually good raw material for these —
mine it for STAR-format stories. But practise saying them out loud; writing ≠ speaking.

**3. Frontend depth.** Phase 2 gives you enough React to not embarrass the backend. That's deliberate.

**4. Language breadth.** This is a Java/Spring plan. That's the right bet for your career right
now, but be aware it's a bet.

---

## Phases

### [Phase 0 — Foundations](learning/phase-0-foundations.md) · 2 weeks
Modern Java, Maven, Docker, Git discipline. The ground you stand on.

### [Phase 1 — Spring & Data](learning/phase-1-spring-core.md) · 4 weeks
Spring Boot internals, JPA done right, Postgres, Spring Security, Testcontainers.

### [Phase 2 — Reactive & AI](learning/phase-2-ai-reactive.md) · 4 weeks
WebFlux, Project Reactor, SSE streaming, LLM API integration, prompt engineering, evals.

### [Phase 3 — Distributed Systems](learning/phase-3-microservices-kafka.md) · 5 weeks
**The most important phase.** Service decomposition, Kafka, outbox, saga, idempotency, CAP.

### [Phase 4 — Container Security](learning/phase-4-sandbox-security.md) · 4 weeks
Linux namespaces, cgroups, seccomp, running untrusted code without losing your cluster.

### [Phase 5 — Data & Retrieval](learning/phase-5-rag-data.md) · 3 weeks
Embeddings, vector search, pgvector, hybrid retrieval, Postgres performance.

### [Phase 6 — Kubernetes & Observability](learning/phase-6-kubernetes-observability.md) · 5 weeks
K8s properly, Helm, GitOps, OpenTelemetry, SLOs, the three pillars.

### [Phase 7 — Cloud & Resilience](learning/phase-7-cloud-resilience.md) · 4 weeks
AWS, Terraform, networking, load testing, chaos engineering, cost.

### [Phase 8 — Adaptive Engine](learning/phase-8-adaptive-engine.md) · 3 weeks
Spaced repetition algorithms, Bayesian knowledge tracing, CQRS read models.

---

## Running in parallel — system design

System design is **not a phase**. It is a continuous track running underneath all of them,
because it is what interviews actually test and it needs months to marinate.

### The sequence

**Weeks 1–4 — Alex Xu, *System Design Interview* Vol 1** (already owned)

Read it alongside Phase 0. Phase 0 is Java/Maven/Docker — almost no cognitive overlap, so the
two sit together comfortably.

- **Ch. 1–5 are the foundational ones** (scale from zero to millions, rate limiter, consistent
  hashing, key-value store, unique ID generator). The rest are case studies; read in any order.
- **Read it actively.** For every chapter: spend 10 minutes designing the system yourself on
  paper *before* reading his solution, then compare. **The gap between your design and his is
  the learning.** Reading the solution cold teaches you almost nothing.
- Log each one in `interview/README.md` under the system design track — specifically *where you
  struggled*, not what the answer was.
- Vol 2 is more worked examples. Skip for now; revisit before interview loops.

> ⚠️ **"Complete it" is the wrong frame.** Alex Xu is a reference you revisit, not a book you
> finish. Its weakness is that it gives you patterns without mechanisms — you can recite
> "use consistent hashing" without knowing why it beats modulo hashing when a node dies.
> That gap is exactly what a senior interviewer finds by asking "why?" twice. DDIA closes it.

**Weeks 4–34 — 📕 *Designing Data-Intensive Applications* (Kleppmann), one chapter per week**

The single highest-leverage book in backend engineering. Start after Alex Xu has given you the
vocabulary, then run it start to finish underneath the whole project.

Starting at week 4 is deliberate, not a delay — it puts the chapters where they'll land hardest:

| DDIA chapters | ~Week | Lands during |
|---|---|---|
| Ch. 1–4 — Foundations, data models, encoding | 4–7 | Phase 1 (data modelling) |
| **Ch. 5–6 — Replication, Partitioning** | 8–9 | Phase 1→3 transition |
| **Ch. 7–9 — Transactions, Distributed Trouble, Consistency & Consensus** | 10–12 | **Phase 3 — exactly when you're fighting sagas, outbox and idempotency** |
| Ch. 10–11 — Batch & Stream Processing | 13–15 | Phase 3 Kafka work |
| Ch. 12 — The Future of Data Systems | 16 | Phase 5/8 |

After Ch. 12, reread Ch. 7–9. They read completely differently the second time.

### From month 4 onward — 2 hrs/week

- 🆓 [Hello Interview — System Design](https://www.hellointerview.com/learn/system-design/in-a-hurry/introduction) — the best free structured prep available; stronger than Alex Xu on *how to run the interview itself*
- 🆓 [ByteByteGo YouTube](https://www.youtube.com/@ByteByteGo) — Alex Xu's channel; short and visual, good for commutes
- 🆓 [microservices.io patterns](https://microservices.io/patterns/index.html) — Chris Richardson's catalog; your Phase 3 reference
- 🆓 [AWS Builders' Library](https://aws.amazon.com/builders-library/) — real distributed-systems writing by principal engineers. Underrated for system design prep.

## Weekly rhythm

| | |
|---|---|
| **Mon–Thu** · 1.5h | Learn — current phase material |
| **Sat** · 4h | Build — the deep work block |
| **Sun** · 2h | Build + ADR + `./bin/weekly` |
| **Any day** · 20min | One DDIA chapter section |

That's ~11 hrs/week. Protect the Saturday block above everything else.

**Total: ~355 hrs across 34 weeks.** If you add the DSA track, budget ~14 hrs/week overall.

> If you miss a week, do not "catch up" by doubling the next week. Just resume. The schedule is
> a direction, not a debt. The people who finish long projects are the ones who are bad at
> quitting, not the ones who never slip.
