# Phase 2 — Reactive & AI

**4 weeks · ~45 hrs** · The phase that makes the project *feel* impressive. Streaming AI
responses token-by-token is the demo that gets attention.

> **Build at the end:** `tutor` service — WebFlux, streams Claude responses over SSE to a React
> UI, generates lessons + Mermaid diagrams + quizzes, with semantic caching and an eval harness.

---

## 1. Project Reactor & WebFlux — 14 hrs

**Concepts**
- Why reactive at all — and **be honest that virtual threads removed most of the original reason.**
  Reactive still wins for *streaming* and *backpressure*, which is exactly your use case
- `Mono` / `Flux`, cold vs hot publishers
- Operators: `map`, `flatMap`, `concatMap`, `switchMap` — the differences matter enormously
- **Backpressure** strategies: buffer, drop, latest, error
- Schedulers: `parallel`, `boundedElastic`, `single`, and never blocking the event loop
- Context propagation (`contextWrite`) — reactive's answer to ThreadLocal; needed for tracing
- Error handling: `onErrorResume`, `retryWhen` with exponential backoff
- `StepVerifier` for testing
- `WebClient` — the reactive HTTP client you'll call Claude with

**Resources**
- 📄🆓 **[Reactor Reference Guide](https://projectreactor.io/docs/core/release/reference/)** — the "Which operator do I need?" appendix is gold
- 🆓 **[Reactive Programming with Reactor 3 — tech.io](https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/Intro)** — free interactive lessons from the Reactor team. Do all of them.
- 📕💰 [Hands-On Reactive Programming in Spring 5](https://www.packtpub.com/en-us/product/hands-on-reactive-programming-in-spring-5-9781787284951) — dated version, concepts still right
- 🎥🆓 [Josh Long — Spring Tips](https://www.youtube.com/@SpringSourceDev) — WebFlux episodes
- 📄🆓 [Spring WebFlux reference](https://docs.spring.io/spring-framework/reference/web/webflux.html)

**🧪 Lab:** Build a `Flux` that emits 1000 events/sec into a consumer that handles 100/sec.
Watch it break. Then fix it with each backpressure strategy and describe the trade-off of each
in your journal.

---

## 2. Server-Sent Events & streaming — 6 hrs

**Concepts**
- SSE vs WebSockets vs long-polling — why SSE is correct here (unidirectional, HTTP/2-friendly, auto-reconnect)
- `text/event-stream`, event IDs, `Last-Event-ID` for resumption
- Proxy/nginx buffering killing your stream (the classic production surprise)
- Client side: `EventSource` API, and why you need `fetch` + `ReadableStream` to send auth headers
- Heartbeats and idle-timeout handling

**Resources**
- 📄🆓 [MDN — Server-Sent Events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events)
- 📄🆓 [WHATWG HTML spec — EventSource](https://html.spec.whatwg.org/multipage/server-sent-events.html) — the actual protocol
- 📄🆓 [Spring — Streaming responses](https://docs.spring.io/spring-framework/reference/web/webflux/reactive-spring.html)

---

## 3. LLM integration — Claude API — 12 hrs

This is where most portfolio projects are shallow. Go deep and you separate yourself instantly.

**Concepts**
- Messages API: system prompts, multi-turn, `stop_reason`, token accounting
- **Streaming** — SSE from Anthropic → your `Flux` → SSE to browser. Chained streams.
- **Tool use / function calling** — let the tutor query your own curriculum service mid-answer
- Structured output — getting reliable JSON for quizzes without regex-scraping prose
- **Prompt caching** — big latency and cost win for your long system prompts
- Token budgeting, context windows, cost per lesson (track this as a real metric)
- **Semantic caching** — embed the request, serve a cached lesson on a near-miss. Real engineering.
- Rate limits, 429 handling, exponential backoff with jitter, circuit breaking on an external dependency
- **Evals** — a golden dataset + LLM-as-judge scoring, run in CI. This is the differentiator.
- Prompt injection — your users control input that reaches the model. Think about it now.

**Resources**
- 📄🆓 **[Anthropic API docs](https://docs.claude.com/en/api/overview)** — the primary source
- 📄🆓 **[Prompt engineering guide](https://docs.claude.com/en/docs/build-with-claude/prompt-engineering/overview)** — read all of it, it's short and dense
- 📄🆓 [Tool use](https://docs.claude.com/en/docs/agents-and-tools/tool-use/overview) · [Streaming](https://docs.claude.com/en/docs/build-with-claude/streaming) · [Prompt caching](https://docs.claude.com/en/docs/build-with-claude/prompt-caching)
- 🆓 **[Building effective agents — Anthropic engineering blog](https://www.anthropic.com/engineering/building-effective-agents)** — the best thing written on agent design. Read twice.
- 🆓 [Anthropic Cookbook (GitHub)](https://github.com/anthropics/anthropic-cookbook) — runnable patterns including evals
- 📄🆓 [Spring AI reference](https://docs.spring.io/spring-ai/reference/) — decide whether to use it or call the API directly (an ADR!)
- 🆓 [OWASP Top 10 for LLM Applications](https://owasp.org/www-project-top-10-for-large-language-model-applications/)

**🧪 Lab:** Build the eval harness *before* the feature. 20 golden topics, a rubric, an
LLM-judge scoring accuracy/clarity/depth, output as a CI artifact. Then iterate your prompt and
**show the score going up**. That graph is an interview asset.

---

## 4. Enough React to not embarrass the backend — 8 hrs

You're a backend engineer. Build a competent UI, not a beautiful one.

**Concepts**
- Vite + React + TypeScript; function components and hooks only
- `useState` / `useEffect` / `useRef`; consuming a stream into state without re-render storms
- TanStack Query for server state (don't hand-roll fetch caching)
- Tailwind for styling — fastest path to "looks deliberate"
- Rendering streamed Markdown + Mermaid diagrams incrementally

**Resources**
- 📄🆓 **[react.dev — Learn](https://react.dev/learn)** — the official tutorial is genuinely excellent now
- 📄🆓 [TanStack Query docs](https://tanstack.com/query/latest)
- 📄🆓 [Mermaid docs](https://mermaid.js.org/) — your diagram renderer
- 🎥🆓 [Jack Herrington](https://www.youtube.com/@jherr) — pragmatic React for people who don't love React

---

## ✅ Phase 2 Checkpoint

- [ ] Explain `flatMap` vs `concatMap` vs `switchMap` with a concrete use case for each
- [ ] Explain backpressure and name three strategies with trade-offs
- [ ] Explain when you'd choose virtual threads over reactive — and admit when reactive is overkill
- [ ] Explain SSE vs WebSockets and defend SSE for this system
- [ ] Explain prompt caching and quantify what it saves you
- [ ] Your eval suite runs in CI and produces a score

**Then build:** `tutor` service, streaming end to end, eval harness green.
ADRs: `0005-reactive-vs-virtual-threads.md`, `0006-sse-over-websockets.md`, `0007-llm-eval-strategy.md`.

---

## 💬 Interview questions this phase answers

- *"When would you use WebFlux over Spring MVC in 2026?"* (virtual threads make this spicy — have a real answer)
- *"How do you handle backpressure?"*
- *"How do you make an LLM feature reliable and testable?"*
- *"How do you protect against prompt injection?"*
- *"How do you control cost on an LLM-backed feature?"*
