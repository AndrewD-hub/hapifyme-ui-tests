@profile @search
Feature: Căutare utilizator și accesare profil
  Ca utilizator autentificat
  Vreau să caut un utilizator
  Pentru a-i accesa profilul

  Background:
    Given utilizatorul este autentificat în aplicație

  @datatable
  Scenario: Căutare utilizator și deschidere profil folosind Data Table
    When utilizatorul caută următorul profil:
      | searchTerm | expectedName  |
      | Dumi    | Dumi Test  |
    And utilizatorul deschide profilul găsit
    Then pagina profilului utilizatorului este afișată
