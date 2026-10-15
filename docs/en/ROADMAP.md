# Roadmap and Shipping Gate

## Implemented in v0.1
Customer/work-order creation, workflow transitions and integrity gates, survey/report/completion records, simple invoice records, job event log, dashboard charts, EN/DE UI, responsive interface, PostgreSQL/Flyway, Docker Compose, baseline unit tests and CI.

## Phase 1 — Production hardening (blockers)
- Replace HTTP Basic with OIDC Authorization Code + PKCE, short-lived tokens, roles (`ADMIN`, `DISPATCHER`, `TECHNICIAN`, `ACCOUNTING`), organization isolation and scoped server-side permission checks.
- Add PostgreSQL Testcontainers integration tests for every API module, transaction rollback, concurrency, security and migrations; add Playwright E2E and accessibility tests.
- Add standardized API pagination, filtering, OpenAPI schema, Problem Details handling and end-to-end DE/EN validation/error translation.
- CI: vulnerability/SBOM scan, formatting/lint, dependency lock, verified container scans, multi-stage staging deploy, managed secrets.
- Backups with restore drills, HTTPS, tracing/metrics, load and recovery tests, retention/deletion and GDPR review.

## Phase 2 — Real contractor workflows
- Technicians, role assignments, schedules and conflict detection, capacity calendar and notifications.
- File attachments in object storage with malware scanning, signed download URLs, metadata, photo proofs, signed customer acceptance, PDF generation.
- Trade-specific checklists: electrical test results, HVAC commissioning, solar PV commissioning, plumbing safety/pressure records, configurable templates.
- Estimates/quotes, material catalog, consumption, payments, invoice corrections, reminders, tax profiles and document numbers.

## Phase 3 — German enterprise readiness
- XRechnung/ZUGFeRD via maintained standards-compliant library, EN 16931 business-rule validation and downloadable structured invoices.
- GoBD-aligned retention/integrity requirements evaluation with accountant/legal counsel; DATEV integration.
- BPMN 2.0 orchestrated long-running processes (Camunda/Flowable), DMN decisions, timers, escalation and compensation; process versioning with auditable migration.
- PWA offline technician app with durable local queue, conflict resolution and synchronization.

## Phase 4 — Differentiation
- Customer portal, scheduling optimization, GIS/map routing, equipment asset lifecycle, SLA/maintenance contracts, webhooks, event outbox, analytics, ESG/solar impact metrics and opt-in AI-assisted report drafting.

## Mandatory go-live acceptance gate
No real customer production use until all Phase 1 blockers pass, restore drills succeed, security review is completed, GDPR controls are documented, and accountant reviews billing compliance. The sample invoice table is not a compliant billing product.
