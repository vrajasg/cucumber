package stepDefinitions;

import io.cucumber.java.en.Then;
import pages.CheeseWikiPage;
import se.context.PageContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class CheeseWikiSteps {

    private final PageContext pageContext;

    public CheeseWikiSteps(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    private CheeseWikiPage getCheeseWikiPage() {
        return pageContext.getCheeseWikiPage();
    }

    @Then("I verify that {string} page opened")
    public void verifyThatPageOpened(final String pageName) {
        String actual = getCheeseWikiPage().getPageTitle();
        assertThat("Check Cheese page title: ", actual, containsString(pageName));
    }
}
