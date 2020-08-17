Feature: Connect to another MS

  Scenario: Try to connect to another MS
    Given I want to read a message
    When I send the request
    Then I should be able to read the word "Response"