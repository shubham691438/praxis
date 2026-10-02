# Phase 1 — Spring & Data

**4 weeks · ~45 hrs** · The deepest Spring you'll ever need. Most devs use Spring for years
without understanding the container. You're going to.

> **Build at the end:** A modular monolith — `curriculum` (knowledge graph of topics as a DAG)
> + `identity` (auth, users, roles). Postgres, Flyway, Testcontainers, full test suite. Maven reactor build.

---

## 1. Spring Framework internals — 12 hrs

Not "how to use `@Service`." *How the container actually works.*

**Concepts**
- The IoC container: `BeanDefinition` → `BeanFactory` → `ApplicationContext` lifecycle
- `BeanPostProcessor` / `BeanFactoryPostProcessor` — the extension points everything is built on
- **Proxies**: JDK dynamic vs CGLIB. **Why calling a `@Transactional` method from within the same class silently does nothing** — the #1 Spring interview question
- `@Transactional` propagation (`REQUIRED`, `REQUIRES_NEW`, `NESTED`) and isolation levels
- Auto-configuration: `@ConditionalOnClass`, `spring.factories` → `AutoConfiguration.imports`
- Profiles, `@ConfigurationProperties`, relaxed binding, config precedence order
- Startup: what `SpringApplication.run()` actually does, step by step

