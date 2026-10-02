# Architecture Decision Records

An ADR records **one significant decision**: what you chose, what you rejected, and why.

## Why these matter for this project

When an interviewer asks *"why Kafka and not RabbitMQ?"*, most candidates improvise. You hand
them a document you wrote at the time, with the alternatives you considered and the trade-offs
you accepted. That is the difference between a developer and an engineer.

## The rules

1. **Write it before you build**, not after. If you can't justify the choice in writing, you
   don't understand the choice yet.
2. **Numbered, immutable.** Never edit a decided ADR. If you change your mind, write a new one
   that supersedes it — the fact that you changed your mind is *itself* valuable evidence.
3. **Name the rejected options.** An ADR with one option is a diary entry.
4. **Be honest about what you're giving up.** Every real decision costs something.

## Format

Copy `0001-record-architecture-decisions.md` as the template.

## Index

| # | Decision | Status | Phase |
|---|---|---|---|
| [0001](0001-record-architecture-decisions.md) | Record architecture decisions | Accepted | 0 |
| 0002 | Build tooling: Maven over Gradle | _pending_ | 0 |
| 0003 | Modular monolith before microservices | _pending_ | 1 |
| 0004 | JWT vs sessions | _pending_ | 1 |
| 0005 | Reactive vs virtual threads | _pending_ | 2 |
| 0006 | SSE over WebSockets | _pending_ | 2 |
| 0007 | LLM eval strategy | _pending_ | 2 |
| 0008 | Service boundaries | _pending_ | 3 |
| 0009 | Outbox over dual-write | _pending_ | 3 |
| 0010 | Saga orchestration vs choreography | _pending_ | 3 |
| 0011 | Kafka partitioning strategy | _pending_ | 3 |
| 0012 | Sandbox isolation strategy | _pending_ | 4 |
| 0013 | gVisor vs microVM | _pending_ | 4 |
| 0014 | pgvector over a dedicated vector DB | _pending_ | 5 |
| 0015 | Chunking strategy | _pending_ | 5 |
| 0016 | Helm and Kustomize | _pending_ | 6 |
| 0017 | GitOps with ArgoCD | _pending_ | 6 |
| 0018 | No service mesh | _pending_ | 6 |
| 0019 | SLO definitions | _pending_ | 6 |
| 0020 | EKS over self-managed Kubernetes | _pending_ | 7 |
| 0021 | Cost architecture | _pending_ | 7 |
| 0022 | FSRS over SM-2 | _pending_ | 8 |
| 0023 | CQRS read models | _pending_ | 8 |
