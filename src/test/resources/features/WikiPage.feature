Feature: Open search result
         Verify result in wiki page

  @sanity1
  Scenario: Verify wiki page when searched for Cheese
    Given I am on the Google search page
    When I search for "Cheese!"
    And open search result with text "Cheese - Wikipedia"
    Then I verify that "Cheese - Wikipedia" page opened