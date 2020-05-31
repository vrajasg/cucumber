package pages;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Sut {
    private PageCreator pageCreator;
    private final String siteUrl;
    private final String browserName;
    private WebDriver webDriver;

    public Sut(String siteUrl, String browserName) {
        this.siteUrl = siteUrl;
        this.browserName = browserName;
    }

    public PageCreator getPageCreator() {
        if (pageCreator == null) {
            pageCreator = new PageCreator(getWebDriver());
        }
        return pageCreator;
    }

    public WebDriver getWebDriver() {
        if (webDriver == null) {
            webDriver = new WebDriverProvider(browserName, "", new DesiredCapabilities()).webDriver();
        }
        return webDriver;
    }

    public void loadApplicationUrl() {
        webDriver.get(this.siteUrl);
    }

    public void quitBrowser() {
        webDriver.quit();
        webDriver = null;
    }

    public void captureScreenshot(Scenario scenario) {
        try {
            byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            throw new RuntimeException(somePlatformsDontSupportScreenshots);
        }
    }
}
