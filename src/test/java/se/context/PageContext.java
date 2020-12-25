package se.context;

import lombok.Getter;
import lombok.Setter;
import pages.CheeseWikiPage;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.Sut;

import java.util.Optional;

@Getter
@Setter
public class PageContext {
    private final TestContext testContext;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private CheeseWikiPage cheeseWikiPage;

    public PageContext(TestContext testContext) {
        this.testContext = testContext;
        Sut.getSut(testContext).initWebDriver();
    }

    public HomePage initHomePage() {
        homePage = Optional.ofNullable(homePage).orElseGet(() -> new HomePage(testContext.getWebDriver()));
        return homePage;
    }

    /**
     * Not lombok way as return type of search page changes based on searched string so initialize page explicitly.
     *
     * @return CheeseWikiPage
     */
    public CheeseWikiPage getCheeseWikiPage() {
        cheeseWikiPage = Optional.ofNullable(cheeseWikiPage).orElseGet(() -> new CheeseWikiPage(testContext.getWebDriver()));
        return cheeseWikiPage;
    }
}
