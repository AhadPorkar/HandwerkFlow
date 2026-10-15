# Git- und Commit-Leitfaden

## Branches und Pull Requests
`main` schützen und kurze Feature-Branches verwenden, beispielsweise `feat/technician-calendar`, `fix/invoice-validation` oder `docs/de-api`. Jeder Pull Request enthält Testnachweise, bei UI-Änderungen Screenshots sowie Hinweise auf Datenbankmigrationen.

## Conventional Commits
- `feat(jobs): add technician assignment`
- `fix(workflow): prevent premature completion`
- `test(api): add PostgreSQL integration coverage`
- `docs(de): expand invoice documentation`
- `chore(ci): introduce dependency scanning`

## Erstveröffentlichung
```bash
git init
git add .
git commit -m "feat: initial bilingual HandwerkFlow portfolio foundation"
git branch -M main
git remote add origin https://github.com/AhadPorkar/handwerkflow.git
git push -u origin main
```
Zuvor ein leeres Repository auf GitHub erstellen. `.env`, Passwörter und Zugangsdaten dürfen niemals eingecheckt werden. CI, Branch Protection und Secret Scanning aktivieren.

## Definition of Done
Testabdeckung für Änderungen, beide Sprachdateien aktualisiert, Migration geprüft, keine Geheimnisse im Code, Dokumentation angepasst, CI erfolgreich und Sicherheitsfolgen betrachtet.
