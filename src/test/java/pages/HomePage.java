package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Optional;

public class HomePage extends BasePage {

    private static final String URL = "url";
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    @FindBy(name = "q")
    WebElement searchBox;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openHomePage() {
        String appUrl = Optional.of(System.getProperty(URL)).orElseThrow(() -> new RuntimeException("Missing application URL"));
        driver.get(appUrl);
        logger.info("openPage: " + appUrl);
    }

    public SearchResultsPage search(String query) {
        searchBox.sendKeys(query);
        searchBox.submit();
        return new SearchResultsPage(driver);
    }
}
