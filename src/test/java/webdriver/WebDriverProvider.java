package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import stepDefinitions.Hooks;

import java.net.URL;

public class WebDriverProvider {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String CHROME_REMOTE = "chrome_remote";
    private static final String FIREFOX_REMOTE = "firefox_remote";
    private final String browser;
    private final String webDriverVersion;
    private final DesiredCapabilities caps;
    private static final String GRID_URL = System.getProperty("gridUrl");


    public WebDriverProvider(String browser, String webDriverVersion, DesiredCapabilities caps) {
        this.browser = browser;
        this.webDriverVersion = webDriverVersion;
        this.caps = caps;
    }

    public WebDriver webDriver() {
        WebDriver driver;
        switch (browser) {
            case CHROME:
                driver = getChromeDriver();
                break;
            case CHROME_REMOTE:
                driver = getRemoteWebDriver(getChromeOptions());
                break;
            case FIREFOX:
                driver = getFirefoxDriver();
                break;
            case FIREFOX_REMOTE:
                driver = getRemoteWebDriver(getFirefoxOptions());
                break;
            default:
                throw new IllegalArgumentException("Unknown browser:" + browser);
        }
        return driver;
    }

    private RemoteWebDriver getRemoteWebDriver(MutableCapabilities mutableCapabilities) {
        try {
            return new RemoteWebDriver(new URL(GRID_URL), mutableCapabilities);
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }

    private WebDriver getChromeDriver() {
        synchronized (WebDriverManager.class) {
            WebDriverManager.getInstance(DriverManagerType.CHROME)
                    .browserVersion(webDriverVersion)
                    .setup();
        }
        return new ChromeDriver();
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        chromeOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        // name: For test case name in Zalenium dashboard
        chromeOptions.setCapability("name", Hooks.scenarioMap.get(Thread.currentThread().getId()).getName());
        return chromeOptions;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
        firefoxOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        // name: For test case name in Zalenium dashboard
        firefoxOptions.setCapability("name", Hooks.scenarioMap.get(Thread.currentThread().getId()).getName());
        return firefoxOptions;
    }

    private WebDriver getFirefoxDriver() {
        synchronized (WebDriverManager.class) {
            WebDriverManager.getInstance(DriverManagerType.FIREFOX)
                    .browserVersion(webDriverVersion)
                    .setup();
        }
        return new FirefoxDriver();
    }
}
