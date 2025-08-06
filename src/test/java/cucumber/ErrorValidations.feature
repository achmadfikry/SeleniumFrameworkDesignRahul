@tag
  Feature: Error validation

    @tag2
    Scenario Outline:
      Given I landed on Ecommerse page
      When Logged in with username <name> and password <password>
      Then "Incorrect email or password." message is displayed
      Examples:
        | name                       | password    | productName |
        | rahulshetty123@yopmail.com | Password123 | ZARA COAT 3 |