@login
Feature: Autentificare în hapifyMe
  Ca utilizator înregistrat
  Vreau să mă autentific în aplicație
  Pentru a accesa feed-ul meu

  Background:
    Given utilizatorul este pe pagina de login

  @smoke @valid
  Scenario: Login cu succes
    When utilizatorul se autentifică folosind emailul "dumysipopiytprem@gmail.com" și parola "ParolaMeaSecreta123!"
    Then utilizatorul ajunge pe pagina de feed

  @negative
  Scenario Outline: Login eșuat cu date invalide
    When utilizatorul se autentifică folosind emailul "<email>" și parola "<password>"
    Then utilizatorul vede mesajul de eroare "<errorMessage>"

    Examples:
      | email                    | password   | errorMessage                    |
      | user.inexistent@test.com | Pass@1234  | Email or password was incorrect |
      | dumysipopiytprem@gmail.com | gresita123 | Email or password was incorrect |
