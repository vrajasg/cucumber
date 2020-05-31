package context;

import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class TestContext {

    WebDriver driver;
    HomePage homePage;

    public WebDriver getDriver() {
        return this.driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        return this.homePage;
    }

    public void initializePageObjects() {
        this.homePage = new HomePage(this.driver);
    }
}
