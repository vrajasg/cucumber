Feature: Google Search
         Verify search result page title

  @sanity
  Scenario: Verify google search
    Given I am on the Google search page
    When I search for "google"
    Then the page title should start with "google"

  @sanity
  Scenario: Verify something else search
    Given I am on the Google search page
    When I search for "something else"
    Then the page title should start with "something else"

  @smoke
  Scenario: Verify Selenium search
    Given I am on the Google search page
    When I search for "Selenium"
    Then the page title should start with "Selenium"