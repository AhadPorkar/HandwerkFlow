# Git and Commit Guide

## Branching
Use `main` (protected) and short-lived feature branches. Names: `feat/customer-directory`, `fix/survey-transition`, `docs/de-invoicing`, `test/job-api`. Pull requests should link issues, mention affected migrations and include screenshots for UI changes.

## Conventional commits
`feat(jobs): enforce survey before scheduling`
`fix(invoices): reject duplicate invoice per job`
`test(workflow): cover invalid state transitions`
`docs(de): clarify deployment and billing limitations`
`chore(ci): add PostgreSQL integration test job`

## First publication
```bash
git init
git add .
git commit -m "feat: scaffold bilingual HandwerkFlow field operations application"
git branch -M main
git remote add origin https://github.com/AhadPorkar/handwerkflow.git
git push -u origin main
```
Create the GitHub repository first; replace URL if you choose another name. Do not commit `.env`, keys, passwords, user records or vendor dependencies. Configure branch protection and GitHub secret scanning.

## Definition of done
Tests for changed behavior; migrations reviewed; EN/DE UI strings both maintained; README/API docs updated; no secrets; security implications considered; build green in CI; screenshots for UI work.
