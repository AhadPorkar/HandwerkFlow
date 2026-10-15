# German Skilled Trades Market — Product Research (October 2026)

## Scope and research limits
Desk research based on public market information and German tax administration sources. This is **not** a statistically representative scan of all job vacancies, buyers or vendors. No proprietary employer data was accessed.

## Customer segments
Electrical installers, HVAC service firms, solar/PV contractors and plumbing businesses. Typical team personas: dispatcher/office admin, field technician, team lead, customer, bookkeeper and owner.

## Problems and product implications
1. Disconnected job intake and customer master data → centralized work orders and linked customers.
2. Lost paper survey observations → timestamped site surveys and prerequisites.
3. Work completed without verifiable handover → safety/acceptance gates and completion records.
4. Unclear operational pipeline → status timeline, audit events, dashboard.
5. Manual billing and German invoice obligations → financial amounts modeled precisely and future compliant structured e-invoice export.
6. Field connectivity and mobility → responsive layout now; offline/PWA synchronization later.
7. Complex long-running installation work → process state machine now; BPMN/DMN engine as a differentiating future milestone.

## Germany-specific requirements
German federal tax administration recognizes XRechnung and eligible ZUGFeRD structured formats in its EN 16931 e-invoicing discussion. B2B recipients generally must accept e-invoices from 2025; issuance transitions during 2027–2028 depending on turnover and exceptions. **This starter does not generate or validate those formats.** A legally qualified invoice service must model VAT distinctions, invoice immutability/corrections, company tax details and structured schemas, then obtain domain/legal review.

## Competitive/product strategy
Avoid shipping a generic list-based CRM. Distinguish through a visible process timeline, compliance-ready records, explicit transition rules, task ownership, reusable BPMN process definitions and eventually automated orchestration. A modular monolith is the right starting point for a small business product: simpler operational costs than premature microservices, clear extractable boundaries as usage grows.

## Sources reviewed
- Federal Ministry of Finance: https://www.bundesfinanzministerium.de/Content/DE/FAQ/e-rechnung.html
- German federal e-invoicing information: https://e-rechnung-bund.de/e-rechnung/e-rechnung-zwischen-unternehmen-b2b
- Bavaria tax administration: https://www.finanzamt-muenchen.de/steuerinfos/weitere-themen/e-rechnung
- Spring Boot 4.1.1 release: https://spring.io/blog/2026/08/20/spring-boot-4-1-1-available-now/
- Public author portfolio: https://github.com/AhadPorkar and https://www.linkedin.com/in/seapr/

## Discovery questions for real customers
Interview 10–15 SMEs across all four trades; observe field reporting and office handover; validate required inspection forms, commissioning standards, VAT variants, billing adjustments, technician dispatch, photo consent and retention by trade and federal state. Confirm workflow with actual installers before a commercial launch.
