package stepDefinitions;

import pages.HomePage;
import pages.SearchResultsPage;
import se.context.PageContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class HomeSteps {

    private final PageContext pageContext;

    public HomeSteps(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    private HomePage getHomePage() {
        return pageContext.getHomePage();
    }

    @Given("I am on the Google search page")
    public void I_visit_google() {
        HomePage homePage = pageContext.initHomePage();
        homePage.openHomePage();
    }

    @When("I search for {string}")
    public void search_for(String query) {
        SearchResultsPage searchResultsPage = getHomePage().search(query);
        pageContext.setSearchResultsPage(searchResultsPage);
    }
}
