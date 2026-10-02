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

## Phases

### [Phase 0 — Foundations](learning/phase-0-foundations.md) · 2 weeks
Modern Java, Gradle, Docker, Git discipline. The ground you stand on.

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
because it is the thing interviews actually test and it needs months of marination.

**Read 📕 *Designing Data-Intensive Applications* (Kleppmann) at one chapter per week,
start to finish, across the whole project.** It is the single highest-leverage book in backend
engineering. Pair each chapter with the phase you're in — Ch. 5 (Replication) lands beautifully
during Phase 3, Ch. 11 (Stream Processing) during Phase 6.

Supplement, from month 4 onward, 2 hrs/week:
- 🆓 [Hello Interview — System Design](https://www.hellointerview.com/learn/system-design/in-a-hurry/introduction) — the best free structured prep available
- 🆓 [ByteByteGo YouTube](https://www.youtube.com/@ByteByteGo) — short, visual, good for commute
- 📕💰 *System Design Interview* Vol 1 & 2 — Alex Xu — breadth of worked examples
- 🆓 [microservices.io patterns](https://microservices.io/patterns/index.html) — Chris Richardson's catalog; your reference for Phase 3

---

## Weekly rhythm

| | |
|---|---|
| **Mon–Thu** · 1.5h | Learn — current phase material |
| **Sat** · 4h | Build — the deep work block |
| **Sun** · 2h | Build + ADR + `./bin/weekly` |
| **Any day** · 20min | One DDIA chapter section |

That's ~11 hrs/week. Protect the Saturday block above everything else.
