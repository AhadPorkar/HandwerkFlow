# HandwerkFlow — Architektur und Entwicklerdokumentation

## Zielsetzung
HandwerkFlow digitalisiert den Auftragsprozess im Elektro-, Klima-, Solar- und Sanitärhandwerk: Auftragseingang, Besichtigung, Terminplanung, Installation, Abnahme, Abschlussdokumentation und Rechnungsstellung. Im Mittelpunkt steht ein nachvollziehbarer Geschäftsprozess mit Zustandsregeln und Ereignisprotokoll.

## Aufbau
- `frontend/`: React 19, TypeScript, Vite und Recharts. `main.tsx` enthält Ansichten/Formulare; `api.ts` zentralisiert HTTP-Anfragen; `i18n.ts` enthält deutsche und englische Texte; `style.css` definiert das responsive Layout.
- `backend/`: Java 25, Spring Boot 4.1.1, Spring JDBC, Spring Security. `ApiController.java` stellt REST-Endpunkte bereit, `JobStatus.java` regelt Zustandsübergänge, `SecurityConfig.java` die lokale Anmeldung und CORS, `ApiExceptionHandler.java` die Fehlerbehandlung.
- PostgreSQL 17: Tabellen und Indizes werden mit Flyway-Migrationen verwaltet. UUID-Schlüssel, Zeitstempel, Fremdschlüssel und NUMERIC-Beträge.
- `compose.yaml`: Datenbank, Backend und Nginx-Frontend. `.github/workflows/ci.yml`: Build- und Testabläufe.

## Fachliche Regeln
Der Standardprozess lautet `INTAKE → SURVEY → SCHEDULED → IN_PROGRESS → COMPLETED → INVOICED`. Vor der Planung muss eine Besichtigung vorliegen. Zum Abschluss ist ein Montagebericht mit bestandener Sicherheitsprüfung und Kundenabnahme erforderlich. Eine Rechnung kann erst nach dem Abschluss erstellt werden. Zustandsänderungen nutzen eine Versionsprüfung und werden protokolliert. Stornierung ist in frühen Phasen möglich.

## Datenmodell
Ein Kunde kann mehrere Aufträge haben. Ein Auftrag besitzt mehrere Besichtigungen, Montageberichte, Abschlussnachweise und Ereignisse, aber höchstens eine Rechnung. Details stehen im SQL-Schema `backend/src/main/resources/db/migration/V1__schema.sql`.

## REST-Schnittstellen
Alle Endpunkte stehen unter `/api/v1`. `GET /dashboard` liefert Kennzahlen; `GET/POST /customers` verwaltet Kunden; `GET/POST /jobs` verwaltet Aufträge; `GET /jobs/{id}` liefert Auftragsdetails; `PATCH /jobs/{id}/status` nimmt `{ "status": "SURVEY", "expectedVersion": 0 }` entgegen. Über `GET/POST /jobs/{id}/surveys`, `/reports` und `/completions` werden Nachweise verwaltet. `POST /jobs/{id}/invoice` erstellt einen Rechnungseintrag. `GET /invoices` und `GET /jobs/{id}/events` liefern Rechnungen bzw. Aktivitäten. Die Java-`record`-Typen in `ApiController.java` definieren die Eingabestrukturen.

## Ausführen
Docker und Compose installieren, `.env.example` nach `.env` kopieren, beide Passwörter ersetzen, anschließend `docker compose up --build -d` ausführen. Oberfläche: http://localhost:8088. Login über die Variablen `ADMIN_USER` und `ADMIN_PASSWORD`. Für lokale Entwicklung Java 25/Maven 3.9+ und Node 22+ verwenden. Backend: `cd backend && mvn spring-boot:run`; Frontend: `cd frontend && npm install && npm run dev`.

## Tests und Internationalisierung
`cd backend && mvn verify` prüft die vorhandenen Java-Unit-Tests. `cd frontend && npm install && npm run test && npm run build` prüft Übersetzungen und den Build. Die Oberfläche unterstützt Deutsch und Englisch; Englisch ist Standard. Backend-Fehlermeldungen sind noch nicht vollständig übersetzt. Datenbank-Integrationstests und Browser-End-to-End-Tests fehlen noch.

## Sicherheit und bekannte Grenzen
Die Demo verwendet **nur einen Administrator mit HTTP Basic**. Benutzerrollen, Mandantenfähigkeit und sichere Identitätsverwaltung fehlen. Vor einem produktiven Einsatz sind OIDC/PKCE, Rollenprüfung, HTTPS, sichere Geheimnisverwaltung, Datenisolierung, Backup/Restore, Datenschutzprüfung und vollständige Tests Pflicht. Abschlussdokumente sind momentan strukturierte Datenbankeinträge, keine signierten PDF-Dateien. Rechnungen sind einfache Datensätze und **keine** rechtskonformen XRechnung-/ZUGFeRD-Dokumente. BPMN wird aktuell durch eine Zustandsmaschine illustriert, nicht durch eine vollständige BPMN-Engine ausgeführt.
