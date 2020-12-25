package pages;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import se.context.TestContext;
import webdriver.WebDriverProvider;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class Sut {

    private static final String browser = System.getProperty("browser", CHROME);
    private static final String SCREENSHOT_MEDIA_TYPE = "image/png";
    private WebDriverProvider webDriverProvider;
    private WebDriver webDriver;
    private static final ThreadLocal<Sut> sut = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(Sut.class);
    private TestContext testContext;

    public Sut(TestContext testContext) {
        this.testContext = testContext;
    }

    public WebDriverProvider getWebDriverProvider() {
        if (webDriverProvider == null) {
            webDriverProvider = new WebDriverProvider(browser, "", new DesiredCapabilities());
            testContext.setWebDriverProvider(webDriverProvider);
        }
        return webDriverProvider;
    }

    public void addScreenshotToScenario(Scenario scenario) {
        byte[] screenshot = SeleniumWrapper.takeScreenshot(testContext.getWebDriver());
        scenario.embed(screenshot, SCREENSHOT_MEDIA_TYPE, scenario.getName());
    }

    public static Sut getSut(TestContext testContext) {
        Sut currentSut = sut.get();
        if (currentSut == null) {
            logger.info("Initialise - Sut: Start");
            currentSut = new Sut(testContext);
            sut.set(currentSut);
        }
        return currentSut;
    }

    public void stopSut() {
        Sut currentSut = sut.get();
        if (currentSut != null) {
            testContext.getWebDriver().quit();
            testContext.setWebDriver(null);
            testContext.setWebDriverProvider(null);
            sut.remove();
            testContext = null;
            logger.info("CleanUp - Sut: End");
        }
    }

    public void initWebDriver() {
        if (webDriver == null) {
            webDriver = getWebDriverProvider().webDriver();
            testContext.setWebDriver(webDriver);
        }
    }
}
