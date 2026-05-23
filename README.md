# hapifyMe UI & API Tests - Maven + Cucumber + JUnit 4 + Selenide + REST Assured + Awaitility

Proiect pentru testarea automată a aplicației hapifyMe.

Proiectul conține două componente principale:

- UI Testing folosind Cucumber, JUnit 4 și Selenide
- API Testing folosind REST Assured, TestNG și Awaitility

## Tehnologii folosite

- Maven
- Java 17
- Cucumber Java
- Cucumber JUnit 4
- Selenide
- Page Object Model
- REST Assured
- TestNG
- Awaitility
- Jackson Databind

## Structură proiect

```text
src/test/java
├── config
├── pages
├── runners
├── stepdefinitions
└── com/hapifyme/api
    ├── models
    ├── tests
    └── utils


src/test/resources
└── features
```
## Componenta UI Testing

Testele UI folosesc Cucumber, JUnit 4 și Selenide.

Structura principală:

pages            - clase Page Object
stepdefinitions  - pașii Cucumber
runners          - clase de rulare Cucumber
config           - configurări pentru teste

## Componenta API Testing

Componenta API testează fluxul complet de viață al unui utilizator hapifyMe.

Fluxul testat:

Register user
Retrieve confirmation token folosind Awaitility
Confirm email
Login user
Get profile
Update profile
Delete profile
Negative check după ștergere

Endpoint-urile API folosesc base URL-ul actualizat:

https://apps.qualiadept.eu/hapifyme/api

Structura API:

com/hapifyme/api/models

Conține clase POJO pentru request body:

RegisterRequest
LoginRequest
UpdateProfileRequest
com/hapifyme/api/utils

Conține clase utilitare:

DataGenerator - generează date dinamice pentru teste
ApiPoller - folosește Awaitility pentru a aștepta confirmation_token
com/hapifyme/api/tests

Conține testul E2E:

UserLifecycleTest

## Rulare teste

Pentru rularea tuturor testelor:

mvn clean test

Pentru rulare cu date custom pentru partea UI:

mvn clean test -DbaseUrl=https://test.hapifyme.com -DtestEmail=email@test.com -DtestPassword=ParolaTa

## Raport HTML

După rularea testelor UI, raportul Cucumber se găsește aici:

target/cucumber-report.html

## Observații

Partea UI poate folosi setup-ul local/Docker din etapa anterioară a proiectului.
Partea API folosește URL-ul actualizat al aplicației hapifyMe.
Testele API creează date dinamice, confirmă contul, fac login, verifică profilul, actualizează profilul și șterg utilizatorul creat.
Awaitility este folosit pentru a aștepta generarea asincronă a token-ului de confirmare.
Dacă locatorii UI diferă pe versiunea locală a aplicației, se modifică doar clasele din pages, nu Step Definitions.
