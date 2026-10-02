# Phase 4 — Container Security & Sandboxed Execution

**4 weeks · ~45 hrs** · ⭐⭐ The hardest and most distinctive piece. Running untrusted user code
safely is a real engineering problem almost no portfolio project attempts.

> **Build at the end:** `lab` service — users submit Java/SQL/YAML; it executes in a locked-down
> container as a Kubernetes Job with no network, read-only rootfs, seccomp profile, CPU/memory
> caps and a hard timeout. Results streamed back over SSE.

---

## 1. Linux container internals — 14 hrs

Stop treating containers as magic. This is what separates people who *use* Docker from people
who understand it.

**Concepts**
- **Namespaces**: `pid`, `net`, `mnt`, `uts`, `ipc`, `user`, `cgroup` — what each isolates
- **cgroups v2**: CPU shares/quota, memory limits, the OOM killer, `pids` limit (fork-bomb defence)
- **Capabilities** — dropping `ALL` and adding back only what's needed
- **seccomp-bpf** — syscall filtering; writing a profile
- AppArmor / SELinux basics
- Rootless containers, user namespace remapping
- Union filesystems, overlayfs, read-only root with a tmpfs scratch
- Container escape vectors: privileged mode, `hostPath` mounts, the docker socket

**Resources**
- 🆓 **[Liz Rice — Containers From Scratch (talk)](https://www.youtube.com/watch?v=8fi7uSYlOdc)** — builds a container in Go live. Watch it twice.
- 📕💰 **[Container Security](https://www.oreilly.com/library/view/container-security/9781492056690/) — Liz Rice.** The book for this phase. Ch. 2–9.
- 📄🆓 [man7 — namespaces(7)](https://man7.org/linux/man-pages/man7/namespaces.7.html), [cgroups(7)](https://man7.org/linux/man-pages/man7/cgroups.7.html), [capabilities(7)](https://man7.org/linux/man-pages/man7/capabilities.7.html)
- 📄🆓 [Docker — seccomp](https://docs.docker.com/engine/security/seccomp/) + [default profile](https://github.com/moby/moby/blob/master/profiles/seccomp/default.json)

**🧪 Lab:** Write a container escape *against your own sandbox* and then close it. Try: fork
bomb, memory exhaustion, filling the disk, outbound network call, reading `/proc`. Document each
attack and mitigation. **This document is an interview weapon.**

---

## 2. Sandboxing runtimes — 8 hrs

**Concepts**
- **gVisor (runsc)** — userspace kernel, syscall interception; the practical choice
- **Kata Containers / Firecracker** — microVM isolation; what AWS Lambda actually uses
- Trade-offs: isolation strength vs startup latency vs syscall overhead
- WASM sandboxing as an alternative (Wasmtime) — worth knowing, probably not using

**Resources**
- 📄🆓 [gVisor docs](https://gvisor.dev/docs/) — read "What is gVisor" and the security model page
- 📄🆓 [Firecracker design](https://github.com/firecracker-microvm/firecracker/blob/main/docs/design.md)
- 🆓 [Firecracker NSDI'20 paper](https://www.usenix.org/conference/nsdi20/presentation/agache) — readable, excellent

---

## 3. Kubernetes Jobs & the Java client — 10 hrs

**Concepts**
- `Job` vs `CronJob`; `backoffLimit`, `activeDeadlineSeconds`, `ttlSecondsAfterFinished`
- `securityContext`: `runAsNonRoot`, `readOnlyRootFilesystem`, `allowPrivilegeEscalation: false`,
  `seccompProfile`, dropped capabilities
- `NetworkPolicy` — default-deny egress for lab pods
- `ResourceQuota` and `LimitRange`
- Pod Security Admission (restricted profile)
- The **Fabric8 Kubernetes Java client** — creating Jobs, watching pod status, streaming logs
- RBAC for your service account — least privilege for the thing that creates pods

**Resources**
- 📄🆓 [K8s — Jobs](https://kubernetes.io/docs/concepts/workloads/controllers/job/) · [Security Context](https://kubernetes.io/docs/tasks/configure-pod-container/security-context/) · [Pod Security Standards](https://kubernetes.io/docs/concepts/security/pod-security-standards/)
- 📄🆓 [Fabric8 Kubernetes Client](https://github.com/fabric8io/kubernetes-client)
- 📄🆓 [K8s NetworkPolicy](https://kubernetes.io/docs/concepts/services-networking/network-policies/)

---

## 4. Application security — 8 hrs

**Concepts**
- OWASP Top 10, practically — not as a checklist
- Input validation at the boundary; why allow-lists beat deny-lists
- Secrets: never in env vars in plain text; External Secrets Operator / sealed-secrets
- Supply chain: SBOM (Syft), vulnerability scanning (Trivy, Grype), dependency pinning
- Image signing (cosign / Sigstore)
- Rate limiting and abuse prevention for an endpoint that burns CPU on demand

**Resources**
- 🆓 [OWASP Top 10](https://owasp.org/www-project-top-ten/) · [OWASP Cheat Sheet Series](https://cheatsheetseries.owasp.org/) — the cheat sheets are the useful part
- 📄🆓 [Trivy](https://trivy.dev/) · [Syft](https://github.com/anchore/syft) · [cosign](https://docs.sigstore.dev/cosign/signing/overview/)
- 🆓 [SLSA framework](https://slsa.dev/) — supply chain levels; good vocabulary for interviews

---

## ✅ Phase 4 Checkpoint

- [ ] Name all seven namespaces and what each isolates, from memory
- [ ] Explain how you'd stop a fork bomb, a memory bomb and a disk-fill in a container
- [ ] Explain what gVisor does differently from runc
- [ ] Write a restricted `securityContext` from memory
- [ ] Explain your defence-in-depth layers for running untrusted code
- [ ] Your own attack-and-mitigation document is written up

**Then build:** `lab` service. ADRs: `0012-sandbox-isolation-strategy.md`, `0013-gvisor-vs-microvm.md`.

---

## 💬 Interview questions this phase answers

- *"How would you safely run code a user uploaded?"* — most candidates have nothing here
- *"What is a container, actually?"*
- *"How do you limit a container's resources, and what happens at the limit?"*
- *"How do you secure your supply chain?"*
