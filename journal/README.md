# The Journal

The record of the whole journey. **This is not optional and not decoration.**

## Why this matters more than you think

1. **Interviews run on stories, not facts.** "I know about the outbox pattern" is worth nothing.
   "I had a bug where a DB commit succeeded and the Kafka publish failed, here's how I found it
   and here's what I changed" gets you hired. Those stories live here, written while you still
   remember the details.
2. **It makes learning stick.** Writing down what broke and why is retrieval practice, which is
   the single most effective study technique there is.
3. **It becomes the blog posts.** Three months of entries is enough raw material for the
   write-ups that make this project visible.
4. **A visible 7-month trail of disciplined work is itself the signal.** Interviewers who scroll
   this see persistence, not just a code dump.

## How to use it

```bash
./bin/log            # new entry for today, pre-filled with your git activity
./bin/log "kafka rebalancing nightmare"   # with a title
./bin/weekly         # Sunday — roll the week up
```

Entries live at `journal/YYYY-MM-DD-day-NNN-slug.md`.

## The rules

- **Write it the same day.** A day later you've already lost the detail that mattered.
- **Write the failures.** An entry that's all success is a useless entry. The bugs are the asset.
- **Three minutes minimum, fifteen maximum.** This is a log, not an essay.
- **Log learning days too**, not just building days. "Read DDIA Ch. 7, finally understood
  snapshot isolation" is a legitimate entry.
- **Never backfill fiction.** If you missed three days, say you missed three days.

## Entry types

| Prefix | For |
|---|---|
| `day-NNN` | Normal daily entry |
| `week-NN` | Weekly rollup from `./bin/weekly` |
| `postmortem` | Something broke badly — use the full postmortem format |
| `decision` | Thinking through a choice *before* it becomes an ADR |
