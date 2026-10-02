# 0001 — Record architecture decisions

- **Status:** Accepted
- **Date:** 2026-10-02
- **Phase:** 0
- **Supersedes:** —
- **Superseded by:** —

## Context

Praxis has two purposes: it is a system, and it is evidence of engineering judgment. The second
purpose fails if the reasoning behind the system lives only in my head. Seven months from now I
will not remember why I chose Maven, why the saga is orchestrated rather than choreographed, or
why I skipped the service mesh — and those are precisely the questions an interviewer asks.

Lightweight ADRs, as described by Michael Nygard, solve this at near-zero cost.

## Decision

Every significant architectural decision gets an ADR in `docs/adr/`, numbered sequentially and
written **before** the implementation.

"Significant" means: it is expensive to reverse, it constrains later choices, or a reasonable
engineer would have chosen differently.

ADRs are immutable once accepted. Changing course means writing a new ADR that supersedes the
old one, leaving both in the repo.

## Alternatives considered

| Option | Why rejected |
|---|---|
| **A wiki or Notion page** | Drifts from the code, isn't versioned with it, not visible to anyone reading the repo |
| **Comments in code** | Explains the *what*, has no room for rejected alternatives or trade-offs |
| **Nothing — rely on memory** | The reasoning is the asset. Losing it loses most of the project's interview value. |
| **Full RFC documents** | Too heavy for a solo project; the ceremony would stop me writing them at all |

## Consequences

**Good**
- Decisions and their reasoning are versioned alongside the code that implements them
- Reversals are visible, which is a feature — changing your mind with evidence is good engineering
- Directly reusable as interview answers

**Bad**
- Friction before each significant decision (this is mostly the point)
- The index needs maintaining

## Notes

Format adapted from [Michael Nygard's original](https://cognitect.com/blog/2011/11/15/documenting-architecture-decisions).
