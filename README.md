# Praxis

A distributed, AI-native learning platform that teaches backend engineering — built as the
means of learning backend engineering.

> **The point:** Praxis teaches the concepts it is itself built on, and can prove it at runtime.
> Open the lesson on distributed tracing and it renders the *actual* OpenTelemetry trace of the
> request that delivered that lesson. The Kafka lesson replays real events off its own topics.
> The Kubernetes lesson reads live pod and HPA state from the cluster it is running in.

---

## Status

| | |
|---|---|
| **Started** | 2026-10-02 |
| **Current phase** | Phase -1 — Java, Actually Written (learning) |
| **Mode** | 📚 Learning · baseline diagnostic 7/20, Phase -1 added |
| **Java** | 21 LTS |
| **Journal entries** | see [`journal/`](journal/) |

---

## How this repo works

This project runs **learn → checkpoint → build → journal**, in that order, every phase.

```
learning/phase-N-*.md   What to learn, with resources and a pass/fail checkpoint
        ↓
   (checkpoint)         Can you do the thing without looking it up?
        ↓
      build             Ship the phase. It must be usable, not just compiling.
        ↓
docs/adr/NNNN-*.md      Why you chose what you chose
        ↓
journal/YYYY-MM-DD-*.md What happened, what broke, what you understand now
        ↓
interview/README.md     The condensed revision sheet, growing as you go
```

| Directory | Holds |
|---|---|
| [`learning/`](learning/) | Per-phase curriculum: concepts, resources, checkpoints |
| [`journal/`](journal/) | Dated build log — the record of the whole journey |
| [`docs/adr/`](docs/adr/) | Architecture Decision Records |
| [`docs/architecture/`](docs/architecture/) | System design docs and diagrams |
| [`interview/`](interview/) | Accumulating interview revision sheet |
| [`bin/`](bin/) | `log` (new journal entry), `weekly` (weekly summary) |

---

## The system

Ten services, each chosen because it forces a different concept.

| Service | Stack | Forces you to learn |
|---|---|---|
| `edge-gateway` | Spring Cloud Gateway | Routing, JWT, Redis rate limiting, circuit breakers |
| `identity` | Spring Security, OAuth2/OIDC | AuthN/Z, refresh-token rotation, RBAC |
| `curriculum` | Postgres, recursive CTEs | Knowledge graph as a DAG, prerequisite resolution |
| `tutor` ⭐ | WebFlux + Claude API | Reactive streams, SSE streaming, semantic caching, backpressure |
| `knowledge` ⭐ | pgvector | RAG, chunking, hybrid search, re-ranking |
| `lab` ⭐⭐ | K8s Jobs, gVisor | Sandboxed execution of untrusted code |
| `assessment` | Kafka consumers | Async grading, idempotent consumers |
| `progress` | Event sourcing + CQRS | FSRS spaced repetition, knowledge tracing |
| `notification` | Transactional outbox | Exactly-once-ish delivery, dedup |
| `analytics` | TimescaleDB | CQRS read models, cohort queries, interview-sheet generation |

**Backbone:** Kafka · Postgres per service · Redis · OpenTelemetry → Tempo/Loki/Prometheus/Grafana
· Testcontainers · Flyway · Helm + Kustomize · ArgoCD · Terraform

---

## Roadmap

| Phase | Learn | Build | Weeks |
|---|---|---|---|
| **-1** | [Java, actually written](learning/phase--1-java-foundations.md) | CLI app, written alone, no AI | 10 |
| **0** | [Foundations](learning/phase-0-foundations.md) | Maven monorepo, Compose, CI | 2 |
| **1** | [Spring & data](learning/phase-1-spring-core.md) | Modular monolith: curriculum + identity | 4 |
| **2** | [Reactive & AI](learning/phase-2-ai-reactive.md) | `tutor` service, SSE streaming, React UI | 4 |
| **3** | [Distributed systems](learning/phase-3-microservices-kafka.md) | **Split the monolith**, Kafka, outbox, saga | 5 |
| **4** | [Container security](learning/phase-4-sandbox-security.md) | `lab` sandboxed execution | 4 |
| **5** | [Data & retrieval](learning/phase-5-rag-data.md) | `knowledge` RAG service | 3 |
| **6** | [Kubernetes & observability](learning/phase-6-kubernetes-observability.md) | Full K8s, GitOps, OTel | 5 |
| **7** | [Cloud & resilience](learning/phase-7-cloud-resilience.md) | EKS via Terraform, load + chaos tests | 4 |
| **8** | [Algorithms & CQRS](learning/phase-8-adaptive-engine.md) | Adaptive engine, interview-sheet generator | 3 |

**~44 weeks at 10–12 hrs/week.** Start: 2026-10-02. Target: ~September 2027.

> Phase -1 was added on day 0 after a baseline diagnostic scored 7/20. The pattern was clear:
> conceptual questions right, runtime-behaviour questions wrong — the signature of reading about
> Java without writing it. Ten weeks of hands-on practice now, or a system that can't be
> explained in seven months.

See [LEARNING-PATH.md](LEARNING-PATH.md) for the full curriculum index.
