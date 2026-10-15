# Roadmap und Freigabekriterien

## Version 0.1 — umgesetzt
Kunden- und Auftragsanlage, Prozesszustände, Plausibilitäts- und Übergangsprüfungen, Besichtigungen, Montageberichte, Abschlussdatensätze, einfache Rechnungsdatensätze, Aktivitätsprotokoll, Dashboard, Englisch/Deutsch, Docker, Flyway sowie Basistests und CI.

## Phase 1 — zwingend vor dem Echtbetrieb
OIDC/PKCE statt HTTP Basic; rollen- und mandantenbasierte Zugriffsprüfung; Integrationstests mit PostgreSQL/Testcontainers für alle Module; Browser- und Barrierefreiheitstests; OpenAPI, Pagination und vollständig zweisprachige Validierung; Sicherheits- und Abhängigkeitsprüfungen; HTTPS; Secret-Management; Monitoring; Backups samt Wiederherstellungstests; DSGVO-Prüfung.

## Phase 2 — Handwerksprozesse
Technikerdisposition, Kalender und Ressourcenplanung; Fotos und Anhänge mit Virenprüfung; PDF-Abnahmeprotokolle und elektronische Unterschriften; gewerkespezifische Prüfprotokolle; Materialwirtschaft, Angebote, Zahlungen und Rechnungskorrekturen.

## Phase 3 — Deutschlandfähigkeit
XRechnung/ZUGFeRD mit EN-16931-Validierung, steuerliche Prüfung, DATEV, Aufbewahrung/GoBD-Bewertung, BPMN-Engine (z. B. Camunda oder Flowable), DMN-Regeln, Eskalation, Fristen und Offline-PWA.

## Phase 4 — Produktdifferenzierung
Kundenportal, Wartungsverträge, Routenoptimierung, Webhooks, Outbox-Pattern, Analysefunktionen und optionale KI-Unterstützung für Berichte.

## Freigabe
Keine echten Kundendaten oder produktive Rechnungsstellung vor erfolgreichem Abschluss der Phase-1-Pflichtthemen, fachlicher Prüfung der Rechnungsregeln und dokumentierter Sicherheitsabnahme.
