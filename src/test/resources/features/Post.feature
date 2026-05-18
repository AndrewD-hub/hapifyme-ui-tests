@post
Feature: Creare postare în feed
  Ca utilizator autentificat
  Vreau să creez postări noi
  Pentru a distribui mesaje în feed

  Background:
    Given utilizatorul este autentificat în aplicație

  @smoke
  Scenario Outline: Creare postări multiple în feed
    When utilizatorul creează o postare cu mesajul "<message>"
    Then postarea cu mesajul "<message>" este afișată în feed

    Examples:
      | message                                      |
      | Test automat Cucumber Selenide postare 1      |
      | Test automat Cucumber Selenide postare 2      |
