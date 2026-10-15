# HandwerkFlow ⚡

**EN:** A bilingual, workflow-oriented field-service management application for electricians, HVAC installers, solar installers and plumbers. A focused open-source portfolio application built by [Ahad Porkar](https://github.com/AhadPorkar) to demonstrate process-centric full-stack design.

**DE:** Eine zweisprachige, prozessorientierte Anwendung zur Auftrags- und Einsatzverwaltung für Elektro-, Klima-, Solar- und Sanitärbetriebe. Ein Open-Source-Portfolio-Projekt zur Demonstration moderner Full-Stack- und Prozessarchitektur.

**Status: portfolio foundation / Portfolio-Grundversion (v0.1). Not audited or certified for live commercial invoicing.**

## Screens / Ansichten

- Overview with operational KPIs, pipeline distribution, trade breakdown, recent orders.
- Customers and work orders with intake and details.
- Guided lifecycle: Intake → Survey → Scheduled → In progress → Completed → Invoiced.
- Surveys, installation reports, completion documents (structured DB entries), simple invoices and immutable event log entries.
- English (default) and German interface, responsive sidebar and layouts.

## Stack

Java 25 LTS, Spring Boot 4.1.1, Spring Security, Spring JDBC, Flyway, PostgreSQL 17, React 19, TypeScript, Vite, Recharts, Docker Compose, GitHub Actions, JUnit 5, Vitest.

## Quick start (Docker)

Install Docker Engine + Docker Compose. From the repository root:

```bash
cp .env.example .env
# Replace BOTH passwords in .env with strong unique values.
docker compose up --build -d
docker compose ps
```

Open **http://localhost:8088**, sign in with `ADMIN_USER` / `ADMIN_PASSWORD` from your `.env`. Backend is exposed internally to the Nginx frontend proxy. Database persists in the Docker volume `pgdata`.

To stop: `docker compose down`. **Do not** use `docker compose down -v` unless you intend to permanently delete database data.

## Local development

Start PostgreSQL locally and set `DB_URL`, `DB_USER`, `DB_PASSWORD`, and `ADMIN_PASSWORD`. Run backend with Java 25 and Maven 3.9+: `cd backend && mvn spring-boot:run`. In a separate terminal run `cd frontend && npm install && npm run dev` (Node 22+). Open http://localhost:5173. Frontend proxies `/api` to localhost:8080.

## Tests / Tests ausführen

```bash
cd backend && mvn verify
cd frontend && npm install && npm run test && npm run build
```

The included tests cover the Java workflow state machine and bilingual translation parity. **No live PostgreSQL integration tests, browser E2E suite, or full module-level coverage are included yet.** CI runs these checks on each push and PR. Do not claim every module has been tested.

## Documentation / Dokumentation

- [English: architecture, API, operations and limitations](docs/en/ARCHITECTURE.md)
- [English: market research and product decisions](docs/en/RESEARCH.md)
- [English: roadmap and launch checklist](docs/en/ROADMAP.md)
- [English: Git/commit convention](docs/en/CONTRIBUTING.md)
- [Deutsch: Architektur, Funktionen und Betrieb](docs/de/ARCHITEKTUR.md)
- [Deutsch: Marktanforderungen](docs/de/MARKTANALYSE.md)
- [Deutsch: Roadmap und Freigabe](docs/de/ROADMAP.md)
- [Deutsch: Git- und Commit-Leitfaden](docs/de/BEITRAGEN.md)

## Disclosures

This is a **working source-code foundation**, not a production-certified SaaS. Basic authentication is currently administrator-only; there is no organization isolation, RBAC, identity provider, event bus, BPMN execution engine, offline sync, PDF generation, electronic signature or validated German e-invoice export. Invoices are financial records, not compliant XRechnung/ZUGFeRD. Do not expose publicly without completing the [release checklist](docs/en/ROADMAP.md).

## Author

[Ahad Porkar](https://github.com/AhadPorkar) · [Website](https://ahadporkar.com/) · [LinkedIn](https://www.linkedin.com/in/seapr/)

License: [MIT](LICENSE). No affiliation with GraphBPMS or other software companies is implied.
