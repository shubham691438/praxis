# Phase 0 — Foundations

**2 weeks · ~22 hrs** · Ground you stand on. Resist the urge to skip this; every later phase
assumes it.

> **Prerequisite: [Phase -1](phase--1-java-foundations.md) must be complete** — including the
> 16/20 diagnostic retake. This phase assumes you can read Java and predict what it does.

> **Build at the end:** Maven multi-module monorepo, Docker Compose with Postgres + Redis +
> Kafka, GitHub Actions CI running tests on every push.

---

## 1. Modern Java (21 LTS) — 8 hrs

Phase -1 gave you working Java. This is the modern layer on top: the parts that landed in the
language recently and that most working Java devs still haven't picked up.

**Concepts**
- Records, sealed interfaces, pattern matching for `switch` — modelling domains without Lombok
- `Optional` done right (return types only, never fields or params)
- Streams: when they're clearer than a loop, and when they're worse. `Collectors.teeing`, `flatMap`
- **Virtual threads** — what Loom actually changed, why `synchronized` pinning matters,
  why thread pools become an anti-pattern
- Structured concurrency (`StructuredTaskScope`)
- `CompletableFuture` — still needed for composition
- The memory model: `volatile`, happens-before, why double-checked locking was broken

**Resources**
**Start here, in this order:**

