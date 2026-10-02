# Phase 5 — Data & Retrieval

**3 weeks · ~30 hrs** · Make the tutor teach from *your* material — your notes, Udemy
transcripts, the Joveo codebase — not just its training data.

> **Build at the end:** `knowledge` service — ingests documents, chunks and embeds them into
> pgvector, serves hybrid (keyword + semantic) search with re-ranking, feeds the `tutor` service.

---

## 1. Embeddings & vector search — 10 hrs

**Concepts**
- What an embedding *is*; cosine similarity vs dot product vs L2
- Dimensionality, model choice, and why you can't mix models in one index
- **ANN indexes**: HNSW vs IVFFlat — build time, recall, memory. Tuning `m`, `ef_construction`, `ef_search`
- The recall/latency trade-off — there is no free lunch
- pgvector vs a dedicated vector DB (Qdrant, Weaviate) — **a great ADR**; for your scale, pgvector wins and you should be able to say why

**Resources**
- 📄🆓 **[pgvector README](https://github.com/pgvector/pgvector)** — genuinely the best doc on this
- 🆓 [Pinecone Learning Center](https://www.pinecone.io/learn/) — vendor-neutral enough, very clear on ANN/HNSW
- 🆓 [HNSW paper (Malkov & Yashunin)](https://arxiv.org/abs/1603.09320) — skim the intuition, skip the proofs
- 📄🆓 [Anthropic — Embeddings](https://docs.claude.com/en/docs/build-with-claude/embeddings)

---

## 2. RAG done properly — 12 hrs

**Concepts**
- **Chunking** — fixed-size, recursive, semantic, document-structure-aware. The single biggest
  quality lever, and where most RAG implementations fail
- Chunk overlap, metadata attachment, parent-document retrieval
- **Hybrid search**: BM25 (Postgres full-text) + vector, fused with **Reciprocal Rank Fusion**
- **Re-ranking** — cross-encoders; retrieve 50, re-rank to 5
- Query transformation: HyDE, multi-query expansion, query decomposition
- **Contextual retrieval** — prepending chunk-specific context before embedding; large accuracy win
- Evaluating RAG: retrieval precision/recall@k separately from answer quality
- Citations and grounding — making the tutor say *where* it learned something

**Resources**
- 🆓 **[Anthropic — Contextual Retrieval](https://www.anthropic.com/news/contextual-retrieval)** — read this; it's the current state of the art and easy to implement
- 📄🆓 [Anthropic — RAG guide](https://docs.claude.com/en/docs/build-with-claude/search-and-retrieval)
- 🆓 [Anthropic Cookbook — RAG notebooks](https://github.com/anthropics/anthropic-cookbook)
- 🆓 [Ragas](https://docs.ragas.io/) — RAG evaluation metrics; steal the metric definitions even if you implement them yourself
- 📄🆓 [Postgres full-text search](https://www.postgresql.org/docs/current/textsearch.html)

**🧪 Lab:** Build the retrieval eval *first*. 30 questions with known-correct source chunks.
Measure recall@5 for: naive chunking → semantic chunking → + hybrid → + re-ranking → + contextual
retrieval. **Plot the improvement.** This chart goes in your README.

---

## 3. Postgres at scale — 8 hrs

**Concepts**
- Partitioning (range, list, hash) — your events and analytics tables need it
- `VACUUM`, autovacuum tuning, bloat, transaction ID wraparound
- Replication: streaming vs logical; read replicas and replica lag
- Connection pooling at scale: PgBouncer, transaction vs session pooling
- `pg_stat_statements` — finding your actual slow queries
- When to reach for TimescaleDB (you will, in Phase 8)

**Resources**
- 📕🆓 [PostgreSQL Internals — Egor Rogov](https://postgrespro.com/community/books/internals) — free; the VACUUM and WAL parts
- 🆓 [Postgres Weekly](https://postgresweekly.com/) — subscribe, it's good
- 📕 **DDIA Ch. 5 (Replication), 6 (Partitioning)** — read alongside this

---

## ✅ Phase 5 Checkpoint

- [ ] Explain HNSW's trade-offs and which parameters you'd tune for recall vs latency
- [ ] Explain why chunking strategy dominates RAG quality
- [ ] Explain Reciprocal Rank Fusion and why hybrid beats pure vector search
- [ ] Explain how you'd evaluate retrieval separately from generation
- [ ] Defend pgvector over a dedicated vector DB for your scale
- [ ] Your recall@5 improvement chart exists

**Then build:** `knowledge` service. ADRs: `0014-pgvector-over-dedicated-vectordb.md`, `0015-chunking-strategy.md`.

---

## 💬 Interview questions this phase answers

- *"How does RAG work and where does it typically fail?"*
- *"How do you evaluate a retrieval system?"*
- *"Vector DB or Postgres — how did you decide?"*
- *"How do you partition a table that grows forever?"*
