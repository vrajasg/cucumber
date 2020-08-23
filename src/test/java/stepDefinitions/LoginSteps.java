package stepDefinitions;

import org.springframework.beans.factory.annotation.Autowired;
import se.context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class LoginSteps {

    @Autowired
    private TestContext testContext;

    @Given("I am on the Google search page")
    public void I_visit_google() {
        testContext.getHomePage().openPage("http://google.co.uk");
    }

    @When("I search for {string}")
    public void search_for(String query) {
        testContext.getHomePage().search(query);
    }

    @Then("the page title should start with {string}")
    public void checkTitle(final String titleStartsWith) {
        String actual = testContext.getHomePage().getPageTitle();
        assertThat("Check page-title: ", actual, containsString(titleStartsWith));
    }

}