1. 🧪 **Do the lab below first.** Hit the wall before reading about the wall.
2. 📄🆓 [JEP 444 — Virtual Threads](https://openjdk.org/jeps/444) + [JEP 453 — Structured Concurrency](https://openjdk.org/jeps/453) — read the JEPs themselves, not blog summaries. ~40 min.
3. 📕💰 **[Effective Java, 3rd ed](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/) — Bloch.** Items 10–17 (object methods), 42–48 (lambdas/streams), 78–84 (concurrency). *The* Java book. Read these items slowly — one or two per sitting.
4. 🎥🆓 [Jose Paumard — JEP Café](https://www.youtube.com/@JosePaumard) — best Java deep-dives on YouTube. Watch the virtual threads and structured concurrency episodes.
5. 📕💰 *Java Concurrency in Practice* — Goetz. Ch. 2–5, 10–11. Dated on APIs, perfect on reasoning. Read if the memory-model checkpoint item feels shaky.

**Optional / skip if comfortable:**
- 📕💰 [Modern Java in Action](https://www.manning.com/books/modern-java-in-action) — Ch. 1–7 for streams/lambdas. **Paid, and dated** — it targets Java 8–11 and predates virtual threads entirely. You write Java daily; you almost certainly don't need it. Only pick it up if streams genuinely confuse you.

**🧪 Lab:** Write a program that fetches 10,000 URLs. Do it three ways — platform thread pool,
`CompletableFuture`, virtual threads. Measure. Explain the difference in your journal.

---

## 2. Maven — 4 hrs

You'll live in Maven for 7 months. An afternoon now saves weeks of confusion.

**Concepts**
- The build lifecycle: `validate → compile → test → package → verify → install → deploy`.
  **Phases vs goals** — the distinction most devs never learn
- Parent POM vs **BOM** (`dependencyManagement` with `<scope>import</scope>`) — the right way
  to pin versions across 10 modules
- Multi-module reactor builds: `<modules>`, build order resolution, `-pl` / `-am` flags
- Dependency scopes (`compile`, `provided`, `runtime`, `test`) and **dependency mediation** —
  how Maven picks a version when two paths disagree (nearest-wins), and why that bites you
- `mvn dependency:tree` — your weapon against version conflicts
- Plugin binding: how `spring-boot-maven-plugin` hooks `repackage` into `package`
- Maven Wrapper (`mvnw`) — committed, so CI and your machine agree on the Maven version
- `maven-enforcer-plugin` — ban duplicate classes, require a Java version, fail on convergence errors
- Profiles — and why you should use them sparingly (they make builds non-reproducible)

**Resources**
- 📄🆓 **[Maven — Introduction to the Build Lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)** — read this properly, it's the core mental model
- 📄🆓 [Introduction to the POM](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html) + [Dependency Mechanism](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)
- 📕🆓 [Maven: The Complete Reference](https://books.sonatype.com/mvnref-book/reference/index.html) — free from Sonatype. Dated in places, still the best structured read. Ch. 3, 6, 8.
- 📄🆓 [Spring Boot — Maven Plugin docs](https://docs.spring.io/spring-boot/maven-plugin/index.html) — layered jars, `build-image`, `repackage`
- 🆓 [Baeldung — Maven guides](https://www.baeldung.com/maven) — good for specific questions

**🧪 Lab:** Build a 3-module reactor (`core`, `api`, `app`) with a parent POM, a `<dependencyManagement>`
BOM import for Spring Boot, and the enforcer plugin requiring Java 21. `./mvnw clean verify` must
work from clean. Then deliberately introduce a transitive version conflict and resolve it using
`mvn dependency:tree`.

> **Interview note:** "Why Maven over Gradle?" is a real question. Have the answer ready —
> reproducibility and convention over configuration, declarative XML that can't become a
> program, overwhelmingly the standard in enterprise Java/Spring shops. Write it up in
> `docs/adr/0002-build-tooling.md`.

## 3. Docker & Compose — 6 hrs

**Concepts**
- Images vs containers vs layers; why layer order determines build speed
- Multi-stage builds — a 90MB Java image instead of 700MB
- JVM-in-container: `-XX:MaxRAMPercentage`, why `-Xmx` is wrong in containers, CPU shares
- Compose: networks, volumes, `depends_on` + healthchecks (the `condition: service_healthy` trap)
- `docker exec`, `logs`, `inspect` — debugging without guessing
- Distroless / jlink custom runtimes

**Resources**
- 📕💰 **[Docker Deep Dive](https://nigelpoulton.com/books/) — Nigel Poulton.** Short, excellent, no fluff.
- 📄🆓 [Docker official docs — Build](https://docs.docker.com/build/) — the Dockerfile reference
- 🆓 [Testcontainers docs](https://testcontainers.com/) — you'll need these in Phase 1; skim now
- 📄🆓 [Spring Boot — Container Images](https://docs.spring.io/spring-boot/reference/packaging/container-images/index.html) — layered jars, Cloud Native Buildpacks

**🧪 Lab:** Containerise a hello-world Spring Boot app two ways — naive single-stage, then
multi-stage + layered jar. Compare image size and rebuild time after a one-line code change.

---

## 4. Git discipline — 4 hrs

This repo is a public artifact an interviewer will read. The history is part of the work.

**Concepts**
- Conventional Commits (`feat:`, `fix:`, `refactor:`, `docs:`) — enables changelog generation
- Interactive rebase, squashing, `--fixup` + `--autosquash`
- Trunk-based vs GitFlow — and why for a solo project trunk + short branches wins
- `git bisect` — the most underused tool in Git
- Signed commits (GPG/SSH) — small detail, signals seriousness
- GitHub Actions basics: workflow syntax, caching, matrix builds

**Resources**
- 🆓📄 [Conventional Commits spec](https://www.conventionalcommits.org/) — 10 minutes
- 🆓 [Learn Git Branching](https://learngitbranching.js.org/) — interactive, genuinely the fastest way to internalise rebase
- 📄🆓 [GitHub Actions docs — Workflow syntax](https://docs.github.com/en/actions/writing-workflows/workflow-syntax-for-github-actions)
- 📄🆓 [Pro Git, Ch. 7](https://git-scm.com/book/en/v2) — free, the rewriting-history chapter

---

## ✅ Phase 0 Checkpoint

Pass all of these **from memory, without searching**, before writing Phase 1 code:

- [ ] Explain virtual threads to a non-Java dev in 60 seconds, including one case where they *don't* help
- [ ] Write a multi-module parent POM with a `dependencyManagement` BOM import from scratch
- [ ] Explain Maven's nearest-wins dependency mediation and resolve a conflict with `dependency:tree`
- [ ] Write a multi-stage Dockerfile for a Spring Boot app, explaining each layer's purpose
- [ ] Explain why `-Xmx` is the wrong flag in a container and what to use instead
- [ ] Rebase 3 messy commits into 1 clean one, interactively
- [ ] Your CI runs green on a push to GitHub

**Then build:** monorepo skeleton + Compose stack + CI. Write `docs/adr/0002-build-tooling.md`
covering Maven vs Gradle, and multi-module reactor layout. Journal it.

---

## 💬 Interview questions this phase answers

- *"What's the difference between a platform thread and a virtual thread?"*
- *"How do you size JVM memory inside a container?"*
- *"How would you structure a Maven monorepo with 10 modules?"*
- *"Walk me through your CI pipeline."*
