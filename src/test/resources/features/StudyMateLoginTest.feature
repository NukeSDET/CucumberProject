Feature: all login tests scenarios are this file

  Background:
    Given user navigates to login page

  @loginPositive
  Scenario: user logs in with valid credentials
    When user enters email "admin@codewise.com" to the email field
    And user enters password "codewise123" to the passsword field
    Then user performs click action on the login button
    Then user should be logged in to the application

   @loginNegative
  Scenario Outline: user tries to logs in with invalid credentials
    When user enters email "<email>" to the email field
    And user enters password "<password>" to the passsword field
    Then user performs click action on the login button
    Then user should see error message
    Examples:
      | email            | password |
      | nurlan@gmail.com | mukh     |
      | abc@gmail.com    | 123456   |
      | John@gmail.com   | can1234  |
      | Sindy@gmail.com  | xyz 456  |