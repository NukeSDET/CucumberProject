Feature: test login

  Background:


  @Outline
  Scenario Outline: Various login attempts
    Given the user is on the login page
    When the user enters correct "<username>" username
    And correct "<password>" password
    And the user clicks the login button
    Then verify the user logs in successfully

    Examples:
      | username    | password   |
      | John        | pass123    |
      | AliceSmith  | secret456  |
      | TestUser1   | testpass   |