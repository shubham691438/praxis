# Phase 6 — Kubernetes & Observability

**5 weeks · ~55 hrs** · Where the project becomes a *platform*. Also where the self-referential
demo lands: the Kubernetes lesson reads live state from the cluster it runs in.

> **Build at the end:** Everything on Kubernetes. Helm charts, HPA, ArgoCD GitOps, full OTel
> stack (Tempo/Loki/Prometheus/Grafana), SLOs with burn-rate alerts.

---

## 1. Kubernetes properly — 20 hrs

Not "I can write a Deployment YAML." Understand the control loop.

**Concepts**
- Architecture: API server, etcd, scheduler, controller-manager, kubelet, kube-proxy
- **The reconciliation loop** — the core idea; everything else follows from it
- Workloads: Deployment, StatefulSet, DaemonSet, Job, CronJob — and when each
- Services: ClusterIP, NodePort, LoadBalancer, Headless; `Ingress` vs **Gateway API** (the future)
- Networking: CNI, pod-to-pod, DNS, `NetworkPolicy`
- Storage: PV, PVC, StorageClass, dynamic provisioning
- Config: ConfigMap, Secret, projected volumes
- **Scheduling**: requests vs limits, QoS classes, affinity/anti-affinity, taints/tolerations,
  topology spread, PodDisruptionBudgets
- **Autoscaling**: HPA (incl. custom metrics via Prometheus Adapter), VPA, Cluster Autoscaler, KEDA
- Probes: liveness vs readiness vs startup — **and how a wrong liveness probe causes outages**
- Rolling updates, `maxSurge`/`maxUnavailable`, graceful shutdown + `preStop` hooks
- Operators and CRDs — write a tiny one; it proves you understand the control loop
- Helm: templating, values hierarchy, hooks. Kustomize: overlays. When each.

**Resources**
- 📕💰 **[The Kubernetes Book](https://nigelpoulton.com/books/) — Nigel Poulton.** Updated yearly, best starting point.
- 📕💰 **[Kubernetes Patterns, 2nd ed](https://www.oreilly.com/library/view/kubernetes-patterns-2nd/9781098131678/) — Ibryam & Huß.** The *design* book. Essential for interviews.
- 🆓 **[KodeKloud CKA course](https://kodekloud.com/)** 💰 — browser labs, the fastest way to build real muscle memory. Worth the money.
- 📄🆓 [kubernetes.io — Concepts](https://kubernetes.io/docs/concepts/) — surprisingly good; read Workloads and Services sections
- 🆓 [Kubernetes The Hard Way — Kelsey Hightower](https://github.com/kelseyhightower/kubernetes-the-hard-way) — **do this once.** Painful, clarifying.
- 📄🆓 [Helm docs](https://helm.sh/docs/) · [Kustomize](https://kubectl.docs.kubernetes.io/references/kustomize/)

**🧪 Lab:** Kubernetes The Hard Way, then deliberately misconfigure a liveness probe so the pod
restarts under load. Diagnose it from metrics alone. Journal it.

---

## 2. GitOps — 6 hrs

**Concepts**
- Declarative infra; Git as the single source of truth
- ArgoCD: Applications, sync policies, self-heal, drift detection, ApplicationSets
- App-of-apps pattern
- Progressive delivery: canary and blue/green with Argo Rollouts
- Secrets in GitOps — sealed-secrets or External Secrets Operator (never plaintext in Git)

**Resources**
- 📄🆓 [Argo CD docs](https://argo-cd.readthedocs.io/)
- 🆓 [OpenGitOps principles](https://opengitops.dev/)
- 📄🆓 [Argo Rollouts](https://argo-rollouts.readthedocs.io/)

---

## 3. Observability, fully — 16 hrs

**Concepts**
- OpenTelemetry: SDK, Collector, OTLP, auto vs manual instrumentation
- Collector pipelines: receivers → processors → exporters; tail-based sampling
- **Tempo** (traces), **Loki** (logs), **Prometheus/Mimir** (metrics), **Grafana** (all of it)
- Exemplars — jumping from a latency spike on a metric straight to the trace that caused it
- PromQL — rate, histogram_quantile, aggregation. **Learn this properly; it's interviewed.**
- Micrometer custom metrics; `@Timed`, `@Counted`, histograms vs summaries
- **SLIs, SLOs, error budgets, multi-window multi-burn-rate alerts**
- Cardinality explosions — the #1 way to destroy a metrics bill
- Continuous profiling with Pyroscope

**Resources**
- 📕🆓 **[Google SRE Book](https://sre.google/sre-book/table-of-contents/) — free online.** Ch. 4 (SLOs), 6 (Monitoring). And **[The SRE Workbook](https://sre.google/workbook/table-of-contents/)** Ch. 2 & 5 for alerting on burn rate.
- 📄🆓 [OpenTelemetry docs](https://opentelemetry.io/docs/) · [Collector](https://opentelemetry.io/docs/collector/)
- 📄🆓 [Prometheus — PromQL basics](https://prometheus.io/docs/prometheus/latest/querying/basics/)
- 🆓 [Grafana Labs — free courses](https://grafana.com/tutorials/)
- 📕💰 [Observability Engineering](https://www.oreilly.com/library/view/observability-engineering/9781492076438/) — Ch. 7–12

**🧪 Lab:** Define an SLO for lesson-generation latency. Build a multi-burn-rate alert. Inject
latency until it fires. Then build the Grafana dashboard that the *app itself* embeds in its
own observability lesson.

---

## 4. Service mesh — optional, 6 hrs

Know it, probably don't use it. Deciding *not* to adopt something is a senior signal.

**Concepts**
- Sidecar vs ambient/sidecar-less (Istio ambient, Cilium)
- mTLS, traffic shifting, retries at the mesh layer
- **The cost**: latency, complexity, debugging. Why you may have skipped it deliberately.

**Resources**
- 📄🆓 [Istio — Ambient mode](https://istio.io/latest/docs/ambient/) · [Linkerd docs](https://linkerd.io/2/overview/)

---

## ✅ Phase 6 Checkpoint

- [ ] Explain the reconciliation loop and what a controller does
- [ ] Explain requests vs limits and the three QoS classes
- [ ] Explain how a bad liveness probe causes a cascading outage
- [ ] Write PromQL for p99 latency from a histogram, from memory
- [ ] Explain error budgets and multi-burn-rate alerting
- [ ] Explain how you'd debug a slow request across six services
- [ ] Explain why you did or didn't adopt a service mesh

**Then build:** full K8s deployment + GitOps + observability stack, and the self-referential
lesson that reads live cluster state. ADRs: `0016-helm-and-kustomize.md`, `0017-gitops-argocd.md`,
`0018-no-service-mesh.md`, `0019-slo-definitions.md`.

---

## 💬 Interview questions this phase answers

- *"Explain how Kubernetes works."*
- *"A pod is CrashLoopBackOff. Walk me through debugging it."*
- *"What's your SLO and how did you pick it?"*
- *"How do you do zero-downtime deploys?"*
