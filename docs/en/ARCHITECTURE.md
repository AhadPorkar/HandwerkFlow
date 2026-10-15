# HandwerkFlow — Architecture and Developer Guide

## Purpose
HandwerkFlow digitizes the job lifecycle of electrical, HVAC, solar and plumbing contractors: intake, survey, scheduling, installation, acceptance, completion documentation and invoicing. Its distinguishing feature is a business process state model rather than isolated forms.

## Architecture
- `frontend/`: React 19 + TypeScript single-page app. `main.tsx` implements navigation, state and forms; `api.ts` centralizes HTTP; `i18n.ts` supplies EN/DE copy; `style.css` supplies responsive design.
- `backend/`: Java 25, Spring Boot 4.1.1. `api/ApiController` exposes routes; `domain/JobStatus` specifies allowed transitions; `SecurityConfig` configures Basic Auth and CORS; `ApiExceptionHandler` returns error details.
- `backend/src/main/resources/db/migration`: version-controlled Flyway DDL. PostgreSQL enforces foreign keys, uniqueness and amount constraints.
- `compose.yaml`: PostgreSQL, API and Nginx UI services with durable Postgres volume.
- `.github/workflows/ci.yml`: test and build jobs for both stacks.

## Data model
`customers 1--N jobs`. `jobs 1--N surveys`, `jobs 1--N installation_reports`, `jobs 1--N completion_documents`, `jobs 1--N job_events`, `jobs 1--0..1 invoices`. UUID primary keys, timestamped records and a `version` counter on jobs. All financial amounts are PostgreSQL NUMERIC/Java BigDecimal.

## Domain rules
`INTAKE → SURVEY → SCHEDULED → IN_PROGRESS → COMPLETED → INVOICED`. Cancellation is permitted in the first four statuses. A survey must exist before scheduling; a report with `safety_checked=true` AND `customer_accepted=true` must exist before completion. Only completed jobs can be invoiced. Status changes use an optimistic version check and are logged. Invoice creation is transactional and unique by job.

## REST API
Authentication: `Authorization: Basic base64(user:password)` over **TLS only outside localhost**. Routes use `/api/v1`. GET is read-only; POST creates; PATCH changes lifecycle state. JSON request/response; standard 400, 401, 404 and 409 error statuses.

| Method | Route | Function |
|---|---|---|
| GET | `/dashboard` | Status counts, recent jobs, invoice gross amount |
| GET/POST | `/customers` | Browse/create customers |
| GET/POST | `/jobs` | Browse/create jobs; optional `?status=SURVEY` |
| GET | `/jobs/{id}` | Work order details and optimistic version |
| PATCH | `/jobs/{id}/status` | `{ "status": "SURVEY", "expectedVersion": 0 }` |
| GET/POST | `/jobs/{id}/surveys` | Read/record site surveys |
| GET/POST | `/jobs/{id}/reports` | Read/record installation reports |
| GET/POST | `/jobs/{id}/completions` | Read/record completion documentation |
| POST | `/jobs/{id}/invoice` | Invoice from a completed work order |
| GET | `/invoices` | Invoice register |
| GET | `/jobs/{id}/events` | Job audit/activity trail |

For POST payloads, see Java `record` input types in `ApiController.java` (Bean Validation contracts). Every new write endpoint should have a migration, validation, authorization, integration tests, translations and documentation updates.

## Example usage
```bash
curl -u 'admin:YOUR_PASSWORD' -H 'Content-Type: application/json' \
  -d '{"name":"Musterbau GmbH","email":"office@example.de","address":"Berlin"}' \
  http://localhost:8080/api/v1/customers
```
Docker's default compose setup does not publish port 8080; use the frontend reverse proxy at `http://localhost:8088/api/v1/customers` or publish the API only for a trusted development environment.

## Security model and gaps
Single configured admin username/password, bcrypt hash in memory, stateless HTTP Basic. Basic Auth is intentionally simple for local demos; password remains in sessionStorage as a credential pair on the browser and must not be considered a deployment-ready identity model. CORS restricted to configured origin. Server-side validation, SQL bind parameters, FK constraints, no hard-coded secret in Git. **Before any live tenant usage, replace Basic Auth with OIDC/PKCE, add roles, tenant isolation, scoped authorization checks, TLS/reverse proxy, rate limits, secrets management, audit retention policy, backup/restore, log redaction, privacy assessments and security tests.**

## Observability and operations
Spring Actuator health endpoint at `/actuator/health`. Logs are process stdout. Set resource quotas, centralized logs/metrics/traces, connection pool limits, backup schedules and alerting for production. Migrations run at startup via Flyway.

## Localization
English is the default. Users switch to German using the top-right language toggle. UI dictionaries are in `frontend/src/i18n.ts`. Validation errors and backend domain messages are currently English only; end-to-end localization is a future task. Dates/numbers follow selected UI locale where implemented, invoice amounts format in EUR.

## Deliberately not implemented
No true BPMN engine (only workflow state machine); no assignment/technician availability, uploads, attachment storage, signed PDF documents, incoming mail, online payment, e-invoice XML, external integrations or production-ready multi-tenancy. These require real engineering work, not mock declarations.
