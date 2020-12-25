package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheeseWikiPage extends BasePage {

    public CheeseWikiPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
