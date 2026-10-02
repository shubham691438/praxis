# Phase 7 — Cloud & Resilience

**4 weeks · ~45 hrs** · Ship it for real, then try to break it. Prove it with numbers.

> **Build at the end:** Running on AWS EKS, provisioned entirely by Terraform. Load-tested with
> k6, chaos-tested, with a documented performance story and a monthly cost figure.

---

## 1. AWS fundamentals — 14 hrs

Go deep on few services rather than shallow on many.

**Concepts**
- **IAM** — policies, roles, trust relationships, **IRSA** (IAM Roles for Service Accounts). Most-interviewed AWS topic.
- **VPC** — subnets, route tables, NAT gateways, security groups vs NACLs, VPC endpoints
- **EKS** — managed control plane, node groups vs Fargate vs Karpenter, the AWS LB Controller
- **RDS** — Multi-AZ vs read replicas, parameter groups, failover behaviour
- **MSK** or self-managed Kafka — and the cost difference
- **S3** — storage classes, lifecycle policies, presigned URLs
- ECR, Secrets Manager, Parameter Store, CloudWatch
- **Cost**: NAT gateway charges, cross-AZ data transfer, the things that silently bankrupt side projects

**Resources**
- 🎥💰 **[Adrian Cantrill — AWS SAA](https://learn.cantrill.io/)** — widely regarded as the best AWS course. Deep, not exam-cram.
- 🆓 **[AWS Builders' Library](https://aws.amazon.com/builders-library/)** — free, written by principal engineers. Read every article. Genuinely top-tier distributed-systems writing.
- 📄🆓 [EKS Best Practices Guides](https://docs.aws.amazon.com/eks/latest/best-practices/introduction.html)
- 🆓 [AWS Well-Architected Framework](https://aws.amazon.com/architecture/well-architected/) — the five pillars are an interview framework

> 💸 **Set a billing alarm at $20 before you create anything.** Use Spot instances, one NAT
> gateway (or a NAT instance), and `terraform destroy` whenever you're not actively working.
> Budget ~$40–80/month if you leave it running; near-zero if you tear down nightly.

---

## 2. Terraform — 10 hrs

**Concepts**
- HCL, providers, resources, data sources
- **State** — remote backend (S3 + DynamoDB lock), why state is the whole ballgame
- Modules, composition, versioning
- `plan` vs `apply`, drift, `import`, `moved` blocks
- Workspaces vs directory-per-environment (prefer directories)
- Terragrunt — know it exists, decide against it for this scale

**Resources**
- 📕💰 **[Terraform: Up & Running, 3rd ed](https://www.terraformupandrunning.com/) — Brikman.** The book.
- 📄🆓 [HashiCorp tutorials](https://developer.hashicorp.com/terraform/tutorials) — free and good
- 📄🆓 [terraform-aws-modules/eks](https://github.com/terraform-aws-modules/terraform-aws-eks) — read this module's source; it's a masterclass

---

## 3. Performance & load testing — 10 hrs

**Concepts**
- **Latency percentiles** — why averages lie, why p99 matters, **tail latency amplification**
  in a 6-service call chain
- Little's Law — relating throughput, concurrency and latency
- **Coordinated omission** — the measurement bug in most load tests. Knowing this is a strong signal.
- k6 or Gatling: scenarios, ramping, thresholds as pass/fail
- Profiling: async-profiler, JFR, flame graphs
- JVM tuning: GC selection (G1 vs ZGC), heap sizing, JIT warmup
- Benchmarking traps: JIT, cold caches, the need for JMH on micro-benchmarks

**Resources**
- 🆓 **[Gil Tene — "How NOT to Measure Latency"](https://www.youtube.com/watch?v=lJ8ydIuPFeU)** — the single most valuable hour on performance. Watch it.
- 📄🆓 [k6 docs](https://grafana.com/docs/k6/latest/)
- 🆓 [async-profiler](https://github.com/async-profiler/async-profiler) · [Brendan Gregg — Flame Graphs](https://www.brendangregg.com/flamegraphs.html)
- 📕💰 [Optimizing Java](https://www.oreilly.com/library/view/optimizing-java/9781492039259/) — Evans, Gough, Newland
- 🆓 [AWS Builders' Library — Using load shedding to avoid overload](https://aws.amazon.com/builders-library/using-load-shedding-to-avoid-overload/)

**🧪 Lab:** Load test, find the bottleneck, fix it, re-test. Do this three times. **Record
before/after p99 for each.** Put the table in your README. This is the single most persuasive
artifact in the whole project.

---

## 4. Chaos engineering — 6 hrs

**Concepts**
- Steady-state hypothesis, blast radius, running experiments in a controlled way
- Failure injection: kill pods, add latency, partition the network, fill a disk
- Litmus / Chaos Mesh for Kubernetes
- Game days; writing a blameless postmortem

**Resources**
- 📕🆓 [Chaos Engineering — Principles](https://principlesofchaos.org/) — short manifesto
- 📄🆓 [Chaos Mesh](https://chaos-mesh.org/docs/) · [LitmusChaos](https://litmuschaos.io/)
- 🆓 [Google SRE Book — Postmortem Culture](https://sre.google/sre-book/postmortem-culture/)

**🧪 Lab:** Kill the Kafka broker mid-saga. Does the saga recover? Partition the network between
two services. Write a real postmortem for whatever broke. **Postmortems in a side project repo
are extremely unusual and signal maturity.**

---

## ✅ Phase 7 Checkpoint

- [ ] Explain IRSA and why it beats static credentials
- [ ] Explain tail latency amplification with a concrete calculation
- [ ] Explain coordinated omission and how to avoid it
- [ ] Explain Terraform state and why remote state with locking matters
- [ ] Explain your three performance fixes with before/after p99 numbers
- [ ] You have written at least one real postmortem

**Then build:** EKS via Terraform, load + chaos results documented.
ADRs: `0020-eks-over-self-managed.md`, `0021-cost-architecture.md`.

---

## 💬 Interview questions this phase answers

- *"How do you deploy to production?"*
- *"Your p99 latency doubled. What do you do?"*
- *"How do you test that your system survives failure?"*
- *"How do you manage infrastructure as code?"*
