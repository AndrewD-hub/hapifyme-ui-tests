# hapifyMe UI Tests - Maven + Cucumber + JUnit 4 + Selenide

Proiect pentru testarea automată UI a aplicației hapifyMe.

## Tehnologii folosite

- Maven
- Cucumber Java
- Cucumber JUnit 4
- Selenide
- Page Object Model

## Structură

```text
src/test/java
├── config
├── pages
├── runners
└── stepdefinitions

src/test/resources
└── features
```

## Rulare teste

```bash
mvn clean test
```

Sau cu date custom:

```bash
mvn clean test -DbaseUrl=https://test.hapifyme.com -DtestEmail=email@test.com -DtestPassword=ParolaTa
```

## Raport HTML

După rulare, raportul se găsește aici:

```text
target/cucumber-report.html
```

## Observații

Dacă locatorii diferă pe versiunea locală a aplicației, modifică doar clasele din `pages`, nu Step Definitions.
