package pages;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Sut {
    private final String siteUrl;
    private final String browserName;
    private WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(Sut.class);

    public Sut(String siteUrl, String browserName) {
        this.siteUrl = siteUrl;
        this.browserName = browserName;
    }

    public WebDriver getWebDriver() {
        if (webDriver == null) {
            webDriver = new WebDriverProvider(browserName, "", new DesiredCapabilities()).webDriver();
        }
        return webDriver;
    }

    public void loadApplicationUrl() {
        getWebDriver();
        webDriver.get(this.siteUrl);
        logger.info("URL: {} launched in {} browser", siteUrl, browserName);
    }

    public void quitBrowser() {
        webDriver.quit();
        webDriver = null;
        logger.info("browser closed");
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
