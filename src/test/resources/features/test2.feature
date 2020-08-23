Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  @sanity
  Scenario: Finding some cheese 3
    Given I am on the Google search page
    When I search for "Cheese!"
    Then the page title should start with "Cheese"

  @sanity
  Scenario: Finding some cheese 4
    Given I am on the Google search page
    When I search for "Cheese!"
    Then the page title should start with "Cheese"