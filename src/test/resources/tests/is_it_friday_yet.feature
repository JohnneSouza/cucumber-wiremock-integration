@Week
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  @Friday
  Scenario: Sunday isn't Friday
    Given today is Sunday
    When I ask whether it's Friday yet
    Then I should be told "Nope"

  @Sunday
  Scenario: Sunday is Sunday
    Given today is Sunday
    When I ask whether it's Sunday yet
    Then I should be told "Yes"