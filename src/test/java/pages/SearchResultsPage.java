package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends BasePage {

    String resultLinkFormat = "//span[.='%s']";

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openResultLink(String resultLink) {
        String link = String.format(resultLinkFormat, resultLink);
        byXpath(link).click();
    }
}
