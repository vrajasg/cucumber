package pages;

import org.openqa.selenium.*;

public class SeleniumWrapper {
    protected WebDriver driver;

    public SeleniumWrapper(WebDriver driver) {
        this.driver = driver;
    }

    public static byte[] takeScreenshot(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public WebElement byXpath(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}
