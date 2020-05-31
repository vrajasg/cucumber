Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  @smoke
  Scenario: Finding some cheese 1
    Given I am on the Google search page
    When I search for "Cheese!"
    Then the page title should start with "Cheese"

  @sanity
  Scenario: Finding some cheese 2
    Given I am on the Google search page
    When I search for "Cheese!"
    Then the page title should start with "Cheese"