**Resources**
- 📕💰 **[Spring Start Here](https://www.manning.com/books/spring-start-here) — Laurentiu Spilca.** Best structured intro to the *container*, not just the annotations.
- 🎥🆓 **[Laurentiu Spilca — YouTube](https://www.youtube.com/@laurspilca)** — exceptionally clear on proxies and transactions
- 🆓 **[Spring Academy](https://spring.academy/)** — VMware's own free courses. Do "Spring Framework Essentials."
- 📄🆓 [Spring Framework Reference — Core](https://docs.spring.io/spring-framework/reference/core.html) — the actual docs are excellent; read the IoC chapter
- 🎥🆓 [Dan Vega](https://www.youtube.com/@DanVega) — Spring Developer Advocate, current and practical
- 🆓 [Baeldung](https://www.baeldung.com/) — reference, not curriculum. Look things up, don't browse.

**🧪 Lab:** Write a custom `BeanPostProcessor` that logs the init time of every bean. Then write
a tiny custom auto-configuration in its own module and `@ConditionalOnProperty` it on.

---

## 2. JPA / Hibernate — 12 hrs

Where most backend bugs and most performance disasters live.

**Concepts**
- Persistence context, 1st-level cache, dirty checking, flush modes
- Entity lifecycle: transient → managed → detached → removed
- **N+1 queries** — how to detect (enable SQL logging + `hibernate.generate_statistics`) and the four fixes: `JOIN FETCH`, `@EntityGraph`, `@BatchSize`, projections
- Lazy vs eager; `LazyInitializationException` and why `open-in-view` should be `false`
- Optimistic (`@Version`) vs pessimistic locking
- DTO projections — why you should rarely return entities from a controller
- When to abandon JPA for jOOQ or plain JDBC (and say so in an interview — it shows judgment)

**Resources**
- 📕💰 **[High-Performance Java Persistence](https://vladmihalcea.com/books/high-performance-java-persistence/) — Vlad Mihalcea.** The definitive book. Part II and III.
- 🆓 **[vladmihalcea.com blog](https://vladmihalcea.com/)** — free and enormous; the N+1 and batching articles alone are worth the phase
- 📄🆓 [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/reference/)
- 🆓 [Use The Index, Luke](https://use-the-index-luke.com/) — SQL indexing from the developer's side. Free, short, brilliant.

**🧪 Lab:** Build an entity graph three levels deep. Trigger an N+1 deliberately. Prove it with
SQL logs. Fix it four different ways. Benchmark each. **This becomes a journal entry and an
interview story.**

---

## 3. PostgreSQL — 8 hrs

**Concepts**
- MVCC, transaction isolation levels in practice, what "repeatable read" actually prevents
- Indexes: B-tree, GIN, GiST, partial, covering, composite — and column order in composites
- `EXPLAIN (ANALYZE, BUFFERS)` — reading a query plan without fear
- Recursive CTEs — **you need these for the curriculum DAG's prerequisite resolution**
- Connection pooling: HikariCP sizing, why "more connections" makes things slower
- `JSONB` — when it's right, when it's laziness
- Migrations with Flyway: versioning, repeatable migrations, the rules of never editing an applied migration

**Resources**
- 📕🆓 **[PostgreSQL Internals](https://postgrespro.com/community/books/internals) — Egor Rogov. Free PDF.** Outstanding. Read the MVCC and indexing parts.
- 🆓 [Use The Index, Luke](https://use-the-index-luke.com/) — again; it's that good
- 📄🆓 [Postgres docs — Indexes & Performance Tips](https://www.postgresql.org/docs/current/performance-tips.html)
- 🆓 [pgMustard — EXPLAIN glossary](https://www.pgmustard.com/docs/explain) — decodes every plan node
- 📄🆓 [Flyway docs](https://documentation.red-gate.com/fd)

**🧪 Lab:** Model the curriculum DAG (topics + prerequisites). Write a recursive CTE that returns
the full prerequisite closure of a topic in dependency order. Then `EXPLAIN ANALYZE` it and add
the index that makes it fast.

---

## 4. Spring Security — 8 hrs

**Concepts**
- The filter chain — the mental model everything else depends on
- `SecurityContext`, `Authentication`, `AuthenticationProvider`
- JWT: structure, signing (HMAC vs RSA), validation, **why "just use JWT" is often wrong**
- Access vs refresh tokens; refresh-token rotation and reuse detection
- Password hashing: bcrypt vs argon2, work factors
- Method security: `@PreAuthorize`, SpEL, RBAC vs ABAC
- OAuth2 / OIDC flows — authorization code + PKCE specifically
- CORS, CSRF — when CSRF protection matters and when it genuinely doesn't

**Resources**
- 📕💰 **[Spring Security in Action, 2nd ed](https://www.manning.com/books/spring-security-in-action-second-edition) — Spilca.** The book on this.
- 📄🆓 [Spring Security reference — Architecture](https://docs.spring.io/spring-security/reference/servlet/architecture.html) — read the filter chain page twice
- 🆓 [OAuth 2.0 Simplified](https://www.oauth.com/) — Aaron Parecki. Free, clear.
- 🆓 [jwt.io](https://jwt.io/) + [RFC 8725 — JWT Best Current Practices](https://datatracker.ietf.org/doc/html/rfc8725)

**🧪 Lab:** Implement refresh-token rotation with reuse detection — if an already-used refresh
token is presented, invalidate the entire token family. This is a real security control and a
great thing to be able to explain.

---

## 5. Testing — 5 hrs

**Concepts**
- Test pyramid in practice; what `@SpringBootTest` actually costs you in time
- Slice tests: `@WebMvcTest`, `@DataJpaTest`, `@JsonTest`
- **Testcontainers** — real Postgres/Kafka in tests, no H2 lies
- `@ServiceConnection` (Spring Boot 3.1+) — Testcontainers wiring with zero config
- Reusable containers + singleton pattern for fast suites
- ArchUnit — enforcing module boundaries in code, which becomes critical for the Phase 3 split
- Mutation testing with PIT — measures whether your tests actually assert anything

**Resources**
- 📄🆓 [Testcontainers for Java](https://java.testcontainers.org/) + [Spring Boot support](https://docs.spring.io/spring-boot/reference/testing/testcontainers.html)
- 🆓 [Testing Spring Boot Applications — Philip Riecks](https://rieckpil.de/) — the best free resource on Spring testing
- 📄🆓 [ArchUnit User Guide](https://www.archunit.org/userguide/html/000_Index.html)
- 🆓 [Martin Fowler — Test Pyramid / Testing Strategies](https://martinfowler.com/articles/practical-test-pyramid.html)

**🧪 Lab:** Add an ArchUnit rule: `curriculum` classes may not import `identity` internals,
only its public API package. This is the seam you will cut along in Phase 3.

---

## ✅ Phase 1 Checkpoint

- [ ] Explain why a self-invoked `@Transactional` method doesn't start a transaction — and three ways to fix it
- [ ] Spot an N+1 from SQL logs and fix it four ways, from memory
- [ ] Draw the Spring Security filter chain on a whiteboard
- [ ] Write a recursive CTE without looking up the syntax
- [ ] Explain refresh-token rotation and what attack reuse-detection stops
- [ ] Your test suite runs against real Postgres via Testcontainers, green, in under 90s

**Then build:** the modular monolith. Two modules, clean boundary enforced by ArchUnit, real
auth, real DAG, >80% meaningful coverage. ADRs: `0003-modular-monolith-first.md`,
`0004-jwt-vs-sessions.md`.

> **Why a monolith first?** Deliberately. Splitting it in Phase 3 teaches you *why* services
> get split and what it costs — and "I started with a modular monolith and extracted services
> when I had evidence I needed them" is a far stronger interview answer than "I used
> microservices because microservices."

---

## 💬 Interview questions this phase answers

- *"Why doesn't `@Transactional` work when I call the method from the same class?"*
- *"What's an N+1 query and how do you find one in production?"*
- *"Walk me through what happens when a request hits a secured Spring endpoint."*
- *"JWT or sessions? Defend your choice."*
- *"How do you test a service that talks to a database?"*
- *"What's the difference between optimistic and pessimistic locking?"*
