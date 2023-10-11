@firstTag

Feature: verify login functionality


  Scenario: user logs in with correct credentials
    Given correct username "Aika"
    And correct password "Abc123!"
    When user clicks login button
    Then user logs in


  Scenario: user logs in with correct credentials
    Given incorrect username "Aikaaa"
    And incorrect password "12345"
    When user clicks login button
    Then user does not log in

@FirstOutline
  Scenario Outline:
    Given correct <username> username
    And correct <password> password
    When user is clicking login button
    Then verify user logs in
    Examples:
      | username    | password   |
      | Aika        | abc123     |
      | HarryPotter | xyz456     |
      | Barbie@     | canHello1  |
      | Barbie123@# | Codewise1! |
