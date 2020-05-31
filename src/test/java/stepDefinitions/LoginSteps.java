package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static stepDefinitions.BaseSteps.getSut;

public class LoginSteps {

    private HomePage getHomePage(){
        return getSut().getPageCreator().getHomePage();
    }

    @Given("I am on the Google search page")
    public void I_visit_google() {
        getHomePage().openPage("http://google.co.uk");
    }

    @When("I search for {string}")
    public void search_for(String query) {
        getHomePage().search(query);
    }

    @Then("the page title should start with {string}")
    public void checkTitle(final String titleStartsWith) {
        String actual = getHomePage().getPageTitle();
        assertThat("Check page-title: ", actual, containsString(titleStartsWith));
    }

}
