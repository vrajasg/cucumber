package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchResultsPage;
import se.context.PageContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class SearchResultsSteps {

    private final PageContext pageContext;

    public SearchResultsSteps(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    private SearchResultsPage getSearchResultsPage() {
        return pageContext.getSearchResultsPage();
    }

    @Then("the page title should start with {string}")
    public void checkTitle(final String titleStartsWith) {
        String actual = getSearchResultsPage().getPageTitle();
        assertThat("Check page-title: ", actual, containsString(titleStartsWith));
    }

    @When("open search result with text {string}")
    public void openSearchResultWithText(final String linkText) {
        getSearchResultsPage().openResultLink(linkText);
    }
}
