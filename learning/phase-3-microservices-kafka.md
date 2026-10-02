# Phase 3 — Distributed Systems

**5 weeks · ~60 hrs** · ⭐ **The most important phase in the project.** This is what senior
backend interviews are actually about. Do not rush it.

> **Build at the end:** Split the monolith into `curriculum`, `identity`, `progress`,
> `notification`, `assessment` + `edge-gateway`. Kafka event backbone. Transactional outbox via
> Debezium CDC. A saga for enrollment. Idempotent consumers throughout.

---

## 1. Service decomposition — 8 hrs

**Concepts**
- Bounded contexts (DDD) — the *only* defensible basis for a service boundary
- Aggregates, entities, value objects; consistency boundaries
- Strangler fig pattern — extracting services from a monolith incrementally
- **Database-per-service** and the pain it causes: no joins, no FK across services, no distributed transactions
- Shared-nothing vs shared-database anti-pattern
- The distributed monolith — how to recognise you've built one
- Conway's Law and why it's a technical constraint, not an HR observation

**Resources**
- 📕💰 **[Building Microservices, 2nd ed](https://www.oreilly.com/library/view/building-microservices-2nd/9781492034018/) — Sam Newman.** Ch. 1–5, 13. The sane book on this topic.
- 📕💰 **[Microservices Patterns](https://www.manning.com/books/microservices-patterns) — Chris Richardson.** Ch. 2–5, 8. The implementation companion.
- 🆓 **[microservices.io](https://microservices.io/patterns/index.html)** — Richardson's free pattern catalog. Your reference all phase.
- 🆓 [Martin Fowler — Microservices](https://martinfowler.com/articles/microservices.html) + [MonolithFirst](https://martinfowler.com/bliki/MonolithFirst.html)
- 📕💰 [Learning Domain-Driven Design](https://www.oreilly.com/library/view/learning-domain-driven-design/9781098100124/) — Khononov. The approachable DDD book.

---

## 2. Apache Kafka — 16 hrs

**Concepts**
- Topics, partitions, offsets, consumer groups, rebalancing
- **Partition key choice determines ordering guarantees** — the most consequential design decision
- Producer: `acks`, `idempotence`, `max.in.flight`, batching, compression
- Consumer: auto vs manual commit, at-least-once vs at-most-once, poll loop, `max.poll.interval.ms`
- **Exactly-once semantics** — what Kafka transactions really give you, and why end-to-end EOS
  across an external DB is a myth
- Rebalance protocols, static membership, cooperative sticky assignor
- Retention, compaction, tiered storage
- **Schema Registry + Avro/Protobuf** — schema evolution, backward/forward compatibility
- Dead-letter topics, retry topics, poison-pill handling
- Consumer lag as your primary health metric
- Spring Kafka: `@KafkaListener`, error handlers, `DefaultErrorHandler`, batch listeners

**Resources**
- 📕💰 **[Kafka: The Definitive Guide, 2nd ed](https://www.confluent.io/resources/kafka-the-definitive-guide/)** — free PDF from Confluent with a form. Ch. 1–8, 11.
- 🆓 **[Confluent Developer](https://developer.confluent.io/courses/)** — free, excellent, hands-on courses. Do "Apache Kafka 101" and "Kafka Internals."
- 📕🆓 **[Designing Event-Driven Systems](https://www.confluent.io/designing-event-driven-systems/) — Ben Stopford. Free.** Short and conceptually sharp.
- 📄🆓 [Spring for Apache Kafka reference](https://docs.spring.io/spring-kafka/reference/)
- 🎥🆓 [Confluent YouTube — Kafka Internals](https://www.youtube.com/@ConfluentIO)

**🧪 Lab:** Deliberately break ordering by using the wrong partition key. Observe out-of-order
processing. Fix it. Then kill a consumer mid-batch and prove your consumer is idempotent by
replaying. **Journal both — these are stories, not facts.**

---

## 3. Distributed data patterns — 14 hrs

The heart of the phase. These four patterns are what "senior" means on a backend CV.

**Concepts**
- **Transactional outbox** — why dual-writes (DB + Kafka) are always broken, and how an outbox
  table in the same transaction fixes it
- **Change Data Capture with Debezium** — reading the Postgres WAL, logical replication slots
- **Saga pattern** — choreography vs orchestration, compensating transactions, and why
  "rollback" doesn't exist in distributed systems
- **Idempotency** — idempotency keys, dedup tables, natural idempotency, why every consumer needs it
- CQRS — separating read models from write models; eventual consistency and how to tell the user
- Event sourcing — when it's right (rarely) and the cost (always)
- Distributed locking with Redis — and **why Redlock is contested** (read both sides; this is a
  great "show me you think critically" interview topic)
- API composition vs CQRS read model for cross-service queries

**Resources**
- 🆓 **[microservices.io — Saga](https://microservices.io/patterns/data/saga.html) · [Outbox](https://microservices.io/patterns/data/transactional-outbox.html) · [CQRS](https://microservices.io/patterns/data/cqrs.html)**
- 🆓 **[Debezium docs + blog](https://debezium.io/documentation/reference/stable/)** — the outbox event router is built in
- 📕 **DDIA Ch. 7 (Transactions), 8 (Distributed Trouble), 9 (Consistency & Consensus), 11 (Stream Processing)** — read these *this phase*, they land perfectly
- 🆓 [Martin Kleppmann — How to do distributed locking](https://martin.kleppmann.com/2016/02/08/how-to-do-distributed-locking.html) + [antirez's reply](http://antirez.com/news/101) — read both
- 🆓 [Chris Richardson — Saga talks on YouTube](https://www.youtube.com/results?search_query=chris+richardson+saga+pattern)

**🧪 Lab:** Implement enrollment as a saga across three services. Force a failure in step 3.
Watch compensations run. Then make every consumer idempotent and replay the entire topic from
offset 0 — the final state must be identical. **This is your flagship interview story.**

---

## 4. Resilience & communication — 10 hrs

**Concepts**
- Sync vs async; when REST is right and when events are right
- **Circuit breaker, bulkhead, retry, rate limiter, timeout** — Resilience4j for all five
- Retry storms and why retries without jitter cause cascading failure
- Timeouts: why every outbound call needs one, and how to pick the number
- Service discovery; client-side vs server-side load balancing
- API gateway: Spring Cloud Gateway, routing, filters, auth offload
- gRPC vs REST — protobuf, when the performance matters
- Contract testing with Pact / Spring Cloud Contract — catching breaking changes in CI
- API versioning strategies

**Resources**
- 📄🆓 [Resilience4j docs](https://resilience4j.readme.io/docs)
- 📄🆓 [Spring Cloud Gateway reference](https://docs.spring.io/spring-cloud-gateway/reference/)
- 📕💰 [Release It!, 2nd ed](https://pragprog.com/titles/mnee2/release-it-second-edition/) — Michael Nygard. **Stability patterns. Read Part I.** Where circuit breakers came from.
- 🆓 [AWS Builders' Library — Timeouts, retries and backoff with jitter](https://aws.amazon.com/builders-library/timeouts-retries-and-backoff-with-jitter/) — the whole library is free and world-class
- 📄🆓 [Spring Cloud Contract](https://docs.spring.io/spring-cloud-contract/reference/)

---

## 5. Observability foundations — 8 hrs

Start here, not in Phase 6 — you'll need it to debug everything above.

**Concepts**
- The three pillars: metrics, logs, traces — and what each is actually for
- **W3C Trace Context** propagation — HTTP headers *and* Kafka record headers
- **Why propagating a trace across an async boundary is hard** — this is your showpiece
- Micrometer + Micrometer Tracing → OpenTelemetry
- Structured logging (JSON), correlation IDs, MDC
- RED metrics (Rate, Errors, Duration) and USE metrics

**Resources**
- 📄🆓 [OpenTelemetry docs — Java](https://opentelemetry.io/docs/languages/java/)
- 📄🆓 [W3C Trace Context spec](https://www.w3.org/TR/trace-context/) — short, read it
- 📄🆓 [Micrometer Tracing](https://docs.micrometer.io/tracing/reference/)
- 📕💰 [Observability Engineering](https://www.oreilly.com/library/view/observability-engineering/9781492076438/) — Majors, Fong-Jones, Miranda. Ch. 1–6.

---

## ✅ Phase 3 Checkpoint

- [ ] Explain why dual-writes are broken, and draw the outbox pattern from memory
- [ ] Explain a saga, choreography vs orchestration, and what a compensating transaction is
- [ ] Explain what Kafka's "exactly-once" does and does not cover
- [ ] Explain how partition key choice affects ordering and parallelism
- [ ] Make a consumer idempotent and explain three ways to do it
- [ ] Explain CAP honestly — including why it's often misquoted (and mention PACELC)
- [ ] Argue both sides of the Redlock debate
- [ ] One trace spans HTTP → producer → Kafka → consumer → HTTP in your Tempo UI

**Then build:** the full split. ADRs: `0008-service-boundaries.md`, `0009-outbox-over-dual-write.md`,
`0010-saga-orchestration.md`, `0011-kafka-partitioning-strategy.md`.

---

## 💬 Interview questions this phase answers

Nearly all of them. Specifically:
- *"How do you keep data consistent across microservices?"*
- *"What happens if the DB commit succeeds but the Kafka publish fails?"*
- *"How do you handle a message being delivered twice?"*
- *"How would you split this monolith?"*
- *"What's a saga? When would you not use one?"*
- *"How do you debug a request that crosses six services?"*
